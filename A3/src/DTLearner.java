import java.util.*;
import java.io.*;

class DTLearner {
    Scheme scheme;
    DataSet dataSet;

    /**
     * create a decisive tree learner
     * 
     * @param scheme
     */
    DTLearner(Scheme scheme) {
        this.scheme = scheme;
    }// end constructor

    /**
     * cecisionTreeLearning method implements decision tree learning algorithm.
     * 
     * @param set
     * @param attributeList
     * @param sMajor
     * @return node of the tree
     */
    Node<String> decisionTreeLearning(DataSet set, List<Attribute> attributeList, int sMajor) {
        // check if example siz is zero
        if (set.examples.size() == 0)
            return new Node<String>(this.scheme.function.valueList.get(sMajor));
        // check if attribute size is zero
        if (attributeList.size() == 0)
            return new Node<String>(this.scheme.function.valueList.get(set.getMajorityValue(this.scheme)));
        // check if all of them are in the same class
        if (set.isAllSameClass())
            return new Node<String>(this.scheme.function.valueList.get(set.getMajorityValue(this.scheme)));
        // initialize the attribute the
        Attribute currentAttribute = set.getAttribute(attributeList, set);
        Node<String> tree = new Node<String>(currentAttribute.attributeName);
        int m = set.getMajorityValue(scheme);
        // for loop the attribute and sub
        for (String value : currentAttribute.valueList) {
            DataSet subg = new DataSet();
            for (Example e : set.examples)
                if (e.attributeValues[currentAttribute.numberOfValue] == currentAttribute.getIndexOfValues(value))
                    subg.examples.add(e);
            // recursively remove the old attributu and add to the tree
            Node<String> subTree = decisionTreeLearning(subg, Util.removeAttribute(currentAttribute, attributeList), m);
            subTree.setData(value + ": " + subTree.getData());
            tree.addChild(subTree);
        } // end for
        return tree;
    }// end function

    public static void main(String[] args) {
        // initialze and declare vaiables
        Scheme scheme = new Scheme();
        DataSet sample = new DataSet();
        scheme.loadSchemeFile(args[0]);
        sample.loadExamples(args[1], scheme);
        DTLearner dtlearn = new DTLearner(scheme);
        Util util = new Util();
        Node<String> root;
        // run the decision tree learning
        scheme.setFunction();
        scheme.attrList.remove(scheme.attrList.size() - 1);
        root = dtlearn.decisionTreeLearning(sample, scheme.attrList, sample.getMajorityValue(scheme));
        util.printTree(root, "-");

    }

}
