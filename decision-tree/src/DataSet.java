import java.io.BufferedReader;
import java.io.FileReader;
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
     * Create a data set
     */
    DataSet(Scheme scheme) {
        this.aScheme = scheme;
        this.dataSet = new ArrayList<Example>();
    }

    /**
     * This method loads a data file and store values in array list dataSet based on
     * scheme file
     */
    public void loadDataSetFile(String dataSetFile) {
        String fileName = dataSetFile;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (Exception err) {
            System.out.println("loadDataSetFile(): Can not open file:" + err.toString());
        }

        // Read the file
        System.out.println("LoadDataSetFile(): Reading file...");
        String line = null;
        do {
            try {
                // Get the line
                line = br.readLine();
                if (line == null)
                    break;
                // Add to the data set
                String[] list = line.split(" ");
                Example ex = new Example();
                System.out.println("***** " + list.length);

                // Find the index from the value
                for (int x = 0; x < list.length; x++) {
                    ArrayList<String> vals = this.aScheme.attributeList.get(x).valueList;
                    System.out.println("***** " + vals.size());
                    for (int y = 0; y < vals.size(); y++) {
                        if (vals.get(y).equals(list[x])) {
                            System.out.println("***** "+vals.get(y));
                            ex.add(y);
                        }
                    }
                }

                // Add example to data set
                this.dataSet.add(ex);
            } catch (Exception err) {
                System.out.println("loadDataSetFile(): Error reading file: " + err);
            }
        } while (line != null);
    }// End function

    /**
     * This method print out data set
     */
    public void printDataSet() {
        this.dataSet.forEach((k) -> {
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
