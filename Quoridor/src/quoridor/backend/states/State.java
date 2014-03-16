package quoridor.backend.states;

/**
 * @author Team 4 Men And A Cripple
 *
 */
public interface State {

    /**
     * @return The result of running the code associated with executing this
     *         state. For example, after successful initialization the init
     *         state returns true. 
     */
    public boolean execute();

    /**
     * Transition from the current state to a new state.
     * 
     * @param b The flag to determine which state should be returned.
     * @return The state transitioned to.
     */
    public State transition(boolean b);
    
}