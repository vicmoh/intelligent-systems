/**
 *
 * @author Vicky Mohammad
 * email: mohammav@uoguelph.ca
 */
public interface GenericState <S , A > {
     S getState(); //return the state
     void printState();//print out the current state
     void move(A a); //move from action a.
}//end generic state
