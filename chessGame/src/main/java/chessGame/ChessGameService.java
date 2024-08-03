package chessGame;

import java.util.Scanner;

import chessGame.pieces.Piece;

public class ChessGameService {
    private final Board board;
    private final Player[] players;
    private int currentPlayer;

    public ChessGameService() {
        board = new Board();
        players = new Player[]{new Player(Color.WHITE), new Player(Color.BLACK)};
        currentPlayer = 0;
    }

    public void start(){
        while(!isGameOver()){
            Player player=players[currentPlayer];
            System.out.println(player.getColor() + "'s turn.");
            Move move= getPlayerMove(player);

            try {
                player.makeMove(board, move);
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
                continue;
            }

            currentPlayer = (currentPlayer + 1) % 2;
        }
        displayResult();
    }

    private boolean isGameOver() {
        return board.isCheckmate(players[0].getColor()) || board.isCheckmate(players[1].getColor()) ||
                board.isStalemate(players[0].getColor()) || board.isStalemate(players[1].getColor());
    }

    private Move getPlayerMove(Player player){
         Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source row: ");
        int sourceRow = scanner.nextInt();
        System.out.print("Enter source column: ");
        int sourceCol = scanner.nextInt();
        System.out.print("Enter destination row: ");
        int destRow = scanner.nextInt();
        System.out.print("Enter destination column: ");
        int destCol = scanner.nextInt();

         Piece piece = board.getPiece(sourceRow, sourceCol);
        if (piece == null || piece.getColor() != player.getColor()) {
            throw new IllegalArgumentException("Invalid piece selection!");
        }
        return new Move(piece, destRow, destCol);
    }

    private void displayResult() {
        if (board.isCheckmate(Color.WHITE)) {
            System.out.println("Black wins by checkmate!");
        } else if (board.isCheckmate(Color.BLACK)) {
            System.out.println("White wins by checkmate!");
        } else if (board.isStalemate(Color.WHITE) || board.isStalemate(Color.BLACK)) {
            System.out.println("The game ends in a stalemate!");
        }
    }

}
