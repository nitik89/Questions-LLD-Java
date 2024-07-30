package coffeeVendingMachine;

import java.util.Map;

public class Coffee {
    private final String name;
    private final double price;
    private final Map<Ingredients,Integer>ingredient;

    public Coffee(String name, double price, Map<Ingredients,Integer> ingredient) {
        this.name = name;
        this.price = price;
        this.ingredient = ingredient;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Map<Ingredients, Integer> getRecipe() {
        return ingredient;
    }
}
