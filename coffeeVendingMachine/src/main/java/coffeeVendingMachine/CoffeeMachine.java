package coffeeVendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoffeeMachine {
    private static  CoffeeMachine instance;
    private final List<Coffee> coffeeMenu;
    private final Map<String ,Ingredients> ingredients;

    private CoffeeMachine(){
        coffeeMenu=new ArrayList<>();
        ingredients=new ConcurrentHashMap<>();
        intializeIngredients();
        initializeCoffeeMenu();
    }

    public static CoffeeMachine getInstance(){
        if(instance==null){
            instance=new CoffeeMachine();
        }
        return instance;
    }

    private void intializeIngredients(){
        ingredients.put("Coffee",new Ingredients("Coffee",100));
        ingredients.put("Milk",new Ingredients("Milk",100));
        ingredients.put("Sugar",new Ingredients("Sugar",100));
        ingredients.put("Water",new Ingredients("Water",100));
    }

    private void initializeCoffeeMenu(){
        Map<Ingredients, Integer> espresso=new ConcurrentHashMap<>();
        espresso.put(ingredients.get("Coffee"),2);
        espresso.put(ingredients.get("Milk"),1);
        espresso.put(ingredients.get("Sugar"),1);
        espresso.put(ingredients.get("Water"),2);
        coffeeMenu.add(new Coffee("Espresso", 3.50, espresso));

        Map<Ingredients, Integer> latte=new ConcurrentHashMap<>();
        latte.put(ingredients.get("Coffee"),2);
        latte.put(ingredients.get("Milk"),1);
        latte.put(ingredients.get("Sugar"),1);
        latte.put(ingredients.get("Water"),2);
        coffeeMenu.add(new Coffee("Latte", 4.00, latte));
    }

    public void displayMenu(){
        System.out.println("Coffee Menu");
        for(Coffee coffee: coffeeMenu){
            System.out.println(coffee.getName() +" - $"+ coffee.getPrice());
        }
    }

    public synchronized Coffee selectCoffee(String coffeeName){
        for(Coffee coffee: coffeeMenu){
            if(coffee.getName().equalsIgnoreCase(coffeeName)){
                return coffee;
            }
        }
        return null;
    }

    public synchronized void dispenseCoffee(Coffee coffee, Payment payment){
        if(payment.getAmount()>=coffee.getPrice()){
            if(hasEnoughIngredients(coffee)){
                updateIngredients(coffee);
                System.out.println("Dispensing " + coffee.getName());
                double change=payment.getAmount()-coffee.getPrice();
                if(change>0){
                    System.out.println("Please collect your change: $" + change);
                }
            }
            else{
                System.out.println("Not enough ingredients to dispense " + coffee.getName());
            }
        }
        else{
            System.out.println("Insufficient funds. Please insert more money. "+ coffee.getName());
        }
    }

    private boolean hasEnoughIngredients(Coffee coffee){
        for (Map.Entry<Ingredients, Integer> entry : coffee.getRecipe().entrySet()) {
            Ingredients ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            if (ingredient.getQuantity() < requiredQuantity) {
                return false;
            }
        }
        return true;
    }

    private void updateIngredients(Coffee coffee){
        for (Map.Entry<Ingredients, Integer> entry : coffee.getRecipe().entrySet()) {
            Ingredients ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            ingredient.increaseQuantity(-requiredQuantity);
            if (ingredient.getQuantity() < 3) {
                System.out.println("Low inventory alert: " + ingredient.getName());
            }
        }
    }










}
