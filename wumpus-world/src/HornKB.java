import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * A class that contains horn clause knowledge base.
 * 
 * @author Vicky Mohammad
 */
public class HornKB {
  private ArrayList<HornClause> knowledgeBase; // Knowledge base consists of horn clauses.
  private Stack<Literal> agenda = new Stack<Literal>();
  private HashMap<String, Boolean> inferred = new HashMap<String, Boolean>();
  private HashMap<HornClause, Integer> count = new HashMap<HornClause, Integer>();
  private HashMap<String, ArrayList<HornClause>> premise = new HashMap<String, ArrayList<HornClause>>();

  HornKB() {
    this.knowledgeBase = new ArrayList<HornClause>();
  }// end constructor

  HornKB(ArrayList<HornClause> kb) {
    this.knowledgeBase = kb;
  }// end consrtuctor

  public void addKnowledge(HornClause toBeAdded){
    this.knowledgeBase.add(toBeAdded);
  }//end func

  public ArrayList<HornClause> getKB() {
    return this.knowledgeBase;
  }// end func

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

    // find all knowledge head clause that is to be true
    for(int x=0; x<this.knowledgeBase.size(); x++){
      HornClause hc = this.knowledgeBase.get(x);
      // inferred the head 
      if(hc.getBody().isEmpty()) this.agenda.push(hc.getHead());
      this.inferred.put(hc.getHead().getSymbolString(), false);
      // set count
      this.count.put(hc, hc.getBody().size());
      // set the primse
      this.premise.put(hc.getHead().getSymbolString(), this.knowledgeBase);
      // inferred the body
      for(int y=0; y<hc.getBody().size(); y++){
        this.inferred.put(hc.getBody().get(y).getSymbolString(), false);
      }
    }
    
    while(!this.agenda.isEmpty()){
      Literal lit = agenda.pop();

      Helper.debug("plFcEntail(): ", "lit = " + lit.getSymbolString());

      // check if it finds the clause
      if(lit.equals(q)){
        return true;
      }//end if

      if(!this.inferred.get(lit.getSymbolString())){
        Helper.debug("plFcEntail(): ", "debug = " + Helper.cyan(lit.getSymbolString()));
        // add to the infereed
        inferred.put(lit.getSymbolString(), true);
        // find all the premise
        if(this.premise.get(lit.getSymbolString()) != null){
          // go through the premise
          this.premise.get(lit.getSymbolString()).forEach(clause -> {
            Integer numOfPremise = count.get(clause);
            
            if(numOfPremise != null){
              numOfPremise = numOfPremise - 1;
              count.put(clause, numOfPremise);
              
              if(numOfPremise == 0){
                // added to the agenda
                this.agenda.add(clause.getHead());
                // print inferred
                System.out.println(
                  Helper.yellow("Symbol: ") + 
                  Helper.red(clause.getHead().toString()) + 
                  Helper.yellow(" is inferred")
                );
              }
            }
          });
        }//end if
      }//end if
    }//end while

    return false;
  }// end func
}// end class
