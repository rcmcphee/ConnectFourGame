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
    public final char PLAYER1 = 'X';
    
    /** constant for the char representing player two's moves */
    public final char PLAYER2 = 'O'; 
    
    /** Number of directions to check for victory */
    public static final int DIRECTIONS = 8;
    
    /** Corresponds to checking in the up direction */
    public static final int UP = 0;
    
    /** Corresponds to checking in the up-right direction */
    public static final int UPRIGHT = 1;
    
    /** Corresponds to checking in the right direction */
    public static final int RIGHT = 2;
    
    /** Corresponds to checking in the down-right direction */
    public static final int DOWNRIGHT = 3;
    
    /** Corresponds to checking in the down direction */
    public static final int DOWN = 4;
    
    /** Corresponds to checking in the down-left direction */
    public static final int DOWNLEFT = 5;
    
    /** Corresponds to checking in the left direction */
    public static final int LEFT = 6;
    
    /** Corresponds to checking in the up-left direction */
    public static final int UPLEFT = 7;
    
    /** Pieces in a row needed to win */
    public static final int WINNUM = 4;
    
    /** private variable for the two dimensional array of the board */
    private char[][] board;
    
    /** private integer array of the number of pieces in each column */
    private int[] piecesInColumns;
    
    /** Board object constructor */
    public Board() {
        this.board = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                this.board[i][j] = '_';
            }
        }
        this.piecesInColumns = new int[COLUMNS];
        for (int i = 0; i < COLUMNS; i++) {
            this.piecesInColumns[i] = 0;
        }
    }

    public int[] getPiecesInColumns() {
        return piecesInColumns;
    }
    
    /**
     * Tells how many pieces are in each column
     * 
     * @param numPiecesInEachColumn number of pieces in each column
     * @param desiredColumn the player's desired column
     * @return the updated amount of the number of pieces in each column
     * 
     */ 
    public static int[] updateNumPiecesInEachColumn (int desiredColumn, int[] numPiecesInEachColumn) {

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
     * @return the updated version of the board
     * 
     */ 
    public void updateBoard(int desiredColumn, char player, int[] numPiecesInEachColumn) {
        desiredColumn--;
        int numPiecesInThisColumn = numPiecesInEachColumn[desiredColumn];
  
        this.board[(ROWS - 1) - numPiecesInThisColumn][desiredColumn] = player;
        updateNumPiecesInEachColumn(desiredColumn, numPiecesInEachColumn);

    }
    
    public boolean checkFull(int column, int[] numPiecesInEachColumn) {
        column--;
        if (numPiecesInEachColumn[column] == 4) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Prints the column number header and the entire board with moves
     */
    public void printBoard() {
        System.out.println(" ( 1 ) ( 2 ) ( 3 ) ( 4 ) ");
        System.out.println("_________________________");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|     ");
            }
            System.out.println("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|  " + this.board[i][j] + "  ");
            }
            System.out.println("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|_____");
            }
            System.out.println("|");
        }
        System.out.println();
    } 
    
    /**
     * Determines if match is over
     * @return whether or not the match is over
     * 
     */ 
    public boolean matchWon(int[] numPiecesInEachColumn, int desiredColumn) {

        int numPiecesInThisColumn = numPiecesInEachColumn[desiredColumn];

        if (lineCheck(ROWS - numPiecesInThisColumn, desiredColumn - 1)) {
            return true;
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
    public boolean lineCheck(int row, int column) {
        boolean winStatus = false;
        int checkDirection = 0;
        while (checkDirection < DIRECTIONS && !winStatus) {
            try {
                if (checkDirection == UP) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row + i][column]) {
                            checkDirection = UPRIGHT;
                        }
                    }
                    if (checkDirection != UPRIGHT) {
                        winStatus = true;
                    }
                } else if (checkDirection == UPRIGHT) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row + i][column + i]) {
                            checkDirection = RIGHT;
                        }
                    }
                    if (checkDirection != RIGHT) {
                        winStatus = true;
                    }
                } else if (checkDirection == RIGHT) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row][column + i]) {
                            checkDirection = DOWNRIGHT;
                        }
                    }
                    if (checkDirection != DOWNRIGHT) {
                        winStatus = true;
                    }
                } else if (checkDirection == DOWNRIGHT) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row - i][column + i]) {
                            checkDirection = DOWN;
                        }
                    }
                    if (checkDirection != DOWN) {
                        winStatus = true;
                    }
                } else if (checkDirection == DOWN) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row - i][column]) {
                            checkDirection = DOWNLEFT;
                        }
                    }
                    if (checkDirection != DOWNLEFT) {
                        winStatus = true;
                    }
                } else if (checkDirection == DOWNLEFT) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row - i][column - i]) {
                            checkDirection = LEFT;
                        }
                    }
                    if (checkDirection != LEFT) {
                        winStatus = true;
                    }
                } else if (checkDirection == LEFT) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row][column - i]) {
                            checkDirection = UPLEFT;
                        }
                    }
                    if (checkDirection != UPLEFT) {
                        winStatus = true;
                    }
                } else if (checkDirection == UPLEFT) {
                    for (int i = 1; i < WINNUM; i++) {
                        if (this.board[row][column] != this.board[row + i][column - i]) {
                            checkDirection = DIRECTIONS;
                        }
                    }
                    if (checkDirection != DIRECTIONS) {
                        winStatus = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                checkDirection++;
            }
        }
        return winStatus;
    }
}
