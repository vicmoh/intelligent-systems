import java.util.ArrayList;

class Node {
    // Attributes
    public String label;
    private boolean isLeaf;
    public String value;
    private Attribute splitOn = null;

    // tree linking stuff
    public Node parent = null;
    public ArrayList<Node> children = null;

    // Constructor
    public Node(String label) {
        this.label = label;
        isLeaf = true;
    }

    public Node(Attribute splitOn) {
        this.splitOn = splitOn;
        isLeaf = false;
        this.label = splitOn.name;
        children = new ArrayList<Node>();
    }

    // Public Methods
    public void printTree() {
        this.printTree(0);
    }

    public void printTree(int depth) {
        String offset = "";
        for (int i = 0; i < depth; i++) {
            offset += "  ";
        }

        if (parent == null) { // if root
            System.out.println("\nPrinting Decision Tree:");
            System.out.println(offset + "> root");
            System.out.println(offset + "------> " + label + "");
        } else if (isLeaf) { // if leaf
            System.out.println(offset + "> " + value);
            System.out.println(offset + "------> [" + label + "]");
        } else { // if leaf
            System.out.println(offset + "> " + value);
            System.out.println(offset + "------> " + label + "");
        }

        if (children != null && children.size() > 0) {
            for (Node n : children) {
                n.printTree(depth + 1);
            }
        }
    }

    // Private Methods

}
