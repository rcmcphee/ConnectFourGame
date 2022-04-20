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

    /**
     * Returns an array which details how many pieces are in each column
     * @return the number of pieces in each column
     */
    public int[] getPiecesInColumns() {
        return piecesInColumns;
    }
    
    /**
     * Tells how many pieces are in each column
     * @param desiredColumn the player's desired column
     * @return the updated amount of the number of pieces in each column
     * 
     */ 
    public int[] updateNumPiecesInEachColumn (int desiredColumn) {

        int[] updatedNumPiecesInEachColumn = piecesInColumns;

        updatedNumPiecesInEachColumn[desiredColumn] 
            = updatedNumPiecesInEachColumn[desiredColumn] + 1;

        return updatedNumPiecesInEachColumn;
    }

    /**
     * Updates the board
     * @param desiredColumn the column the player desires to place their piece in
     * @param player which player is making the move
     * 
     */ 
    public void updateBoard(int desiredColumn, char player) {
        desiredColumn--;
        int numPiecesInThisColumn = piecesInColumns[desiredColumn];
  
        this.board[(ROWS - 1) - numPiecesInThisColumn][desiredColumn] = player;
        updateNumPiecesInEachColumn(desiredColumn);

    }
    
    /**
     * Checks if a particular column is full
     * @param column the column that is being checked for fullness
     * @return whether or not the particular column is full
     */
    public boolean checkFull(int column) {
        column--;
        if (piecesInColumns[column] == ROWS) {
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
     * @param latestColumn The column which has has the most recent
     * piece put in it
     * @return whether or not the match is over
     */ 
    public boolean matchWon(int latestColumn) {
        int numPiecesInThisColumn = piecesInColumns[latestColumn - 1];
        if (endPoint(ROWS - numPiecesInThisColumn, latestColumn - 1)) {
            return true;
        }
        return false;
    }
    
    /**
     * Determines if match is over (with a tie)
     * @return whether or not the match is tied
     */ 
    public boolean checkTied() {
        boolean tied = true;
        
        for (int i = 0; i < piecesInColumns.length; i++) {
            if (piecesInColumns[i] != ROWS) {
                tied = false;
            }
        }
        return tied;
    }
    
    /**
     * checks if pieces have 3 other pieces in a line with them
     * Reads the position of a piece and the position of the endpoint of 
     * a line that a piece makes with pieces of the same kind
     * into line check
     * @param row row at which the check is begun
     * @param column column at which the check is begun
     * @return whether or not the specific piece has 3 of the same type of piece beside it
     * 
     */
    public boolean endPoint(int row, int column) {
        int checkDirection = UPRIGHT;
        int endPointRowUpDiag = -1;
        int endPointColUpDiag = -1;
        int endPointRowDownDiag = -1;
        int endPointColDownDiag = -1;
        int endPointRowSideways = -1;
        int endPointColSideways = -1;

        try {
            if (checkDirection == UPRIGHT) {
                if (this.board[row][column] == this.board[row - 1][column + 1]
                    && this.board[row][column] == this.board[row + 1][column - 1] ) {
                    for (int i = 0; i < WINNUM; i++) {
                        if (row + i < WINNUM && column - i >= 0) {
                            if (this.board[row][column] == this.board[row + i][column - i]) {
                                endPointRowUpDiag = row + i;
                                endPointColUpDiag = column - i;
                            }
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            checkDirection = RIGHT;
        }

        checkDirection = RIGHT;

        try {
            System.out.println("here5");
            if (checkDirection == RIGHT) {
                System.out.println("here4");
                if (this.board[row][column] == this.board[row][column + 1]
                    && this.board[row][column] == this.board[row][column - 1] ) {
                    System.out.println("here3");
                    for (int i = 0; i < WINNUM; i++) {
                        if (column + i < WINNUM) {
                            System.out.println("here2");
                            if (this.board[row][column] == this.board[row][column + i]) {
                                endPointRowSideways = row;
                                endPointColSideways = column + i;
                                System.out.println("here1");
                            }
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            checkDirection = DOWNRIGHT;
        }

        checkDirection = DOWNRIGHT;

        try {
            if (checkDirection == DOWNRIGHT) {
                if (this.board[row][column] == this.board[row + 1][column + 1]
                    && this.board[row][column] == this.board[row - 1][column - 1] ) {
                    for (int i = 0; i < WINNUM; i++) {
                        if (row + i < WINNUM && column + i < WINNUM) {
                            if (this.board[row][column] == this.board[row + i][column + i]) {
                                endPointRowDownDiag = row + i;
                                endPointColDownDiag = column + i;
                            }
                        }
                    }
                    checkDirection++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            checkDirection++;
        } 
        
        if (lineCheck(row, column)) {
            return true;
        }
        if (endPointColDownDiag >= 0) {
            if (lineCheck(endPointRowDownDiag, endPointColDownDiag)) {
                return true;
            }
        }
        if (endPointColSideways >= 0) {
            if (lineCheck(endPointRowSideways, endPointColSideways)) {
                return true;
            }
        }
        if (endPointColUpDiag >= 0) {
            if (lineCheck(endPointRowUpDiag, endPointColUpDiag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if pieces have 3 other pieces in a line with them 
     * this does not account for pieces placed into a line 
     * which has four in a row, but where the new piece is not
     * placed at the endpoints of that line
     * @param row row at which the check is begun
     * @param column column at which the check is begun
     * @return whether or not the specific piece has 3 of the same type of piece beside it
     * 
     */
    public boolean lineCheck(int row, int column) {
        boolean winStatus = false;
        int checkDirection = UP;
        
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
