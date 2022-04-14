import java.util.*;

/**
 * Connect Four
 * @author Alex Mize
 * @author Kyle Albury
 * @author Ryan McPhee
 */

public class Board {
    
    /** constant for the number of rows on the board */
    public static final int ROWS = 4;
    
    /** constant for the number of columns on the board */
    public static final int COLUMNS = 4;
    
    /** constant for the maximum possible moves on a 4 x 4 board */
    public static final int MAX_MOVES = 16;
    
    /** constant for the char representing player one's moves */
    public static final char PLAYER1 = 'X';
    
    /** constant for the char representing player two's moves */
    public static final char PLAYER2 = 'O';
    
    /** private variable for the two dimensional array of the board */
    private char[][] board;
    
    /** Board object constructor */
    public Board() {
        this.board = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; i < COLUMNS; j++) {
                this.board[i][j] = '_';
            }
        }
    }

    /**
     * Tells how many pieces are in each column
     * 
     * @param numPiecesInEachColumn number of pieces in each column
     * @param desiredColumn the player's desired column
     * @return the updated amount of the number of pieces in each column
     * 
     */ 
    public static int[] numPiecesInEachColumn (int desiredColumn, int[] numPiecesInEachColumn) {

        int[] updatedNumPiecesInEachColumn = numPiecesInEachColumn;

        updatedNumPiecesInEachColumn[desiredColumn] = updatedNumPiecesInEachColumn[desiredColumn] + 1;

        return updatedNumPiecesInEachColumn;
    }

    /**
     * Updates the board
     * 
     * @param numPiecesInEachColumn number of pieces in each column
     * @param desiredColumn the column the player desires to place their piece in
     * @param player which player is making the move
     * @return the updated version of boardStateInfo
     * 
     */ 
    public void updateBoardStateInfo(int desiredColumn, char player, int[] numPiecesInEachColumn) {
        int numPiecesInThisColumn = numPiecesInEachColumn[desiredColumn];

        this.board[ROWS - numPiecesInThisColumn][desiredColumn - 1] = player;
    }
    
    /**
     * Prints the column number header and the entire board with moves
     */
    public void printBoard() {
        System.out.println(" ( 1 ) ( 2 ) ( 3 ) ( 4 ) ");
        System.out.println();
        System.out.println("__________________________");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|_" + this.board[i][j] + "_");
            }
            System.out.println("|");
        }
    }  
    
    /**
     * Determines if match is over
     * @return whether or not the match is over
     * 
     */ 
    public boolean matchwon() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(this.board[i][j] != 0) {
                    if(lineCheck(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks if pieces have 3 other pieces in a line with them
     * @param row row at which the check is begun
     * @param column column at which the check is begun
     * @param boardStateInfo state of the board
     * @return whether or not the specific piece has 3 of the same type of piece beside it
     * 
     */
    public static boolean lineCheck(int row, int column) {
        return false;
    }
}
