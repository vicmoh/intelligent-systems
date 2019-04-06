import java.util.ArrayList;

/**
 * Example class is used to store attribute values of a data example. It uses integer to store attribute values.
 * @author Le
 */
public class Example {
    ArrayList<Integer> attributes;
    public Example() {
        attributes = new ArrayList<Integer>();
    }
    
    //This method adds values of a data example to attributes
    public void add(int value) {
        attributes.add(value);
    }
    
    //This method prints out an example's attributes
    public void printExample() {
        attributes.forEach(k->System.out.print(k + " "));
        System.out.println();
    }
    
}
