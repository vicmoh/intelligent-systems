import java.util.Arrays;
import java.util.List;
import java.util.*;

/**
 *
 * @author Vicky Mohmmmad
 */
public class Node {
    String nodeLabel = ""; // attribute Name
    String linkLabel = ""; // atribute values of parent
    Node parent = null;

    // Extra instances
    List<Node> children;
    int depth = 0;
    int indexAtDepth = 0;

    public Node(String nodeLabel) {
        this.nodeLabel = nodeLabel;
        children = new LinkedList<Node>();
    }// End construtor

    // --------------------------------------------------------------------
    // Other function
    // --------------------------------------------------------------------

    public void addChild(Node child){
        this.children.add(child);
    }

    public void linkNode(Node parent, String linkLabel){
        this.linkLabel = linkLabel;
        this.parent = parent;
    }
}// End class
