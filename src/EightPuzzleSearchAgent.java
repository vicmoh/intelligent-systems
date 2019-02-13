

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Optional;

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleSearchAgent {

    EightPuzzleProblem problem;
    private final Queue<Node<EightPuzzleBoard, EightPuzzleAction>> frontier;

    /***********************************************
     * functions
     ***********************************************/

    public EightPuzzleSearchAgent(EightPuzzleProblem aProblem) {
        problem = aProblem;
        frontier = new LinkedList();
    }//end func
    public static void main(String[] args){
		//You can use 2D array it's ** great ** too.
		//int [] c = EightPuzzleSearchAgent.readFile("StateFile");
		//if you use 1D, you need to know x,y coordinate.
		
        int [] a = new int[]{0,1,2,3,4,5,6,7,8};
        int [] b = new int[]{1,2,0,3,4,5,6,7,8};
        EightPuzzleBoard initialState = new EightPuzzleBoard(a);
        EightPuzzleBoard goalState = new EightPuzzleBoard(b);
        
        EightPuzzleProblem problem = new EightPuzzleProblem(initialState,goalState);        
        EightPuzzleSearchAgent sa = new EightPuzzleSearchAgent(problem);
        sa.showSolution();
    }//end func

    public void showSolution() {
        //do the search and print out     
    }//end func
    
    public void printTree(Node node){
        
    }//end func
    
    public static int[] readFile(String afile){
       return null;
	}//end func
    
    /***********************************************
     * other methods
     ***********************************************/
    
}//end lcasses

class BreathFirstSearch {

}//end classes
class AStarSearch{

}//end classes

//Additional class or methods that you might need ...
