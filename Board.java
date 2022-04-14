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
    public static final char PLAYER2 = "O";
    
    /** private variable for the two dimensional array of the board */
    private char[][] board;
    
    /** Board object constructor */
    public Board {
        this.board = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; i < COLUMNS; j++) {
                this.board[i][j] = '_';
            }
        }
    }
    
    /**
     * Prints the column number header and the entire board with moves
     */
    public static void printBoard() {
        System.out.println(" ( 1 ) ( 2 ) ( 3 ) ( 4 ) ");
        System.out.println();
        System.out.println("__________________________");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|_" + board[i][j] + "_");
            }
            System.out.println("|");
        }
    }        
}
    
    