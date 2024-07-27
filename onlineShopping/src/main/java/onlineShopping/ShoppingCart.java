package onlineShopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShoppingCart {
    private final Map<String,OrderItem> orderItems;

    public ShoppingCart() {
        this.orderItems = new ConcurrentHashMap<>();
    }

    public void addItem(Product product,int quantity) {
        String productId=product.getId();
        if(orderItems.containsKey(productId)){
            OrderItem orderItem=orderItems.get(productId);
           quantity+=orderItem.getQuantity();
        }
        orderItems.put(productId,new OrderItem(product,quantity));
    }

    public void removeItem(String productId) {
        orderItems.remove(productId);
    }

    public void updateItemQuantity(String productId,int quantity){
        OrderItem orderItem=orderItems.get(productId);
        if(orderItem!=null){
            orderItems.put(productId, new OrderItem(orderItem.getProduct(), quantity));
        }
    }

     public List<OrderItem> getItems() {
        return new ArrayList<>(orderItems.values());
    }

    public void clear() {
        orderItems.clear();
    }





}
