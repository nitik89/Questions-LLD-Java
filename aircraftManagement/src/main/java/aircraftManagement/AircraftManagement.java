/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aircraftManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import aircraftManagement.Booking.Booking;
import aircraftManagement.Flight.Flight;
import aircraftManagement.Seat.Seat;
import aircraftManagement.Seat.SeatType;

/**
 *
 * @author nitiknarang
 */
public class AircraftManagement {

    public static void main(String[] args) {
        AirCraftSystem airlineManagementSystem = new AirCraftSystem();

          // Create users
          Passanger passenger1 = new Passanger("U001", "John Doe", "john@example.com", "1234567890");

            // Create flights
        LocalDateTime departureTime1 = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivalTime1 = departureTime1.plusHours(2);
        Flight flight1 = new Flight("F001", "New York", "London", departureTime1, arrivalTime1);

        LocalDateTime departureTime2 = LocalDateTime.now().plusDays(3);
        LocalDateTime arrivalTime2 = departureTime2.plusHours(5);
        Flight flight2 = new Flight("F002", "Paris", "Tokyo", departureTime2, arrivalTime2);

        airlineManagementSystem.addFlight(flight1);
        airlineManagementSystem.addFlight(flight2);

          // Create aircrafts
          Aircraft aircraft1 = new Aircraft("A001", "Boeing 747", 300);
          Aircraft aircraft2 = new Aircraft("A002", "Airbus A380", 500);
          airlineManagementSystem.addAircraft(aircraft1);
          airlineManagementSystem.addAircraft(aircraft2);


           // Search flights
        List<Flight> searchResults = airlineManagementSystem.searchFlights("New York", "London", LocalDate.now().plusDays(1));
        System.out.println("Search Results:");
        for (Flight flight : searchResults) {
            System.out.println("Flight: " + flight.getFlightNumber() + " - " + flight.getSource() + " to " + flight.getDestination());
        }

        Seat seat = new Seat("25A", SeatType.ECONOMY);

        // Book a flight
        Booking booking = airlineManagementSystem.bookFlight(flight1, passenger1, seat, 100);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getBookingNumber());
        } else {
            System.out.println("Booking failed.");
        }

        // Cancel a booking
        airlineManagementSystem.cancelBooking(booking.getBookingNumber());
        System.out.println("Booking cancelled.");


    }
}