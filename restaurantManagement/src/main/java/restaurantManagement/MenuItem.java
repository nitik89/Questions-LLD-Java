package restaurantManagement;

public class MenuItem {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final boolean available;

    public MenuItem(int id, String name, String description, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
