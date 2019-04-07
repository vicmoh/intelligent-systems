import java.util.*;
import java.io.*;

class Example {
  int[] attributeValues;
  int functionValue;
  int arraySize;
  Example(int numAttr){
    this.attributeValues = new int[numAttr];
    this.arraySize=numAttr;
  }
  void setFunctionValue(int fVal){
    this.functionValue = fVal;
  }
  void setAttributeValues(int[] list){
    this.attributeValues = list;
  }
  int getFunctionValue(){
    return this.attributeValues[arraySize-1];
  }
}
