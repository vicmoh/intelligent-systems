import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Scheme {
    // Attributes
    public ArrayList<Attribute> attributes;
    public Attribute function;

    // Constructors
    public Scheme(String filename) {
        loadSchemeFile(filename);
    }

    public Scheme(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    // Private Methods
    private void loadSchemeFile(String schemeFilename) {
        try (BufferedReader br = new BufferedReader(new FileReader(schemeFilename))) {
            int paragraphCount = -1;

            for (String line; (line = br.readLine()) != null; ) {
                if (line.length() != 0) {
                    line = line.trim();
                    // Check if it's the first line
                    if (paragraphCount == -1) {
                        paragraphCount = Integer.parseInt(line);
                        attributes = new ArrayList<Attribute>(paragraphCount);
                    } else {
                        // First set attribute name
                        String name = line;
                        line = br.readLine().trim();

                        // Read attribute count
                        int count = Integer.parseInt(line);
                        ArrayList<String> values = new ArrayList<String>(count);
                        line = br.readLine().trim();

                        // Read attribute values
                        String[] s = line.split("\\s+");
                        for (String val : s) {
                            values.add(val);
                        }

                        // create Attribute
                        if (paragraphCount != 1) {
                            attributes.add(new Attribute(name, values, false));
                        } else {
                            function = new Attribute(name, values, true);
                        }
                        
                        paragraphCount -= 1;
                    }
                }
            }
            // line is not visible here.
        } catch (IOException e) {
            System.out.println("ERROR: something went wrong reading from file: " + schemeFilename);
            e.printStackTrace();
        }

        return;
    } 
}
