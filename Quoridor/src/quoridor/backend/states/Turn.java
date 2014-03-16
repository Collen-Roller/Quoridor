package quoridor.backend.states;

import java.util.HashMap;
import java.util.Iterator;

import quoridor.backend.containers.Player;
import quoridor.backend.pieces.Pawn;
import quoridor.main.Quoridor;

/**
 * @author Team 4 Men And A Cripple
 * 
 * This class represents a state that executes a single turn in Quoridor.
 */
public class Turn implements State {

    /**
     * The transitions that exist within this state.
     */
    private HashMap<Boolean, State> transitions;

    /**
     * Constructs a new turn state.
     */
    public Turn() {
        transitions = new HashMap<Boolean, State>();
        transitions.put(false, this);
        transitions.put(true, new Win());
    }

    /* (non-Javadoc)
     * @see quoridor.backend.states.State#execute()
     */
    @Override
    public boolean execute() {
        // TODO: Turn resolution
    	Iterator<Player> itr2 = Quoridor.getGameState().getPlayer().iterator();
        Iterator<Pawn> itr = Quoridor.getGameState().getPawns().iterator();
        while(itr.hasNext()) {
            if(Quoridor.getGameState().hasWon())
                return true;
            Pawn p = itr.next();
            Player p2 = itr2.next();
            String move = p.getMove().trim();
            if(move.equals("ERROR")) {
            	Quoridor.getGUI().getPanel().writeToConsole(p2.getName() + " is being removed");
                Quoridor.getGUI().getPanel().writeToConsole("Invalid input.");
                p.boot();
                itr.remove();
                itr2.remove();
            }else if(move.length() == 2) {
                if(!Quoridor.getGameState().movePawn(p, move)) {
                	Quoridor.getGUI().getPanel().writeToConsole(p2.getName() + " is being removed");
                    Quoridor.getGUI().getPanel().writeToConsole("Invalid move.");
                    p.boot();
                    itr.remove();
                    itr2.remove();
                }else{
                	p2.updateLocation(move);
                	p2.updateMove();
                }
            } else if(move.length() == 3){
                if(!Quoridor.getGameState().addWall(move)) {
                	Quoridor.getGUI().getPanel().writeToConsole(p2.getName() + " is being removed");
                    Quoridor.getGUI().getPanel().writeToConsole("Invalid wall placement.");
                    p.boot();
                    itr.remove();
                    itr2.remove();
                }else
                	p2.updateWalls();
            }
            
            Quoridor.getGUI().getPanel().update();
        }
        return false;
    }

    /* (non-Javadoc)
     * @see quoridor.backend.states.State#transition(boolean)
     */
    @Override
    public State transition(boolean b) {
        return transitions.get(b);
    }

    /* TODO: Does this method need to exist?
    void setTransition(boolean b, State s) {
        transitions.put(b, s);
    }*/

}
