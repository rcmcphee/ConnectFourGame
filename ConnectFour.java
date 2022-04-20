import java.util.*;

/** 
 * Takes input from player 1 and player 2 and outputs updated board
 * @author Kyle Albury
 * @author Alex Mize
 * @author Ryan McPhee 
 */
 
public class ConnectFour {
    
    /** Represents the upper bound of the numberInput allowed */
    public static final int UPPER_BOUND = 4;
    
    /** Represents the lower bound of the numberInput allowed */
    public static final int LOWER_BOUND = 0;
    
    /**
     * Prompts user for move input and then prints instructions and board
     * @param args command-line parameters (not used)
     */
    public static void main (String[] args) {
        boolean game = true;
        int moves = 0;
        Board gameBoard = new Board();
        Scanner scnr = new Scanner(System.in);
        while (game) {
            gameBoard.printBoard();
            if (gameBoard.checkTied() == true) {
                System.out.println("You tied");
                game = false;
            } else {
                game = playGame(scnr, moves, gameBoard);
                moves++;
            }

        }  
    }
    
    /**
     * Plays a round of connect four and checks if the game is over
     * @param scnr takes the user input
     * @param moves how many moves there have been
     * @param board The object which holds the connect four board's info
     * @return whether or not the game is over
     */
    public static boolean playGame (Scanner scnr, int moves, Board board) {
        boolean nIsntAProperInteger = true;
        char player = Board.PLAYER1;

        int n = 0;
        int playerCheck = moves % 2;
        
        if (playerCheck == 0) {
            System.out.println("Player One's Turn");
            player = Board.PLAYER1;
        } else if (playerCheck == 1) {
            System.out.println("Player Two's Turn");
            player = Board.PLAYER2;
        }
        
        while (nIsntAProperInteger == true) {
            System.out.print("Enter desired move (Ex: 1 for column 1): ");
            if (scnr.hasNextInt()) {
                
                n = scnr.nextInt();

                if (n > LOWER_BOUND && n <= UPPER_BOUND) {
                    nIsntAProperInteger = false;
                } else {
                    System.out.println("Enter a number between 1 and 4");
                    continue;
                }   
            }
            else {
                System.out.println("Enter a number between 1 and 4");
                scnr.next();
            }
            if (!nIsntAProperInteger) {
                if (board.checkFull(n) == true) {
                    System.out.println("Column full, please try again");
                    nIsntAProperInteger = true;
                }
            }
        }
        board.updateBoard(n, player);
        if (board.matchWon(n) == true) {
            if (moves % 2 == 0) {
                board.printBoard();
                System.out.println("Congratulations Player 1, you win");
                return false;
            } else if (moves % 2 == 1) {
                board.printBoard();
                System.out.println("Congratulations Player 2, you win");
                return false;
            }
        }
        return true;
    }
}