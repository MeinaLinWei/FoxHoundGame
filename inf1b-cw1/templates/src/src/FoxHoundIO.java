import javax.swing.plaf.BorderUIResource;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.IOException;


/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {

    public static char loadGame(String[] players, Path input){
        if(input == null){
            throw new NullPointerException();
        } else {
            try{
                int dimension = 8;
                char answer;

                if(players.length != 5){
                    throw new IllegalArgumentException();
                }

                File openFile = new File(String.valueOf(input));
                Scanner scanner = new Scanner(openFile);
                char nextMove = (scanner.next().charAt(0));
                if(nextMove == 'F' || nextMove == 'H'){
                    answer = nextMove;
                    int i = 0;
                    while (scanner.hasNext()){
                        players[i] = scanner.next();
                        int numDest = Integer.parseInt(players[i].substring(1));
                        char rowDest = players[i].charAt(0);
                        boolean result = true;
                        if(numDest > dimension || rowDest > ((char)('A'+ (dimension-1)))){
                            result =  false;
                            return '#';
                        }
                        i++;
                    }
                    return answer;
                } else {
                    return '#';
                }
            } catch (FileNotFoundException e){

            }
        }
        return '#';

    }

    public static boolean saveGame(String[] players, char nextMove, Path saveFile){
        if(saveFile.equals(null)){
            throw new NullPointerException();
        } else {
            try{
                File saveMove = new File(String.valueOf(saveFile));
                if(players.length != 5){
                    throw new IllegalArgumentException();
                }
                if(saveMove.exists()){
                    System.out.println("This file exist!");
                    System.exit(0);
                }
                PrintWriter writer = new PrintWriter(saveMove);
                writer.print((nextMove + " "));

                for(int i = 0; i < players.length; i++){
                    writer.print(players[i] + " ");
                }
                writer.close();
                return true;

            } catch (IOException e){
                throw new UncheckedIOException("Saving file test failed", e);
            }
        }


    }
}


