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
        while (game == true) {
            gameBoard.printBoard();
            playGame(scnr, moves, gameBoard);
            moves++;
            /*if (//Game is won) {
                game = false;
            }
            if (moves == 15) {
                //Tie game method
                game = false;
            }*/
        }  
    }
    
    /**
     * Gets column number that user wants to play in
     * @param scnr takes the user input
     * @return n number which is the column the user wants to play in
     */
    public static void playGame (Scanner scnr, int moves, Board board) {
        boolean nIsntAProperInteger = true;
        char player = board.PLAYER1;

        int n = 0;
        int playerCheck = moves % 2;
        
        if (playerCheck == 0) {
            System.out.println("Player One's Turn");
            player = board.PLAYER1;
        } else if (playerCheck == 1) {
            System.out.println("Player Two's Turn");
            player = board.PLAYER2;
        }

        while (nIsntAProperInteger) {
            System.out.print("Enter desired move (Ex: 1 for column 1): ");
            if (scnr.hasNextInt()) {
                
                n = scnr.nextInt();

                if (n > LOWER_BOUND && n < UPPER_BOUND) {
                    nIsntAProperInteger = false;
                }
                else {
                    System.out.println("Enter a number between 1 and 4");
                }

            }
            else {
                System.out.println("Enter a number between 1 and 4");
                scnr.next();
            }
        }
        
        board.updateBoard(n, player, board.getPiecesInColumns());
    }
}