package roomManagement.Payment;

public class CashPayment implements Payment{
    @Override
    public boolean processPayment(double amount) {
        // Implement cash payment logic here
        return true; // Return true if payment is successful
    }
}
