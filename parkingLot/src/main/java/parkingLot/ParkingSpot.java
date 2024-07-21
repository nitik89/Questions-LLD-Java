package parkingLot;
import parkingLot.Vehicle.Vehicle;
import parkingLot.Vehicle.VehicleType;
public class ParkingSpot {
    private final int spotNumber;
    private final  VehicleType vehicleType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.vehicleType = VehicleType.CAR; // Default vehicle type is CAR
    }
    //complete it
    public synchronized  boolean isAvailable() {
        return parkedVehicle == null;
    }
    public synchronized void parkVehicle(Vehicle vehicle) {
        if (vehicleType == vehicle.getVehicleType() && isAvailable() ) {
            parkedVehicle = vehicle;
        } else {
            throw new IllegalArgumentException("Invalid vehicle type or spot already occupied.");
        }
    }

    public synchronized void unparkVehicle() {
        parkedVehicle = null;
    }

    public synchronized Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

}
