import java.util.ArrayList;
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
}// End class
