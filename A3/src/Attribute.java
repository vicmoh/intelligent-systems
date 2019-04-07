import java.util.*;
import java.io.*;

/**
 * Attribute class is used to store an attribute. 
 * An attribute has its name, number of values and 
 * its possible values.
 * @author Vicky Mohammad
 */
class Attribute {
  String attributeName;
  int numberOfValue;
  List<String> valueList;

  /**
   * Creates attributue object
   * 
   * @param attributeName
   * @param valueList
   * @param numberOfValue
   */
  Attribute(String attributeName, int numberOfValue, List<String> valueList) {
    this.attributeName = attributeName;
    this.valueList = valueList;
    this.numberOfValue = numberOfValue;
  }// end construtor

  /**
   * 
   * @param toBeCheck
   * @return int value of the index, return -1 if doesn't exist
   */
  int getIndexOfValues(String toBeCheck) {
    for (int x = 0; x < this.valueList.size(); x++)
      if (toBeCheck.equals(this.valueList.get(x)))
        return x;
    return -1;
  }// end function

  /**
   * a function to print the attribute
   */
  public void printAttribute() {
    System.out.println(attributeName);
    System.out.println(numberOfValue);
    valueList.forEach((k) -> {
      System.out.print(k + " ");
    });
    System.out.println();
  }// end function
}// end class
