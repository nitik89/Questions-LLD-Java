package snakesAndLadders;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private int currentPlayerIndex;
    public Game(List<String> playerNames) {
        this.board = new Board();
        players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
    
        this.dice = new Dice();
        this.currentPlayerIndex = 0;
    }
    public void playGame() {
        while(!isGameOver()){
            Player currentPlayer = players.get(currentPlayerIndex);
            int diceRoll = dice.roll();
            int newPosition = currentPlayer.getCurrentPosition() + diceRoll;
            if(newPosition<=board.getBoardSize()){
                currentPlayer.setPosition(board.getNewPositionAfterSnakeOrLadder(newPosition));
                System.out.println(currentPlayer.getName() + " moved to position " + currentPlayer.getCurrentPosition());
            }
            //declare winner
            if(currentPlayer.getCurrentPosition()==board.getBoardSize()){
                System.out.println(currentPlayer.getName() + " wins the game!");
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
    private boolean isGameOver() {
        for (Player player : players) {
            if (player.getCurrentPosition() == board.getBoardSize()) {
                return true;
            }
        }
        return false;
    }
}
