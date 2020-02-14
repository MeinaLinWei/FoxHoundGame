import java.util.Scanner;
import java.util.Objects;
import java.util.Arrays;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 2;

    public static void displayBoard(String[] players, int dimension) {

        String firstLine = printLetters(dimension);

        if (dimension < 10) {
            for (int i = 0; i <= (dimension + 1); i++) {
                if (i == 0 || i == (dimension + 1)) { // Print the letters ABC... for the first line and the last line.
                    System.out.println("\n" + "  " + firstLine + "\n");
                } else {
                    printFoxHound(players, dimension, (i-1));
                }
            }
        } else {
            for (int i = 0; i <= (dimension + 1); i++) {
                if (i == 0 || i == (dimension + 1)) {
                    System.out.println("\n" + "   " + firstLine + "\n");
                } else {
                    printFoxHound(players, dimension, (i-1));
                }
            }
        }
    }

    public static void printFoxHound(String[] players, int dimension, int count){

        // Setting all intial values to zero.
        String[][] board = new String[dimension][dimension];

        // Store all alphabets needed for size of the board in an array.
        char[] letter = new char[dimension];

        // set positions of players based on number of letter and number
        int[] letterPositon = new int[players.length];
        int[] rowPosition = new int[players.length];

        for(int i = 0; i < letter.length;i++) {
            letter[i] = (char) ('A' + i);
        }

        for(int i = 0; i < players.length;i++) {
            letterPositon[i] = (players[i].charAt(0) - 64);
            rowPosition[i] = Integer.parseInt(players[i].substring(1)); // To get the row position of fox and hounds.
        }

        // Set the initial position of whole game board as dots by using a 2D Array.
        for(int i = 0; i < dimension;i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = ".";
            }
        }

        // Set the position of Hounds.
        for(int i = 0; i < letterPositon.length;i++) {
            if (rowPosition[i] >= 1 && letterPositon[i] >= 1) {
                board[rowPosition[i] - 1][letterPositon[i] - 1] = "H";
            }
        }

        // Set position of Fox.
        board[rowPosition[rowPosition.length -1]-1][letterPositon[letterPositon.length -1]-1]="F";

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



    /**
     * Print the main menu and query the user for an entry selection.
     * 
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerExcmainMenuQueryeption if the given Scanner is null
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
}