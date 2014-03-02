package quoridor.backend.managers;

import quoridor.backend.states.Init;
import quoridor.backend.states.State;

public class StateMachine {

    private State current;
    private boolean isRunning;

    public StateMachine() {
        current = new Init();
    }

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
