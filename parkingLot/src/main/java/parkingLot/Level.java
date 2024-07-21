package parkingLot;

import java.util.ArrayList;
import java.util.List;

import parkingLot.Vehicle.Vehicle;

public class Level {
    private final int floor;
    private final List<ParkingSpot>parkingSpots;
    public Level(int floor,int numberOfParkingSpots) {
        this.floor = floor;
        this.parkingSpots = new ArrayList<>(numberOfParkingSpots);
        for (int i = 0; i < numberOfParkingSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }
    public int getFloor() {
        return floor;
    }
    
    public synchronized boolean parkedVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.getVehicleType()==vehicle.getVehicleType()) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle() == vehicle ) {
                spot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Level " + floor + " Availability:");
        for (ParkingSpot spot : parkingSpots) {
            System.out.println("Spot " + spot.getSpotNumber() + ": " + (spot.isAvailable() ? "Available" : "Occupied"));
        }
    }








}
