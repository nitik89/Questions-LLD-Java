package stackOverflow;

public class User {
    private final String id;
    private final String name;
    private final String email;
    private  String notification;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public void setNotification(String notification) {
        this.notification=notification;
    }
    public void displayNotification(){
        System.out.println(notification);
    }
}
