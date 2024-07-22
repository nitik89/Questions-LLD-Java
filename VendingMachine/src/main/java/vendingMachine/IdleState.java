package vendingMachine;

public class IdleState implements VendingMachineState{
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
      if(vendingMachine.inventory.hasProduct(product)){
        vendingMachine.setSelectedProduct(product);
        vendingMachine.setCurrentState(new ReadyState(vendingMachine));
        System.out.println("Product selected: " + product.getName());
    } else {
        System.out.println("Product not available: " + product.getName());
    }
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void insertNotes(Note note) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product and make payment.");
    }

    @Override
    public void returnChange() {
        System.out.println("No change to return.");
    }

}
