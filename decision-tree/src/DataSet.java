import java.util.ArrayList;

/**
 * Data set class is used to store training data set. It is also stored a scheme related to data set.
 * @author Le
 */
public class DataSet {
    Scheme aScheme = null;
    ArrayList<Example> dataSet;
    int dataSetSize = 0;

    /*This method loads a data file and store values in array list dataSet based on scheme file*/
    public void loadDataSetFile(String dataSetFile){
        
    }
    /*This method print out data set*/
    public void printDataSet(){
        dataSet.forEach((k)->{k.printExample();});
    }
     
    /*
        The method getBestAttribute it calculates information gain for the attributes
        It returns the attribute with max gain.
    */
    public Attribute getBestAttribute(){
        return null;
    }
    
    /* Calculate the entropy */
    public double getEntropy(ArrayList<Example> g, int h){
        return 0.0;
    }
    
    /* This method calculate the remainder */
    public double getRemainder(Attribute a, ArrayList<Example> g ,ArrayList<Attribute> attributeList, int h){
        return 0.0;
    }
    
}
