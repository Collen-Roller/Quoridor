package quoridor.backend.managers;

import quoridor.backend.states.Init;
import quoridor.backend.states.State;

/**
 * @author Team 4 Men And A Cripple
 *
 * This class represents the state machine driving a game of Quoridor.
 */
public class StateMachine {

    /**
     * The state currently under execution.
     */
    private State current;

    /**
     * Whether the state machine should continue execution.
     */
    private boolean isRunning;

    /**
     * Constructs a new clean state machine.
     */
    public StateMachine() {
        current = new Init();
    }

    /**
     * Execute the current state then transition to the new state until the is
     * running flag indicates the thread should be run out.
     */
    public void run() {
        isRunning = true;
        while(isRunning || current != null) {
            current = current.transition(current.execute());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // TODO: Write a test the makes a dummy set of states and runs them checking
    // the output of the state against the predetermined output.

}
