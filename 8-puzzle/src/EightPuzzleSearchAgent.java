import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.*;
import java.io.*; 

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleSearchAgent {
    // dec vars
    EightPuzzleProblem problem;

    // unused variable
    private final Queue<Node<EightPuzzleBoard, EightPuzzleAction>> frontier;
    public static void main(String[] args){
		//You can use 2D array it's ** great ** too.
		//int [] c = EightPuzzleSearchAgent.readFile("StateFile");
        //if you use 1D, you need to know x,y coordinate.

        // from file
        String fileName = args[0];
        Color.yellow("\nReading " + fileName + " ...");
        int[] allBoard = readFile(fileName);
        int[] initBoard = getInitialBoard(allBoard);
        int[] goalBoard = getGoalBoard(allBoard);

        // create the board
        EightPuzzleBoard initBoardFromFile = new EightPuzzleBoard(initBoard);
        EightPuzzleBoard goalBoardFromFile = new EightPuzzleBoard(goalBoard);
        
        // print the init and goal board
        Color.cyan("Initial Goal: \n");
        Color.green(initBoardFromFile.toString());
        Color.cyan("Goal State: \n");
        Color.green(goalBoardFromFile.toString());
        
        /// solve the problem
        EightPuzzleProblem problemFromFile = new EightPuzzleProblem(
            initBoardFromFile,
            goalBoardFromFile 
        );
        
        // show the solution for the problem from the file
        EightPuzzleSearchAgent puzzAgentFromFile = new EightPuzzleSearchAgent(problemFromFile);
        Color.yellow("\nSolving...");
        try{
            puzzAgentFromFile.showSolution();
        }catch(Exception e){
            System.out.println(Color.red("There are no solution for this board!"));
        }//end try
    }//end func

    public EightPuzzleSearchAgent(EightPuzzleProblem aProblem) {
        problem = aProblem;
        frontier = new LinkedList<>();
    }//end func

    /***********************************************
     * functions
     ***********************************************/

    public void showSolution() {
        // for breadth first search
        BreadthFirstSearch bfs = new BreadthFirstSearch(this.problem);
        System.out.println(bfs.toStringBoardSteps());
        
        // for manhatten a star
        AStarSearch assmh = new AStarSearch(this.problem, false);
        System.out.println(assmh.toStringBoardSteps());
        // for missing tile a star
        AStarSearch assmt = new AStarSearch(this.problem, true);
        System.out.println(assmt.toStringBoardSteps());
        // print summary
        System.out.println(Color.header("Summary"));
        System.out.println(this.toStringInitAndGoalState());
        System.out.println(this.toStringSummary(assmt, assmh, bfs));
        // print steps
        System.out.println(bfs.toStringSolution());
        System.out.println(assmt.toStringSolution());
        System.out.println(assmh.toStringSolution());
    }//end func

    public String toStringInitAndGoalState(){
        String toBeReturn = "";
        toBeReturn+= Color.cyan("Initial State: ") + "\n";
        toBeReturn+= Color.green(this.problem.getInitialState().toString()) + "\n";
        toBeReturn+= Color.cyan("Goal State: ") + "\n";
        toBeReturn+= Color.green(this.problem.getGoalState().toString()) + "\n";
        return toBeReturn;
    }//end func

    public String toStringSummary(AStarSearch assmt, AStarSearch assmh, BreadthFirstSearch bfs){
        String toBeReturn =  Color.YELLOW + "";
        List<Integer> assmtDepth = new LinkedList<>();
        List<Integer> assmhDepth = new LinkedList<>();
        List<Integer> bfsDepth = new LinkedList<>();
        
        ///add to list
        assmt.depthMap.forEach((k,v) -> assmtDepth.add(v));
        assmh.depthMap.forEach((k,v) -> assmhDepth.add(v));
        bfs.depthMap.forEach((k,v) -> bfsDepth.add(v));
        
        // to be print
        toBeReturn+= "+------------+-------------+------------+------------+\n";
        toBeReturn+= "|   Depth    | Search Cost |     Generated Nodes     |\n";
        toBeReturn+= "|            |    A*(H1)   |    A*(H2   |     BFS    |\n";
        toBeReturn+= "+------------+-------------+------------+------------+\n";
        // this.depthMap.forEach((k,v) -> System.out.println("Depth "+ k +": " + v + " nodes"));
        for(int x=0;x< bfs.depthMap.size(); x++){
            toBeReturn+= String.format("|%11d ", x) 
                + String.format("|%12d |", assmtDepth.get(x)) 
                + String.format("%11d |", assmhDepth.get(x)) 
                + String.format("%11d |", bfsDepth.get(x)) + "\n";
        }//end for
        toBeReturn+= "+------------+-------------+------------+------------+\n" + Color.RESET;
        return toBeReturn;
    }//end func
    
    public void printTree(Node<EightPuzzleBoard, EightPuzzleAction> node){
        Node<EightPuzzleBoard, EightPuzzleAction> curNode = node;
        EightPuzzleBoard currentState = curNode.getState();
        System.out.println("State: ");
        System.out.println(currentState.getBoardState().toString());
    }//end func

    public static void closeFile(BufferedReader br){
        try{
            br.close();
        }catch(IOException brErr){
            System.out.println("readFile(): failed to close file: " + brErr.toString());
        }//end catch
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
            closeFile(br);
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
                closeFile(br);
                return null;
            }//end try

            // break if line is null
            if(line == null) break;
            if(line.toUpperCase().contains("INITIAL")) continue;
            if(line.toUpperCase().contains("GOAL")) continue;
            if(!line.matches(".*\\d+.*")) continue; 
            
            // get the board row and assign it
            String[] boardRow = line.split(" ");
            for(int x=0; x< boardRow.length; x++){
                allBoard[counter] = Integer.parseInt(boardRow[x]);
                counter++;
            }//end for
        }while(line != null);

        // return
        closeFile(br);
        return allBoard;
    }//end func
    
    /***********************************************
     * other methods
     ***********************************************/

    public static int[] getGoalBoard(int[] allBoard){
        int[] boardToReturn = new int[9];
        int counter = 0;
        for(int x=10-1; x<allBoard.length;x++){
            boardToReturn[counter] = allBoard[x];
            counter++;
        }//end for
        return boardToReturn;
    }//end func

    public static int[] getInitialBoard(int[] allBoard){
        int[] boardToReturn = new int[9];
        for(int x=0; x<allBoard.length-9;x++){
            boardToReturn[x] = allBoard[x];    
        }//end for
        return boardToReturn;
    }//end func
}//end classes

