import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /**
     * Default dimension of the game board in case none is specified.
     */
    public static final int DEFAULT_DIM = 8;
    /**
     * Minimum possible dimension of the game board.
     */
    public static final int MIN_DIM = 4;
    /**
     * Maximum possiimport java.util.Arrays;ble dimension of the game board.
     */
    public static final int MAX_DIM = 26;

    /**
     * Symbol to represent a hound figure.
     */
    public static final char HOUND_FIELD = 'H';
    /**
     * Symbol to represent the fox figure.
     */
    public static final char FOX_FIELD = 'F';


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

        // Store all alphabets needed for size of the board in an array.
        char[] letter = new char[dimension];

        // set positions of players based on number of letter and number
        int[] letterPositon = new int[players.length];
        int[] columnPosition = new int[players.length];

        // Setting all intial values to zero.
        String[][] gameBoard = new String[dimension][dimension];

        for(int i = 0; i < letter.length;i++) {
            letter[i] = (char) ('A' + i);
        }

        for(int i = 0; i < players.length;i++) {
            letterPositon[i] = (players[i].charAt(0) - 64);
            columnPosition[i] = Integer.parseInt(players[i].substring(1)); // To get the column position of fox and hounds.
        }

        // Set the initial position of whole game board as dots by using a 2D Array.
        for(int i = 0; i < dimension;i++) {
            for (int j = 0; j < dimension; j++) {
                gameBoard[i][j] = ".";
            }
        }

        // Set the position of Hounds.
        for(int i = 0; i < letterPositon.length;i++) {
            if (columnPosition[i] >= 1 && letterPositon[i] >= 1) {
                gameBoard[columnPosition[i] - 1][letterPositon[i] - 1] = "H";
            }
        }

        // Set position of Fox.
        gameBoard[columnPosition[columnPosition.length -1]-1][letterPositon[letterPositon.length -1]-1]="F";

        if(dimension < 10){
            System.out.print((count+1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(gameBoard[count][j]);
            }
            System.out.print(" "+(count+1)+"\n");
        } else if ((dimension >= 10) && count < 9){
            System.out.print("0"+(count+1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(gameBoard[count][j]);
            }
            System.out.print(" "+"0"+(count+1)+"\n");
        } else {
            System.out.print((count+1) + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(gameBoard[count][j]);
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

        System.out.println(Arrays.toString(locations));
        displayBoard(locations, dimension);

    }
}