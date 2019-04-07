import java.util.ArrayList;

class DTLearn {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("ERROR: please call the executable with 2 parameters:");
            System.out.println("    java DTLearn <SchemeFile> <DataFile>");
            return;
        }

        String schemeFileName = args[0];
        String dataFileName = args[1];
        DTLearn mlStuff = new DTLearn(schemeFileName, dataFileName);
        mlStuff.doDecisionTree();
    }

    // Attributes
    private Scheme scheme;
    private Sample sample;
    private Node tree;

    // Constructors
    public DTLearn(String schemeFileName, String dataFileName) {
        scheme = new Scheme(schemeFileName);
        sample = new Sample(scheme, dataFileName);
    }

    // Public Methods
    public void doDecisionTree() {
        System.out.println("Learning Starts:");
        tree = learnDecisionTree(sample.trainingSet, scheme.attributes, "");
        tree.printTree();
    }

    // Private Methods
    private Node learnDecisionTree(ArrayList<Example> examples, ArrayList<Attribute> attribs, String majorityLabel) {
        // 3 base cases
        // case 1.
        if (examples == null || examples.size() == 0) {
            Node n = new Node(majorityLabel);
            return n;
        }

        // case 2.
        String item = null;
        boolean allSame = true;
        for (Example e: examples) {
            String classifier = scheme.function.values.get(e.funcIndex);
            if (item == null) {
                item = classifier;
            } else if (!item.equals(classifier)) {
                allSame = false;
            }
        }
        if (allSame) {
            Node n = new Node(item);
            return n;
        }

        // case 3.
        if (attribs == null || attribs.size() == 0) {
            Node n = new Node(this.getMajorityLabel(examples));
            return n;
        }

        Attribute best = sample.getAttribute(attribs, examples);
        Node treeRoot = new Node(best);
        String majority = getMajorityLabel(examples);
        int valuesCount = best.values.size();
        int attribIndex = scheme.attributes.indexOf(best);

        // split into subgroups
        ArrayList<Example>[] subGroups = new ArrayList[valuesCount];
        for (int i = 0; i < valuesCount; i++) {
            subGroups[i] = new ArrayList<Example>();
        }
        for (Example e : examples) {
            int i = e.attribIndices.get(attribIndex);
            subGroups[i].add(e);
        }
        // create and link subtrees
        ArrayList<Attribute> subAttribs = (ArrayList<Attribute>)attribs.clone();
        subAttribs.remove(best);
        for (int i = 0; i < valuesCount; i++) {
            Node subTree = learnDecisionTree(subGroups[i], subAttribs, majority);
            treeRoot.children.add(subTree);
            subTree.parent = treeRoot;
            subTree.value = best.values.get(i);
        }

        return treeRoot;
    }

    private String getMajorityLabel(ArrayList<Example> examples) {
        int labelCount = scheme.function.values.size();
        int[] counter = new int[labelCount];
        for (int i = 0; i < labelCount; i++) {
            counter[i] = 0;
        }
        for (Example e: examples) {
            counter[e.funcIndex] += 1;
        }

        int maxIndex = -1;
        int max = -1;
        for (int i = 0; i < labelCount; i++) {
            if (counter[i] > max) {
                max = counter[i];
                maxIndex = i;
            }
        }

        return scheme.function.values.get(maxIndex);
    }
}
