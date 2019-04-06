import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Scheme class provides the definition of attributes and a function related to data file.
 * It stores scheme file attributes and the function
 * @author Le
 */
public class Scheme {  
    ArrayList <Attribute> attributeList; //attribute list of scheme file
    int numberOfAttribute = 0; // number of attributes
    Attribute function = null;    //a function
    
    public Scheme(){
        attributeList = new ArrayList<Attribute>();
    }
    public void loadSchemeFile(String schemeFile){
        
    }
    public void printScheme(){
        attributeList.forEach((k)->{ k.printAttribute();});
        System.out.println(numberOfAttribute);
        if(function == null) 
            System.out.println("Function: null");
        else{
            System.out.println("Function: ");
            function.printAttribute();
        }
    }
    public static void main(String [] args){
        Scheme s = new Scheme();
        s.loadSchemeFile("GolfScheme.txt");
        s.printScheme();
    }
    

}
