package carRental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private final String reservationId;
    private final Car car;
    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;

    public Reservation(String reservationId, Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice(){
        long daysRented=ChronoUnit.DAYS.between(startDate, endDate);
        return car.getRentalPricePerDay()*daysRented;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }



}
