

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.io.*; // for reading a file
// import java.util.Optional;
// import java.util.*;

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
        Print.yellow("\nReading from a file...");
        int[] allBoard = readFile("./assets/dog.txt");
        int[] initBoard = getInitialBoard(allBoard);
        int[] goalBoard = getGoalBoard(allBoard);
        // create the board
        EightPuzzleBoard initBoardFromFile = new EightPuzzleBoard(initBoard);
        EightPuzzleBoard goalBoardFromFile = new EightPuzzleBoard(goalBoard);
        // print the init and goal board
        Print.cyan("Initial Goal: \n");
        Print.green(initBoardFromFile.toString());
        Print.cyan("Goal State: \n");
        Print.green(goalBoardFromFile.toString());
        /// solve the problem
        EightPuzzleProblem problemFromFile = new EightPuzzleProblem(
            initBoardFromFile,
            goalBoardFromFile 
        );
        // show the solution for the problem from the file
        EightPuzzleSearchAgent puzzAgentFromFile = new EightPuzzleSearchAgent(problemFromFile);
        Print.yellow("\nSolving...");
        puzzAgentFromFile.showSolution();
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
        BreathFirstSearch bfs = new BreathFirstSearch(this.problem);
        bfs.solve();
    }//end func
    
    public void printTree(Node<EightPuzzleBoard, EightPuzzleAction> node){
        Node<EightPuzzleBoard, EightPuzzleAction> curNode = node;
        EightPuzzleBoard currentState = curNode.getState();
        System.out.println("State: ");
        System.out.println(currentState.getBoardState().toString());
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

        // return
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

    /***********************************************
     * other unused functioins that may be helpful
     ***********************************************/

    public static boolean isBoardEqual(EightPuzzleBoard board1, EightPuzzleBoard board2){
        // check if the same pointer
        if(board1 == board2){
            return true;
        }//end if

        // check if length is the same
        if(board1.getBoardState().length != board2.getBoardState().length){
            return false;
        }//end if

        // check if data is the same
        for(int x=0; x<board1.getBoardState().length; x++){
            if(board2.getBoardState()[x] != board2.getBoardState()[x]){
                return false;
            }//end if
        }//end for
        return true;
    }//end func

    public static boolean isPuzzleSolvable(int[][] matrix){
		int count = 0;
		List<Integer> array = new ArrayList<Integer>();
        Integer[] anotherArray = new Integer[array.size()];
		array.toArray(anotherArray);
        
        // add to the array for comparasion
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				array.add(matrix[y][x]);
			}//end for
		}//end for
    
        // check two array and compare
		for (int y = 0; y < anotherArray.length - 1; y++) {
			for (int x = y + 1; x < anotherArray.length; x++) {
				if (anotherArray[y] != 0 && anotherArray[x] != 0 && anotherArray[y] > anotherArray[x]) {
					count++;
				}//end if
			}//end for
        }//end for
        
        // return
		return count % 2 == 0;
    }//end func
    
    public static int[][] getMatrix(int[] board){
        // dec vars
        int[][] matrix = new int[3][3];
        int y = 0;
        int x = 0;
        boolean flag = false;

        // assign to the board
        for(int a=0; a<board.length; a++){
            if(a % 3 == 0 && flag == true){
                y++;
                x = 0;
            }//end if
            flag = true;
            matrix[x][y] = board[a];
            x++;
        }//end for
        return matrix;
    }//end func

    public static void printMatrix(int[][] matrix){
        for(int y=0; y<matrix.length; y++){
            for(int x=0; x<matrix[y].length; x++){
                System.out.print(Integer.toString(matrix[x][y]) + " ");
            }//end for
            System.out.println("");
        }//end for
    }//end func

    public static void printAllBoard(int[] allBoard){
        boolean flag = false;
        // print whats on the board
        for(int x=0; x<allBoard.length; x++){
            if(x % 3 == 0 && flag == true){
                System.out.println("");
            }//end if
            flag = true;
            System.out.print(Integer.toString(allBoard[x]) + " ");
        }//end for
        System.out.println("\n");
    }//end func
}//end classes

