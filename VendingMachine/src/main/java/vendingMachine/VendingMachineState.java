package vendingMachine;

public interface VendingMachineState {
    void selectProduct(Product product);
    void insertCoin(Coin coin);
    void insertNotes(Note notes);
    void dispenseProduct();
    void returnChange();
}
