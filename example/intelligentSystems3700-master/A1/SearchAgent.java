// SearchAgent.java: Agent who solves a problem by tree search.
// It is task independent and supports both blind and heruistic search.
import java.util.LinkedList;
abstract class SearchAgent {
    Problem problem;
    LinkedList<Node> tree;
    LinkedList<Node> fringe;
    LinkedList solution;

    void setProblem(Problem p) {
        this.problem = p;
    }

    // Search for solution.
    // Post: solution variable is set.
    // Note:
    //   Search tree and fringe are separately maintained using LinkedList.
    //   One copy of each node is stored and it is referenced by both lists.
    void search() {
        Node root = new Node(problem.getInitialState());
        tree = new LinkedList<Node>(); 
        // search tree
        fringe = new LinkedList<Node>(); 
        // fringe
        tree.add(root);
        insertFringe(root, fringe); // one copy of root is referenced in both lists
        while (fringe.size() != 0) {
            System.out.println("Fringe size="+fringe.size());
            Node n = (Node) fringe.removeFirst(); 
            // node to visit
            System.out.print("Visit"); 
            n.show();
            ObjectPlus nodeState = n.getState();
            if (problem.isGoalState(nodeState)) {
                solution = n.getPathFromRoot();
                return;
            }
            LinkedList successors = problem.getSuccessor(nodeState); // child states
            LinkedList childnodes = n.stateToNode(successors); // child nodes
            for (int i=0;i<childnodes.size();i++) {
                tree.add(((Node) childnodes.get(i)));
                insertFringe((Node) childnodes.get(i), fringe);
            }
        }
        return;
    }
    
    abstract void showSolution();
    abstract void showTree();
    abstract void insertFringe(Node nd, LinkedList<Node> ll);
}
