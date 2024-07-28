package restaurantManagement.Payment;

import restaurantManagement.PaymentStatus;

public class Payment {
    private final int id;
    private final double amount;
    private final PaymentMethod method;
    private  PaymentStatus status;

    public Payment(int id, double amount, PaymentMethod method, PaymentStatus status) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
