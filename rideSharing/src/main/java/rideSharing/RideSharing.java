/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rideSharing;

/**
 *
 * @author nitiknarang
 */
public class RideSharing {

    public static void main(String[] args) {

        RideService rideService = RideService.getInstance();
        // Create passengers and drivers
        Passanger passenger1 = new Passanger(1, "John Doe", "1234567890", new Location(10, 20));
        Passanger passenger2 = new Passanger(2, "Jane Smith", "9876543210", new Location(20, 30));

        Driver driver1 = new Driver(1, "John Doe", "1234567890", "ABC123", DriverStatus.AVAILABLE, new Location(15, 25));
        Driver driver2 = new Driver(2, "Jane Smith", "9876543210", "DEF456", DriverStatus.AVAILABLE, new Location(25, 35));

        rideService.addPassenger(passenger1);
        rideService.addPassenger(passenger2);

        rideService.addDriver(driver1);
        rideService.addDriver(driver2);

        // Request rides
        rideService.requestRide(passenger1, passenger1.getLocation(), new Location(20, 30));
        rideService.requestRide(passenger2,  passenger2.getLocation(), new Location(10, 20));

        // Process rides
        Ride ride1=rideService.getRequestedRides().poll();
        rideService.acceptRide(driver1, ride1);

        // Start the ride
        rideService.startRide(driver1,ride1);

        // Complete the ride
        rideService.completeRide(ride1);

      
    }
}
