import java.util.*;
import java.io.*;

/**
 * The util is the extra helper file needed for AI
 */
class Util {
    /**
     * ceatil an util for helper function
     */
    Util() {
    }// end constructor

    /**
     * print attribute list
     * 
     * @param scheme
     */
    void printAttrList(Scheme scheme) {
        for (Attribute attribute : scheme.attributeList) {
            System.out.println(attribute.attributeName);
            for (String attributeValue : attribute.valueList)
                System.out.print(attributeValue + " ");
            // print the atttribute list
            System.out.println();
        } // end for
    }// end function

    /**
     * print sample
     * 
     * @param sample
     */
    void printSample(DataSet sample) {
        for (Example example : sample.dataSet) {
            for (int i = 0; i < example.attributeValues.length; i++)
                System.out.print(example.attributeValues[i] + " ");
            // print the sample
            System.out.println();
        } // end for
    }// end function

    /**
     * remove an attribute
     * 
     * @param toRemove
     * @param list
     * @return list of attribute that has been removed
     */
    static List<Attribute> removeAttribute(Attribute toRemove, List<Attribute> list) {
        List<Attribute> newList = new ArrayList<Attribute>();
        for (Attribute attribute : list)
            if (attribute.attributeName.equals(toRemove.attributeName) == false)
                newList.add(attribute);
        // return the remove attribute list
        return newList;
    }// end function

    /**
     * print a tree
     * 
     * @param <T>
     * @param node
     * @param appender
     */
    static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + "> " + node.getData());
        node.getChildren().forEach(each -> printTree(each, appender + "-"));
    }// end function
}// end class
