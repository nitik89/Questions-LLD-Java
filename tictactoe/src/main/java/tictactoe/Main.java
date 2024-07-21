package tictactoe;

public class Main {
    public static void main(String[] args) {
        Player playerX = new Player('X');
        Player playerO = new Player('O');
        Game game = new Game(playerX, playerO);
        game.play();  // Start the game
    }
}
