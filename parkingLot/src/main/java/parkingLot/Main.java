package parkingLot;

import parkingLot.Vehicle.Car;
import parkingLot.Vehicle.Motorcycle;
import parkingLot.Vehicle.Vehicle;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot =  ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 5));
        parkingLot.addLevel(new Level(2, 4));

        Vehicle car=new Car("ABC123");
        Vehicle motorcycle=new Motorcycle("DEF456");
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(motorcycle);
        parkingLot.displayAvailability();
        System.out.println();
          // Unpark vehicle
          parkingLot.unparkVehicle(motorcycle);
          // Display updated availability
          parkingLot.displayAvailability();

    }
}
