import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /**Default dimension of the game board in case none is specified.*/
    public static final int DEFAULT_DIM = 8;

    /**Minimum possible dimension of the game board.*/
    public static final int MIN_DIM = 4;

    /**Maximum possiimport java.util.Arrays;ble dimension of the game board.*/
    public static final int MAX_DIM = 26;

    /**Symbol to represent a hound figure.*/
    public static final char HOUND_FIELD = 'H';

    /**Symbol to represent the fox figure.*/
    public static final char FOX_FIELD = 'F';

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;

    /** Main menu display string. */
    private static final String MAIN_MENU = "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;

    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 2;





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

        // Calculate the new position of possible destination.
        String finalRightUp = (rightUp+""+(numOrigin-1));
        String finalRightDown = (rightDown+""+(numOrigin+1)); // fox can move in all four directions.
        String finalLeftUp = (leftUp+""+(numOrigin-1));
        String finalLeftDown = (leftDown+""+(numOrigin+1)); // fox can move in all four directions.

        boolean ans = true;
        if ((isElement(dim, players, origin, destination))== true){
            switch (figure) {
                case 'F':
                    if ((finalRightUp.equals(destination) || (finalRightDown.equals(destination)) ||
                            (finalLeftUp.equals(destination)) || (finalLeftDown.equals(destination))) == true) {
                        ans = true;
                    } else {
                        ans = false;
                    }
                    break;
                case 'H':
                    if (((finalRightDown.equals(destination)) || (finalLeftDown.equals(destination))) == true) {
                        ans = true;
                    } else {
                        ans = false;
                    }
                    break;
            }
        } else {
            ans = false;
        }
        return ans;
    }

    public static boolean isElement(int dim, String[] players, String origin, String destination){
        boolean ans1 = true;
        boolean ans2 = true;
        int numDest = Integer.parseInt(destination.substring(1));
        char rowDest = destination.charAt(0);

        for(int count = 0; count < players.length; count ++){
            if(players[count].equals(origin)){
                ans1 = true;
                break;
            } else {
                ans1 = false;
            }
        }

        if(numDest > dim || rowDest > ((char)('A'+ (dim-1)))){
            ans2 =  false;
        } else {
            ans2 = true;
        }
        return (ans1&&ans2);
    }


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
        int[] rowPosition = new int[players.length];

        // Setting all intial values to zero.
        String[][] gameBoard = new String[dimension][dimension];

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
                gameBoard[i][j] = ".";
            }
        }

        // Set the position of Hounds.
        for(int i = 0; i < letterPositon.length;i++) {
            if (rowPosition[i] >= 1 && letterPositon[i] >= 1) {
                gameBoard[rowPosition[i] - 1][letterPositon[i] - 1] = "H";
            }
        }

        // Set position of Fox.
        gameBoard[rowPosition[rowPosition.length -1]-1][letterPositon[letterPositon.length -1]-1]="F";

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
        }

        // check that board is within the range required.
        if ((dimension < MIN_DIM) || (dimension > MAX_DIM)) {
            System.err.println("ERROR: The dimension of the board should be between 4 and 26.\nDefault dimension of 8x8 is used.");
            dimension = DEFAULT_DIM;
        }



        // locations: coodinates of the hound and the fox in the board according to the dimension that the user has entered
        String[] locations = new String[dimension];
        locations = (initialisePositions(dimension));

        displayBoard(locations, dimension);
        System.out.println("Initial positions of Hounds(H) and Fox(F): \n" + Arrays.toString(locations) + "\n");

        Scanner scan1 = new Scanner(System.in);
        System.out.print("Figure to move: ");
        char fig = scan1.next().charAt(0);

        char animal = 'F';
        if(fig == (FOX_FIELD)){
            animal = (FOX_FIELD);
        } else if (fig == (HOUND_FIELD)){
            animal = (HOUND_FIELD);
        } else {
            System.err.println("ERROR! Wrong animal entered.");
        }

        Scanner scan2 = new Scanner(System.in);
        System.out.print("Current Position: ");
        String currentPos = scan2.nextLine();

        System.out.print("Final position: ");
        String finalPos = scan2.nextLine();


        if(isValidMove(dimension, locations, animal, currentPos, finalPos) == true){
            System.out.println("Correct move.");
        } else {
            System.out.println("Wrong move.");
        }

    }
}