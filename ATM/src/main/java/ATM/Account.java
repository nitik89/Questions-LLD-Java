package ATM;

public class Account {
    private final String accountNumber;
    private double balance;
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    public synchronized void credit(double amount) {
        balance += amount;
        System.out.println("Depositing " + amount + " into account " + accountNumber);
    }
    public synchronized void debit(double amount) {
        if(balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawing " + amount + " from account " + accountNumber);
        } else {
            throw new IllegalArgumentException("Insufficient funds in account " + accountNumber);
        }
    }
    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }
    
}
