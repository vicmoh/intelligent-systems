

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Optional;
import java.io.*; // for reading a file

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleSearchAgent {
    // dec vars
    EightPuzzleProblem problem;
    private final Queue<Node<EightPuzzleBoard, EightPuzzleAction>> frontier;

    public static void main(String[] args){
		//You can use 2D array it's ** great ** too.
		//int [] c = EightPuzzleSearchAgent.readFile("StateFile");
		//if you use 1D, you need to know x,y coordinate.
		
        // int [] a = new int[]{0,1,2,3,4,5,6,7,8};
        // int [] b = new int[]{1,2,0,3,4,5,6,7,8};
        // EightPuzzleBoard initialState = new EightPuzzleBoard(a);
        // EightPuzzleBoard goalState = new EightPuzzleBoard(b);
        
        // EightPuzzleProblem problem = new EightPuzzleProblem(initialState,goalState);        
        // EightPuzzleSearchAgent sa = new EightPuzzleSearchAgent(problem);
        // sa.showSolution();

        System.out.println("Testing...");
        readFile("./assets/StateFile.txt");
    }//end func

    public EightPuzzleSearchAgent(EightPuzzleProblem aProblem) {
        problem = aProblem;
        frontier = new LinkedList<>();
    }//end func

    /***********************************************
     * functions
     ***********************************************/

    public void showSolution() {
        //do the search and print out     
    }//end func
    
    public void printTree(Node node){
        
    }//end func
    
    public static int[] readFile(String afile){
        // read file
        String fileName = afile;
        FileReader fileReader = null;
        BufferedReader br = null;
        try{
            fileReader = new FileReader(fileName);
            br = new BufferedReader(fileReader);
        }catch(Exception err){
            System.out.println("readFile(): file could not be read: " + err.toString());
            try{
                br.close();
            }catch(IOException brErr){
                System.out.println("readFile(): failed to close file: " + brErr.toString());
            }//end catch
            return null;
        }//end catch
        
        // read every line and parse the board
        String line = null;
        int counter = 0;
        int[] allBoard = new int[18];
        do{
            try{
                line = br.readLine();    
            }catch(IOException err){
                System.out.println("readFile(): error when reading file: " + err.toString());
                try{
                    br.close();
                }catch(IOException brErr){
                    System.out.println("readFile(): failed to close file: " + brErr.toString());
                }//end catch
                return null;
            }//end try

            // break if line is null
            if(line == null){
                break;
            }//end if
            
            // parse the file
            if(line.toUpperCase().contains("INITIAL")){
                continue;
            }else if(line.toUpperCase().contains("GOAL")){
                continue;
            }else{
                String[] boardRow = line.split(" ");
                for(int x=0; x< boardRow.length; x++){
                    allBoard[counter] = Integer.parseInt(boardRow[x]);
                    counter++;
                }//end for
            }//end if
        }while(line != null);

        // close file when done
        try{
            br.close();
        }catch(IOException err){
            System.out.println("readFile(): failed to close the file: " + err.toString());
        }//end try

        //print board
        printBoard(allBoard);

        // return
        return allBoard;
	}//end func
    
    /***********************************************
     * other methods
     ***********************************************/

    public static void printBoard(int[] allBoard){
        // print whats on the board
        for(int x=0; x<allBoard.length; x++){
            if(x % 3 == 0){
                System.out.println("");
            }//end if
            System.out.print(Integer.toString(allBoard[x]) + " ");
        }//end for
        System.out.println("\n");
    }//end func
}//end classes

class BreathFirstSearch {

}//end classes
class AStarSearch{

}//end classes

//Additional class or methods that you might need ...
