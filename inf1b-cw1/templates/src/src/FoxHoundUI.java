import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Objects;
import java.util.Arrays;
import java.nio.file.Path;



/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 4;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Save Game\n3. Load Game\n4. Exit\n\nEnter 1 - 4:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to load the program. */
    public static final int MENU_SAVE = 2;
    /** Menu entry to load the program. */
    public static final int MENU_LOAD = 3;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;


    public static void displayBoard(String[] players, int dimension) {

        String firstLine = printLetters(dimension);

        if (dimension < 10) {
            for (int i = 0; i <= (dimension + 1); i++) {
                if (i == 0){ // Print the letters for the first line.
                    System.out.println("  " + firstLine + "  \n");
                } else if (i == (dimension + 1)) {
                    System.out.println("\n  " + firstLine + "  ");
                } else {
                    printFoxHound(players, dimension, (i-1));
                }
            }
        } else {
            for (int i = 0; i <= (dimension + 1); i++) {
                if (i == 0){ // Print the letters for the first line.
                    System.out.println("   " + firstLine + "   \n");
                } else if (i == (dimension + 1)) {
                    System.out.println("\n   " + firstLine + "  ");
                } else {
                    printFoxHound(players, dimension, (i-1));
                }
            }
        }
    }

    public static void printFoxHound(String[] players, int dimension, int count){

        // Setting all initial values to zero.
        String[][] board = new String[dimension][dimension];

        // Store all alphabets needed for size of the board in an array.
        char[] letter = new char[dimension];

        // set positions of players based on number of letter and number
        int[] letterPosition = new int[players.length];
        int[] rowPosition = new int[players.length];

        for(int i = 0; i < letter.length;i++) {
            letter[i] = (char) ('A' + i);
        }

        for(int i = 0; i < players.length;i++) {
            letterPosition[i] = (players[i].charAt(0) - 64);
            rowPosition[i] = Integer.parseInt(players[i].substring(1)); // To get the row position of fox and hounds.
        }

        // Set the initial position of whole game board as dots by using a 2D Array.
        for(int i = 0; i < dimension;i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = ".";
            }
        }

        // Set the position of Hounds.
        for(int i = 0; i < letterPosition.length;i++) {
            if (rowPosition[i] >= 1 && letterPosition[i] >= 1) {
                board[rowPosition[i] - 1][letterPosition[i] - 1] = "H";
            }
        }

        // Set position of Fox.
        board[rowPosition[rowPosition.length -1]-1][letterPosition[letterPosition.length -1]-1]="F";

        if(dimension < 10){
            System.out.print((count+1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(board[count][j]);
            }
            System.out.print(" "+(count+1)+"\n");
        } else if ((dimension >= 10) && count < 9){
            System.out.print("0"+(count+1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(board[count][j]);
            }
            System.out.print(" "+"0"+(count+1)+"\n");
        } else {
            System.out.print((count+1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(board[count][j]);
            }
            System.out.print(" "+(count+1)+"\n");
        }
    }

    public static String printLetters (int dimension){
        String alphabets = "";
        for(int i = 0; i < dimension; i++){
            alphabets = alphabets +  (char) ('A' + i);
        }
        return alphabets;
    }

    public static String[] positionQuery(int dim, Scanner stdin){

        String[] arrayCoord = new String[2];

        String last = String.valueOf((char)('A' + (dim-1)) + Integer.toString(dim)); // To get the last coordinate of the board.

        System.out.println("Provide origin and destination coordinates.\nEnter two positions between A1-" + last + ":\n ");
        String coord = stdin.nextLine();
        String[] splitStrings = coord.split(" ");

        String origin = splitStrings[0];
        String destination = splitStrings[1];


        // Get letter and number from the original coordinate.
        char rowOrigin = origin.charAt(0);
        int numOrigin = Integer.parseInt(origin.substring(1));

        // Get letter and number from the final destination.
        char rowDestination = destination.charAt(0);
        int numDestination = Integer.parseInt(origin.substring(1));

        // Check if original position and final destination exist on board.
        char maxLetter = (char)('A' + (dim-1));

        if('A' <= rowOrigin && rowOrigin <= 'H' && 'A' <= rowDestination && rowDestination <='H'&& rowDestination <= maxLetter &&
                rowOrigin <= maxLetter && numOrigin <=dim && numOrigin>=1 && numDestination <= dim && numDestination>=1){
            // Store coordinates to be return.
            arrayCoord[0] = origin;
            arrayCoord[1] = destination;

        } else {
            System.err.println("These are not valid coordinates");
        }

        return arrayCoord;
    }



    /**
     * Print the main menu and query the user for an entry selection.
     * 
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */
    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }

    public static Path fileQuery(Scanner result){
        System.out.println("Enter file path:");
        result.useDelimiter("\\Z");
        String results = result.next();
        Path path = Paths.get(results);

        if(result.equals(null)){
            throw new NullPointerException();
        }
        return path;
    }


}