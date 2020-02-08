import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possiimport java.util.Arrays;ble dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    public static String displayBoard(String[] players, int dimension) {
        char[] alphabets = new char[dimension];
        for (int count = 0; count < dimension; count++) {
            alphabets[count] = (char) ('A' + count);
        }

        String var = "";
        int length = dimension + 4;
        for (int i = 0; i < length; i++) {
            if ((i == 0 || i == (length - 1)) && (dimension < 10)) {
                var = ("  " + printLetters(dimension) + "  " +
                        "\n" +
                        printRest(dimension) +
                        "\n" +
                        "  " + printLetters(dimension) + "  ");
            } else {
                var = ("   " + printLetters(dimension) + "   " +
                        "\n" +
                        printRest(dimension) +
                        "\n" +
                        "   " + printLetters(dimension) + "   ");
            }

        }
        return var;

    }

    public static String printLetters (int dimension){
        String alphabets = "";
        for(int i = 0; i < dimension; i++){
            alphabets = alphabets +  (char) ('A' + i);
        }
        return alphabets;
    }

    public static int printRest (int dimension){
        int lines = 0;
        String dots = ".";
        for(int count = 0; count < dimension; count++){
            lines = (count+1);
        }
        return lines;
    }


    public static String[] initialisePositions(int dimension) {

        if ((dimension < MIN_DIM) || (dimension > MAX_DIM)) {                           //Checking that the range of the board is within the range required
            System.err.println("ERROR: The dimension of the board should be between 4 and 26.");
            dimension = DEFAULT_DIM;
        }

        int size = ((int)(Math.floor(dimension / 2)) + 1);
        String[] start = new String[size];

        for (int count = 0; count < (dimension / 2); count++) {
            start[count] = ((char) ('B' + (2 * count))) + "1";                        // initial position of Hounds
        }

        boolean even = (dimension % 2 == 0);
        boolean evenColumn = ((dimension+1)%2 == 0);

        if(even){
            start[size-1] = Character.toString((char) ('A' + size-1)) + dimension;          // initial position of Fox
        } else {
            start[size-1] = Character.toString((char) ('A' + size)) + dimension;
        }

        return start;
    }

    public static void main(String[] args){

        int dimension;
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to change board dimension? (Y/N): ");
        String change = scan.next();
        String changeUpper = change.toUpperCase();                              //If the user input lower case y and n

        if(changeUpper.equals("N")){
            System.out.println("The default dimension of the board is 8x8.");
            dimension = DEFAULT_DIM;
        } else {
            System.out.print("Please enter dimension of board: ");
            dimension = scan.nextInt();
            scan.close();
        }

        System.out.println(Arrays.toString(initialisePositions(dimension)));

    }
}