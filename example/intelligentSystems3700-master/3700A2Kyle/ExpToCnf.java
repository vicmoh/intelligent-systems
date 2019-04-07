import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


class ExpToCnf {

    private static String OR = " v ";
    private static String AND = " ^ ";
    private static String NOT = "~";
    private static String IMP = " => ";
    private static String IFF = " <-> ";
    private static String OPEN = "(";
    private static String CLOSE = ")";

    private static List<String> OPS = Arrays.asList(OR, AND, IMP, IFF);

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("ERROR: please call the executable with 2 parameters:");
            System.out.println("    java ExpToCnf <ExpFile> <CnfFile>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        LinkedList<String> cnfList = new LinkedList<String>();
        ExpToCnf converter = new ExpToCnf();
        // read in file

        try(BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            for(String line; (line = br.readLine()) != null; ) {
                if (line.length() != 0) {
                    String exp = new String(line);
                    String cnf = converter.reduce(converter.convert(OPEN + line + CLOSE));
                    cnfList.add(cnf);
                    System.out.println("Exp: " + exp);
                    System.out.println("CNF: " + cnf);
                }
            }
            // line is not visible here.
        } catch (IOException e) {
            System.out.println("ERROR: something went wrong reading from file: " + inputFileName);
            e.printStackTrace();
        }

        try(PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)))) {
            for (String s: cnfList) {
                LinkedList<String> clauses = converter.splitOnDisjunction(s);
                for (String clause: clauses) {
                    pr.write(converter.getInnerSentence(clause) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: something went wrong writing to file: " + outputFileName);
            e.printStackTrace();
        }
        // print to out file

        return;
    }
    

    String convert(String s) {
        System.out.println("CONVERT " + s);

        int opIndex = evaluateSentence(s);
        String op = "";
        String p = "";
        String q = "";

        if (opIndex == -1 && s.startsWith(OPEN)) {
            opIndex = evaluateSentence(getInnerSentence(s));
            if (opIndex != -1) {
                s = getInnerSentence(s);
            }
        }

        if (opIndex != -1) {
            p = s.substring(0, opIndex);
            s = s.substring(opIndex);
            for (String item : OPS) {
                if (s.startsWith(item)) {
                    op = item;
                    break;
                }
            }
            q = s.substring(op.length());
        }
        
        if (op == "" && (isVariable(s) || isVariable(getInnerSentence(s)))) { // is variable
            System.out.println("    RETURN " + s);
            return s;

        } else if (op == "" && (isNegation(s) || isNegation(getInnerSentence(s)))) { // is negated
            if (isVariable(s.substring(1))) {  // is variable
                System.out.println("    RETURN " + s);
                return s;
            } else { // is sentence
                String innerS = getInnerSentence(s);

                if (isNegation(innerS) && isVariable(innerS.substring(1))) {
                    System.out.println("    RETURN " + innerS);
                    return innerS;
                } else if (isNegation(getInnerSentence(innerS)) && isVariable(getInnerSentence(innerS).substring(1))) {
                    innerS = getInnerSentence(innerS);
                    System.out.println("    RETURN " + innerS.substring(1));
                    return innerS.substring(1);
                } else if (canDeMorgann(innerS)) {
                    String ret = convert(applyDeMorgann(innerS));
                    System.out.println("    RETURN " + ret);
                    return ret;
                } else { // else op must be IFF or IMP
                    String t = convert(innerS);
                    String ret = convert(NOT + OPEN + t + CLOSE); // IF NOT WORKING => remove brackets
                    System.out.println("    RETURN " + ret);
                    return ret;
                }
            }

        } else if (op == AND) { // and clause

            p = convert(p);
            if (!p.startsWith(OPEN)) {
                p = OPEN + p + CLOSE;
            }

            q = convert(q);
            if (!q.startsWith(OPEN)) {
                q = OPEN + q + CLOSE;
            }

            String ret = p + AND + q;
            System.out.println("    RETURN " + ret);
            return ret;

        } else if (op == OR) { // or clause
            String p_set = convert(p);
            String q_set = convert(q);
            
            String ret = crossProduct(p_set, q_set);
            System.out.println("    RETURN " + ret);
            return ret;

        } else if (op == IMP) { // implication
            String ret = convert(impElimination(p, q));
            System.out.println("    RETURN " + ret);
            return ret;

        } else if (op == IFF) { // iff 
            String ret = convert(iffElimination(p, q));
            System.out.println("    RETURN " + ret);
            return ret;
        } 

        System.out.println("ERROR: reached the end of convert!");
        return "";
    }

    boolean isVariable(String s) {
        if (s.contains(")") || s.contains("(") || s.contains("~") || s.contains(IFF) || s.contains(IMP) || s.contains(AND) || s.contains(OR) || s.contains(" ")) {
            return false;
        }

        return true;
    }

    boolean isNegation(String s) {
        return s.startsWith(NOT) || s.startsWith(OPEN + NOT);
    }

    boolean canDeMorgann(String s) {

        String op = "";
        int index = evaluateSentence(s);
        if (index == -1) {
            s = getInnerSentence(s);
            index = evaluateSentence(s);
        }
        String sS = s.substring(index);
        
        for (String item : OPS) {
            if (sS.startsWith(item)) {
                op = item;
                break;
            }
        }

        return (op == AND || op == OR);
    }

    // used to find the index of the first lowest level operator or -1 if none exist
    int evaluateSentence(String s) {
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth ++;
            } else if (s.charAt(i) == ')') {
                depth --;
            } else if (s.charAt(i) == ' ' && depth == 0) {
                return i;
            }
        }

        return -1;
    }

    String iffElimination(String p, String q) {
        return OPEN + OPEN + p + AND + q + CLOSE + OR + OPEN + NOT + p + AND + NOT + q + CLOSE + CLOSE;
    }

    String impElimination(String p, String q) {
        return OPEN + NOT + p + OR + q + CLOSE;
    }

    String applyDeMorgann(String s) {
        String p = "";
        String q = "";
        String op = "";
        int opIndex = evaluateSentence(s);
        if (opIndex == -1) {
            s = getInnerSentence(s);
            opIndex = evaluateSentence(s);
        }

        p = s.substring(0, opIndex);
        s = s.substring(opIndex);
        for (String item : OPS) {
            if (s.startsWith(item)) {
                op = item;
                break;
            }
        }
        q = s.substring(op.length());

        if (op == AND) {
            op = OR;
        } else {
            op = AND;
        }

        if (isNegation(p) && isNegation(q)) {
            return OPEN + p.substring(1) + op + q.substring(1) + CLOSE;
        } else if (isNegation(p)) {
            return OPEN + p.substring(1) + op + NOT + q + CLOSE;
        } else if (isNegation(q)) {
            return OPEN + NOT + p + op + q.substring(1) + CLOSE;
        }
        return OPEN + NOT + p + op + NOT + q + CLOSE;
    }

    public String getInnerSentence(String s) {
        int start = -1;
        int end = -1;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (depth == 0) {
                    start = i+1;
                }
                depth ++;
            } else if (s.charAt(i) == ')') {
                depth --;
                if (depth == 0) {
                    end = i;
                }
            }
        }

        if (start != -1 && end != -1) {
            return s.substring(start, end);
        }

        return s;
        
    }

    String crossProduct(String p_set, String q_set) {
        LinkedList<String> p_list = splitOnDisjunction(p_set);
        LinkedList<String> q_list = splitOnDisjunction(q_set);

        String retString = "";

        for (String p: p_list) {
            p = getInnerSentence(p);
            for (String q: q_list) {
                q = getInnerSentence(q);
                if (retString != "") {
                    retString += AND; // if not first run add and between clauses
                }
                retString += OPEN + p + OR + q + CLOSE;
            }
        }

        return retString;
    }

    // WARNING: consumes input string
    public LinkedList<String> splitOnDisjunction(String set) {
        LinkedList<String> list = new LinkedList<String>();

        while(set.contains(AND)) {
            int pos = set.indexOf(AND);
            String temp = set.substring(0,pos);
            set = set.substring(pos + AND.length());
            list.add(temp);
        }
        list.add(set);

        return list;
    }

    // eliminate clauses that contradict themselves
    String reduce(String clauses) {
        String out = "";
    
        LinkedList<String> clauseList = splitOnDisjunction(clauses);

        // remove tautologies
        for(int i = 0; i < clauseList.size(); i++) {
            if (clauseIsTautology(clauseList.get(i))) {
                clauseList.remove(i);
                i --;
            }
        }

        // remove duplicate literals
        for(int i = 0; i < clauseList.size(); i++) {
            clauseList.set(i, removeDuplicates(clauseList.get(i)));
        }

        // add disjunctions back in 
        for (String s: clauseList) {
            if (out != "") {
                out += AND;
            }
            out += s;
        }

        return out;
    }

    // returns true if clause is a tautology (always true)
    // meaning it has 2 complementary literals
    boolean clauseIsTautology(String clause) {
        String innerS = getInnerSentence(clause);
        LinkedList<String> literals = getLiteralList(innerS);

        for (String lit: literals) {
            boolean negative = isNegation(lit);
            if (negative) {
                if (literals.contains(lit.substring(1))) {
                    return true;
                }
            } else {
                if (literals.contains(NOT + lit)) {
                    return true;
                }
            }
        }

        return false;
    }

    String removeDuplicates(String clause) {
        String newClause = "";
        clause = getInnerSentence(clause);
        LinkedList<String> literals = getLiteralList(clause);

        for (String lit: literals) {
            if (newClause == "") {
                newClause += lit;
            } else {
                if (!newClause.contains(lit)) {
                    newClause += OR + lit;
                }
            }
        }

        return OPEN + newClause + CLOSE;
    }

    // split on OR
    LinkedList<String> getLiteralList(String clause) {
        LinkedList<String> list = new LinkedList<String>();

        while(clause.contains(OR)) {
            int pos = clause.indexOf(OR);
            String temp = clause.substring(0,pos);
            clause = clause.substring(pos + AND.length());
            list.add(temp);
        }
        list.add(clause);

        return list;
    }
}

