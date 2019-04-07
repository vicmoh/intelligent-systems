import java.util.*;
import java.io.*;


class DTLearn {
  Scheme scheme;

  DTLearn(Scheme s){
    this.scheme = s;
  }
  Node learnDecisionTree(Sample s, List<Attribute> atList, int sMajor){
    /*for(Example testE : s.examples){
      System.out.println(Arrays.toString(testE.attributeValues));
    }*/

    if(s.examples.size() == 0){
      return new Node(scheme.function.values.get(sMajor));
    }
    if(s.checkIfAllSameClass() == true){
      return new Node(scheme.function.values.get(s.getMajorityClass(scheme)));
    }
    if(atList.size() == 0){
      return new Node(scheme.function.values.get(s.getMajorityClass(scheme)));
    }
    Attribute currentAttribute = s.getAttribute(atList, s);
    //System.out.println("Current attribute is: "+ currentAttribute.name);
    Node<String> tr = new Node(currentAttribute.name);
    int m = s.getMajorityClass(scheme);

    for(String value:currentAttribute.values){
      Sample subg = new Sample();
      for(Example e : s.examples){
        if(e.attributeValues[currentAttribute.pos] == currentAttribute.getIndexOfValues(value)){
          subg.examples.add(e);
        }
      }
      atList = Util.removeAttribute(currentAttribute, atList);
      Node<String> subTree = learnDecisionTree(subg, atList, m);

      subTree.setData(value + ": " +subTree.getData());
      tr.addChild(subTree);
    }
    return tr;
  }
  public static void main(String[] args){

    Scheme sc = new Scheme();
    Sample sample = new Sample();
    sc.loadSchemeFile(args[0]);
    sample.loadExamples(args[1], sc);
    DTLearn dtlearn = new DTLearn(sc);
    Util util = new Util();
    Node<String> root;
    //util.printAttrList(scheme);
    //util.printSample(sample);
    sc.setFunction();
    sc.attrList.remove(sc.attrList.size() - 1);
    root = dtlearn.learnDecisionTree(sample, sc.attrList, sample.getMajorityClass(sc));
    util.printTree(root, "-");

  }

}
