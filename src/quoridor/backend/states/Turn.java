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
    
    private boolean firstRun = true;

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
        if(firstRun) {
        	for(int k = 0; k< Quoridor.getGameState().getPlayer().size(); k++){
        		Player p1 = Quoridor.getGameState().getPlayer().get(k);
        		String s = "QUORIDOR " + p1.getPlayerNumber();
        		for(int i = 0; i < Quoridor.getGameState().getPlayer().size(); i++){
        			Player p2 = Quoridor.getGameState().getPlayer().get(i);
        			if(p1.getPlayerNumber() != p2.getPlayerNumber()){
        				s += " " + p2.getName();
        			}
        		}
        		Quoridor.getGameState().getPawns().get(k)
                .sendMessageToPlayer(s);
        		Quoridor.getGameState().getPawns().get(k).getClient().getString();
        	}
        	firstRun = false;
        	
        }
        	
        	
        	/*
            for(int i = 0; i < Quoridor.getGameState().getPawns().size(); i++){
                String s = "QUORIDOR " + (i+1);
                for(Pawn p : Quoridor.getGameState().getNames().keySet()) {
                	if(!p.equals(Quoridor.getGameState().getPawns().get(i)))
                	     s += " " + Quoridor.getGameState().getNames().get(p);   
                }    
                Quoridor.getGameState().getPawns().get(i)
                    .sendMessageToPlayer(s);
                // TODO needs timeout potentially
                Quoridor.getGameState().getPawns().get(i).getClient().getString();
            }
            firstRun = false;
        }
        */
        Iterator<Pawn> itr = Quoridor.getGameState().getPawns().iterator();
        Iterator<Player> itr2 = Quoridor.getGameState().getPlayer().iterator();
        while(itr.hasNext()) {
            ////////////////////////////////////////////////////////////////////
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ////////////////////////////////////////////////////////////////////
            Pawn p = itr.next();
            Player p2 = itr2.next();
            p.calcMoves();
            p.isTurn(true);
            Quoridor.getGUI().getPanel().update();
            String move = p.getMove(p2.getName()).trim();
            if(move.split(" ").length > 1)
                move = move.split(" ")[1];
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
                	for(Pawn pawn : Quoridor.getGameState().getPawns())
                	    pawn.sendMessageToPlayer("MOVED "
                	            + p2.getPlayerNumber() + " " + move);
                }
            } else if(move.length() == 3){
                if(!Quoridor.getGameState().addWall(move)) {
                	kick(p, p2, "Invalid wall placement");
                    itr.remove();
                    itr2.remove();
                } else if(p2.getRemainingWalls() == 0) {
                	kick(p, p2, "You didn't have a wall to place.");
                    itr.remove();
                    itr2.remove();
                } else {
                	p2.updateWalls();
                    for(Pawn pawn : Quoridor.getGameState().getPawns())
                        pawn.sendMessageToPlayer("MOVED "
                                + p2.getPlayerNumber() + " " + move);
                }
            }
            p.isTurn(false);
            if(Quoridor.getGameState().hasWon(p)) {
            	for(Pawn pawn: Quoridor.getGameState().getPawns())
            		pawn.sendMessageToPlayer("WINNER " + (Quoridor.getGameState().getPawns().size() == 1 
            									? Quoridor.getGameState().getPlayer().get(0).getPlayerNumber() : p2.getPlayerNumber()));
                Quoridor.getGUI().getPanel().writeToConsole((Quoridor.getGameState().getPawns().size() == 1 
						? Quoridor.getGameState().getPlayer().get(0).getPlayerNumber() : p2.getPlayerNumber())
                        + " has won the game!");
                Quoridor.getGUI().getPanel().update();
                Quoridor.getGameState().setWinner((Quoridor.getGameState().getPawns().size() == 1 
						? Quoridor.getGameState().getPlayer().get(0) : p2));
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
        Quoridor.getGUI().getPanel().writeToConsole(p2.getPlayerNumber()
                                                    + " is being removed");
        Quoridor.getGUI().getPanel().writeToConsole(message);
        p.boot(p2);
    }

    /* (non-Javadoc)
     * @see quoridor.backend.states.State#transition(boolean)
     */
    @Override
    public State transition(boolean b) {
        return transitions.get(b);
    }

}
