package aircraftManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aircraftManagement.Booking.Booking;
import aircraftManagement.Booking.BookingManager;
import aircraftManagement.Flight.Flight;
import aircraftManagement.Flight.FlightSearch;
import aircraftManagement.Payment.Payment;
import aircraftManagement.Payment.PaymentProcessor;
import aircraftManagement.Seat.Seat;

public class AirCraftSystem {
    private final List<Flight>flights;
    private final List<Aircraft>aircrafts;
    private final FlightSearch flightSearch;
    private final BookingManager bookingManager;
    private final PaymentProcessor paymentProcessor;

       public AirCraftSystem() {
        flights = new ArrayList<>();
        aircrafts = new ArrayList<>();
        flightSearch = new FlightSearch(flights);
        bookingManager = BookingManager.getInstance();
        paymentProcessor = PaymentProcessor.getInstance();
    }

        public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    public List<Flight> searchFlights(String source, String destination, LocalDate date) {
        return flightSearch.searchFlights(source, destination, date);
    }

    public Booking bookFlight(Flight flight, Passanger passenger, Seat seat, double price) {
        return bookingManager.createBooking(flight, passenger, seat, price);
    }

    public void cancelBooking(String bookingNumber) {
        bookingManager.cancelBooking(bookingNumber);
    }

    public void processPayment(Payment payment) {
        paymentProcessor.processPayment(payment);
    }
}
