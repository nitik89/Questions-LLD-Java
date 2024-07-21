package parkingLot;

import java.util.ArrayList;
import java.util.List;

import parkingLot.Vehicle.Vehicle;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<Level> levels;

    private ParkingLot() {
       levels = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    // complete it

    public void addLevel(Level level) {
        levels.add(level);
    }
    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkedVehicle(vehicle)) {
                return true;
            }
        }
        System.out.println("Could not park the vehicle "+vehicle.getLicensePlate());
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.unparkVehicle(vehicle)) {
                return true;
            }
        }
        System.out.println("Could not unpark the vehicle "+vehicle.getLicensePlate());
        return false;
    }

    public void displayAvailability() {
        for (Level level : levels) {
            level.displayAvailability();
        }
    }

 }
