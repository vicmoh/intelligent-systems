import java.util.*;
import java.io.*;

/**
 * Example class is used to store attribute values of a data example. It uses
 * integer to store attribute values.
 * 
 * @author Vicky Mohammad
 */
class Example {
    ArrayList<Integer> attributes;
    int[] attributeValues;
    int functionValue;
    int arraySize;

    /**
     * create example object
     * 
     * @param numberAttribute
     */
    Example(int numberAttribute) {
        this.attributeValues = new int[numberAttribute];
        this.arraySize = numberAttribute;
    }// end constructor

    /**
     * set attribute values
     * 
     * @param list
     */
    void setAttributeValues(int[] list) {
        this.attributeValues = list;
    }// end function

    /**
     * set function value
     */
    void setFuncVal(int val) {
        this.functionValue = val;
    }// end function

    /**
     * get function value
     * 
     * @return the function value
     */
    int getFuncVal() {
        return this.attributeValues[arraySize - 1];
    }// end function

    /**
     * print example
     */
    public void printExample() {
        attributes.forEach(k -> System.out.print(k + " "));
        System.out.println();
    }// end function
}// end function
