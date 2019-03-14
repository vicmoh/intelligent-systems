import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Le
 */
public class LogicAgent {
    HornKB knowledgeBase;

    public static void main(String [] args){
      System.out.println("main():");
    }

    public HornKB getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(HornKB knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }
    
    public LogicAgent() {
        knowledgeBase = new HornKB();
    }
    
    public LogicAgent(HornKB knowledgeBase){
        this.knowledgeBase = knowledgeBase;
    }
    
/**
 * tell method adds the horn clause into the knowledge
 * @author Le
 */    
    public void tell(HornClause clause){
        //add clause to KB
    }
    
 /**
 * ask method check the entailment for the argument horn clause
 * @return true or false  true if it can be entailed false otherwise
 * @author Le
 */     
    public boolean ask(HornClause clause){
			return false;
    }

    //Load query from file. 
    public String getQueryClause ( String file ) {
		return null;
    }
    //Load KB file to Knowledgebase.      
    public void loadKB(String file){
              
    }
    

}
