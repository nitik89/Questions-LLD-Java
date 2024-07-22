package vendingMachine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<Product,Integer> inventory;

    public Inventory() {
        this.inventory = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        inventory.put(product, quantity);
    }
    public void removeProduct(Product product) {
        inventory.remove(product);
    }

    public int getQuantity(Product product) {
        return inventory.getOrDefault(product, 0);
    }

    public void updateQuantity(Product product, int quantity){
        inventory.put(product, quantity);
    }

    public boolean hasProduct(Product product) {
        return inventory.containsKey(product) && inventory.get(product) > 0;
    }
}
