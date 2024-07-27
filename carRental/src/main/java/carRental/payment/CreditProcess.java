package carRental.payment;

public class CreditProcess implements PaymentProcessor {
    @Override

    public boolean processPayment(double amount) {
        // Implementation for processing credit card payment
        return true; // Return true if payment is successful
    }
}
