package aircraftManagement.Booking;

import aircraftManagement.Flight.Flight;
import aircraftManagement.Passanger;
import aircraftManagement.Seat.Seat;

public class Booking {
    private final String bookingNumber;
    private final Flight flight;
    private final Passanger passanger;
    private final Seat seat;
    private final double price;
    private BookingStatus status;

    public Booking(String bookingNumber, Flight flight, Passanger passanger, Seat seat, double price) {
        this.bookingNumber = bookingNumber;
        this.flight = flight;
        this.passanger = passanger;
        this.seat = seat;
        this.price = price;
        this.status = BookingStatus.PENDING;
    }
    public void cancel(){
        status = BookingStatus.CANCELLED;
    }

    public BookingStatus getStatus() {
        return status;
    }

     public String getBookingNumber() {
        return bookingNumber;
    }
}
