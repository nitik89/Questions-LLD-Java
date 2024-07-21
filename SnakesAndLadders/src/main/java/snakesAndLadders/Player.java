package snakesAndLadders;

public class Player {
    private final String name;
    private int currentPosition;
    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
    }
    public String getName() {
        return name;
    }
    public void setPosition(int position) {
        this.currentPosition=position;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
}
