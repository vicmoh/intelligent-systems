import java.util.LinkedList;
import java.util.stream.Stream;

// PuzzleProblem.java: This class contains problem definition for 8-puzzle as discussed
// in lectures. It should be a subclass of Problem described above. The key methods include
// goal test and successor generation. Generate successors in the order of hole movement 
// (Left, Right, Up, Down). If two nodes in fringe tie with evaluation function values (f(n)),
// they are visited in a first-in-first-out order. 
// This determines how nodes are inserted into fringe

class PuzzleProblem extends Problem {

    PuzzleProblem(ObjectPlus initialState, ObjectPlus goalState, String strategyHeuristics) {
        setGoalState(goalState);
        setInitialState(initialState);
        setStrategyHeur(strategyHeuristics);
    }

    PuzzleProblem() {

    }

    LinkedList getSuccessor(ObjectPlus s) {
        char moves[] = { 'L', 'R', 'U', 'D' };
        LinkedList successors = new LinkedList<ObjectPlus>();

        if (s instanceof Board) {
            Board board = (Board)(s);
            for (char move : moves) {
                if (board.isMoveValid(move)) {
                    successors.add(board.makeMove(move));
                }
            }
        }
        
        return successors;
    }

    boolean isGoalState(ObjectPlus s) {
        if (s instanceof Board) {
            Board board = (Board)(s);
            return board.isGoalState();
        }
        return false;
    }
}