class BreadthFirstSearch {
    private double time = 0;
    private EightPuzzleBoard initialState;
    private EightPuzzleBoard goalState;
    private EightPuzzleBoard solutionState;
    public Node<EightPuzzleBoard, EightPuzzleAction> root;
    public Queue<Node<EightPuzzleBoard, EightPuzzleAction>> frontier = new LinkedList<>();
    public Map<String, Boolean> exploredMap = new HashMap<>();
    public Map<Integer, Integer> depthMap = new HashMap<>();
    
    BreadthFirstSearch(EightPuzzleProblem problem){
        this.initialState = problem.getInitialState();
        this.goalState = problem.getGoalState();
        this.root = new Node<EightPuzzleBoard, EightPuzzleAction>(this.initialState);
        frontier.add(root);
        this.solve();
    }//end constructor

    public void exploreNeighbour(EightPuzzleBoard neighbour){
        if(!this.exploredMap.containsKey(neighbour.toString())){
            this.frontier.add(new Node<EightPuzzleBoard, EightPuzzleAction>(neighbour));
            this.exploredMap.put(neighbour.toString(), true);
        }//end if
    }//end func

    public boolean solve(){
        double startTime = System.nanoTime();
        while(!frontier.isEmpty()){
            // dec vars
            Node<EightPuzzleBoard, EightPuzzleAction> currentNode = frontier.poll();        
            EightPuzzleBoard currentState = currentNode.getState();
            EightPuzzleAction currentAction = currentNode.getAction();
            ExploreState exploreState = new ExploreState(currentState, currentAction);

            // count the number of node in each depth
            if(currentState.listOfState.size() != 0){
                int numOfNodeInDepth = (this.depthMap.get(currentState.listOfState.size()) != null)
                    ? this.depthMap.get(currentState.listOfState.size()) + 1 : 1;
                this.depthMap.put(currentState.listOfState.size(), numOfNodeInDepth);
            }//end if

            // check if goal state
            if(currentState.equals(this.goalState)){
                double endTime = System.nanoTime();
                this.solutionState = currentState;    
                this.time = (endTime - startTime)/1000000000;
                return true;
            }//end if

            // add if it is not in frontier
            this.exploreNeighbour(exploreState.leftState);
            this.exploreNeighbour(exploreState.rightState);
            this.exploreNeighbour(exploreState.upState);
            this.exploreNeighbour(exploreState.downState);
        }//end while

        // could not find the goal
        Color.cyan("Feedback: ");
        Color.red("Could not find the goal state!" + "\n");
        double endTime = System.nanoTime();
        this.time = (endTime - startTime)/1000000000; 
        return false;
    }//end func

