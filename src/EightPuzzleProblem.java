
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleProblem implements GenericProblem <EightPuzzleBoard, EightPuzzleAction>{
    // dec instance vars
    private EightPuzzleBoard initialState;
    private EightPuzzleBoard goalState;

    /***********************************************
     * functions
     ***********************************************/

    public EightPuzzleProblem(EightPuzzleBoard initialState, EightPuzzleBoard goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }//end func

    public EightPuzzleBoard getGoalState() {
        return goalState;
    }//end func

    public  EightPuzzleBoard getResult(EightPuzzleBoard state, EightPuzzleAction action) {
		return null;
    }//end func

    /***********************************************
     * override functions
     ***********************************************/
    
    @Override
    public EightPuzzleBoard getInitialState() {
        return initialState;
    }//end func
    
    @Override
    public List<EightPuzzleAction> getActions(EightPuzzleBoard boardState) {
        return boardState.listOfActions;
    }//end func
    
    @Override
    public boolean goalTest(EightPuzzleBoard boardState) {
        return false;
    }//end func

    @Override
    public double getStepCosts(EightPuzzleBoard state1, EightPuzzleAction action, EightPuzzleBoard state2) {
        return 1.0;
    }//end func
}//end classes

//Additional class or methods that you might need ...