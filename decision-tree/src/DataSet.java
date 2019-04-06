import java.util.ArrayList;

/**
 * Data set class is used to store training data set. It is also stored a scheme
 * related to data set.
 * 
 * @author Vicky Mohammad
 */
public class DataSet {
    Scheme aScheme = null;
    ArrayList<Example> dataSet;
    int dataSetSize = 0;

    /**
     * This method loads a data file and store values in array list dataSet based on
     * scheme file
     */
    public void loadDataSetFile(String dataSetFile) {

    }// End function

    /* This method print out data set */
    public void printDataSet() {
        dataSet.forEach((k) -> {
            k.printExample();
        });
    }// End function

    /**
     * The method getBestAttribute it calculates information gain for the attributes
     * It returns the attribute with max gain.
     */
    public Attribute getBestAttribute() {
        return null;
    }// End function

    /**
     * Calculate the entropy
     * 
     * @param g list of example
     * @param h is the value
     */
    public double getEntropy(ArrayList<Example> g, int h) {
        return 0.0;
    }// End function

    /**
     * Get the remainder
     * 
     * @param a             attribute
     * @param g             list of example
     * @param attributeList list of attribute
     * @param h             value
     * @return remainder
     */
    public double getRemainder(Attribute a, ArrayList<Example> g, ArrayList<Attribute> attributeList, int h) {
        return 0.0;
    }// End function
}// End class