    /***********************************************
     * toString function
     ***********************************************/

    public String toStringBoardSteps(){
        String toBeReturn = "";
        toBeReturn+= Color.header("BFS: Steps for solution") + "\n";
        toBeReturn+= Color.green(this.initialState.toString()) + "\n";
        toBeReturn+= this.solutionState.toStringBoardActions();
        return toBeReturn;
    }//end func

    public String toStringSolution(){
        String toBeReturn = "";
        toBeReturn+= Color.header("BFS: Feedback") + "\n";
        toBeReturn+= Color.cyan("Number or nodes visited: ") + Color.green(Integer.toString(this.exploredMap.size())) + "\n";
        toBeReturn+= Color.cyan("Number of moves for solution: ") + Color.green(Integer.toString(this.solutionState.listOfActions.size())) + "\n";
        toBeReturn+= Color.cyan("Time it took in seconds: ") + Color.green(Double.toString(this.time)) + "\n";
        toBeReturn+= Color.cyan("Solution: ") + Color.red(this.solutionState.toStringActions()) + "\n";
        return toBeReturn;
    }//end func
}//end classes

class AStarSearch{
    private boolean isMissingTile = false;
    private double time = 0;
    private EightPuzzleBoard initialState;
    private EightPuzzleBoard goalState;
    private EightPuzzleBoard solutionState;
    public Node<EightPuzzleBoard, EightPuzzleAction> root;
    public PriorityQueue<Node<EightPuzzleBoard, EightPuzzleAction>> frontier = new PriorityQueue<>(new CompareFCost());
    public Map<String, Boolean> exploredMap = new HashMap<>();
    public Map<Integer, Integer> depthMap = new HashMap<>();
    
    AStarSearch(EightPuzzleProblem problem, boolean isMissingTile){
        this.initialState = problem.getInitialState();
        this.goalState = problem.getGoalState();
        this.isMissingTile = isMissingTile;
        if(isMissingTile == false) this.initialState.setHForManHattan(this.goalState.getBoardState());
        if(isMissingTile == true) this.initialState.setHForMissingTile(this.goalState.getBoardState());
        this.root = new Node<EightPuzzleBoard, EightPuzzleAction>(this.initialState);
        frontier.add(root);
        this.solve();
    }//end constructor

    public void exploreNeighbour(EightPuzzleBoard neighbour){
        if(!this.exploredMap.containsKey(neighbour.toString())){
            this.frontier.add(new Node<EightPuzzleBoard, EightPuzzleAction>(neighbour));
            this.exploredMap.put(neighbour.toString(), true);
        }//end if
    }//end func

