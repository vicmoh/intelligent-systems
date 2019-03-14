import java.util.ArrayList;

/**
 *
 * @author Le
 */
public class HornClause {

    private ArrayList<Literal> body;  //Premises consists of conjunction of literal
    private Literal head; //Conclusion consists of a single literal
    private boolean withHead; //

    public HornClause() {
        withHead = false; //start with false; once populate with head then set to true
        body = new ArrayList<Literal>();
        head = new Literal();
    }

    public ArrayList<Literal> getBody() {
        return body;
    }

    public void setBody(ArrayList<Literal> body) {
        this.body = body;
    }

    public Literal getHead() {
        return head;
    }

    public void setHead(Literal head) {
        this.head = head;
    }

    public boolean isWithHead() {
        return withHead;
    }

    public void setWithHead(boolean withHead) {
        this.withHead = withHead;
    }

}
