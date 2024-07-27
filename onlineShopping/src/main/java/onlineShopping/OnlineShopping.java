/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package onlineShopping;

import java.util.List;

import onlineShopping.payment.CreditCardPayment;
import onlineShopping.payment.Payment;

/**
 *
 * @author nitiknarang
 */
public class OnlineShopping {

    public static void main(String[] args) {
        OrderService shoppingService = OrderService.getInstance();

        User user1 = new User("U001", "John Doe", "john@example.com", "password123");
        User user2 = new User("U002", "Jane Smith", "jane@example.com", "password456");

        shoppingService.registerUser(user1);
        shoppingService.registerUser(user2);

        Product product1 = new Product("P001", "Laptop","Elec" ,1000.0, 10);
        Product product2 = new Product("P002", "Mobile","Elec", 500.0, 5);
        Product product3 = new Product("P003", "Camera","Elec", 200.0, 20);


        shoppingService.addProduct(product1);
        shoppingService.addProduct(product2);

         // User 1 adds products to cart and places an order
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addItem(product1, 2);
        cart1.addItem(product2, 1);
        Payment payment1 = new CreditCardPayment();
        Order order1 = shoppingService.placeOrder(user1, cart1, payment1);

        System.out.println("Order placed: " + order1.getOrderId());


         // User 2 searches for products and adds to cart
      

        // User 1 views order history
        List<Order> userOrders = user1.getOrders();
        System.out.println("User 1 Order History:");
        for (Order order : userOrders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Status: " + order.getStatus());
        }




    }
}
