import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Sample {
    // Attributes
    public ArrayList<Example> trainingSet;
    private Scheme scheme;

    // Constructors
    public Sample(Scheme scheme, String dataFile) {
        this.scheme = scheme;
        trainingSet = new ArrayList<Example>();
        if (!readDataFile(dataFile)) {
            System.out.println("ERROR: datafile does not parallel schemafile!");
            System.exit(1);
        }
    }

    // Public Methods
    // getAttribute(attrib, g)
    public Attribute getAttribute(ArrayList<Attribute> attribs, ArrayList<Example> examples) {
        int classCount = scheme.function.values.size();
        double info = getInfoGain(examples, classCount);
        double maxGain = -1.0;
        Attribute bestAttrib = null;
        for (Attribute attrib : attribs) {
            double remainder = getRemainder(attrib, examples, classCount);
            double gain = info - remainder;
            System.out.println("\tTest " + attrib.name + ": gain = " + gain);
            if (gain > maxGain) {
                maxGain = gain;
                bestAttrib = attrib;
            }
        }
        System.out.println("\t\tSelect attribute " + bestAttrib.name);
        return bestAttrib;
    }

    // Private Methods
    // infoFmGp(g, k)
    private double getInfoGain(ArrayList<Example> examples, int classCount) {
        int size = examples.size();
        if (size == 0) {
            return 0.0;
        }
        int[] count = new int[classCount];
        for (Example e : examples) {
            count[e.funcIndex] += 1;
        }

        double infoGain = 0.0;
        for (int i = 0; i < classCount; i++) {
            double pr = (double)count[i] / (double)size; 
            if (pr != 0.0 && pr != -0.0) {
                infoGain -= pr * Math.log(pr);
            }
        }

        return infoGain;
    }

    // getRemainder(b, g,k)
    private double getRemainder(Attribute attrib, ArrayList<Example> examples, int classCount) {
        int size = examples.size();
        int valuesCount = attrib.values.size();
        int attribIndex = scheme.attributes.indexOf(attrib);
        // split into subgroups
        ArrayList<Example>[] subGroups = new ArrayList[valuesCount];
        for (int i = 0; i < valuesCount; i++) {
            subGroups[i] = new ArrayList<Example>();
        }
        for (Example e : examples) {
            int i = e.attribIndices.get(attribIndex);
            subGroups[i].add(e);
        }
        // get subCounts
        int[] subCounts = new int[valuesCount];
        for (int i = 0; i < valuesCount; i++) {
            subCounts[i] = subGroups[i].size();
        }
        // calculate remainder (whatever that is)
        double remainder = 0.0;
        for (int i = 0; i < valuesCount; i++) {
            double pr = (double)subCounts[i] / (double)size;

            double infoGain = getInfoGain(subGroups[i], classCount);
            remainder += pr * infoGain;
        }

        return remainder;
    }

    private boolean readDataFile(String schemeFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(schemeFileName))) {
            int lineNum = 1;

            for (String line; (line = br.readLine()) != null; ) {
                if (line.length() != 0) {
                    line = line.trim();
                    // Check if it's the first line
                    if (lineNum == 1) {
                        // validate dataFile against schema
                        String[] s = line.split("\\s+");
                        for (int i = 0; i < s.length; i += 1) {
                            if (i+1 == s.length) { // is func?
                                if (!s[i].equals(scheme.function.name)) {
                                    System.out.println(s[i] + " does not equal " + scheme.function.name);
                                    return false;
                                }
                            } else { // is attribute
                                if (!s[i].equals(scheme.attributes.get(i).name)) {
                                    System.out.println(s[i] + " does not equal " + scheme.attributes.get(i).name);
                                    return false;
                                }
                            }
                        }

                        lineNum += 1;
                    } else {
                        ArrayList<Integer> attribIndices = new ArrayList<Integer>();
                        String[] s = line.split("\\s+");
                        
                        for (int i = 0; i < s.length; i += 1) {
                            
                            if (i+1 == s.length) { // is func?
                                int index = scheme.function.values.indexOf(s[i]);
                                if (index == -1 || attribIndices.contains(-1)) { // validate data
                                    System.out.println(s[i] + " could not be found in the scheme of this dataset");
                                    return false;
                                }
                                trainingSet.add(new Example(attribIndices, index));
                                
                            } else { // is attribute
                                attribIndices.add(scheme.attributes.get(i).values.indexOf(s[i]));
                            }
                        }

                    }
                }
            }
            // line is not visible here.
        } catch (IOException e) {
            System.out.println("ERROR: something went wrong reading from file: " + schemeFileName);
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
