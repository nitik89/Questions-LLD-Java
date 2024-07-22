package vendingMachine;

public class VendingMachine {
    private static VendingMachine instance;
    Inventory inventory;
    private Product selectedProduct;
    private VendingMachineState currentState;
    private int amount;
    private VendingMachine(){
        this.inventory = new Inventory();
        this.currentState = new IdleState(this);
        selectedProduct = null;
    }
    public static synchronized VendingMachine getInstance(){
        if(instance==null){
            instance = new VendingMachine();
        }
        return instance;
    }
    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }
    public void selectProduct(Product product){
        currentState.selectProduct(product);
    }
    public void insertCoin(Coin coin){
        currentState.insertCoin(coin);
    }

    public void insertNotes(Note notes){
        currentState.insertNotes(notes);
    }

    public void returnChange(){
        currentState.returnChange();
    }

   public void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

   public void addCoins(Coin coin){
        amount += coin.getValue();
    }
   public void addNotes(Note notes){
        amount += notes.getValue();
    }

    public Product getSelectedProduct(){
        return selectedProduct;
    }

    public int getTotalAmount(){
        return amount;
    }

    public void resetPayment(){
        this.amount=0;
    }

    public void resetSelectedProduct() {
        this.selectedProduct = null;
    }

    public void dispenseProduct(){
        currentState.dispenseProduct();
    }

}
