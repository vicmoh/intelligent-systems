import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.source.doctree.AttributeTree;

/**
 * Scheme class provides the definition of attributes and a function related to
 * data file. It stores scheme file attributes and the function
 * 
 * @author Vicky Mohammad
 */
public class Scheme {
    ArrayList<Attribute> attributeList; // attribute list of scheme file
    int numberOfAttribute = 0; // number of attributes
    Attribute function = null; // a function

    /**
     * Creates scheme object
     */
    public Scheme() {
        attributeList = new ArrayList<Attribute>();
    }// End constructor

    /**
     * Load the scheme file
     * 
     * @param schemeFile string of the path
     */
    public void loadSchemeFile(String schemeFile) {
        String fileName = schemeFile;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (Exception err) {
            System.out.println(err.toString());
        } // End try

        // Read the file
        String firstLine = null;
        System.out.println("loadSchemeFile(): Reading file...");

        try {
            // Get first line
            firstLine = br.readLine();
            if (firstLine == null || firstLine == "") {
                System.out.println("loadSchemeFile(): Fail to parse, wrong format.");
            }

            // Get the number of attribute
            this.numberOfAttribute = Integer.parseInt(firstLine);

            // Read each attribute
            for (int x = 0; x < this.numberOfAttribute - 1; x++) {
                if (br.readLine() == null)
                    break;
                String name = br.readLine();
                String val = br.readLine();
                String[] list = br.readLine().split(" ");
                ArrayList<String> vals = new ArrayList<String>();
                for (int y = 0; y < list.length; y++)
                    vals.add(list[y]);
                this.attributeList.add(new Attribute(name, Integer.parseInt(val), vals));
            }
        } catch (Exception err) {
            closeFile(br);
        }

        // Print the attributes
        this.printScheme();
    }// End function

    /**
     * Function to close the buffered reader for reading and writing files.
     * 
     * @param br the buffered reader object
     */
    public void closeFile(BufferedReader br) {
        try {
            br.close();
        } catch (Exception err) {
            System.out.println("closeFile(): failed to close file: " + err.toString());
        }
    }

    /**
     * Print thee Scheme
     */
    public void printScheme() {
        attributeList.forEach((k) -> {
            k.printAttribute();
        });
        System.out.println(numberOfAttribute);
        if (function == null) {
            System.out.println("Function: null");
        } else {
            System.out.println("Function: ");
            function.printAttribute();
        } // End if
    }// End function

    public static void main(String[] args) {
        Scheme s = new Scheme();
        s.loadSchemeFile("./assets/GolfScheme.txt");
        s.printScheme();
    }// End main
}// End class
