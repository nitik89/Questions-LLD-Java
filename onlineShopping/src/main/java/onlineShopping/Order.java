package onlineShopping;

import java.util.List;

public class Order {
    private final String id;
    private final User user;
    private final List<OrderItem>items;
    private final double totalAmount;
    private OrderStatus status;

    public Order(String id,User user,List<OrderItem> items) {
      this.id=id;
      this.user=user;
      this.items=items;
      this.totalAmount=calculateTotalAmount();
      this.status=OrderStatus.PENDING;
    }

    public double calculateTotalAmount() {
        return items.stream().mapToDouble(item->item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
    // getters and setters

    public String getOrderId() {
        return id;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public OrderStatus getStatus() {
        return status;
    }


}
