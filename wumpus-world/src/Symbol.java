/**
 * This class is a proposition symbol
 * 
 * @author Vicky Mohammad
 */
public class Symbol {
    private String symbol; // a proposition symbol
    
    // constructor 
    Symbol(String proposition){
        this.symbol = proposition;    
    }//end constructor
    
    String getSymbol(){
        return this.symbol;
    }//end func

    @Override
    public String toString() {
        return this.symbol;
    }//end func
}// end class
