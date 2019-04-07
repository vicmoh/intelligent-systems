import java.util.*;
import java.io.*;

class DTLearner {
    Scheme scheme;

    DTLearner(Scheme s) {
        this.scheme = s;
    }

    Node<String> learnDecisionTree(Sample s, List<Attribute> atList, int sMajor) {
        if (s.examples.size() == 0) {
            return new Node<String>(scheme.function.valueList.get(sMajor));
        }
        if (s.checkIfAllSameClass() == true) {
            return new Node<String>(scheme.function.valueList.get(s.getMajorityClass(scheme)));
        }
        if (atList.size() == 0) {
            return new Node<String>(scheme.function.valueList.get(s.getMajorityClass(scheme)));
        }
        Attribute currentAttribute = s.getAttribute(atList, s);
        // System.out.println("Current attribute is: "+ currentAttribute.name);
        Node<String> tr = new Node<String>(currentAttribute.attributeName);
        int m = s.getMajorityClass(scheme);

        for (String value : currentAttribute.valueList) {
            Sample subg = new Sample();
            for (Example e : s.examples) {
                if (e.attributeValues[currentAttribute.numberOfValue] == currentAttribute.getIndexOfValues(value)) {
                    subg.examples.add(e);
                }
            }
            atList = Util.removeAttribute(currentAttribute, atList);
            Node<String> subTree = learnDecisionTree(subg, atList, m);

            subTree.setData(value + ": " + subTree.getData());
            tr.addChild(subTree);
        }
        return tr;
    }

    public static void main(String[] args) {

        Scheme sc = new Scheme();
        Sample sample = new Sample();
        sc.loadSchemeFile(args[0]);
        sample.loadExamples(args[1], sc);
        DTLearner dtlearn = new DTLearner(sc);
        Util util = new Util();
        Node<String> root;
        // util.printAttrList(scheme);
        // util.printSample(sample);
        sc.setFunction();
        sc.attrList.remove(sc.attrList.size() - 1);
        root = dtlearn.learnDecisionTree(sample, sc.attrList, sample.getMajorityClass(sc));
        util.printTree(root, "-");

    }

}
