package vendingMachine;

public class ReturnChangeState implements VendingMachineState {
    private final VendingMachine vendingMachine;
    public ReturnChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void selectProduct(Product product) {
        System.out.println("Cannot select product while returning change");
    }
    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Cannot insert coin while returning change");
    }
    @Override
    public void insertNotes(Note notes) {
        System.out.println("Cannot insert notes while returning change");
    }
    @Override
    public void dispenseProduct() {
        System.out.println("Cannot dispense product while returning change");
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

    
}
