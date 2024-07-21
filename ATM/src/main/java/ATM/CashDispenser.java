package ATM;

public class CashDispenser {
    private int cashValue;
    public CashDispenser(int cashValue) {
        this.cashValue = cashValue;
    }
    public synchronized  void dispense(int amount) {
        if(cashValue >= amount) {
            cashValue -= amount;
            System.out.println("Dispensing cash " + amount);
        } else {
           throw new IllegalArgumentException("Insufficient cash available in the ATM.");
        }
    }
}
