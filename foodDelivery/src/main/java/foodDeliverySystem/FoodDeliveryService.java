package foodDeliverySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import foodDeliverySystem.Order.Order;
import foodDeliverySystem.Order.OrderItem;
import foodDeliverySystem.Order.OrderStatus;

public class FoodDeliveryService {
    private static FoodDeliveryService instance;
    private final Map<String, Restaurant>restaurants;
    private final Map<String, Customer> customers;
    private final Map<String,Order>orders;
    private final Map<String,DeliveryAgent> deliveryAgents; 

    private FoodDeliveryService(){
        customers = new ConcurrentHashMap<>();
        restaurants = new ConcurrentHashMap<>();
        orders = new ConcurrentHashMap<>();
        deliveryAgents = new ConcurrentHashMap<>();
    }
    public static FoodDeliveryService getInstance(){
        if(instance == null){
            instance = new FoodDeliveryService();
        }
        return instance;
    }

  public void registerCustomer(Customer customer){
     customers.put(customer.getId(),customer);
  }

  public void registerRestaurant(Restaurant restaurant){
     restaurants.put(restaurant.getId(), restaurant);
  }

  public void registerDeliveryAgent(DeliveryAgent agent){
    deliveryAgents.put(agent.getId(), agent);
  }

  public List<Restaurant> getAvailableRestaurants(){
    return new ArrayList<>(restaurants.values());
  }

  public List<MenuItem> getAvailableMenuItems(String restaurantId){
    Restaurant restaurant = restaurants.get(restaurantId);
    if (restaurant != null) {
        return restaurant.getMenu();
    }
    return new ArrayList<>();
  }

  public Order placeOrder(String customerId,String restaurantId,List<OrderItem>orderItems ){
    Customer customer = customers.get(customerId);
    Restaurant restaurant = restaurants.get(restaurantId);
    if(customer!= null && restaurant!= null){
        Order order = new Order(generateOrderId(),customer,restaurant);
        orders.put(order.getId(), order);
        for(OrderItem orderItem : orderItems){
            order.addItem(orderItem);
        }
        notifyRestaurant(order);
        System.out.println("Order placed "+order.getId());
        return order;
    }
    return null;
  }

  public void updateOrderStatus(String orderId, OrderStatus status){
    Order order = orders.get(orderId);
    if(order!= null){
      order.setStatus(status);
      notifyCustomer(order);
      if(status == OrderStatus.DELIVERED){
        notifyDeliveryAgent(order);
      }
      System.out.println("Order status updated "+orderId);
    }
  }

  public void assignDeliveryAgent(Order order){
    for (DeliveryAgent agent : deliveryAgents.values()) {
        if (agent.isAvailable()) {
            agent.setAvailable(false);
            order.assignDeliveryAgent(agent);
            notifyDeliveryAgent(order);
            break;
        }
    }
  }
  public Customer getCustomer(String id){
    return customers.get(id);
  }

  private String generateOrderId(){
    return "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
  }

  private void notifyRestaurant(Order order){
    Restaurant restaurant = order.getRestaurant();
    System.out.println("Notifying restaurant "+restaurant.getName()+" about new order "+order.getId());
  }

  private void notifyCustomer(Order order){
    Customer customer = order.getCustomer();
    System.out.println("Notifying customer "+customer.getName()+" about new order "+order.getId());
  }
  private void notifyDeliveryAgent(Order order){
    DeliveryAgent deliveryAgent = order.getDeliveryAgent();
    if(deliveryAgent!= null && deliveryAgent.isAvailable()){
      System.out.println("Notifying delivery agent "+deliveryAgent.getName()+" about new order "+order.getId());
    }
  }





}
