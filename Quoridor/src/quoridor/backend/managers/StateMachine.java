package quoridor.backend.managers;

import quoridor.backend.states.Init;
import quoridor.backend.states.State;

public class StateMachine {

    private State current;
    private boolean isRunning;

    // Creates a state object
    public StateMachine() {
        current = new Init();
    }

    // Runs the state object
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

}
