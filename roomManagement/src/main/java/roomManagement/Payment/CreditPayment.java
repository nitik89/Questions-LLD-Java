package roomManagement.Payment;

public class CreditPayment implements Payment{
    @Override
    public boolean processPayment(double amount) {
        // Implement credit card payment logic here
        return true; // Return true if payment is successful
    }
}
