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

    int getIndexOfAttribute(String toCheck) {
        int j = 0;
        for (Attribute i : this.attributeList) {
            if (i.attributeName.equals(toCheck))
                return j;
            j++;
        }
        System.out.println("Could not find attribute");
        return -1;
    }

    void removeAttribute(Attribute toRemove) {
        int remIndex = -1;
        int countIndex = 0;
        for (Attribute a : this.attributeList) {
            if (a.attributeName.equals(toRemove.attributeName)) {
                remIndex = countIndex;
            }
            countIndex++;
        }
        if (remIndex > -1) {
            this.attributeList.remove(remIndex);
        }
    }
}
