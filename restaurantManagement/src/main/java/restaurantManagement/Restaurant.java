package restaurantManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import restaurantManagement.Payment.Payment;

public class Restaurant {
    private static Restaurant instance;
    private final List<MenuItem>menu;
    private final Map<Integer,Order>orderes;
    private final List<Staff> staff;
     private final List<Reservation> reservations;
    private final Map<Integer, Payment> payments;

    private Restaurant(){
        menu = new CopyOnWriteArrayList<>();
        orderes = new ConcurrentHashMap<>();
        staff = new CopyOnWriteArrayList<>();
        payments = new ConcurrentHashMap<>();
        reservations = new CopyOnWriteArrayList<>();
    }
    public static synchronized Restaurant getInstance(){
        if(instance == null){
            instance = new Restaurant();
        }
        return instance;
    }

    // methods for adding, updating, deleting menu items, orders, staff, payments and reservations
    public void addMenuItem(MenuItem item){
        menu.add(item);
    }
    public void removeMenuItem(MenuItem item){
        menu.remove(item);
    }

    public List<MenuItem> getMenu() {
        return new ArrayList<>(menu);
    }

    public void placeOrder(Order order){
        orderes.put(order.getId(), order);
    }

    public void updateOrderStatus(int orderId,OrderStatus status){
        Order order = orderes.get(orderId);
        if(order!= null){
            order.setStatus(status);
        }
    }

    public void makeReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void cancelReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public void processPayment(Payment payment) {
        payments.put(payment.getId(), payment);
        // Process the payment through a payment gateway
        // ...
    }

    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }

    public void removeStaff(Staff staff) {
        this.staff.remove(staff);
    }



}
