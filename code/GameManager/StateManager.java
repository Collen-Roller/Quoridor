package GameManager;

import java.util.Scanner;

import States.Init;
import States.State;

/*
 * Class: StateManager
 * ------------------------------
 * This class represents a state machine
 */
public class StateManager {

    // The active state
    private State current;
    
    /*
     * Constructor: StateMachine
     * ------------------------------
     * Constructs a new StateMachine with a new Init state
     */
    public StateManager() {
        current = new Init();
    }
    
    /*
     * Method: test
     * ------------------------------
     * Runs the state machine on the given input for testing
     */
    public void test(String input) {
        Scanner sc = new Scanner(input);
        System.out.println("**** Starting Test ****");
        while(sc.hasNextBoolean()) {
            current.execute();
            current = current.transition(sc.nextBoolean());
            if(current == null)
                break;
        }
        sc.close();
        System.out.println("**** Test Complete ****");
    }
    
}
