import java.util.ArrayList;
import java.util.ListIterator;

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

    public HornClause(String line) {
        // init the instances
        withHead = false; // start with false; once populate with head then set to true
        body = new ArrayList<Literal>();
        head = new Literal();

        // parse the line
        String strBuff = "";
        ArrayList<Literal> tempLit = new ArrayList<Literal>();
        for (int x = 0; x < line.length(); x++) {
            char charBuff = line.charAt(x);

            // get the first part
            if (charBuff == '+' || charBuff == '-') {
                strBuff += Character.toString(charBuff) + " ";
                x++;
                continue;
            } // end if

            // case for the end of the line
            if (x == line.length() - 1 && Character.isDigit(charBuff)) {
                strBuff += Character.toString(charBuff);
                tempLit.add(new Literal(strBuff));
                break;
            } // end if

            // case where literal is not the last
            if (line.charAt(x) != ' ') {
                strBuff += Character.toString(charBuff);
            } else {
                tempLit.add(new Literal(strBuff));
                strBuff = "";
            } // end else
        } // end for

        // added to the instance
        this.head = tempLit.get(tempLit.size()-1);
        this.withHead = true;
        if(tempLit.size() > 1){
            for(int x=0; x<tempLit.size()-1; x++){
                this.body.add(tempLit.get((x)));
            }//end for
        }//end if
    }// end func

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
    public boolean equals(Object obj) {
        if(!(obj instanceof Literal)) return false;
        Literal compare = (String)(obj);
        return this.getHead().getSymbolString().equalsIgnoreCase(compare.getSymbolString());
    }//end func

    @Override
    public String toString() {
        String toBeReturn = "";
        for (int x = 0; x < this.body.size(); x++) {
            Literal lit = this.body.get(x);
            toBeReturn += lit.toString() + " ";
        } // end for
        toBeReturn += this.head.toString();
        return toBeReturn;
    }//end func
}// end class
