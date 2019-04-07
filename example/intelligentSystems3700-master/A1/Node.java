// Node.java: A node in a search tree.
// It is task independent and supports both blind and heruistic search.
import java.util.LinkedList;
class Node {
    Node parent;
    ObjectPlus state;
    int depth; 
    // depth is equivalent to uniform step cost
    Node child[] = null; 
    // useful for tree printing
    // Create root node with state s.
    Node(ObjectPlus s) {
        parent = null;
        state = s;
        depth = 0;
    }
    // Create a node with state s and parent p.
    Node(ObjectPlus s, Node p) {
        parent = p;
        state = s;
        depth = p.getDepth() + 1;
    }

    boolean isRootNode() {
        if (parent == null) return true;
        return false;
    }

    Node getParent() {
        return parent;

    }
    void setParent(Node p) {
        this.parent = p;
    }
    
    ObjectPlus getState() {
        return state;
    }
    
    void setState(ObjectPlus s) {
        this.state = s;
    }
    
    int getDepth() {
        return depth;
    }
    void setDepth(int d) {
        this.depth = d;
    }
    // Get path from root to this node in a list with the root as the head.
    LinkedList getPathFromRoot() {
        LinkedList<Node> ll = new LinkedList<Node>();
        Node current = this;
        while(!(current.isRootNode())) {
            ll.addFirst(current);
            current = current.getParent();
        }
        ll.addFirst(current); // take care of root node
        return ll;
    }
    // Given a set ss of succeesor states of this node, construct
    // a list of child nodes.
    LinkedList stateToNode(LinkedList ss) {
        LinkedList<Node> nds = new LinkedList<Node>();
        int scnt = ss.size(); 
        // # successor of this node
        child = new Node[scnt]; 
        // successor pointers
        for (int i=0;i<scnt;i++) {
            Node n = new Node((ObjectPlus) ss.get(i), this);
            // new succeesor node
            nds.add(n);
            child[i] = n; 
            // point to successor
        }
        return nds;
    }
    
    void show() {
        System.out.println(" node: dep="+depth+" state>>");
        state.show();
    }
    // Depending the index, show part of the node.
    void showPart(int index) {
        state.showPart(index);
    }
}
