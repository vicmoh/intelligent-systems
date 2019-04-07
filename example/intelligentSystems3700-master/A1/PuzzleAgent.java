import java.util.LinkedList;

import javax.naming.InitialContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class PuzzleAgent extends SearchAgent {

    PuzzleAgent() {

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("requires 1 argument: <init filename>");
        }

        String line;
        int count = 0;
        boolean initFull = false;
        int initState[] = new int[9];
        int goalState[] = new int[9];

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            while((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        if (!initFull) {
                            initState[count] = Character.getNumericValue(c);
                        } else {
                            goalState[count] = Character.getNumericValue(c);
                        }
                        count++;
                        if (count == 9) {
                            count = 0;
                            initFull = true;
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("error opening file: " + args[0]);
        }

        PuzzleProblem problem = new PuzzleProblem();

        Board goalBoard = new Board(goalState, ' ', problem, -1); // -1 means skip hVal calculation
        problem.setGoalState(goalBoard);

        Board initBoard = new Board(initState, ' ', problem, 0);
        problem.setInitialState(initBoard);
        
        PuzzleAgent agent = new PuzzleAgent();
        agent.setProblem(problem);

        agent.search();
        System.out.println("\n");
        agent.showTree();
        System.out.println("\n");
        agent.showSolution();
    }

    void showSolution() {
        System.out.println("Solution to 8-puzzle");
        for (int i = 0; i < solution.size(); i++) {
            Node n = (Node) solution.get(i);
            n.show();
        }
    }

    void showTree() {
        System.out.println("Search Tree Size = " + this.tree.size());
        System.out.println("Hole movement shown as <, >, v, or ^");
        System.out.println("All f(), g(), and h() values are shown");
        printSubTree(tree.get(0));
    }

    void printSubTree(Node current) {
        if (current.state instanceof Board) {
            Board board = (Board)(current.state);
            board.treePrint();
        }
        if (current.child != null) {
            for (Node childNode: current.child) {
                printSubTree(childNode);
            }
        }
    }

    void insertFringe(Node nd, LinkedList<Node> ll) {
        if (nd.state instanceof Board) {
            Board board = (Board)(nd.state);
            
            for (int i = 0; i < ll.size(); i++) {
                Node current = ll.get(i);
                if (current.state instanceof Board) {
                    Board currentBoard = (Board)(current.state);
                    if (board.getFVal() < currentBoard.getFVal()) {
                        ll.add(i, nd);
                        break;
                    }
                }
            }

            if (ll.size() == 0) {
                ll.add(nd);
            }
        }
    }

}