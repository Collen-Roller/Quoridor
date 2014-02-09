package States;

import java.util.HashMap;

/*
 * Class: Turn
 * ------------------------------
 * Represents a single instance of the turn step in a game of Quoridor
 */
public class Turn implements State {

    // A map of transitions relevant to this state
    private HashMap<Boolean, State> transitions;

    /*
     * Constructor: Turn
     * ------------------------------
     * Constructs a new Turn state with a new Win state
     */
    public Turn() {
        transitions = new HashMap<Boolean, State>();
        transitions.put(false, this);
        transitions.put(true, new Win());
    }

    /*
     * Method: execute
     * ------------------------------
     * The code run when this state is executed, for now simply prints the
     * name of the state.
     */
    @Override
    public void execute() {
        System.out.println(">> Turn <<");
    }

    /*
     * Method: transition
     * ------------------------------
     * Passes the control of the current state to the next mapped state
     */
    @Override
    public State transition(boolean b) {
        System.out.println("-- Transition --");
        return transitions.get(b);
    }

    void setTransition(boolean b, State s) {
        transitions.put(b, s);
    }

}