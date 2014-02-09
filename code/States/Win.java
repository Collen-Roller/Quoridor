package States;

public class Win implements State {
    
    /*
     * Method: execute
     * ------------------------------
     * The code run when this state is executed, for now simply prints the
     * name of the state.
     */
    @Override
    public void execute() {
        System.out.println(">> Win <<");
    }

    /*
     * Method: transition
     * ------------------------------
     * Passes the control of the current state to the next mapped state, as the
     * Win state is a death state it returns a null state to signify execution
     * of the state machine should not continue
     */
    @Override
    public State transition(boolean b) {
        System.out.println("-- Transition --");
        return null;
    }

}