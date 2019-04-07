import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.Data;

/**
 * DTLearn class is used to perform the decision learning tree algorithm
 * 
 * @author Vicky Mohammad
 */
public class DTLeaner {
    Scheme aScheme;
    DataSet dataSet;

    DTLeaner(Scheme scheme, DataSet dataSet) {
        this.aScheme = scheme;
        this.dataSet = dataSet;
    }

    /**
     * DecisionTreeLearning method implements decision tree learning algorithm
     * 
     * @return node
     */
    public Node decisionTreeLearning(DataSet dataSet, ArrayList<Attribute> schemaAttrList, String value) {
        // Error check
        if (this.dataSet == null || this.aScheme == null)
            return new Node(value);

        // Init the output
        int singleOutput = this.dataSet.singleOutput();
        int majorOutput = this.dataSet.majorOutput();

        // If single value is 0 or larger
        if (singleOutput >= 0)
            return new Node(this.aScheme.function.valueList.get(singleOutput));

        // If scheme attribute is empty
        if (schemaAttrList.size() == 0)
            return new Node(this.aScheme.function.valueList.get(majorOutput));

        // Init the attribute and node
        Attribute attribute = this.dataSet.getAttribute(schemaAttrList);
        int attributeIndex = this.aScheme.attributeIndex(attribute);
        Node tree = new Node(attribute.attributeName);

        // Go through for each value of attribute
        for (int x = 0; x < attribute.valueList.size(); x++) {
            DataSet subData = new DataSet(this.aScheme);
            for (int y = 0; y < this.dataSet.dataSet.size(); y++)
                if (this.dataSet.dataSet.get(y).attributes.get(attributeIndex) == x)
                    subData.addExample(this.dataSet.dataSet.get(y));

            // If data is empty
            if (subData.dataSet.size() == 0)
                subData = null;

            // Create children using recursion
            ArrayList<Attribute> attributeList = new ArrayList<Attribute>(schemaAttrList);
            attributeList = removeAttributes(attributeList, attribute);
            Node subTree = decisionTreeLearning(subData, attributeList,
                    this.aScheme.function.valueList.get(majorOutput));
            subTree.linkNode(tree, this.aScheme.attributeList.get(attributeIndex).valueList.get(x));
            subTree.addChild(subTree);
        }

        return tree;
    }// End function

    /**
     * the method majorityValue calculate the majority of target value of data set.
     * 
     * @param dataset
     * @return
     */
    public String majorityValue(ArrayList<Example> dataset) {
        return "";
    }// End function

    /**
     * The method sameClassification check if the dataset has the same
     * classification. It returns the value of the same class
     * 
     * @param dataset
     * @return
     */
    public String sameClassification(ArrayList<Example> dataset) {
        return "";
    }// End function

    // ---------------------------------------------------------------
    // Other function
    // ---------------------------------------------------------------

    public ArrayList<Attribute> removeAttributes(ArrayList<Attribute> attributes, Attribute attRm) {
        for (int x = 0; x < attributes.size(); x++)
            if (attributes.get(x).equals(attRm))
                attributes.remove(x);
        return attributes;
    }

    public void printTree(Node root) {
        System.out.println("printTree(): Printing...");
        // Linearize into a ingle list instead of tree
        LinkedList<Node> linearList = new LinkedList<Node>();
        linearList = linearizeTree(root, 0, linearList);
        // Find the max depth of the decision tree
        int max = -1;
        for (Node curNode : linearList)
            if (curNode.depth > max)
                max = curNode.depth;

        // Print the root
        System.out.println(root.nodeLabel);
        System.out.println("0\n");

        // Loop through each depth of the tree
        for (int x = 1; x < max + 1; x++) {
            LinkedList<Node> curDepth = new LinkedList<Node>();
            for (Node curNode : linearList)
                if (curNode.depth == x)
                    curDepth.add(curNode);
            this.printTreeAtDepth(curDepth);
        }
    }

    private LinkedList<Node> linearizeTree(Node node, int depth, LinkedList<Node> list) {
        node.depth = depth;
        list.add(node);
        depth++;
        for (Node curNode : node.children)
            this.linearizeTree(curNode, depth, list);
        return list;
    }

    private void printTreeAtDepth(LinkedList<Node> nodes) {
        int size = nodes.size();

        // Print the parent node of each node
        for (int x = 0; x < size; x++)
            System.out.print(nodes.get(x).parent.indexAtDepth + "          ");
        System.out.println("");

        // Print the parent node of each node
        for (int x = 0; x < size; x++)
            System.out.print("|          ");
        System.out.println("");

        // Print a line for each node with the name of the attribute
        // value that connect this node to its parent
        for (int x = 0; x < size; x++) {
            System.out.print("|" + nodes.get(x).linkLabel);
            for (int y = nodes.get(x).linkLabel.length(); y <= 9; y++)
                System.out.print(" ");
        }
        System.out.println("");

        // Print a chevron for each node
        for (int x = 0; x < size; x++) {
            System.out.print("v          ");
        }
        System.out.println("");

        // Print the label and output of each node
        for (int x = 0; x < size; x++) {
            System.out.print(nodes.get(x).nodeLabel);
            for (int y = nodes.get(x).nodeLabel.length(); y <= 10; y++)
                System.out.print(" ");

        }
        System.out.println("");

        // Print each nodes index at the current depth
        for (int x = 0; x < size; x++) {
            System.out.print(x + "          ");
            nodes.get(x).IndexAtDepth = x;
        }
        System.out.println("");
    }

}// End class
