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
        
        Scanner scnr = new Scanner(System.in);
        numberInput(scnr);
    }
    
    /**
     * Gets column number that user wants to play in
     * @param scnr takes the user input
     * @return n number which is the column the user wants to play in
     */
    public static int numberInput (Scanner scnr) {
        boolean nIsntAProperInteger = true; 
        int n = 0;

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
        return n;
    }
}