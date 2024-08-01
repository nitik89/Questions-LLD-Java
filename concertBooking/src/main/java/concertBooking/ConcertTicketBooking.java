package concertBooking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcertTicketBooking {
    private static ConcertTicketBooking instance;
    private final Map<String, Concert> concerts;
    private final Map<String, Booking>bookings;
    private final Object lock = new Object();

    private ConcertTicketBooking() {
        this.concerts = new ConcurrentHashMap<>();
        this.bookings = new ConcurrentHashMap<>();
    }

    public static ConcertTicketBooking getInstance() {
        if(instance == null) {
            instance = new ConcertTicketBooking();
        }
        return instance;
    }

    public void addConcert(Concert concert) {
        synchronized (lock) {
            concerts.put(concert.getId(), concert);
        }
    }

    public Concert getConcert(String id) {
        return concerts.get(id);
    }

    public List<Concert> searchConcerts(String artist, String venue,LocalDateTime dateTime){
        return concerts.values().stream()
                .filter(concert -> concert.getArtist().equalsIgnoreCase(artist) &&
                        concert.getVenue().equalsIgnoreCase(venue) &&
                        concert.getDateTime().equals(dateTime))
                .collect(Collectors.toList());
    }

    public Booking bookTickets(User user, Concert concert, List<Seat> seats) {
       synchronized (lock) {
        for (Seat seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new SeatNotAvailableException("Seat " + seat.getSeatNumber() + " is not available.");
            }
        }
        seats.forEach(Seat::book);

        String bookingId=generateBookingId();
        Booking booking = new Booking(bookingId, user, concert, seats);
        bookings.put(bookingId, booking);

        processPayment(booking);

        // Confirm booking
        booking.confirmBooking();

        System.out.println("Booking " + booking.getId() + " - " + booking.getSeats().size() + " seats booked");

        return booking;


       }
    }

      private void processPayment(Booking booking) {
        // Process payment for the booking
        // ...
    }

    private String generateBookingId() {
        return "BKG" + UUID.randomUUID();
    }


}
