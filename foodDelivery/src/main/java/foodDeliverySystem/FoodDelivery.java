/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package foodDeliverySystem;

import java.util.ArrayList;
import java.util.List;

import foodDeliverySystem.Order.Order;
import foodDeliverySystem.Order.OrderItem;
import foodDeliverySystem.Order.OrderStatus;

/**
 *
 * @author nitiknarang
 */
public class FoodDelivery {

    public static void main(String[] args) {
       FoodDeliveryService deliveryService = FoodDeliveryService.getInstance();

       Customer customer1 = new Customer("CUST1", "John Doe", "johndoe@example.com", "1234567890");
       Customer customer2= new Customer("CUST2", "John Doe", "johndoe@example", "1234567890");

       deliveryService.registerCustomer(customer1);
       deliveryService.registerCustomer(customer2);

       List<MenuItem> items = new ArrayList<>();
       items.add(new MenuItem("Hamburger","M001","Burger", 10.99));
       items.add(new MenuItem("Pizza","M002","Cheesy pizza", 15.99));
       items.add(new MenuItem("Fries","M003","French fries", 3.99));
       items.add(new MenuItem("Cola","M004","Pepsi", 2.99));
       Restaurant restaurant1=new Restaurant("R001","Restaurant 1","Address 1",items);
       deliveryService.registerRestaurant(restaurant1);

       //register delivery agents
       DeliveryAgent agent1 = new DeliveryAgent("D001", "Agent 1", "9999999999");
       DeliveryAgent agent2 = new DeliveryAgent("D002", "Agent 2", "8888888888");
       deliveryService.registerDeliveryAgent(agent1);
       deliveryService.registerDeliveryAgent(agent2);

       //place orders
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(items.get(0), 2));
        orderItems.add(new OrderItem(items.get(1), 1));
       Order order1 = deliveryService.placeOrder(customer1.getId(), restaurant1.getId(), orderItems);
       order1.assignDeliveryAgent(agent1);

       deliveryService.updateOrderStatus(order1.getId(), OrderStatus.CONFIRMED);
        System.out.println("Order status updated: " + order1.getStatus());

        //deliver the order 
        deliveryService.assignDeliveryAgent(order1);

        deliveryService.updateOrderStatus(order1.getId(), OrderStatus.DELIVERED);

        System.out.println("Order status updated: " + order1.getStatus());
       



    }
}
