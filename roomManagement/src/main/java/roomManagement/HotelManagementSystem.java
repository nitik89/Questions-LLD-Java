package roomManagement;

import java.time.LocalDate;

import roomManagement.Payment.CreditPayment;
import roomManagement.Payment.Payment;

public class HotelManagementSystem {
    public static void main(String[] args) {
        
        HotelManagement hotelManagement = HotelManagement.getInstance();

        // Create guests
        Guest guest1 = new Guest("G1", "John Doe", "john.doe@example.com", "1234567890");
        Guest guest2 = new Guest("G2", "Jane Smith", "jane.smith@example.com", "9876543210");

        // Add guests to the hotel management system
        hotelManagement.addGuest(guest1);
        hotelManagement.addGuest(guest2);

        // Create rooms
        Room room1 = new Room("R1",RoomType.SINGLE,100.0);
        Room room2 = new Room("R2", RoomType.DOUBLE,500.0);

        // Add rooms to the hotel management system
        hotelManagement.addRoom(room1);
        hotelManagement.addRoom(room2);

        LocalDate checkInDate=LocalDate.now();
        LocalDate checkOutDate=checkInDate.plusDays(3);
        Reservation reservation = hotelManagement.bookRoom(guest1, room1, checkInDate, checkOutDate);

        if(reservation!=null){
            System.out.println("Reservation made successfully. Reservation ID: "+reservation.getId());
        }
        else{
            System.out.println("Failed to make reservation.");
        }

        // Confirm the reservation
        hotelManagement.checkIn(reservation.getId());
        System.out.println("Reservation confirmed.");

        Payment payment=new CreditPayment();
        hotelManagement.checkOut(reservation.getId(), payment);
        System.out.println("Reservation checked out.");
        

       
    }
}
