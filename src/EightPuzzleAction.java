/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Objects;

/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public class EightPuzzleAction implements GenericAction {
    // dec instance vars
    private String action ="";
    static final String [] actions = {"LEFT","RIGHT","UP","DOWN"};

    /***********************************************
     * functions
     ***********************************************/

    public EightPuzzleAction() {
        
    }//end func

    public EightPuzzleAction(String action) {
        this.action = action;
    }//end func

    public String getAction() {
        return action;
    }//end func

    public void setAction(String action) {
        this.action = action;
    }//end func

    /***********************************************
     * override functions
     ***********************************************/
    
    @Override public void printAction() {
        String temp="";
        for(String s: actions){
            temp += s + ", ";
        }//end for
        System.out.println(temp);
    }//end func

    @Override public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.action);
        return hash;
    }// end func

    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }//end if
        if (obj == null) {
            return false;
        }//end if
        if (getClass() != obj.getClass()) {
            return false;
        }//end if
        final EightPuzzleAction other = (EightPuzzleAction) obj;
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }//end if
        return true;
    }//end func

    @Override public String toString() {
        return "EightPuzzleAction{" + "action=" + action + '}';
    }//end func
}//end class
