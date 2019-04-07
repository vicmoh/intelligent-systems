import java.util.ArrayList;

/**
 * Example class is used to store attribute values of a data example. It uses
 * integer to store attribute values.
 * 
 * @author Vicky Mohammmad
 */
public class Example {
    ArrayList<Integer> attributes;

    /**
     * Creates example object
     */
    public Example() {
        attributes = new ArrayList<Integer>();
    }// End constructor

    /**
     * This method adds values of a data example to attributes
     * 
     * @param value integer to be added to the example
     */
    public void add(int value) {
        attributes.add(value);
    }// End function

    /**
     * This method prints out an example's attributes
     */
    public void printExample() {
        attributes.forEach(k -> System.out.print(k + " "));
        System.out.println();
    }// End function

    //------------------------------------------------------------
    // Custom function
    //------------------------------------------------------------

    public int functionOutput(){
        if(attributes.size() == 0)
            return 0;
        return attributes.get(this.attributes.size()-1);
    }

}// End class
