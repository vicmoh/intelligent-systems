import java.util.ArrayList;

/**
 * DTLearn class is used to perform the decision learning tree algorithm
 * 
 * @author Vicky Mohammad
 */
public class DTLeaner {
    Scheme aScheme;
    DataSet dataSet;

    /**
     * DecisionTreeLearning method implements decision tree learning algorithm
     * 
     * @return node
     */
    public Node decisionTreeLearning() {
        return new Node();
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
}// End class
