/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package carRental;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nitiknarang
 */
public class CarRental {

    public static void main(String[] args) {
      RentalSystem rentalSystem=RentalSystem.getInstance();

       // Add cars to the rental system
      rentalSystem.addCar(new Car("Toyota", "Camry", 2022, "ABC123", 50.0));
      rentalSystem.addCar(new Car("Honda", "Civic", 2021, "XYZ789", 45.0));
      rentalSystem.addCar(new Car("Ford", "Mustang", 2023, "DEF456", 80.0));

      Customer customer1 = new Customer("John Doe", "john@example.com", "DL1234");

      LocalDate startDate = LocalDate.now();
      LocalDate endDate = startDate.plusDays(3);


      List<Car> availableCars = rentalSystem.searchCar("Toyota", "Camry", startDate, endDate);

      if(!availableCars.isEmpty()){
        Car selectedCar = availableCars.getFirst();
        Reservation reservation = rentalSystem.makeReservation(customer1, selectedCar, startDate, endDate);

        if(reservation != null){
            boolean paymentSuccess=rentalSystem.processPayment(reservation);
            if (paymentSuccess) {
                System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
            } else {
                System.out.println("Payment failed. Reservation canceled.");
                rentalSystem.cancelReservation(reservation.getReservationId());
            }
         }
         else{
            System.out.println("Select car is not available for the given criteria.");
         }
      }
      else{
          System.out.println("No available cars for the given criteria.");
      }

    }

}
