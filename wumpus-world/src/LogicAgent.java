import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author Vicky Mohammad Logic agents for the knowledge base of the AI.
 */
public class LogicAgent {
    HornKB knowledgeBase;
    List<String> hornKBFile = new ArrayList<String>();
    String query = null;

    public static void main(String[] args) {
        // assign the file name
        String hornKBFileName = args[0]; // HornKB.txt
        String queryFileName = args[1]; // Query1.txt
        Helper.debug("main(): hornKBfile = " + hornKBFileName);
        Helper.debug("main(): queryFileName = " + queryFileName);
        
        // read the horn file
        LogicAgent agent = new LogicAgent();
        agent.loadKB(hornKBFileName);
    }// main function to run the program

    public HornKB getKnowledgeBase() {
        return knowledgeBase;
    }// end func

    public void setKnowledgeBase(HornKB knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }// end func

    public LogicAgent() {
        knowledgeBase = new HornKB();
    }// end func

    public LogicAgent(HornKB knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }// end func

    /**
     * tell method adds the horn clause into the knowledge
     */
    public void tell(HornClause clause) {
        // add clause to KB
    }//end func

    /**
     * ask method check the entailment for the argument horn clause.
     * 
     * @return true or false true if it can be entailed false otherwise
     */
    public boolean ask(HornClause clause) {
        return false;
    }// end func

    // Load query from file.
    public String getQueryClause(String file) {
        return null;
    }// end func

    // Load KB file to Knowledgebase.
    public void loadKB(String file) {
        // set the file
        String fileName = file;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(fileName));
        }catch(Exception err){
            Helper.error("LoadKB(): Unable to readfile.");
            Helper.error(err.toString());
            closeFile(br);
        }//end try
        
        // loop and read each file
        String line = null;
        Helper.debug("Reading file...");
        do{
            try{
                line = br.readLine();
                if(line == null) break;
                this.hornKBFile.add(line);
                Helper.debug(line);
            }catch(Exception err){
                closeFile(br);
            }//end try
        }while(line != null);
    }// end func

    public void closeFile(BufferedReader br){
        try{
            br.close();
        }catch(Exception brErr){
            System.out.println("closeFile(): failed to close file: " + brErr.toString());
        }//end catch
    }//end func
}//end class

class Helper{
    public static final String RESET = "\033[0m";  
    // background colors
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    public static void debug(String toBePrinted){
        System.out.println(PURPLE + toBePrinted + RESET);
    }//end func
    
    public static void error(String toBePrinted){
        System.out.println(RED + "Try Catch: " + PURPLE + toBePrinted + RESET);
    }//end func

    public static String cyan(String string){
        return CYAN + string + RESET;
    }//end func

    public static String yellow(String string){
        return YELLOW + string + RESET;
    }//end func
    
    public static String green(String string){
        return GREEN + string + RESET;
    }//end func

    public static String red(String string){
        return RED + string + RESET;
    }//end func

    public static String header(String string){
        return Helper.yellow("----------<<<( " + string + " )>>>----------");
    }//end func
}//end func
