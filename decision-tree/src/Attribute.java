import java.util.ArrayList;

/**
 * Attribute class is used to store an attribute. An attribute has its name, number of values and its possible values.
 * @author Le
 */
public class Attribute {
    //an attribute name
    String attributeName;
    //number of values of an attribute
    int numberOfValue;
    //an array list of attribute values
    ArrayList<String> valueList;

    public Attribute() {
    }

    public Attribute(String attributeName, int numberOfValue, ArrayList<String> valueList) {
        this.attributeName = attributeName;
        this.numberOfValue = numberOfValue;
        this.valueList = valueList;
    }

    public void printAttribute() {
        System.out.println(attributeName);
        System.out.println(numberOfValue);
        valueList.forEach((k)->{ System.out.print(k + " ");});
        System.out.println();
    }


    
    
}
