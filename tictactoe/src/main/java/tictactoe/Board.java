package tictactoe;

public class Board {
   //board of size 3x3 grid
   private char[][] board;
   int movesCount;

   public Board() {
       board = new char[3][3];
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               board[i][j] ='-';
           }
       }
       movesCount=0;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
       return movesCount == 9;
    }

    public boolean isWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;

    }
    
    public boolean isDraw() {
        return isBoardFull() && !isWin('X') && !isWin('O');
    }
    //make move board
    public synchronized  void makeMove(int row, int col, char player) throws IllegalArgumentException {
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col]!= '-') {
            throw new IllegalArgumentException("Invalid move. Choose an empty cell.");
        }
        board[row][col] = player;
        movesCount++;
    }

}
