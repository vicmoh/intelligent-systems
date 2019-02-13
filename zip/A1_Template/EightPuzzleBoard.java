
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le
 */
public class EightPuzzleBoard implements GenericState <EightPuzzleBoard, EightPuzzleAction>{


        
    private int[] boardState;
    private EightPuzzleAction left;
    private EightPuzzleAction right;
    private EightPuzzleAction up;
    private EightPuzzleAction down;
    
    
    
    public EightPuzzleAction getLeft() {
        return left;
    }

    public EightPuzzleAction getRight() {
        return right;
    }

    public EightPuzzleAction getUp() {
        return up;
    }

    public EightPuzzleAction getDown() {
        return down;
    }  
    public EightPuzzleBoard(){
        boardState = new int[]{0,1,2,3,4,5,6,7,8};
        left = new EightPuzzleAction(EightPuzzleAction.actions[0]);
        right = new EightPuzzleAction(EightPuzzleAction.actions[1]);
        up = new EightPuzzleAction(EightPuzzleAction.actions[2]);
        down = new EightPuzzleAction(EightPuzzleAction.actions[3]);
                 
    }
    public EightPuzzleBoard(int[] inputState){


    }
    @Override
    public EightPuzzleBoard getState() {
        return this;
    }

    @Override
    public void printState() {
        System.out.println(this);   

    }

    public void move(EightPuzzleAction a) {
          //do the move here.
        
    }

    
    public int[] getBoardState() {
        return null;
    }

    public void setBoardState(int[] boardState) {

    }

    @Override
    public String toString() {
        return boardState[0] + " " + boardState[1] + " " + boardState[2] + "\n"
	     + boardState[3] + " " + boardState[4] + " " + boardState[5] + "\n"
	     + boardState[6] + " " + boardState[7] + " " + boardState[8];
    }

	@Override
	public boolean equals(Object o) {

		return false;
	}

	@Override
	public int hashCode() {

		return 1;
	}
    
}
//Additional class or methods that you might need.
