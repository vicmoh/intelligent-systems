import java.util.ArrayList;

/**
 * Horn clause class.
 * 
 * @author Vicky Mohammmad
 */
public class HornClause {
    private ArrayList<Literal> body; // Premises consists of conjunction of literal.
    private Literal head; // Conclusion consists of a single literal.
    private boolean withHead; // bool to let user know if its with head.

    public HornClause() {
        withHead = false; // start with false; once populate with head then set to true
        body = new ArrayList<Literal>();
        head = new Literal();
    }// end constructor

    public HornClause(String line){
        String strBuff = "";
        boolean foundStart = false;
        for(int x=0; x<line.length; x++){
            char charBuff = line.charAt(x);
            
            // get the first part
            if(charBuff == '+' || charBuff == '-'){
                foundStart = true;
                strBuff+= Character.toString(charBuff);
                x++;
                continue;
            }//end if

            // case where literal is not the last
            if(line.charAt(x+1) != ' '){
                strBuff+= Character.toString(charBuff);
                continue;
            }else{
                Helper.debug("HornClause(): adding '"+strBuff.toString()+"'");
                this.body.add(new Literal(strBuff));
            }//end else

            // case for the end of the line
            if(x != line.length()-1 && Character.isDigit(charBuff)){
                Helper.debug("HornClause(): adding '"+strBuff.toString()+"'");
                this.body.add(new Literal(strBuff));
                break;   
            }//end if
        }//end for
    }//end func

    public ArrayList<Literal> getBody() {
        return body;
    }// class func

    public void setBody(ArrayList<Literal> body) {
        this.body = body;
    }// end func

    public Literal getHead() {
        return head;
    }// end func

    public void setHead(Literal head) {
        this.head = head;
    }// end func

    public boolean isWithHead() {
        return withHead;
    }// end func

    public void setWithHead(boolean withHead) {
        this.withHead = withHead;
    }// end func

    @Override
    public String toString() {
        String toBeReturn = "";
        for(int x=0; x<this.body.size(); x++){
            Literal lit = this.body.get(x);
            toBeReturn+= (x != this.size()-1) ? lit.toString() + " " : lit.toString();
        }//end for
        return toBeReturn;
    }
}// end class
