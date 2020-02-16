import java.util.Arrays;
import java.util.Scanner;

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
    // boardCoordinates: position of hounds and fox.



    public static String[] initialisePositions(int dimension) {

        // array size: total number of hounds and fox.
        int size = ((int)(Math.floor(dimension / 2)) + 1);
        String[] boardCoordinates = new String[size];

        // calculate position of the middle column.
        int middleColumn = ((dimension/2)+1);
        int[] pos = new int[size];

        // check that board is within the range required.
        if ((dimension < MIN_DIM) || (dimension > MAX_DIM)) {
            System.err.println("ERROR: The dimension of the board should be between 4 and 26.");
            dimension = DEFAULT_DIM;
        }

        // initial position of Hounds.
        for (int count = 0; count < (dimension / 2); count++) {
            boardCoordinates[count] = ((char) ('B' + (2 * count))) + "1";
        }

        // initial position of Fox.
        if(dimension % 2 == 0){
            if((middleColumn % 2) == 0){
                boardCoordinates[dimension/2] = Character.toString((char) ('A' + middleColumn-2)) + dimension;
            } else {
                boardCoordinates[dimension/2] = Character.toString((char) ('A' + middleColumn -1)) + dimension;
            }
        } else {
            if((middleColumn % 2) == 0){
                boardCoordinates[dimension/2] = Character.toString((char) ('A' + middleColumn - 1)) + dimension;
            } else {
                boardCoordinates[dimension/2] = Character.toString((char) ('A' + middleColumn)) +dimension;
            }
        }

        return boardCoordinates;
    }

    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String destination){

        // Get the letter part of the initial position.
        char rowOrigin = origin.charAt(0);
        // Get the number part of the initial position.
        int numOrigin = Integer.parseInt(origin.substring(1));

        // Calculate the new letter of the destination.
        char rightUp = (char)(rowOrigin+1);
        char rightDown = (char)(rowOrigin+1);
        char leftUp = (char)(rowOrigin-1);
        char leftDown = (char)(rowOrigin-1);

        // Calculate possible destination.
        String finalRightUp = (rightUp+""+(numOrigin-1));
        String finalRightDown = (rightDown+""+(numOrigin+1)); // fox can move in all four directions.
        String finalLeftUp = (leftUp+""+(numOrigin-1));
        String finalLeftDown = (leftDown+""+(numOrigin+1)); // fox can move in all four directions.

        boolean correctDestination = true;
        if ((isElement(dim, players, origin, destination)) == true){
            switch (figure) {
                case 'F':
                    if ((finalRightUp.equals(destination) || (finalRightDown.equals(destination)) ||
                            (finalLeftUp.equals(destination)) || (finalLeftDown.equals(destination))) == true) {
                        correctDestination = true;
                    } else {
                        correctDestination = false;
                    }
                    break;
                case 'H':
                    if (((finalRightDown.equals(destination)) || (finalLeftDown.equals(destination))) == true) {
                        correctDestination = true;
                    } else {
                        correctDestination = false;
                    }
                    break;
            }
        } else {
            correctDestination = false;
            System.err.println("ERROR: Invalid move. Try again!\n");
        }
        return correctDestination;
    }

    // Check if destination coordinate is on board and if original coordinate is either Hound or Fox
    public static boolean isElement(int dim, String[] players, String origin, String destination){
        boolean result1 = true;
        boolean result2 = true;

        // Get number and letter of final position.
        int numDest = Integer.parseInt(destination.substring(1));
        char rowDest = destination.charAt(0);

        // Check that original position on board contains either Hound or Fox.
        for(int count = 0; count < players.length; count ++){
            if(players[count].equals(origin)){
                result1 = true;
                break; // terminate loop if original position contain either Hound or Fox.
            } else {
                result1 = false;
            }
        }

        // Check final destination coordinates exist on given the dimension of the board.
        if(numDest > dim || rowDest > ((char)('A'+ (dim-1)))){
            result2 =  false;
        } else {
            result2 = true;
        }
        return (result1 && result2); // Check that both conditions are true.
    }

}
