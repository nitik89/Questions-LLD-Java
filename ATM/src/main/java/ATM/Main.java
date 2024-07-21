package ATM;

public class Main {
    public static void main(String[] args) {
        BankingService bankingService=new BankingService();
        CashDispenser cashDispenser=new CashDispenser(10000);
        ATM atm=new ATM(bankingService, cashDispenser);
        bankingService.createAccount("123456789", 1000.0);
        Card card = new Card("1234567890", "1234");
        atm.authenticateUser(card);
        atm.depositCash("123456789", 500.0);
        atm.withdrawCash("123456789", 200.0);
        double balance = atm.checkBalance("123456789");
        System.out.println("Current balance: " + balance);

    }
}
