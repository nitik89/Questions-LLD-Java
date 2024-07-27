package foodDeliverySystem;

public class MenuItem {
    private final String name;
    private final String id;
    private final String description;
    private final double price;
    private boolean available;

    public MenuItem(String name, String id, String description, double price){
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.available = true;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String getName() {
        return name;
    }
}
