package rideSharing;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RideService {
    private static RideService instance;
    private final Map<Integer,Passanger>passangers;
    private final Map<Integer, Driver> drivers;
    private final Map<Integer,Ride>rides;
    private final Queue<Ride>requestedRides;
    private RideService() {
        passangers = new ConcurrentHashMap<>();
        drivers = new ConcurrentHashMap<>();
        rides = new ConcurrentHashMap<>();
        requestedRides = new ConcurrentLinkedQueue<>();
    }
    public static synchronized RideService getInstance() {
        if (instance == null) {
            instance = new RideService();
        }
        return instance;
    }
    public void addPassenger(Passanger passenger) {
        passangers.put(passenger.getId(), passenger);
    }

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    private int generateRideId() {
        return (int) (System.currentTimeMillis() / 1000);
    }
    
    public void requestRide(Passanger passenger, Location source, Location destination) {
        Ride ride = new Ride(generateRideId(), passenger, null, source, destination, RideStatus.REQUESTED, 0.0);
        requestedRides.offer(ride);
        notifyDrivers(ride);
    }

    public void acceptRide(Driver driver, Ride ride){
        if(ride.getStatus()==RideStatus.REQUESTED) {
            ride.setDriver(driver);
            ride.setStatus(RideStatus.ACCEPTED);
            driver.setStatus(DriverStatus.BUSY);
            notifyPassanger(ride);
        }
    }

    public void startRide(Driver driver,Ride ride){
        if(ride.getStatus()==RideStatus.ACCEPTED){
            ride.setDriver(driver);
            ride.setStatus(RideStatus.IN_PROGRESS);
            driver.setStatus(DriverStatus.BUSY);
            notifyPassanger(ride);
        }
    }

    public void completeRide(Ride ride){
        if(ride.getStatus()==RideStatus.IN_PROGRESS){
            ride.setStatus(RideStatus.COMPLETED);
            ride.getDriver().setStatus(DriverStatus.AVAILABLE);
            double fare=calculateFare(ride);
            ride.setFare(fare);
            processPayment(ride,fare);
            notifyPassanger(ride);
            notifyDrivers(ride);
        }
    }

    private void notifyDrivers(Ride ride){
        for(Driver driver: drivers.values()){
            if(driver.getStatus() == DriverStatus.AVAILABLE){
                double distance= calculateDistance(driver.getLocation(),ride.getSource());
                if(distance<=5.0){
                    System.out.println("Notifying driver: " + driver.getName() + " about ride request: " + ride.getId());
                }
            }
        }
    }

    private void notifyPassanger(Ride ride) {
        // Notify the passenger about ride status updates
        // ...
        Passanger passenger = ride.getPassenger();
        String message = "";
        switch (ride.getStatus()) {
            case ACCEPTED:
                message = "Your ride has been accepted by driver: " + ride.getDriver().getName();
                break;
            case IN_PROGRESS:
                message = "Your ride is in progress";
                break;
            case COMPLETED:
                message = "Your ride has been completed. Fare: $" + ride.getFare();
                break;
            case CANCELLED:
                message = "Your ride has been cancelled";
                break;
        }
        // Send notification to the passenger
        System.out.println("Notifying passenger: " + passenger.getName() + " - " + message);
    }

    public double calculateDistance(Location source,Location destination){
        return Math.random()*20+1;
    }

    private double calculateDuration(Location source,Location destination){
        double distance = calculateDistance(source, destination);
        return (distance / 30)*60; //assume speed of 30
    }
    private double calculateFare(Ride ride){
        double baseFare=2.0;
        double perKmFare=1.5;
        double perMinuteFare=0.25;

        double distance = calculateDistance(ride.getSource(), ride.getDestination());
        double duration = calculateDuration(ride.getSource(), ride.getDestination());

        double fare = baseFare + (distance * perKmFare) + (duration * perMinuteFare);
        return Math.round(fare*100.0)/100.0;
    }

    public Map<Integer, Ride> getRides() {
        return rides;
    }

    public Queue<Ride> getRequestedRides() {
        return requestedRides;
    }

    private void processPayment(Ride ride, double amount) {
        // Process the payment for the ride
        // ...
    }



}
