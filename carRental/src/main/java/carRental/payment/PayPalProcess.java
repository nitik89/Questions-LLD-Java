package carRental.payment;

public class PayPalProcess implements  PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Implementation for processing PayPal payment
        return true; // Return true if payment is successful
    }
}
