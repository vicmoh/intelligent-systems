
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleBoard implements GenericState <EightPuzzleBoard, EightPuzzleAction>{
    // the instance vars
    private int[] boardState;
    private EightPuzzleAction left;
    private EightPuzzleAction right;
    private EightPuzzleAction up;
    private EightPuzzleAction down;

    /***********************************************
     * functions
     ***********************************************/

    public EightPuzzleAction getLeft() {
        return left;
    }//end func

    public EightPuzzleAction getRight() {
        return right;
    }//end func

    public EightPuzzleAction getUp() {
        return up;
    }//end func

    public EightPuzzleAction getDown() {
        return down;
    }//end func
    
    public EightPuzzleBoard(){
        boardState = new int[]{0,1,2,3,4,5,6,7,8};
        left = new EightPuzzleAction(EightPuzzleAction.actions[0]);
        right = new EightPuzzleAction(EightPuzzleAction.actions[1]);
        up = new EightPuzzleAction(EightPuzzleAction.actions[2]);
        down = new EightPuzzleAction(EightPuzzleAction.actions[3]);
    }//end func
    
    public EightPuzzleBoard(int[] inputState){
        
    }//end func

    public void move(EightPuzzleAction a) {
        //do the move here.
    }//end func

    public int[] getBoardState() {
        return null;
    }//end func

    public void setBoardState(int[] boardState) {

    }//end func

    /***********************************************
     * override functions
     ***********************************************/

    @Override public EightPuzzleBoard getState() {
        return this;
    }//end func

    @Override public void printState() {
        System.out.println(this);   
    }//end func

    @Override public String toString() {
        return boardState[0] + " " + boardState[1] + " " + boardState[2] + "\n"
             + boardState[3] + " " + boardState[4] + " " + boardState[5] + "\n"
             + boardState[6] + " " + boardState[7] + " " + boardState[8];
    }//end func

	@Override public boolean equals(Object o) {
		return false;
	}//end func

	@Override public int hashCode() {
		return 1;
	}//end func
}//end func

// Additional class or methods that you might need ...
