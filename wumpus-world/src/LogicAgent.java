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

    public static void main(String[] args) {
        System.out.println("main():");
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
    }

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

    }// end func
}
