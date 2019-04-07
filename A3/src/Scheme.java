import java.util.*;
import java.io.*;

class Scheme {
    ArrayList<Attribute> attributeList; // attribute list of scheme file
    int numberOfAttribute = 0; // number of attributes
    Attribute function = null; // a function

    /**
     * create scheme
     */
    Scheme() {
        this.attributeList = new ArrayList<Attribute>();
    }// end constructor

    /**
     * load scheme file
     * 
     * @param fileName
     */
    void loadSchemeFile(String fileName) {
        Scanner scanner = null;
        try {
            // read each line
            scanner = new Scanner(new File(fileName));
            int numPara = scanner.nextInt();
            scanner.nextLine();
            // loop through and read each attribute section
            for (int i = 0; i < numPara; i++) {
                scanner.nextLine();
                String name = scanner.nextLine();
                scanner.nextInt();
                scanner.nextLine();
                // loop through the last data and split the value attribute
                // and added to the list
                String lineVals = scanner.nextLine();
                List<String> tempAttributeList = new ArrayList<String>();
                String[] attributeArray = lineVals.split(" ");
                for (String a : attributeArray)
                    tempAttributeList.add(a);
                // add to the attribute list
                Attribute newAttribute = new Attribute(name, i, tempAttributeList);
                this.attributeList.add(newAttribute);
            } // end for
        } catch (FileNotFoundException error) {
            System.out.println("Please enter a scheme file " + error);
        } // end catch
        scanner.close();
    }// end function

    /**
     * set function
     */
    void setFunction() {
        this.function = attributeList.get(attributeList.size() - 1);
    }// end function

    /**
     * get function
     * 
     * @return functioni attributes
     */
    Attribute getFunctionAttribute() {
        return this.attributeList.get(attributeList.size() - 1);
    }// end function

    int getIndexOfAttribute(String toBeCheck) {
        for (int x = 0; x < this.attributeList.size(); x++) {
            Attribute curAttribute = this.attributeList.get(x);
            if (curAttribute.attributeName.equals(toBeCheck))
                return x;
        } // end for
        System.out.println("Could not find attribute");
        return -1;
    }// end function

    /**
     * remove the attribute
     * 
     * @param toBeRemove
     */
    void removeAttribute(Attribute toBeRemove) {
        int removeIndex = -1;
        int countIndex = 0;
        // loop through the attribute list and find the matching val to remove
        for (Attribute curAttribute : this.attributeList) {
            if (curAttribute.attributeName.equals(toBeRemove.attributeName))
                removeIndex = countIndex;
            countIndex++;
        } // end for

        // if remain index is greater than zero
        if (removeIndex >= 0)
            this.attributeList.remove(removeIndex);
    }// end function
}// end class