    public boolean solve(){
        double startTime = System.nanoTime();
        while(!frontier.isEmpty()){
            // dec vars
            Node<EightPuzzleBoard, EightPuzzleAction> currentNode = frontier.poll();        
            EightPuzzleBoard currentState = currentNode.getState();
            EightPuzzleAction currentAction = currentNode.getAction();
            ExploreState exploreState = new ExploreState(currentState, currentAction);

            // set the manhattan h cost
            if(isMissingTile == false){
                exploreState.leftState.setHForManHattan(this.goalState.getBoardState());
                exploreState.rightState.setHForManHattan(this.goalState.getBoardState());
                exploreState.upState.setHForManHattan(this.goalState.getBoardState());
                exploreState.downState.setHForManHattan(this.goalState.getBoardState());
            }else if(isMissingTile == true){
                exploreState.leftState.setHForMissingTile(this.goalState.getBoardState());
                exploreState.rightState.setHForMissingTile(this.goalState.getBoardState());
                exploreState.upState.setHForMissingTile(this.goalState.getBoardState());
                exploreState.downState.setHForMissingTile(this.goalState.getBoardState());
            }//end if

            // count the number of node in each depth
            if(currentState.listOfState.size() != 0){
                int numOfNodeInDepth = (this.depthMap.get(currentState.listOfState.size()) != null)
                    ? this.depthMap.get(currentState.listOfState.size()) + 1 : 1;
                this.depthMap.put(currentState.listOfState.size(), numOfNodeInDepth);
            }//end if

            // check if goal state
            if(currentState.equals(this.goalState)){
                double endTime = System.nanoTime();
                this.solutionState = currentState;    
                this.time = (endTime - startTime)/1000000000;
                return true;
            }//end if

            // add if it is not in frontier
            this.exploreNeighbour(exploreState.leftState);
            this.exploreNeighbour(exploreState.rightState);
            this.exploreNeighbour(exploreState.upState);
            this.exploreNeighbour(exploreState.downState);
        }//end while

        // could not find the goal
        Color.cyan("Feedback: ");
        Color.red("Could not find the goal state!" + "\n");
        double endTime = System.nanoTime();
        this.time = (endTime - startTime)/1000000000; 
        return false;
    }//end func

    /***********************************************
     * toString function
     ***********************************************/

    public String toStringBoardSteps(){
        String toBeReturn = "";
        String type = (isMissingTile == true) ? "AStar H1" : "AStar H2";
        toBeReturn+= Color.header(type + ": Steps for solution") + "\n";
        toBeReturn+= Color.green(this.initialState.toString()) + "\n";
        toBeReturn+= this.solutionState.toStringBoardActionsForAStar();
        return toBeReturn;
    }//end func

    public String toStringSolution(){
        String toBeReturn = "";
        String type = (isMissingTile == true) ? "AStar H1" : "AStar H2";
        toBeReturn+= Color.header(type + ": Feedback") + "\n";
        toBeReturn+= Color.cyan("Number or nodes visited: ") + Color.green(Integer.toString(this.exploredMap.size())) + "\n";
        toBeReturn+= Color.cyan("Number of moves for solution: ") + Color.green(Integer.toString(this.solutionState.listOfActions.size())) + "\n";
        toBeReturn+= Color.cyan("Time it took in seconds: ") + Color.green(Double.toString(this.time)) + "\n";
        toBeReturn+= Color.cyan("Solution: ") + Color.red(this.solutionState.toStringActions()) + "\n";
        return toBeReturn;
    }//end func
}//end classes

//Additional class or methods that you might need ...

class ExploreState{
    // dec all possible state
    EightPuzzleBoard upState;
    EightPuzzleBoard downState;
    EightPuzzleBoard leftState;
    EightPuzzleBoard rightState;
    // init moves
    EightPuzzleAction upAction = new EightPuzzleAction("UP");
    EightPuzzleAction downAction = new EightPuzzleAction("DOWN");
    EightPuzzleAction leftAction = new EightPuzzleAction("LEFT");
    EightPuzzleAction rightAction = new EightPuzzleAction("RIGHT");
    
