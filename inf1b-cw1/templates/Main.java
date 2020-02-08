import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String displayBoard(String[] players, int dimension) {
        char[] alphabets = new char[dimension];
        for (int count = 0; count < dimension; count++) {
            alphabets[count] = (char) ('A' + count);
        }

        String var = "";
        int length = dimension + 4;
        for (int i = 0; i < length; i++) {
            if ((i == 0 || i == (length - 1)) && (dimension < 10)) {
                var = ("  " + printLetters(dimension) + "  ");
            } else {//( ((i = 0 || i = (length - 1)) && (dimension > 10))) {
                var = ("   " + printLetters(dimension) + "   ");
            }

        }
        return var;

    }

    public static String printLetters ( int dimension){
        String alphabets = "";
        for(int i = 0; i < dimension; i++){
            alphabets = alphabets +  (char) ('A' + i);
        }
        return alphabets;
    }


    public static void main(String[] args) {

        int dimension;
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to change board dimension? (Y/N): ");
        String change = scan.next();
        String changeUpper = change.toUpperCase();                              //If the user input lower case y and n

        if (changeUpper.equals("N")) {
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

        int size = ((int) (Math.floor(dimension / 2)) + 1);
        String[] start = new String[size];

        for (int count = 0; count < (dimension / 2); count++) {
            start[count] = ((char) ('B' + (2 * count))) + "1";                        // initial position of Hounds
        }

        if (dimension % 2 == 0) {
            start[size - 1] = Character.toString((char) ('A' + size - 1)) + dimension;          // initial position of Fox
        } else {
            start[size - 1] = Character.toString((char) ('A' + size)) + dimension;
        }

        System.out.println((displayBoard(start, dimension)));

    }
}