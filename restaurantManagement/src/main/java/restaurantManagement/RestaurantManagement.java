/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package restaurantManagement;

import java.sql.Timestamp;
import java.util.List;

import restaurantManagement.Payment.Payment;
import restaurantManagement.Payment.PaymentMethod;

/**
 *
 * @author nitiknarang
 */
public class RestaurantManagement {

    public static void main(String[] args) {
        Restaurant restaurant = Restaurant.getInstance();
        restaurant.addMenuItem(new MenuItem(1, "Burger", "Delicious burger", 9.99, true));
        restaurant.addMenuItem(new MenuItem(2, "Pizza", "Cheesy pizza", 12.99, true));
        restaurant.addMenuItem(new MenuItem(3, "Salad", "Fresh salad", 7.99, true));

        Order order = new Order(1, restaurant.getMenu(), 25.98, OrderStatus.PENDING, new Timestamp(System.currentTimeMillis()));

          // Make a reservation
          Reservation reservation = new Reservation(1, "John Doe", "1234567890", 4, new Timestamp(System.currentTimeMillis()));
          restaurant.makeReservation(reservation);

        // Place the order

        // Process a payment
        Payment payment = new Payment(1, 17.98, PaymentMethod.CREDIT_CARD, PaymentStatus.PENDING);
        restaurant.processPayment(payment);

        // Add staff
        restaurant.addStaff(new Staff(1, "Alice", "Manager", "9876543210"));
        restaurant.addStaff(new Staff(2, "Bob", "Chef", "5432109876"));

         // Get menu
        List<MenuItem> menu = restaurant.getMenu();
        System.out.println("Menu:");
        for (MenuItem item : menu) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        
    }
}
