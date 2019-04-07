import java.util.*;
import java.io.*;

class Example {
  ArrayList<Integer> attributes;
  int[] attributeValues;
  int functionValue;
  int arraySize;

  Example(int numAttr) {
    this.attributeValues = new int[numAttr];
    this.arraySize = numAttr;
  }

  void setFunctionValue(int fVal) {
    this.functionValue = fVal;
  }

  void setAttributeValues(int[] list) {
    this.attributeValues = list;
  }

  int getFunctionValue() {
    return this.attributeValues[arraySize - 1];
  }

  // This method prints out an example's attributes
  public void printExample() {
    attributes.forEach(k -> System.out.print(k + " "));
    System.out.println();
  }
}