class BreathFirstSearch {
    private EightPuzzleBoard initialState;
    private EightPuzzleBoard goalState;
    private int totalCost = 0;
    private int time = 0;
    private Node<EightPuzzleBoard, EightPuzzleAction> root;
    private Queue<Node<EightPuzzleBoard, EightPuzzleAction>> frontier = new LinkedList<>();
    private Map<String, Boolean> exploredMap = new HashMap<>();
    
    BreathFirstSearch(EightPuzzleProblem problem){
        this.initialState = problem.getInitialState();
        this.goalState = problem.getGoalState();
        this.root = new Node<EightPuzzleBoard, EightPuzzleAction>(this.initialState);
        frontier.add(root);
    }//end constructor

    public int getTime(){
        return this.time;
    }//end func

    public int getTotalCost(){
        return this.totalCost;
    }//end func

    public void exploreNeighbour(EightPuzzleBoard neighbour){
        if(!this.exploredMap.containsKey(neighbour.toString())){
            this.frontier.add(new Node<EightPuzzleBoard, EightPuzzleAction>(neighbour));
            this.exploredMap.put(neighbour.toString(), true);
        }//end if
    }//end func

    public EightPuzzleBoard solve(){
        while(!frontier.isEmpty()){
            // dec vars
            Node<EightPuzzleBoard, EightPuzzleAction> currentNode = frontier.poll();        
            EightPuzzleBoard currentState = currentNode.getState();
            Explore exploreState = new Explore(currentState);

            // check if goal state
            if(currentState.equals(this.goalState)){
                Print.cyan("Found Goal State!\n");
                Print.green(currentState.toString() + "\n");
                Print.cyan("Solution: ");
                Print.red(currentState.toStringActions() + "\n");
                return currentState;
            }//end if

            // add if it is not in frontier
            this.exploreNeighbour(exploreState.upState);
            this.exploreNeighbour(exploreState.downState);
            this.exploreNeighbour(exploreState.leftState);
            this.exploreNeighbour(exploreState.rightState);
        }//end while

        // could not find the goal
        Print.cyan("Feedback: ");
        Print.red("Could not find the goal state!" + "\n");
        return null;
    }//end func
}//end classes

class AStarSearch{
    private int[] initialState;
    private int[] goalState;
    
    AStarSearch(int[] initialState, int[] goalState){
        this.initialState = initialState;
        this.goalState = goalState;
        if(initialState.equals(goalState)){
            System.out.println("It is already in goal state!!");
        }//end if
    }//end constructor

    public int calculateCost(int[][] initial, int[][] goal) {
		int count = 0;
		int n = initial.length;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (initial[y][x] != 0 && initial[y][x] != goal[y][x]) {
					count++;
				}//end if
			}//end for
		}//end for
		return count;
	}//end func
}//end classes

//Additional class or methods that you might need ...

class Explore{
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
    
    // constructor
    Explore(EightPuzzleBoard boardToExplore){
        // set the different state
        upState = new EightPuzzleBoard(boardToExplore);
        downState = new EightPuzzleBoard(boardToExplore);
        leftState = new EightPuzzleBoard(boardToExplore);
        rightState = new EightPuzzleBoard(boardToExplore);
        // set moves
        upState.move(upAction);
        downState.move(downAction);
        leftState.move(leftAction);
        rightState.move(rightAction);
    }//end func
}//end func

class Print{
    public static final String RESET = "\033[0m";  
    // background colors
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    
    public static void cyan(String toBePrinted){
        System.out.print(CYAN + toBePrinted + RESET);
    }//end func

    public static void yellow(String toBePrinted){
        System.out.println(YELLOW + toBePrinted + RESET);
    }//end func
    
    public static void green(String toBePrinted){
        System.out.println(GREEN + toBePrinted + RESET);
    }//end func

    public static void red(String toBePrinted){
        System.out.println(RED + toBePrinted + RESET);
    }//end func
}//end func