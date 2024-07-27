package onlineShopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import onlineShopping.payment.Payment;

public class OrderService {
    private static OrderService instance;
    private final Map<String,User>users;
    private final Map<String,Order>orders;
    private final Map<String, Product> products;

    private OrderService() {
        this.users = new ConcurrentHashMap<>();
        this.orders = new ConcurrentHashMap<>();
        this.products = new ConcurrentHashMap<>();
    }

    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }
    public void registerUser(User user){
        users.put(user.getId(),user);
    }

    public User getUser(String id){
        return users.get(id);
    }

    public Product getProduct(String id){
        return products.get(id);
    }

    public List<Product> searchProducts(String keyword){
        return products.values().stream().filter(product -> product.getName().equals(keyword.toLowerCase())).collect(Collectors.toList());
    }

    public synchronized Order placeOrder(User user,ShoppingCart cart, Payment payment){
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItem orderItem : cart.getItems()){
            Product product = orderItem.getProduct();
            int quantity = product.getQuantity();
            if(product.isAvailable(quantity)){
                product.updateQuantity(-quantity);
                orderItems.add(orderItem);
            }
        }
        if(orderItems.isEmpty()){
            throw new IllegalStateException("Insufficient stock");
        }
        String orderId=generateOrderId();
        Order order = new Order(orderId,user,orderItems);
        orders.put(orderId, order);
        user.addOrder(order);
        cart.clear();

        if(payment.processPayment(order.getTotalAmount())){
            order.updateStatus(OrderStatus.PROCESSING);
        }
        else{
            order.updateStatus(OrderStatus.CANCELLED);
            for (OrderItem item : orderItems) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                product.updateQuantity(quantity);
            }
        }
        return order;

    }

    private String generateOrderId(){
        return "OID-" + System.currentTimeMillis();
    }
}
