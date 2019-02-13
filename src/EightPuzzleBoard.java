
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
    // custom variable
    private int holeLocation;

    /***********************************************
     * constructor
     ***********************************************/

    public EightPuzzleBoard(){
        this.boardState = new int[]{0,1,2,3,4,5,6,7,8};
        left = new EightPuzzleAction(EightPuzzleAction.actions[0]);
        right = new EightPuzzleAction(EightPuzzleAction.actions[1]);
        up = new EightPuzzleAction(EightPuzzleAction.actions[2]);
        down = new EightPuzzleAction(EightPuzzleAction.actions[3]);
        findHole();
    }//end func
    
    public EightPuzzleBoard(int[] inputState){
        if(inputState == null) System.out.println("WARNING: EightPuzzleBoard(): inputState = null");
        left = new EightPuzzleAction(EightPuzzleAction.actions[0]);
        right = new EightPuzzleAction(EightPuzzleAction.actions[1]);
        up = new EightPuzzleAction(EightPuzzleAction.actions[2]);
        down = new EightPuzzleAction(EightPuzzleAction.actions[3]);
        this.boardState = inputState;
        findHole();
    }//end func

    /***********************************************
     * custom functions
     ***********************************************/

    private void findHole(){
        for(int x=0; x<boardState.length; x++){
            if(boardState[x] == 0){
                this.holeLocation = x;
            }//end if
        }//end for
    }//end func

    /***********************************************
     * pre-defined functions
     ***********************************************/

    public void setBoardState(int[] boardState) {
        if(boardState == null) System.out.println("WARNING: EightPuzzleBoard(): boardState = null");
        this.boardState = boardState;
    }//end func

    public void move(EightPuzzleAction a) {
        //do the move here.
        
        // move down
        if(a.getAction().equals("DOWN")){
            if(this.holeLocation <= 5){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation+3];
                this.boardState[this.holeLocation+3] = 0;
            }//end if
        }else if(a.getAction().equals("UP")){
            if(this.holeLocation > 2){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation-3];
                this.boardState[this.holeLocation-3] = 0;
            }//end if
        }else if(a.getAction().equals("LEFT")){
            if(this.holeLocation != 0 && this.holeLocation != 3 && this.holeLocation != 6){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation-1];
                this.boardState[this.holeLocation-1] = 0;
            }//end if
        }else if(a.getAction().equals("RIGHT")){
            if(this.holeLocation != 2 && this.holeLocation != 5 && this.holeLocation != 8){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation+1];
                this.boardState[this.holeLocation+1] = 0;
            }//end if
        }//end for
    }//end func

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

    public int[] getBoardState() {
        return this.boardState;
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
        EightPuzzleBoard temp = (EightPuzzleBoard) o;
        for(int x=0; x<this.boardState.length; x++){
            if(this.boardState[x] != temp.boardState[x]){
                return false;
            }//end if
        }//end for
        return true;
	}//end func

	@Override public int hashCode() {
		return 1;
	}//end func
}//end func

// Additional class or methods that you might need ...
