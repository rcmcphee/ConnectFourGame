/**
 * Connect Four
 * @author Alex Mize
 * @author Kyle Albury
 * @author Ryan McPhee
 */

public class Board {
    
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

    /** The first double digit number*/
    public static final int FIRST_DOUBLE_DIGIT = 10;
    
    /** Pieces in a row needed to win */
    private int winnum;

    /** private variable for the number of rows on the board */
    private int rows;
    
    /** private variable for the number of columns on the board */
    private int columns;
    
    /** private variable for the two dimensional array of the board */
    private char[][] board;
    
    /** private integer array of the number of pieces in each column */
    private int[] piecesInColumns;
    
    /** Board object constructor 
     * @param rows number of rows in the board
     * @param columns number of columns in the board
     * @param winnum number of pieces in a row needed to win
    */
    public Board(int rows, int columns, int winnum) {
        this.rows = rows;
        this.columns = columns;
        this.winnum = winnum;

        this.board = new char[this.rows][this.columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.board[i][j] = '_';
            }
        }
        this.piecesInColumns = new int[this.rows];
        for (int i = 0; i < this.columns; i++) {
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
  
        this.board[(this.rows - 1) - numPiecesInThisColumn][desiredColumn] = player;
        updateNumPiecesInEachColumn(desiredColumn);

    }
    
    /**
     * Checks if a particular column is full
     * @param column the column that is being checked for fullness
     * @return whether or not the particular column is full
     */
    public boolean checkFull(int column) {
        column--;
        if (piecesInColumns[column] == this.rows) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints the column number header and the entire board with moves
     */
    public void printBoard() {
        for (int i = 1; i <= this.columns; i++) {
            if (i >= FIRST_DOUBLE_DIGIT) {
                System.out.print(" (" + String.valueOf(i).charAt(0) 
                    + " " + String.valueOf(i).charAt(1) + ")");
            } else {
                System.out.print(" ( " + i + " )");
            }
        }

        System.out.println();

        for (int i = 1; i <= this.columns; i++) {
            System.out.print("______");
        }

        System.out.println();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print("|     ");
            }
            System.out.println("|");
            for (int j = 0; j < this.columns; j++) {
                System.out.print("|  " + this.board[i][j] + "  ");
            }
            System.out.println("|");
            for (int j = 0; j < this.columns; j++) {
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
        if (endPoint(this.rows - numPiecesInThisColumn, latestColumn - 1)) {
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
            if (piecesInColumns[i] != this.rows) {
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
                   for (int i = 0; i < winnum; i++) {
                       if (row + i < winnum && column - i >= 0) {
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
           if (checkDirection == RIGHT) {
               if (this.board[row][column] == this.board[row][column + 1]
                   && this.board[row][column] == this.board[row][column - 1] ) {
                   for (int i = 0; i < winnum; i++) {
                       if (column + i < winnum) {
                           if (this.board[row][column] == this.board[row][column + i]) {
                               endPointRowSideways = row;
                               endPointColSideways = column + i;
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
                   for (int i = 0; i < winnum; i++) {
                       if (row + i < winnum && column + i < winnum) {
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
     * @param row row at which the check is begun
     * @param column column at which the check is begun
     * @return whether or not the specific piece has 3 of the same type of piece beside it
     * 
     */
    public boolean lineCheck(int row, int column) {
        boolean winStatus = false;
        int checkDirection = 0;
        while (checkDirection < DIRECTIONS && !winStatus) {
            try {
                if (checkDirection == UP) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row + i][column]) {
                            checkDirection = UPRIGHT;
                        }
                    }
                    if (checkDirection != UPRIGHT) {
                        winStatus = true;
                    }
                } else if (checkDirection == UPRIGHT) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row + i][column + i]) {
                            checkDirection = RIGHT;
                        }
                    }
                    if (checkDirection != RIGHT) {
                        winStatus = true;
                    }
                } else if (checkDirection == RIGHT) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row][column + i]) {
                            checkDirection = DOWNRIGHT;
                        }
                    }
                    if (checkDirection != DOWNRIGHT) {
                        winStatus = true;
                    }
                } else if (checkDirection == DOWNRIGHT) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row - i][column + i]) {
                            checkDirection = DOWN;
                        }
                    }
                    if (checkDirection != DOWN) {
                        winStatus = true;
                    }
                } else if (checkDirection == DOWN) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row - i][column]) {
                            checkDirection = DOWNLEFT;
                        }
                    }
                    if (checkDirection != DOWNLEFT) {
                        winStatus = true;
                    }
                } else if (checkDirection == DOWNLEFT) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row - i][column - i]) {
                            checkDirection = LEFT;
                        }
                    }
                    if (checkDirection != LEFT) {
                        winStatus = true;
                    }
                } else if (checkDirection == LEFT) {
                    for (int i = 1; i < winnum; i++) {
                        if (this.board[row][column] != this.board[row][column - i]) {
                            checkDirection = UPLEFT;
                        }
                    }
                    if (checkDirection != UPLEFT) {
                        winStatus = true;
                    }
                } else if (checkDirection == UPLEFT) {
                    for (int i = 1; i < winnum; i++) {
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
