import java.util.Arrays;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */

public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

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

    // HINT Write your own constants here to improve code readability ...
    // size: store the size of the array for initial positions both hound and fox.
    // start: initial position of hounds and fox


    public static String[] initialisePositions(int dimension) {

        if ((dimension < MIN_DIM) || (dimension > MAX_DIM)) {                           //Checking that the range of the board is within the range required
            System.out.println("The dimension of the board should be between 4 and 26.");
            dimension = DEFAULT_DIM;
        }

        int size = ((int)(Math.floor(dimension / 2)) + 1);
        String[] start = new String[size];

        for (int count = 0; count < (dimension / 2); count++) {
            start[count] = ((char) ('B' + (2 * count))) + "1";                        // initial position of Hounds
        }

        start[size-1] = Character.toString((char) ('A' + size-1)) + dimension;          // initial position of Fox

        return start;
    }

    public static void main(String[] args){

        System.out.println(Arrays.toString(initialisePositions(8)));

    }

}
