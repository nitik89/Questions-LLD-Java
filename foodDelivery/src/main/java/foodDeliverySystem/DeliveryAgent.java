package foodDeliverySystem;

public class DeliveryAgent {
    private final String id;
    private final String name;
    private final String phone;
    private boolean availabe;

    public DeliveryAgent(String id,String name,String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.availabe = true;
    }

    public void setAvailable(boolean available) {
        this.availabe = available;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAvailable() {
        return availabe;
    }


}
