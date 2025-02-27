import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleBoard implements GenericState <EightPuzzleBoard, EightPuzzleAction>{
    // the instance vars
    private int h = 0;
    private int[] boardState;
    private EightPuzzleAction left;
    private EightPuzzleAction right;
    private EightPuzzleAction up;
    private EightPuzzleAction down;
    // custom variable
    private int holeLocation;
    public EightPuzzleAction actionTaken = null;
    public List<EightPuzzleBoard> listOfState = new ArrayList<EightPuzzleBoard>();
    public List<EightPuzzleAction> listOfActions = new ArrayList<EightPuzzleAction>();

    /***********************************************
     * constructor
     ***********************************************/
    
    public EightPuzzleBoard(int[] inputState){
        if(inputState == null) System.out.println("WARNING: EightPuzzleBoard(): inputState = null");
        this.boardState = inputState;
        initAction();
        findHole();
    }//end constructor

    public EightPuzzleBoard(EightPuzzleBoard parent, EightPuzzleAction actionTaken){
        if(parent == null) System.out.println("WARNING: EightPuzzleBoard(): parent = null");
        this.listOfState = new ArrayList<>(parent.listOfState);
        this.listOfActions = new ArrayList<>(parent.listOfActions);
        this.boardState = parent.getBoardState();
        this.actionTaken = actionTaken;
        this.h = parent.h;
        initAction();
        findHole();
    }//end constructor
    
    private void initAction(){
        this.left = new EightPuzzleAction(EightPuzzleAction.actions[0]);
        this.right = new EightPuzzleAction(EightPuzzleAction.actions[1]);
        this.up = new EightPuzzleAction(EightPuzzleAction.actions[2]);
        this.down = new EightPuzzleAction(EightPuzzleAction.actions[3]);
    }//end func

    public int getH(){
        return this.h;
    }//end func
    
    public int getG(){
        return this.listOfState.size();
    }//end func

    public int getF(){
        return this.getG() + this.getH();
    }//end func

    public void setHForManHattan(int[] goalState){
        this.h = manhattan(goalState);
    }//end func

    public void setHForMissingTile(int[] goalState){
        this.h = missingTile(goalState);
    }//end func

    public int manhattan(int[] goalState) {
        int tempManhattan = 0;
        for (int x=0; x < this.boardState.length; x++) {
            for(int y=0; y < goalState.length; y++){
                if(this.boardState[x] != goalState[y]) continue;
                int prevRow = (int)(x/3);
                int prevCol = x%3;
                int goalRow = (int)(y/3);
                int goalCol = y%3;
                tempManhattan += Math.abs(prevRow-goalRow) + Math.abs(prevCol - goalCol);
            }//end for
        }//end for
        return  tempManhattan;
    }//end func

    public int missingTile(int[] goalState){
        int result = 0;
        for(int x=0; x<goalState.length; x++){
            if(goalState[x] != this.boardState[x] ){
                result++;
            }//end if
        }//end for
        return result;
    }//end func

    /***********************************************
     * custom functions
     ***********************************************/

    public String toStringBoardActionsForAStar(){
        String toBeReturn = "";
        EightPuzzleBoard lastBoard = this;
        int f = lastBoard.listOfActions.size();
        for(int x=0; x<this.listOfState.size(); x++){
            EightPuzzleBoard curBoard = this.listOfState.get(x);
            EightPuzzleAction curAction = this.listOfActions.get(x);         
            toBeReturn+= Color.CYAN + "Action: " + Color.RESET;
            toBeReturn+= Color.RED + curAction.getAction() + "\n" + Color.RESET;  
            toBeReturn+= Color.CYAN + "g = " + Color.RED + Integer.toString(curBoard.getG()) + Color.RESET + ", ";
            toBeReturn+= Color.CYAN + "h = " + Color.RED + Integer.toString(curBoard.getH()) + Color.RESET + ", ";
            toBeReturn+= Color.CYAN + "f = " + Color.RED + Integer.toString(curBoard.getF()) + Color.RESET + "\n";
            toBeReturn+= Color.GREEN + curBoard.toString() + "\n" + Color.RESET;
        }//end for
        toBeReturn+= Color.CYAN + "g = " + Color.RED + Integer.toString(lastBoard.getG()) + Color.RESET + ", ";
        toBeReturn+= Color.CYAN + "h = " + Color.RED + Integer.toString(lastBoard.getH()) + Color.RESET + ", ";
        toBeReturn+= Color.CYAN + "f = " + Color.RED + Integer.toString(lastBoard.getF()) + Color.RESET + "\n";
        return toBeReturn;
    }//end func

    public String toStringBoardActions(){
        String toBeReturn = "";
        EightPuzzleBoard lastBoard = this;
        for(int x=0; x<this.listOfState.size(); x++){
            EightPuzzleBoard curBoard = this.listOfState.get(x);
            EightPuzzleAction curAction = this.listOfActions.get(x);          
            toBeReturn+= Color.CYAN + "Action: " + Color.RESET;
            toBeReturn+= Color.RED + curAction.getAction() + "\n" + Color.RESET;  
            toBeReturn+= Color.CYAN + "g = " + Color.RED + Integer.toString(curBoard.listOfActions.size()-1) + Color.RESET + "\n";
            toBeReturn+= Color.GREEN + curBoard.toString() + "\n" + Color.RESET;
            lastBoard = curBoard;
        }//end for
        toBeReturn+= Color.CYAN + "g = " + Color.RED + Integer.toString(lastBoard.listOfActions.size()) + Color.RESET + "\n";
        return toBeReturn;
    }//end func

    public String toStringActions(){
        String toReturn = "";
        for(int x=0; x<this.listOfActions.size(); x++){
            toReturn+= this.listOfActions.get(x).getAction();
            if(x != this.listOfActions.size()-1){
                toReturn+= ", ";
            }//end if
        }//end for
        return toReturn;
    }//end func

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
        if(a.getAction().equals("LEFT")){
            if(this.holeLocation != 0 && this.holeLocation != 3 && this.holeLocation != 6){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation-1];
                this.boardState[this.holeLocation-1] = 0;
                this.listOfActions.add(a);
                this.listOfState.add(new EightPuzzleBoard(this, a));
            }//end if
        }else if(a.getAction().equals("RIGHT")){
            if(this.holeLocation != 2 && this.holeLocation != 5 && this.holeLocation != 8){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation+1];
                this.boardState[this.holeLocation+1] = 0;
                this.listOfActions.add(a);
                this.listOfState.add(new EightPuzzleBoard(this, a));
            }//end if
        }else if(a.getAction().equals("UP")){
            if(this.holeLocation > 2){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation-3];
                this.boardState[this.holeLocation-3] = 0;
                this.listOfActions.add(a);
                this.listOfState.add(new EightPuzzleBoard(this, a));
            }//end if
        }else if(a.getAction().equals("DOWN")){
            if(this.holeLocation <= 5){
                this.boardState[this.holeLocation] = this.boardState[this.holeLocation+3];
                this.boardState[this.holeLocation+3] = 0;
                this.listOfActions.add(a);
                this.listOfState.add(new EightPuzzleBoard(this, a));
            }//end if
        }//end if
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
        int[] clone = new int[9];
        for(int x=0; x<this.boardState.length; x++){
            clone[x] = this.boardState[x];
        }//end for
        return clone;
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
