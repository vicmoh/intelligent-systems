import java.util.*;
import java.io.*;

class Attribute {
  String name;
  List<String> values;
  int pos;

  Attribute(String n, List<String> vals, int p) {
    this.name = n;
    this.values = vals;
    this.pos=p;
  }

  int getIndexOfValues(String toCheck) {
    for(int j = 0; j<this.values.size(); j++){
      /*System.out.println("Incoming: "+toCheck + " vs "+this.values.get(j));*/
      if(toCheck.equals(this.values.get(j))){
        return j;
      }
    }
    return -1;
  }
}
