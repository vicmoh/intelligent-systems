import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * A class that contains horn clause knowledge base.
 * @author Vicky Mohammad
 */
public class HornKB {
  private ArrayList<HornClause> knowledgeBase; // Knowledge base consists of horn clauses.
  ArrayList<HornClause> agenda = new ArrayList<HornClause>();
  HashMap<String, Boolean> inferred = new HashMap<String, Boolean>();

  HornKB(){
    this.knowledgeBase = new ArrayList<HornClause>();
  }//end constructor

  HornKB(ArrayList<HornClause> kb){
    this.knowledgeBase = kb;
  }//end consrtuctor

  public ArrayList<HornClause> getKB(){
    return this.knowledgeBase;
  }//end func

  /**
   * plFcEntail method - It is an implementation of Forward chaining algorithm
   * function PL-FC-ENTAILS?(KB, q) returns true or false inputs: KB, the
   * knowledge base, a set of propositional definite clauses q, the query, a
   * proposition symbol
   *
   * count ←a table, where count [c] is the number of symbols in c’s premise
   * inferred ←a table,where inferred[s] is initially false for all symbols agenda
   * ←a queue of symbols, initially symbols known to be true in KB
   *
   * while agenda is not empty do p←POP(agenda) if p = q then return true if
   * inferred[p] = false then inferred[p]←true for each clause c in KB where p is
   * in c.PREMISE do decrement count [c] if count [c] = 0 then add c.CONCLUSION to
   * agenda return false
   *
   * @return true or false true if it can be entailed false otherwise
   * @author Vicky Mohammad
   */
  public boolean plFcEntail(Literal q) {
    Helper.debug("plFcEntail(): ", "q = " + q.toString());
    // set up the agenda to start of
    for(int x=0; x>kb.size(); x++){
      if(this.knowledgeBase.get(x).getHead().equals(q)){
        this.agenda.add(this.knowledgeBase.get(x));
      }//end if
    }//end for

    // search for answer
    while (!this.agenda.isEmpty()) {
      for(int x=0; x<knowledgeBase.size(); x++){
        HornClause hc = knowledgeBase.get(x);
        if(hc.getHead().getSymbolString().equalsIgnoreCase("")){
          
        }//end if
      }//end for
    }// end while
    return false;
  }//end func
}//end class
