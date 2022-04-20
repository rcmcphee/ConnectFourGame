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
     * Finds out if input is a proper integer and reprompts if not
     * Returns the input if it is a proper integer
     * 
     * @param scnr the scanner for new inputs
     * @param whatToPromptFor what the while loop reprompts a user for
     * @param errorMessageBadRange error message for bad number
     * @param errorMessageNotANumber error message for non-number value
     * @param lowerBound the lower bound for determining bad numbers
     * @param upperBound upper bound for determining bad numbers
     * @param offset offset of returned number
     * @return n the number
     * 
     */ 
    public static int numberInput(Scanner scnr, String whatToPromptFor, String errorMessageBadRange, String errorMessageNotANumber, int lowerBound, int upperBound, int offset) {
        boolean nIsntAProperInteger = true; 
        int n = 0;

        while (nIsntAProperInteger) {
            System.out.print(whatToPromptFor);
            if (scnr.hasNextInt()) {
                
                n = scnr.nextInt();

                if (n > lowerBound && n < upperBound) {
                    nIsntAProperInteger = false;
                }
                else {
                    System.out.println(errorMessageBadRange);
                }

            }
            else {
                System.out.println(errorMessageNotANumber);
                scnr.next();
            }
        }
        return n + offset;
    } 