package vendingMachine;

public class DispenseState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }
    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Coin inserted. Please collect the dispensed product.");
    }

    @Override
    public void insertNotes(Note notes) {
        System.out.println("Note inserted. Please collect the dispensed product.");
    }

    @Override
    public void returnChange() {
        System.out.println("Please collect the dispensed product first.");
    }

    //complete the code 

    @Override
    public void dispenseProduct() {
        vendingMachine.setCurrentState(new ReadyState(this.vendingMachine));

        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.inventory.updateQuantity(product, vendingMachine.inventory.getQuantity(product) - 1);
        System.out.println("Product dispensed: " + product.getName());
        vendingMachine.setCurrentState(new ReturnChangeState(this.vendingMachine)); 
    }

}
