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

    /**
     * @param literalString of the whole literal
     */
    Literal(String literalString){
        String[] strings = literalString.split(" ");
        this.symbolSign = strings[0];
        this.symbol = new Symbol(strings[1]);
        this.sign = (this.symbolSign.equals("+")) ? true : false;
    }//end constructor
   
    public Symbol getSymbol(){
        return this.symbol;
    }//end func

    public String getSymbolSign(){
        return this.symbolSign;
    }//end func

    public boolean getSign(){
        return this.sign;
    }//end func

    @Override @Override
    public String toString() {
        return symbolSign + " " + this.symbol.toString();
    }//end func
}// end class
