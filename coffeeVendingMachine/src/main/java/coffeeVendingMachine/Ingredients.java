package coffeeVendingMachine;

public class Ingredients {
    private final String name;
    private int quantity;

    public Ingredients(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int amount) {
        quantity -= amount;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

}
