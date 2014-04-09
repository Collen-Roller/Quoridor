package quoridor.backend.states;

import javax.swing.JOptionPane;

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
        if(Quoridor.getGameState().getWinner() == null)
            JOptionPane.showMessageDialog(Quoridor.getGUI(), "Something bad about connections failing.");
        else
            JOptionPane.showMessageDialog(Quoridor.getGUI(), Quoridor.getGameState().getWinner().getName() + " has won the game!");
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