    ExploreState(EightPuzzleBoard boardToExplore, EightPuzzleAction actionTaken){
        // set the different state
        upState = new EightPuzzleBoard(boardToExplore, actionTaken);
        downState = new EightPuzzleBoard(boardToExplore, actionTaken);
        leftState = new EightPuzzleBoard(boardToExplore, actionTaken);
        rightState = new EightPuzzleBoard(boardToExplore, actionTaken);
        // set moves
        upState.move(upAction);
        downState.move(downAction);
        leftState.move(leftAction);
        rightState.move(rightAction);
    }//end func
}//end func

class CompareFCost implements Comparator<Node<EightPuzzleBoard, EightPuzzleAction>>{
    @Override
    public int compare(Node<EightPuzzleBoard, EightPuzzleAction> o1, Node<EightPuzzleBoard, EightPuzzleAction> o2) {
        return o1.getState().getF() - o2.getState().getF();
    }//end func
}//end class

class ArrayConvertor {
    static public int[] d2Tod1(int[][] array){
        int[] newArray = new int[array.length*array[0].length];
        for (int i = 0; i < array.length; ++i) 
        for (int j = 0; j < array[i].length; ++j) {
            newArray[i*array[0].length+j] = array[i][j];
        }//end func
        return newArray;
    }//end func

    static public int[][] d1Tod2(int[] array, int width){
        int[][] newArray = new int[array.length/width][width];
        for (int i = 0; i < array.length; ++i) {
           newArray[i/width][i%width] = array[i];
        }//end func
        return newArray;
    }//end func
}//end class

class Color{
    public static final boolean isColorShown = false;
    public static final String RESET = "\033[0m";  
    // background colors
    public static final String BLACK_BG = (isColorShown) ? "\u001B[40m" : "";
    public static final String RED_BG = (isColorShown) ? "\u001B[41m" : "";
    public static final String GREEN_BG = (isColorShown) ? "\u001B[42m" : "";
    public static final String YELLOW_BG = (isColorShown) ? "\u001B[43m" : "";
    public static final String BLUE_BG = (isColorShown) ? "\u001B[44m" : "";
    public static final String PURPLE_BG = (isColorShown) ? "\u001B[45m" : "";
    public static final String CYAN_BG = (isColorShown) ? "\u001B[46m" : "";
    public static final String WHITE_BG = (isColorShown) ? "\u001B[47m" : "";
    // Regular Colors
    public static final String BLACK = (isColorShown) ? "\033[0;30m" : "";
    public static final String RED = (isColorShown) ? "\033[0;31m" : "";
    public static final String GREEN = (isColorShown) ? "\033[0;32m" : "";
    public static final String YELLOW = (isColorShown) ? "\033[0;33m" : "";
    public static final String BLUE = (isColorShown) ? "\033[0;34m" : "";
    public static final String PURPLE = (isColorShown) ? "\033[0;35m" : "";
    public static final String CYAN = (isColorShown) ? "\033[0;36m" : "";
    public static final String WHITE = (isColorShown) ? "\033[0;37m" : "";

    public static void debug(String toBePrinted){
        System.out.println(toBePrinted);
    }//end func
    
    public static String cyan(String toBePrinted){
        if(!isColorShown) return toBePrinted;
        return CYAN + toBePrinted + RESET;
    }//end func

    public static String yellow(String toBePrinted){
        if(!isColorShown) return toBePrinted;
        return YELLOW + toBePrinted + RESET;
    }//end func
    
    public static String green(String toBePrinted){
        if(!isColorShown) return toBePrinted;
        return GREEN + toBePrinted + RESET;
    }//end func

    public static String red(String toBePrinted){
        if(!isColorShown) return toBePrinted;
        return RED + toBePrinted + RESET;
    }//end func

    public static String header(String toBePrinted){
        if(!isColorShown) return toBePrinted;
        return Color.yellow("----------<<<( " + toBePrinted + " )>>>----------");
    }//end func
}//end func
