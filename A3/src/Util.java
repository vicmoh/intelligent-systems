import java.util.*;
import java.io.*;

class Util {
  Util() {

  }

  void printAttrList(Scheme scheme) {
    for (Attribute a : scheme.attrList) {
      System.out.println(a.attributeName);
      for (String s : a.valueList) {
        System.out.print(s + " ");
      }
      System.out.println();
    }
  }

  void printSample(DataSet sample) {
    for (Example e : sample.dataSet) {
      for (int i = 0; i < e.attributeValues.length; i++) {
        System.out.print(e.attributeValues[i] + " ");
      }
      System.out.println();
    }
  }

  static List<Attribute> removeAttribute(Attribute toRemove, List<Attribute> lst) {
    List<Attribute> newList = new ArrayList<Attribute>();
    for (Attribute a : lst) {
      if (a.attributeName.equals(toRemove.attributeName) == false) {
        newList.add(a);
      }
    }
    return newList;
  }

  static <T> void printTree(Node<T> node, String appender) {
    System.out.println(appender + node.getData());
    node.getChildren().forEach(each -> printTree(each, appender + appender));
  }
}
