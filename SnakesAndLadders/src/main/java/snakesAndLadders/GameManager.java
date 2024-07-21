package snakesAndLadders;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static GameManager instance;
    private final List<Game> games;
    private GameManager() {
        games = new ArrayList<>();
    }
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    public void startGame(List<String> players) {
        Game game = new Game(players);
        games.add(game);
      game.playGame();
    }
}
