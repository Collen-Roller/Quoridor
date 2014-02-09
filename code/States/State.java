package States;

/*
 * Interface: State
 * ------------------------------
 * This interface is implemented by a state within the GameManager
 */
public interface State {

    /*
     * Method: execute
     * ------------------------------
     * This method contains the code that is executed for a given state
     */
    public void execute();
    
    /*
     * Method: transition
     * ------------------------------
     * This method passes control to another state
     */
    public State transition(boolean b);
    
}
