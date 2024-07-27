package foodDeliverySystem.Order;

import java.util.ArrayList;
import java.util.List;

import foodDeliverySystem.Customer;
import foodDeliverySystem.DeliveryAgent;
import foodDeliverySystem.Restaurant;

public class Order {
    private final String id;
    private final Customer customer;
    private final Restaurant restaurant;
    private final List<OrderItem> orderItems;
    private OrderStatus status;
    private DeliveryAgent deliveryAgent;

    public Order(String id, Customer customer, Restaurant restaurant){
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderItems = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.deliveryAgent = null;
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void assignDeliveryAgent(DeliveryAgent agent) {
        this.deliveryAgent = agent;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    
}
