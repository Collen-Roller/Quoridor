package quoridor.backend.states;

import quoridor.main.Quoridor;

/**
 * @author Team 4 Men And A Cripple
 *
 * This class represents a state that ends a game of Quoridor.
 */
public class Win implements State {

    /* (non-Javadoc)
     * @see quoridor.backend.states.State#execute()
     */
    @Override
    public boolean execute() {
        Quoridor.endGame();
        return true;
    }

    /* (non-Javadoc)
     * @see quoridor.backend.states.State#transition(boolean)
     */
    @Override
    public State transition(boolean b) {
        return null;
    }

}
