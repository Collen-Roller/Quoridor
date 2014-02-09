package States;

import java.util.HashMap;

/*
 * Class: Init
 * ------------------------------
 * Represents the starting state of a game of Quoridor, sets up the game board
 * and passes control to the turn state
 */
public class Init implements State {

    // A map of transitions relevant to this state
    private HashMap<Boolean, State> transitions;

    /*
     * Constructor: Init
     * ------------------------------
     * Constructs a new Init state with a new Turn state
     */
    public Init() {
        transitions = new HashMap<Boolean, State>();
        transitions.put(true, new Turn());
    }

    /*
     * Method: execute
     * ------------------------------
     * The code run when this state is executed, for now simply prints the
     * name of the state.
     */
    @Override
    public void execute() {
        System.out.println(">> Init <<");
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

}
