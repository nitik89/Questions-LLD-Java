package movieBooking.booking;

import java.util.List;

import movieBooking.Show;
import movieBooking.User;
import movieBooking.seat.Seat;

public class Booking {
    private final String id;
    private final Show show;
    private final User user;
    private final List<Seat>seats;
    private final double totalPrice;
    private BookingStatus status;

    public Booking(String id, User user, Show show, List<Seat> seats, double totalPrice, BookingStatus status) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

}