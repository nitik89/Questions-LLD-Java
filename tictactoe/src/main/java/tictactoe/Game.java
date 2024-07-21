package tictactoe;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player playerX ;
    private final Player playerO;
    private Player currentPlayer;

    public Game(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board();
        this.currentPlayer = playerX;
    }

    public void play() {
        board.printBoard();
        while (!board.isBoardFull()) { 
            System.out.println("Player " + currentPlayer.getAction() + ", enter your move (row col): ");
            int row=getInput("Enter row (0-2): ");
            int col=getInput("Enter col (0-2): ");
            try {
                board.makeMove(row, col, currentPlayer.getAction());
                board.printBoard();
               if (board.isWin(currentPlayer.getAction())) {
                    System.out.println("Player " + currentPlayer.getAction() + " wins!");
                    break;
                }
            switchPlayer();
                //calling board make move 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        if(board.isBoardFull() && board.isDraw()){
            System.out.println("It's a draw!");
        }
    }
    public void switchPlayer(){
        if (currentPlayer == playerX) {
            currentPlayer = playerO;
        } else {
            currentPlayer = playerX;
        }
    }
    public int getInput(String message){
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0 && input <= 2) {
                    return input;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid input! Please enter a number between 0 and 2.");
        }
        
    }
    
}
