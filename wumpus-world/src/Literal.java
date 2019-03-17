/**
 * A literal is either a positive or negative atomic sentence (single symbol)
 * 
 * @author Vicky Mohammad
 */
public class Literal {
    private Symbol symbol; // a propositon symbol
    private String symbolSign; // a sign of proposition symbol + or -
    private boolean sign; // Every symbol must have a sign. It is a negative or positive sign.

    Literal(){
        // empty literal
    }//end constructor

    /**
     * @param symbol a propositon symbol
     * @param symbolSign a sign of proposition symbol + or -
     * @param sign Every symbol must have a sign. It is a negative or positive sign.
     */
    Literal(Symbol symbol, String symbolSign, boolean sign){
        this.symbol = symbol;
        this.symbolSign = symbolSign;
        this.sign = sign;
    }//end constructor

    Symbol getSymbol(){
        return this.symbol;
    }//end func

    String getSymbolSign(){
        return this.symbolSign;
    }//end func

    boolean getSign(){
        return this.sign;
    }//end func
}// end class
