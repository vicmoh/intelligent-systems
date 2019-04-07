import java.util.*;
import java.io.*;

class Scheme {
    List<Attribute> attrList;
    Attribute function;

    Scheme() {
        this.attrList = new ArrayList<Attribute>();
    }

    void loadSchemeFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int numPara = sc.nextInt();
            /* Read the fist n-1 attrubutes */
            sc.nextLine();
            // System.out.println(numPara);
            for (int i = 0; i < numPara; i++) {
                sc.nextLine();
                String name = sc.nextLine();
                int numVals = sc.nextInt();
                sc.nextLine();
                String valsLine = sc.nextLine();
                List<String> tempAttrList = new ArrayList<String>();
                String[] attrArr = valsLine.split(" ");
                for (String a : attrArr) {
                    tempAttrList.add(a);
                }
                Attribute at = new Attribute(name, i, tempAttrList);
                this.attrList.add(at);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please enter a scheme file " + e);
        }
    }

    Attribute getFunctionAttribute() {
        return this.attrList.get(attrList.size() - 1);
    }

    void setFunction() {
        this.function = attrList.get(attrList.size() - 1);
    }

    int getIndexOfAttribute(String toCheck) {
        int j = 0;
        for (Attribute i : this.attrList) {
            if (i.attributeName.equals(toCheck)) {
                return j;
            }
            j++;
        }
        System.out.println("Could not find attribute");
        return -1;
    }

    void removeAttribute(Attribute toRemove) {
        int remIndex = -1;
        int countIndex = 0;
        for (Attribute a : this.attrList) {
            if (a.attributeName.equals(toRemove.attributeName)) {
                remIndex = countIndex;
            }
            countIndex++;
        }
        if (remIndex > -1) {
            this.attrList.remove(remIndex);
        }
    }
}
