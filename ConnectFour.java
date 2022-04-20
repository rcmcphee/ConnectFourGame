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
    
    /** Represents the lower bound of how many pieces must be connected to win */
    public static final int LOWER_BOUND_VICTORY = 3;
    
    /** Represents the lower bound of how many pieces must be connected to win */
    public static final int FLAG_NO_UPPER_BOUND = -1;

    /** Represents no current victory or tie in the game*/
    public static final int FLAG_NOT_OVER = 0;

    /** Represents player one winning*/
    public static final int FLAG_PLAYER_ONE_VICTORY = 1;

    /** Represents player two winning*/
    public static final int FLAG_PLAYER_TWO_VICTORY = 2;

    /** Represents nobody winning*/
    public static final int FLAG_TIE = 3;

    
    /**
     * Prompts user for move input and then prints instructions and board
     * @param args command-line parameters (not used)
     */
    public static void main (String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to connect four.");
        System.out.println("Enter n to start a new game");
        System.out.println("Enter v to check victory statistics");
        System.out.println("Enter q to exit the game");
        System.out.print("Please enter your choice: ");

        int numPlayer1Wins = 0;
        int numPlayer2Wins = 0;
        int numTies = 0;

        String operatorChoiceStr = scnr.next();

        while (!(operatorChoiceStr.equalsIgnoreCase("n") 
            || operatorChoiceStr.equalsIgnoreCase("v")
            || operatorChoiceStr.equalsIgnoreCase("q"))) {

            System.out.print("Please enter a valid choice: ");
            operatorChoiceStr = scnr.next();
        }
        char operatorChoice = operatorChoiceStr.charAt(0);

        int victoryStatus = FLAG_TIE;

        while (operatorChoice != 'q' && operatorChoice != 'Q') {
            
            switch (operatorChoice) {
                case 'n':
                case 'N':
                    System.out.println();
                    victoryStatus = initializeGame(scnr);
                    if (victoryStatus == FLAG_PLAYER_ONE_VICTORY) {
                        numPlayer1Wins++;
                    } else if (victoryStatus == FLAG_PLAYER_TWO_VICTORY) {
                        numPlayer2Wins++;
                    } else if (victoryStatus == FLAG_TIE) {
                        numTies++;
                    }
                    break;
            
                case 'v':
                case 'V':
                    System.out.println();
                    System.out.println("Number of player 1 victories: " + numPlayer1Wins);
                    System.out.println("Number of player 2 victories: " + numPlayer2Wins);
                    System.out.println("Number of ties: " + numTies);
                    System.out.println();
                    break;
                default: 
                    break;
            }

            System.out.println();
            System.out.println("Enter n to start a new game");
            System.out.println("Enter v to check victory statistics");
            System.out.println("Enter q to exit the game");
            System.out.print("Please enter your choice: ");

            operatorChoiceStr = scnr.next();

            while (!(operatorChoiceStr.equalsIgnoreCase("n") 
                || operatorChoiceStr.equalsIgnoreCase("v")
                || operatorChoiceStr.equalsIgnoreCase("q"))) {

                System.out.print("Please enter a valid choice: ");
                operatorChoiceStr = scnr.next();
            }

            operatorChoice = operatorChoiceStr.charAt(0);
        }
    }

    /**
     * Initializes and round of connect four
     * @param scnr takes the user input
     * @return the status of how the game ended
     */
    public static int initializeGame (Scanner scnr) {
        String promptWinAmt = 
            "Enter many pieces must be connected in a row order to win the game: ";
        String errorMessageBadRangeWinAmt = "Please enter a number greater than three";
        String errorMessageNotANumberWinAmt = "Please enter a number";

        int numPiecesToWin = numberInput(scnr, promptWinAmt, errorMessageBadRangeWinAmt, 
            errorMessageNotANumberWinAmt, LOWER_BOUND_VICTORY, FLAG_NO_UPPER_BOUND, 0);

        int boardLength = 2 * numPiecesToWin;

        int gameStatus = 0;
        int moves = 0;
        Board gameBoard = new Board(boardLength, boardLength, numPiecesToWin);

        while (gameStatus == FLAG_NOT_OVER) {
            gameBoard.printBoard();
            if (gameBoard.checkTied() == true) {
                System.out.println("You tied");
                return FLAG_TIE;
            } else {
                gameStatus = playRound(scnr, moves, gameBoard, boardLength);
                moves++;
            }
        }
        return gameStatus;
    }
    
    /**
     * Plays a round of connect four and checks if the game is over
     * @param scnr takes the user input
     * @param moves how many moves there have been
     * @param board The object which holds the connect four board's info
     * @param boardLength length of one side of the board
     * @return the status of how the game ended
     */
    public static int playRound (Scanner scnr, int moves, Board board, int boardLength) {
        char player = Board.PLAYER1;

        int playerCheck = moves % 2;
        
        if (playerCheck == 0) {
            System.out.println("Player One's Turn");
            player = Board.PLAYER1;
        } else if (playerCheck == 1) {
            System.out.println("Player Two's Turn");
            player = Board.PLAYER2;
        }
        
        boolean badMove = true;
        int playerMove = 0;
        String whatToPromptForMove = "Enter desired move (Ex: 1 for column 1): ";
        String errorMessageBadRangeMove = "Enter a number between 1 and " + boardLength;
        String errorMessageNotANumber = "Enter a number";
        int lowerBoundMove = 0;
        int upperBoundMove = boardLength + 1;
        while(badMove) {
            playerMove = numberInput(scnr, whatToPromptForMove, errorMessageBadRangeMove, 
                errorMessageNotANumber, lowerBoundMove, upperBoundMove, 0);
            if (board.checkFull(playerMove) == true) {
                System.out.println("Column full, please try again");
            } else {
                badMove = false;
            }
        }

        board.updateBoard(playerMove, player);
        if (board.matchWon(playerMove) == true) {
            if (moves % 2 == 0) {
                board.printBoard();
                System.out.println("Congratulations Player 1, you win");
                return FLAG_PLAYER_ONE_VICTORY;
            } else if (moves % 2 == 1) {
                board.printBoard();
                System.out.println("Congratulations Player 2, you win");
                return FLAG_PLAYER_TWO_VICTORY;
            }
        }
        return FLAG_NOT_OVER;
    }
    
    /**
     * Finds out if input is a proper integer and reprompts if not
     * Returns the input if it is a proper integer
     * If upperbound is negative, then there is no upper bound
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
    public static int numberInput(Scanner scnr, String whatToPromptFor, 
        String errorMessageBadRange, String errorMessageNotANumber, int lowerBound, 
        int upperBound, int offset) {
            
        boolean nIsntAProperInteger = true; 
        int n = 0;

        if (upperBound < 0) {
            while (nIsntAProperInteger) {
                System.out.print(whatToPromptFor);
                if (scnr.hasNextInt()) {
                    
                    n = scnr.nextInt();
    
                    if (n > lowerBound) {
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
}