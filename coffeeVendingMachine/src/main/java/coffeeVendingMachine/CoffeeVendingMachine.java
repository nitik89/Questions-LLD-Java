/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package coffeeVendingMachine;

/**
 *
 * @author nitiknarang
 */
public class CoffeeVendingMachine {

    public static void main(String[] args) {
        CoffeeMachine machine=CoffeeMachine.getInstance();
        machine.displayMenu();
        Coffee coffee=machine.selectCoffee("Espresso");
        Payment payment=new Payment(5.00);
        machine.dispenseCoffee(coffee, payment);
    }
}
