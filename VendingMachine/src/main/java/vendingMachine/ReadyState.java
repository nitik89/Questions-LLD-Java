package vendingMachine;

public class ReadyState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void selectProduct(Product product) {
        System.out.println("Product is already selected");
    }

    @Override
    public void insertCoin(Coin coin) {
       vendingMachine.addCoins(coin);
       System.out.println("Inserting Coin "+coin.getValue());
       checkPaymentStatus();
    }

    @Override
    public void insertNotes(Note notes) {
        vendingMachine.addNotes(notes);
        System.out.println("Inserting Notes "+notes.getValue());
        checkPaymentStatus();
    }

    @Override
    public void returnChange() {
        double change = vendingMachine.getTotalAmount() - vendingMachine.getSelectedProduct().getPrice();
        if (change > 0) {
            System.out.println("Change returned: $" + change);
            vendingMachine.resetPayment();
        } else {
            System.out.println("No change to return.");
        }
        vendingMachine.resetSelectedProduct();
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please make payment first.");
    }
    public void checkPaymentStatus() {
        if(vendingMachine.getTotalAmount() >= vendingMachine.getSelectedProduct().getPrice()){
            vendingMachine.setCurrentState(new DispenseState(vendingMachine));
            System.out.println("Dispensing product...");
        }
    }




}
