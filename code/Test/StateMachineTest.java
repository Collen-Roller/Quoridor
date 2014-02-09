package Test;

import GameManager.StateManager;

/*
 * Class: StateMachineTest
 * ------------------------------
 * Instantiates a new state machine and runs a test on it
 */
public class StateMachineTest {

    /*
     * Method: main
     * ------------------------------
     * The code entry point. Expects command line argument of test parameters.
     * Valid input is of the following format: any number of the strings 'true'
     * or 'false'; each string representing a transition from one state to
     * another. Argument must be enclosed in quotations. Example:
     * 
     * java StateMachineTest "true false false false true true"
     * 
     * This input will pass from init to turn as if init completed successfully,
     * stay in the turn state for each false as if no player has won, and
     * finally exit in the win state.
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            StateManager sm = new StateManager();
            sm.test(args[0]);
        } else
            System.out.println("No input provided");
    }
    
}
