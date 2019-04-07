import java.util.ArrayList;

class Attribute {
    // Attributes
    public String name;
    public ArrayList<String> values;
    public boolean isFunc;

    // Constructors
    public Attribute(String name) {
        this.name = name;
        this.values = new ArrayList<String> ();
    }

    public Attribute(String name, ArrayList<String> values, boolean isFunc) {
        this.name = name;
        this.values = values;
        this.isFunc = isFunc;
    }
}
