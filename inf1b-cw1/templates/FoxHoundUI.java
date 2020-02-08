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

    public static String[] displayBoard(String[] players, int dimension) {

        char[] alphabets = new char[dimension];
        for(int count = 0; count < dimension; count++){
            alphabets[count] = (char)('A' + count);
        }

        String[][] location = new String[dimension][dimension];

        for(int i = 0; i < dimension; i++){
            location[0][i+1] = Character.toString(alphabets[i]);
            location[dimension][i+1] = Character.toString(alphabets[i]);
        }

        String dots = ".";
        for(int j = 0; j < (dimension); j++){

            location[j+1][1] = Integer.toString (j+1);
            location[j+1][dimension] = Integer.toString (j+1);

            location[dimension-1][1] = Integer.toString (dimension);
            location[dimension-1][dimension] = Integer.toString (dimension);

            for (int m = 0; m < dimension; m++){
                location[j+1][m] = ".";
            }
        }

        return location;

        /*String[] ans = {};
        String dots = ".";
        for(int i = 0; i <= (dimension); i++){
            for(int j = 0; j < (dimension-2); j++){
                String[] output1 = {(j+1) + dots.repeat(dimension-2) + (j+1)};
                ans = output1;
            }

            String[] output1 = {(i+1) + dots.repeat(dimension-2) + (i+1)};

        }*/

        /*String[] defaultPlayers = new String[]{"B1","D1","F1","H1","E8"};
        String defaultOutput =
                "  ABCDEFGH  \n" +
                "\n" +
                "1 .H.H.H.H 1\n" +
                "2 ........ 2\n" +
                "3 ........ 3\n" +
                "4 ........ 4\n" +
                "5 ........ 5\n" +
                "6 ........ 6\n" +
                "7 ........ 7\n" +
                "8 ....F... 8\n" +
                "\n" +
                "  ABCDEFGH  ";

        return (Arrays.toString(defaultOutput));


         */
    }

    /*public static void main(String[] args){

        int dimension;
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to change board dimension? (Y/N): ");
        String change = scan.next();
        String changeUpper = change.toUpperCase();                              //If the user input lower case y and n

        if(changeUpper.equals("N")){
            System.out.println("The default dimension of the board is 8x8.");
            dimension = 8;
        } else {
            System.out.print("Please enter dimension of board: ");
            dimension = scan.nextInt();
            scan.close();
        }

        if ((dimension < 4) || (dimension > 26)) {                           //Checking that the range of the board is within the range required
            System.err.println("ERROR: The dimension of the board should be between 4 and 26.");
            dimension = 8;
        }

        int size = ((int)(Math.floor(dimension / 2)) + 1);
        String[] start = new String[size];

        for (int count = 0; count < (dimension / 2); count++) {
            start[count] = ((char) ('B' + (2 * count))) + "1";                        // initial position of Hounds
        }

        if(dimension % 2 == 0){
            start[size-1] = Character.toString((char) ('A' + size-1)) + dimension;          // initial position of Fox
        } else {
            start[size-1] = Character.toString((char) ('A' + size)) + dimension;
        }

        System.out.println(Arrays.toString(displayBoard(start, dimension)));

    }*/

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
}







