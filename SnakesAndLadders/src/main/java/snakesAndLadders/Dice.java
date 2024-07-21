package snakesAndLadders;

public class Dice {
    private static final int MIN_VALUE =1;
    private  static final int MAX_VALUE =6;

    public int roll() {
        return MIN_VALUE + (int) (Math.random() * ((MAX_VALUE - MIN_VALUE) + 1));
    }
}
