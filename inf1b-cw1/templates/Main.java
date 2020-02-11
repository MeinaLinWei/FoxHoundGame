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





    public static void displayBoard(String[] players, int dimension) {

        String firstLine = printLetters(dimension);

        // Put all letters in an array. They are later compared for the position of the hounds and the fox.
        String[][] arrayLetters = new String[dimension][dimension];
        for(int j = 0; j < dimension; j++){
            for(int i = 0; i < dimension; i++){
                arrayLetters[j][i] = (firstLine.charAt(i) + Integer.toString(j+1));
            }
        }

        if(dimension < 10){
            for (int i = 0; i <= (dimension+1); i++) {
                if (i == 0 || i == (dimension+1)){
                    System.out.println("  " + firstLine);
                } else {
                    printRest(arrayLetters, players, dimension,i);
                }
            }
        } else {
            for (int i = 0; i <= (dimension+1); i++) {
                if (i == 0 || i == (dimension+1)) {
                    System.out.println("   " + firstLine);
                } else {
                    printRest(arrayLetters, players, dimension, i);
                }
            }
        }

    }





    public static String printLetters (int dimension){
        String alphabets = "";
        for(int i = 0; i < dimension; i++){
            alphabets = alphabets +  (char) ('A' + i);
        }
        return alphabets;
    }





    public static void printRest (String[][] arrayLetters, String[] players, int dimension, int count){
        String dots = ".";

        for(int i = 0; i <  players.length; i++){
            if((players[i]) == arrayLetters[i][i]){
                System.out.println((i+1) + " " + dots.repeat(i-1) + "H" + dots.repeat(dimension-i-2)+ " " + (i+1));
            }
        }

        if (dimension < 10) {
            System.out.println((count) + " " + dots.repeat(dimension) + " " + (count));
        } else if (dimension >= 10 && count < 10){
            System.out.println("0"+(count) + " " + dots.repeat(dimension) + " " + "0"+(count));
        } else {
            System.out.println((count) + " " + dots.repeat(dimension) + " " + (count));
        }






        /*String[] arrayLetters = new String[dimension];
        if (dimension < 10) {
            for(int i = 0; i < (dimension); i++){
                arrayLetters[i] = (firstLine.charAt(i) + Integer.toString(count+1));
            }
            if ((players[count]) == arrayLetters[count]){
                System.out.println((count) + " " + dots.repeat(count-1) + "H" + dots.repeat(dimension-count-2)+ " " + count);
            } else {
                System.out.println((count) + " " + dots.repeat(dimension) + " " + (count));
            }
        } else if (dimension >= 10 && count < 10){
            if ((players[count]) == arrayLetters[count]){
                System.out.println("0"+(count) + " " + dots.repeat(count-1) + "H" + dots.repeat(dimension-count-2)+ " " + "0"+(count));
            } else {
                System.out.println("0"+(count) + " " + dots.repeat(dimension) + " " + "0"+(count));
            }
        } else {
            System.out.println((count) + " " + dots.repeat(dimension) + " " + (count));
        }*/

    }






    public static String[] initialisePositions(int dimension) {

        // array size: total number of hounds and fox.
        int size = ((int)(Math.floor(dimension / 2)) + 1);
        String[] boardCoordinates = new String[size];

        // calculate position of the middle column.
        int middleColumn = ((dimension/2)+1);
        int[] pos = new int[size];


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





    public static void main(String[] args) {
        int dimension;
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to change board dimension? (Y/N): ");
        String change = scan.next();

        //If the user input lowercase 'y' and 'n'.
        String changeUpper = change.toUpperCase();

        if(changeUpper.equals("N")){
            System.out.println("The default dimension of the board is 8x8.");
            dimension = DEFAULT_DIM;
        } else {
            System.out.print("Please enter dimension of board: ");
            dimension = scan.nextInt();
            scan.close();
        }

        // check that board is within the range required.
        if ((dimension < MIN_DIM) || (dimension > MAX_DIM)) {
            System.err.println("ERROR: The dimension of the board should be between 4 and 26.\nDefault dimension of 8x8 is used.");
            dimension = DEFAULT_DIM;
        }

        // locations: coodinates of the hound and the fox in the board according to the dimension that the user has entered
        String[] locations = new String[dimension];
        locations = (initialisePositions(dimension));

        System.out.println(Arrays.toString(locations) + "\n");
        displayBoard(locations, dimension);

    }
}