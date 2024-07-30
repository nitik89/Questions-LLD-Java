package aircraftManagement.Booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import aircraftManagement.Flight.Flight;
import aircraftManagement.Passanger;
import aircraftManagement.Seat.Seat;

public class BookingManager {
    private static BookingManager instance;
    private final Map<String, Booking>bookings;
    private final Object lock=new Object();
    private final AtomicInteger bookingCounter=new AtomicInteger(0);

    private BookingManager() {
        this.bookings = new ConcurrentHashMap<>();
    }

    public static synchronized BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    public Booking createBooking(Flight flight, Passanger passenger, Seat seat, double price) {
        String bookingNumber = generateBookingNumber();
        Booking booking = new Booking(bookingNumber, flight, passenger, seat, price);
        synchronized (lock) {
            bookings.put(bookingNumber, booking);
        }
        return booking;
    }

    public void cancelBooking(String bookingNumber) {
        synchronized (lock) {
            Booking booking = bookings.get(bookingNumber);
            if (booking != null) {
                booking.cancel();
            }
        }
    }

    
    private String generateBookingNumber() {
          int bookingId = bookingCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "BKG" + timestamp + String.format("%06d", bookingId);
    }
}
