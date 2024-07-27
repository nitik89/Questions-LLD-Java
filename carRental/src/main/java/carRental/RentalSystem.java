package carRental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import carRental.payment.CreditProcess;
import carRental.payment.PaymentProcessor;

public class RentalSystem {
    private static RentalSystem instance;
    private final Map<String,Car>cars;
    private final Map<String,Reservation>reservations;
    private final PaymentProcessor paymentProcessor;


    private RentalSystem() {
        this.cars = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
        this.paymentProcessor = new CreditProcess();
    }

    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    public void addCar(Car car){
        cars.put(car.getLicensePlate(), car);
    }

    public void removeCar(String licensePlate){
        cars.remove(licensePlate);
    }

    public List<Car> searchCar(String make, String model, LocalDate startDate, LocalDate endDate){
        List<Car> result = new ArrayList<>();
        for (Car car : cars.values()) {
            if (car.getMake().equalsIgnoreCase(make) && car.getModel().equalsIgnoreCase(model) && car.isAvailable()) {
                if(isCarAvailable(car,startDate,endDate)){
                    result.add(car);
                }

            }
        }
        return result;
    }
    private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate){
        for (Reservation reservation : reservations.values()) {
            if (reservation.getCar().equals(car) && reservation.getStartDate().isBefore(endDate) && reservation.getEndDate().isAfter(startDate)) {
                return false;
            }
        }
        return true;
    }

    public synchronized Reservation makeReservation(Customer customer,Car car, LocalDate startDate, LocalDate endDate){
        if(isCarAvailable(car, startDate, endDate)){
            Reservation reservation = new Reservation(generateReservationId(),car, customer, startDate, endDate);
            reservations.put(reservation.getReservationId(), reservation);
            car.setAvailable(false);
            return reservation;
        }
        return null;

    }

    public synchronized void cancelReservation(String reservationId){
        Reservation reservation = reservations.get(reservationId);
        if(reservation!= null){
         reservation.getCar().setAvailable(true);
        }
    }

    public boolean processPayment(Reservation reservation) {
        return paymentProcessor.processPayment(reservation.getTotalPrice());
    }

    private String generateReservationId(){
        return "R-" + System.currentTimeMillis();
    }


}
