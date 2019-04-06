import java.util.ArrayList;

/**
 * Attribute class is used to store an attribute. 
 * An attribute has its name, number of values and its possible values.
 * @author Vicky Mohammad
 */
public class Attribute {
    String attributeName;
    int numberOfValue;
    ArrayList<String> valueList;

    /**
     * Initilize the attribute
     */
    public Attribute() {

    }// End constructor

    /**
     * Initilize the attribute
     * @param attributeName an attribute name
     * @param numberOfValue number of values of an attribute
     * @param valueList an array list of attribute values
     */
    public Attribute(String attributeName, int numberOfValue, ArrayList<String> valueList) {
        this.attributeName = attributeName;
        this.numberOfValue = numberOfValue;
        this.valueList = valueList;
    }// End constructor

    /**
     * Print the attribute
     */
    public void printAttribute() {
        System.out.println(attributeName);
        System.out.println(numberOfValue);
        valueList.forEach((k)->{ System.out.print(k + " ");});
        System.out.println();
    }// End print function
}// End class
