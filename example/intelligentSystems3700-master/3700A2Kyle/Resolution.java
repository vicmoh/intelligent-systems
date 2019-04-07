import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


class Resolution {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("ERROR: please call the executable with 3 parameters:");
            System.out.println("    java Resolution <KbCnfFile> <QueryFile> <PerceptFile>");
            return;
        }

        String kbFileName = args[0];
        String queryFileName = args[1];
        String perceptionFileName = args[2];

        ClauseBase kb = new ClauseBase();
        kb.loadClauseFile(kbFileName);
        ClauseBase perception = new ClauseBase();
        perception.loadClauseFile(perceptionFileName);

        kb.mergeWith(perception);

        ClauseBase queries = new ClauseBase();
        queries.loadClauseFile(queryFileName);

        for (Clause query: queries.clauses) {
            if (kb.kbEntails(query)) {
                System.out.println("KB |= " + query.querySentence());
            } else {
                System.out.println("KB !|= " + query.querySentence());
            }
        }

    }


}

class Literal {
    // public cuz I aint writin' no setters or getters
    public String symbol;
    public boolean isNegative;

    public Literal (String symbol, boolean isNegative) {
        this.symbol = symbol;
        this.isNegative = isNegative;
    }

    public String printLit() {
        if (isNegative) {
            return "~" + symbol;
        } else {
            return symbol;
        }
    }
}

class Clause {
    static String OR = " v ";
    static String NOT = "~";
    // public cuz I aint writin' no setters or getters
    public ArrayList<Literal> literals;

    public Clause () {
        literals = new ArrayList<Literal>();
    }

    public Clause (ArrayList<Literal> literals) {
        this.literals = literals;
    }

    public Clause (String s) {
        literals = new ArrayList<Literal>(5);
        parseSentence(s);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        } else {
            Clause clause = (Clause) object;
            if (clause.literals.size() != this.literals.size()) {
                return false;
            } else {
                for (int i = 0; i < clause.literals.size(); i ++) {
                    if (!clause.literals.get(i).symbol.equals(this.literals.get(i).symbol)) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    protected void parseSentence(String s) {
        LinkedList<String> stringLiterals = getLiteralList(s);
        for (String lit: stringLiterals) {
            if (lit.startsWith(NOT)) {
                literals.add(new Literal(lit.substring(1), true));
            } else {
                literals.add(new Literal(lit, false));
            }
        }
    }

    // split on OR
    private LinkedList<String> getLiteralList(String clause) {
        LinkedList<String> list = new LinkedList<String>();

        while(clause.contains(OR)) {
            int pos = clause.indexOf(OR);
            String temp = clause.substring(0,pos);
            clause = clause.substring(pos + OR.length());
            list.add(temp);
        }
        list.add(clause);

        return list;
    }

    public String querySentence() {
        String sentence = "";

        for (Literal l: literals) {
            if (sentence != "") {
                sentence += " v ";
            }

            if (l.isNegative) {
                sentence += l.symbol;
            } else {
                sentence += "~" + l.symbol;
            }
        }

        return sentence;
    }

    public String clauseSentence() {
        String sentence = "";

        for (Literal l: literals) {
            if (sentence != "") {
                sentence += " v ";
            }

            if (l.isNegative) {
                sentence += "~" + l.symbol;
            } else {
                sentence += l.symbol;
            }
        }

        return sentence;
    }


    @Override
    public int hashCode() {
        int hash = 13;
        for (Literal l : literals) {
            if (l.isNegative) {
                hash = (hash * 7 ^ l.symbol.hashCode()) - 11;
            } else {
                hash = (hash * 11 ^ l.symbol.hashCode()) + 7;
            }
        }
        return hash;
    }
}

class ClauseBase {
    // public cuz I aint writin' no setters or getters
    public ArrayList<Clause> clauses;

    public ClauseBase () {
        clauses = new ArrayList<Clause>(5);
    }

    public ClauseBase (ArrayList<Clause> clauses) {
        this.clauses = clauses;
    }

    public void mergeWith(ClauseBase other) {
        this.clauses.addAll(other.clauses);
    }

    public void loadClauseFile(String fileName) {
        System.out.println("LOADING FILE: " + fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for(String line; (line = br.readLine()) != null; ) {
                clauses.add(new Clause(line));
                System.out.println("    " + line);
            }
            // line is not visible here.
        } catch (IOException e) {
            System.out.println("ERROR: something went wrong opening file: " + fileName);
            e.printStackTrace();
        }
    }

    public boolean kbEntails(Clause query) {
        ArrayList<Clause> clau = new ArrayList<Clause>(20);
        clau.addAll(this.clauses);
        clau.add(query);
        ArrayList<Clause> newClau = new ArrayList<Clause>();
        Clause p, q;

        System.out.println("RESOLUTION - clau: " + clau.size() + " new: " + newClau.size());

        while(true) {
            for (int i = 0; i < clau.size(); i++) {
                for (int j = i+1; j < clau.size(); j++) {
                    p = clau.get(i);
                    q = clau.get(j);

                    if (!hasComplimentaryLiterals(p, q)) {
                        continue;
                    }

                    Clause resolvent = resolve(p,q);

                    if (!newClau.contains(resolvent)) {
                        newClau.add(resolvent);
                    }

                    if (resolvent.literals.size() == 0) {
                        return true;
                    }
                }
            }

            System.out.println("RESOLUTION - clau: " + clau.size() + " new: " + newClau.size());
            ArrayList<Clause> temp = union(clau, newClau);
            if (temp.size() == clau.size()) {
                return false;
            }
            clau = temp;
            newClau = new ArrayList<Clause>();
        }
    }

    private ArrayList<Clause> union(ArrayList<Clause> a1, ArrayList<Clause> a2) {
        Set<Clause> set = new HashSet<Clause>();

        set.addAll(a1);
        set.addAll(a2);

        return new ArrayList<Clause>(set);
    }

    private Clause resolve(Clause c1, Clause c2) {
        Literal l1 = getComplimentaryLiteral(c1, c2);
        Clause resolvent = new Clause();

        boolean doRemove = true;
        for (Literal l: c1.literals) {
            if (doRemove && l.symbol.equals(l1.symbol) && l.isNegative == l1.isNegative) {
                doRemove = false;
            } else {
                resolvent.literals.add(l);
            }
        }

        doRemove = true;
        for (Literal l: c2.literals) {
            if (doRemove && l.symbol.equals(l1.symbol) && l.isNegative != l1.isNegative) {
                doRemove = false;
            } else {
                resolvent.literals.add(l);
            }
        }

        return resolvent;
    }

    private boolean hasComplimentaryLiterals(Clause c1, Clause c2) {
        for (Literal l1: c1.literals) {
            for (Literal l2: c2.literals) {
                if (l1.symbol.equals(l2.symbol)) {
                    if (l1.isNegative != l2.isNegative) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    private Literal getComplimentaryLiteral(Clause c1, Clause c2) {
        for (Literal l1: c1.literals) {
            for (Literal l2: c2.literals) {
                if (l1.symbol.equals(l2.symbol) && l1.isNegative != l2.isNegative) {
                    return l1;
                }
            }
        }

        return new Literal("", false);
    }
}
