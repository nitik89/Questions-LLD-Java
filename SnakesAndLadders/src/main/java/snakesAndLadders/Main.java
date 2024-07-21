package snakesAndLadders;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager =  GameManager.getInstance();
        //start the game
        List<String> playerNames = Arrays.asList("Player 1", "Player 2", "Player 3");
        gameManager.startGame(playerNames);
    }
}
