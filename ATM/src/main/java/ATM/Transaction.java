package ATM;

public abstract class Transaction {
    protected final String transactionId;
    protected final Account account;
    protected final double amount;

    public Transaction(String transactionId,Account account, double amount) {
        this.account = account;
        this.amount = amount;
        this.transactionId = transactionId;
    }
    public abstract void execute();
}
