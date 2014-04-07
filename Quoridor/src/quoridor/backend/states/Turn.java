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
        if(Quoridor.getGameState().getPawns().size() == 0)
            return true;
        Iterator<Pawn> itr = Quoridor.getGameState().getPawns().iterator();
        Iterator<Player> itr2 = Quoridor.getGameState().getPlayer().iterator();
        while(itr.hasNext()) {
        	
            Pawn p = itr.next();
            Player p2 = itr2.next();
            
            p.calcMoves();
            p.isTurn(true);
            Quoridor.getGUI().getPanel().update();
            String move = p.getMove(p2.getName()).trim();
            if(move.equals("ERROR")) {
                kick(p, p2, "Invalid input.");
                itr.remove();
                itr2.remove();
            }else if(move.length() == 2) {
                if(!Quoridor.getGameState().movePawn(p, move)) {
                	kick(p, p2, "Invalid move.");
                    itr.remove();
                    itr2.remove();
                }else{
                	p2.updateLocation(move);
                	p2.updateMove();
                }
            } else if(move.length() == 3){
                if(!Quoridor.getGameState().addWall(move)) {
                	kick(p, p2, "Invalid wall placement");
                    itr.remove();
                    itr2.remove();
                }else if(p2.getRemainingWalls() == 0){
                	kick(p, p2, "You didn't have a wall to place.");
                    itr.remove();
                    itr2.remove();
                }else
                	p2.updateWalls();
            }
            p.isTurn(false);
            if(Quoridor.getGameState().hasWon(p)){
            	p.sendMessageToPlayer("You have won!!!!!!");
            	Quoridor.getGUI().getPanel().writeToConsole(p2.getName() + " has won the game!");
            	//maybe play some sound to everyone! 
            	Quoridor.getGUI().getPanel().update();
            	
            	
            	try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	return true;
            }
        }
        return false;
    }
    
    /**
     * The frontend-side player kick routine.
     * 
     * @param p The pawn belonging to the player being kicked.
     * @param p2 The player being kicked.
     * @param message The description of why the player is being kicked.
     */
    private void kick(Pawn p, Player p2, String message) {
        Quoridor.getGUI().getPanel().writeToConsole(p2.getName()
                                                    + " is being removed");
        Quoridor.getGUI().getPanel().writeToConsole(message);
        p.boot();
    }

    /* (non-Javadoc)
     * @see quoridor.backend.states.State#transition(boolean)
     */
    @Override
    public State transition(boolean b) {
        return transitions.get(b);
    }

}
