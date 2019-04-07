import java.util.*;
import java.io.*;

class Sample {
  List<Example> examples;
  Sample(){
    this.examples = new ArrayList();
  }

  void loadExamples(String fileName,Scheme scheme){
    try{
      Scanner sc = new Scanner(new File(fileName));
      sc.nextLine();
      while(sc.hasNextLine()){
        Example toAddExample = new Example(scheme.attrList.size());
        int attrIndex = 0;
        String[] line = sc.nextLine().split("\\s+");
        for(String s : line ){
          toAddExample.attributeValues[attrIndex] = scheme.attrList.get(attrIndex).getIndexOfValues(s);
          attrIndex++;
        }
        this.examples.add(toAddExample);
      }
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }
  double getInfo(List<Example> ex, int numClass) {
    int size = ex.size();
    int[] count = new int[numClass];
    for(int j = 0; j<numClass; j++){
      for(Example e : ex){
        if(j == e.getFunctionValue()){
          count[j]++;
        }
      }
    }
    //System.out.println(Arrays.toString(count));
    double I = 0;
    for(int j = 0; j<numClass; j++){
      double pr = (double)count[j]/size;
      if(pr>0){
        I = I - (pr * (Math.log(pr) / Math.log(2)));
      }
    }
    //System.out.println(I);
    return I;
  }
  double getRemainder(Attribute b, List<Example> ex, int k){
    //System.out.println("Attribute: " + b.name);
    int size = ex.size();
    int m = b.values.size();

    Sample[] subg = new Sample[m];
    int[] subCnt = new int[m];
    for(int i = 0; i<m; i++){
      subg[i] = new Sample();
    }
    for(int i = 0; i<m; i++) {
      for(Example e : ex ){
        if(e.attributeValues[b.pos] == i) {
          subg[i].examples.add(e);
        }
      }
    }
    for(int i = 0; i<m; i++){
      subCnt[i] = subg[i].examples.size();
    }
    double remainder = 0;


    for(int i = 0;i<m;i++){
      double pr = (double)subCnt[i]/size;
      double ii = getInfo(subg[i].examples,k);
      remainder += pr*ii;
      //System.out.println(pr+" "+ii+" "+remainder);
    }
    /*for(int i =0;i<m;i++){
      for(int j = 0; j<subg[i].examples.size(); j++){
        for(Example e:subg[i].examples){
          System.out.println(i + ": " + b.pos + ": " +Arrays.toString(e.attributeValues));
        }
      }
    }*/
    return remainder;

  }
  Attribute getAttribute(List<Attribute> attributeList, Sample sam){
    int k = attributeList.get(attributeList.size()-1).values.size();
    double info = getInfo(sam.examples, k);
    double maxGain = -1;
    Attribute bestA = null;
    for(Attribute b : attributeList){
      double remainder = getRemainder(b, sam.examples, k);
      double gain = info - remainder;
      System.out.println("Test " + b.name + ": gain = " + gain);
      if(gain > maxGain){
        maxGain = gain;
        bestA = b;
      }
    }
    System.out.println("\tSelected attribute: "+bestA.name);
    System.out.println();
    return bestA;
  }
  /*Returns the the index of the value that getsf the majority classification*/
  int getMajorityClass(Scheme sc){
    int[] arr = new int[sc.getFunctionAttribute().values.size()];
    for(Example ex : this.examples) {
      arr[ex.getFunctionValue()]++;
    }
    int highest = arr[0];
    int highestIndex = 0;
    for(int i = 0; i< arr.length; i++){
      if(arr[i] > highest){
        highest = arr[i];
        highestIndex = i;
      }
    }
    return highestIndex;
  }

  boolean checkIfAllSameClass(){
    int initialValue = this.examples.get(0).getFunctionValue();
    for(Example e : this.examples){
      if(e.getFunctionValue() != initialValue){
        return false;
      }
    }
    return true;
  }

}
