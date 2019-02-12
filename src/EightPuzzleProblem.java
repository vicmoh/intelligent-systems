
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Le
 */
public class EightPuzzleProblem implements GenericProblem <EightPuzzleBoard, EightPuzzleAction>{
    private EightPuzzleBoard initialState;
    private EightPuzzleBoard goalState;

    @Override
    public EightPuzzleBoard getInitialState() {
        return initialState;
    }

    public EightPuzzleBoard getGoalState() {
        return goalState;
    }
    
    @Override
    public List<EightPuzzleAction> getActions(EightPuzzleBoard boardState) {
        return null;
        
    }
	public  EightPuzzleBoard getResult(EightPuzzleBoard state, EightPuzzleAction action) {

		return null;
	}
    @Override
    public boolean goalTest(EightPuzzleBoard boardState) {
        return false;
    }

    public EightPuzzleProblem(EightPuzzleBoard initialState, EightPuzzleBoard goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }


    @Override
    public double getStepCosts(EightPuzzleBoard state1, EightPuzzleAction action, EightPuzzleBoard state2) {
        return 1.0;
    }
    

    
}
//Additional class or methods that you might need.