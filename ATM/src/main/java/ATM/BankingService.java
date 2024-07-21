package ATM;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankingService {
    private final Map<String,Account> accounts=new ConcurrentHashMap<>();
    
    public void createAccount(String accountNumber, double initialBalance) {
        Account account = new Account(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void processTransaction(Transaction transaction) {
        transaction.execute();
    }
}
