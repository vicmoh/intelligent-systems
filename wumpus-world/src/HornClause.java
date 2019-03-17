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
}// end class
