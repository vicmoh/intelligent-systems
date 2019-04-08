import java.util.*;
import java.io.*;

/**
 * Data set class is used to store training data set. It is also stored a scheme
 * related to data set.
 * 
 * @author Vicky Mohammad
 */
class DataSet {
    Scheme aScheme = null;
    List<Example> dataSet;
    int dataSetSize = 0;

    /**
     * creates the data base object
     */
    DataSet() {
        this.dataSet = new ArrayList<Example>();
    }// end constructor

    /**
     * creates the data base object
     * 
     * @param scheme
     */
    DataSet(Scheme scheme) {
        this.aScheme = scheme;
        this.dataSet = new ArrayList<Example>();
    }// end constructor

    /**
     * load data set file
     * 
     * @param dataSetFile path and name
     */
    void loadDataSetFile(String dataSetFile) {
        // declare and initialze
        Scanner scanner = null;
        // scan the data set from the file path
        try {
            scanner = new Scanner(new File(dataSetFile));
            scanner.nextLine();
            // loop through and scan the next line
            while (scanner.hasNextLine()) {
                int attributeIndex = 0;
                Example toAddExample = new Example(this.aScheme.attributeList.size());
                String[] line = scanner.nextLine().split(" ");
                for (int x = 0; x < line.length; x++) {
                    String token = line[x];
                    toAddExample.attributeValues[attributeIndex] = this.aScheme.attributeList.get(attributeIndex)
                            .getIndexVal(token);
                    attributeIndex = attributeIndex + 1;
                } // end for
                this.dataSet.add(toAddExample);
            } // end while
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } // end catch
        scanner.close();
    }// end function

    /**
     * This method print out data set
     */
    public void printDataSet() {
        dataSet.forEach((k) -> {
            k.printExample();
        });
    }// end function

    /**
     * get the best attribute
     * 
     * @param attributeList
     * @param data
     * @return the best attribute
     */
    Attribute getBestAttribute(List<Attribute> attributeList, DataSet data) {
        // declare variables
        int size = attributeList.get(attributeList.size() - 1).valueList.size();
        double entropy = getEntropy(data.dataSet, size);
        double maxGain = -1;
        Attribute bestAttribute = null;
        // loop through the attribute list
        for (Attribute currentAttribute : attributeList) {
            double remainder = getRemainder(currentAttribute, data.dataSet, size);
            double gain = entropy - remainder;
            System.out.println("\t" + currentAttribute.attributeName + ": information gain = " + gain);
            if (gain > maxGain) {
                bestAttribute = currentAttribute;
                maxGain = gain;
            } // end if
        } // end for
        System.out.println("\t\tChoose attribute: " + bestAttribute.attributeName);
        // System.out.println();
        return bestAttribute;
    }// end function

    /**
     * get the entropy information
     * 
     * @param examples
     * @param numberOfClass
     * @return entropy
     */
    double getEntropy(List<Example> examples, int numberOfClass) {
        // declare and initialize variables
        double result = 0;
        int size = examples.size();
        int[] count = new int[numberOfClass];
        // assigned the attribtue value
        for (int x = 0; x < numberOfClass; x++)
            for (Example e : examples)
                if (e.getFuncVal() == x)
                    count[x]++;
        // find the entropy value
        for (int x = 0; x < numberOfClass; x++) {
            double ratio = (double) count[x] / size;
            if (ratio > 0)
                result = result - (ratio * (Math.log(ratio) / Math.log(2)));
        } // end for
        return result;
    }// end function

    /**
     * This method calculate the remainder
     * 
     * @param attribute
     * @param examples
     * @param numberOfClass
     * @return a double remainder
     */
    double getRemainder(Attribute attribute, List<Example> examples, int numberOfClass) {
        // declare variables
        double remainder = 0;
        int exampleSize = examples.size();
        int attributeSize = attribute.valueList.size();
        int[] subCnt = new int[attributeSize];
        DataSet[] subg = new DataSet[attributeSize];
        // initialize sub data set
        for (int x = 0; x < attributeSize; x++)
            subg[x] = new DataSet();
        // add each example
        for (int x = 0; x < attributeSize; x++)
            for (Example curExample : examples)
                if (curExample.attributeValues[attribute.numberOfValue] == x)
                    subg[x].dataSet.add(curExample);
        // set the data
        for (int x = 0; x < attributeSize; x++)
            subCnt[x] = subg[x].dataSet.size();
        // get the remainder
        for (int x = 0; x < attributeSize; x++) {
            double pr = (double) subCnt[x] / exampleSize;
            double entropy = getEntropy(subg[x].dataSet, numberOfClass);
            remainder += pr * entropy;
        } // end for
        return remainder;
    }// end function

    /**
     * Returns the the index of the value that getsf the majority classification
     * 
     * @param scheme
     * @return largest index
     */
    int getMajorityValue(Scheme scheme) {
        // declare variables
        int[] array = new int[scheme.getFunctionAttribute().valueList.size()];
        int largest = array[0];
        int largestIndex = 0;
        // loop through the examples
        for (Example example : this.dataSet)
            array[example.getFuncVal()]++;
        // find the largest index
        for (int x = 0; x < array.length; x++) {
            if (array[x] > largest) {
                largest = array[x];
                largestIndex = x;
            } // end if
        } // end for
        return largestIndex;
    }// end function

    /**
     * check if is all same class
     * 
     * @return true if is all same class
     */
    boolean isAllSameClass() {
        int initialValue = this.dataSet.get(0).getFuncVal();
        for (Example each : this.dataSet)
            if (each.getFuncVal() != initialValue)
                return false;
        // else return true
        return true;
    }// end function
}// end class
