// Problem.java: Abstract problem definition for tree search.
// It is task independent and supports both blind and heruistic search.
import java.util.LinkedList;
abstract class Problem {
    ObjectPlus initialState, goalState;
    String strategyHeuristics; 

    // strategy heuristics combination
    ObjectPlus getInitialState() {
        return initialState;
    }
    void setInitialState(ObjectPlus s) {
        this.initialState = s;
    }

    ObjectPlus getGoalState() {
        return goalState;
    }
    void setGoalState(ObjectPlus s) {
        this.goalState = s;
    }

    String getStrategyHeur() {
        return new String(strategyHeuristics);
    }
    void setStrategyHeur(String straheur) {
        strategyHeuristics = new String(straheur);
    }

    // Abstract methods
    // Get successors given state s.
    abstract LinkedList getSuccessor(ObjectPlus s);

    // Test if state s is a goal.
    abstract boolean isGoalState(ObjectPlus s);
}