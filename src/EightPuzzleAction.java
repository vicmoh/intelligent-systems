/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Objects;

/**
 *
 * @author Le
 */
public class EightPuzzleAction implements GenericAction {
    private String action ="";
    static final String [] actions = {"LEFT","RIGHT","UP","DOWN"};

    public EightPuzzleAction() {
        
    }

    @Override
    public String toString() {
        return "EightPuzzleAction{" + "action=" + action + '}';
    }
    
    
    @Override
    
    public void printAction() {
        String temp="";
        for(String s: actions){
            temp += s + ", ";
        }
        System.out.println(temp);
            
    }

    public EightPuzzleAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.action);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EightPuzzleAction other = (EightPuzzleAction) obj;
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        return true;
    }
    
    
}
