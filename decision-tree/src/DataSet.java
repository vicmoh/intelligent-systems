import java.awt.desktop.ScreenSleepListener;
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
    ArrayList<Example> dataSet = new ArrayList<Example>();;
    int dataSetSize = 0;

    /**
     * Create a data set
     */
    DataSet(Scheme scheme) {
        this.aScheme = scheme;
    }

    /**
     * Constructor for the temporary sample
     * 
     * @param scheme
     * @param tempAttList
     */
    DataSet(Scheme scheme, ArrayList<Example> tempAttList) {
        this.aScheme = scheme;
        this.dataSet = tempAttList;
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
                            System.out.println("***** " + vals.get(y));
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
    public double getRemainder(Attribute a /* , ArrayList<Example> g, ArrayList<Attribute> attributeList, int h */) {
        // Declare variable
        int exampleSize = this.dataSet.size();
        int attributeSize = a.valueList.size();
        DataSet[] subSamples = this.createSubSample(a);
        int[] subCont = new int[m];

        // Loop and add the size
        for (int i = 0; i < m; i++)
            subCont[i] = subSamples[i].dataSet.size();

        // Get the remainder
        double remainder = 0;
        for (int x = 0; x < attributeSize; x++) {
            double pr = (double) subCont[x] / size;
            double i = (double) subSamples[x].infoFmGp();
            if (pr > 0)
                remainder += (double) pr * i;
        }

        return remainder;
    }// End function

    // --------------------------------------------------------------------
    // Extra function for the get remainder function
    // --------------------------------------------------------------------

    private DataSet[] createSubSample(Attribute attribute) {
        // Declare varibales
        int attributeSize = attribute.valueList.size();
        int attIndex = this.aScheme.attributeIndex(attribute);
        ArrayList<Example> tempAttList = new ArrayList<Example>();
        DataSet[] subSamples = new DataSet[attributeSize];
        // Loop through and create a sub sample
        for (int i = 0; i < attributeSize; i++) {
            for (Example ex : this.dataSet)
                if (i == ex.values[attIndex])
                    tempAttList.add(ex);

            // Add the new data set and example
            subSamples[i] = new DataSet(Scheme, tempAttList);
            tempAttList = new ArrayList<Example>();
        }
        return subSamples;
    }

    private double infoFmGp() {
        int outputCount = this.aScheme.function.valueList.size();
        int size = this.dataSet.size();
        int[] count;
        count = countOutputOfExamples();

        // Check if size is 0
        if (size == 0)
            return 0;

        // Get info
        double toBeReturn = 0;
        for (int x = 0; x < outputCount; x++) {
            double ratio = (double) count[x] / size;
            if (ratio > 0)
                toBeReturn -= (ratio * Math.log(ratio) / Math.log(2));
        }
        return toBeReturn;
    }

    private int[] countOutputOfExamples() {
        int outputCount = this.aScheme.function.valueList.size();
        int[] count = new int[outputCount];
        for (int i = 0; i < outputCount; i++)
            for (Example ex : this.dataSet)
                if (ex.functionOuput() == i)
                    count[i]++;
        return 0;
    }

    // --------------------------------------------------------------------
    // Other function
    // --------------------------------------------------------------------

    public void addExample(Example ex) {
        this.dataSet.add(ex);
    }

    public Attribute getAttribute(ArrayList<Attribute> attribute) {
        double i = this.infoFmGp();
        Attribute best = null;
        double remainder = 0;
        double maxGain = -1;
        double gain = 0;
        // Loop and find the best
        for (Attribute cur : attribute) {
            remainder = this.getRemaindker(cur);
            gain = i - remainder;
            if (gain > maxGain) {
                maxGain = gain;
                best = cur;
            }
        }
        return best;
    }

    public int majorOutput() {
        int[] count = this.countOutputOfExamples();
        int largestIndex = -1;
        int largestCount = -1;

        // Loop and find the biggest index
        for (int x = 0; x < count.length; x++) {
            if (count[x] > largestCount) {
                largestCount = count[x];
                largestIndex = x;
            }
        }
        return largestIndex;
    }

    public int singleOuput() {
        int[] count = countOutputOfExamples();
        for (int x = 0; x < count.length; x++)
            if (count[x] == this.dataSet.size())
                return x;
        // If it could not find the value
        return -1;
    }
}// End class
