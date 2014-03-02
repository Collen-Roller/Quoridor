package quoridor.backend.states;

import java.util.HashMap;
import java.util.Iterator;

import quoridor.backend.pieces.Pawn;
import quoridor.main.Quoridor;

public class Turn implements State {

    private HashMap<Boolean, State> transitions;

    public Turn() {
        transitions = new HashMap<Boolean, State>();
        transitions.put(false, this);
        transitions.put(true, new Win());
    }

    @Override
    public boolean execute() {
        // TODO: Turn resolution
        Iterator<Pawn> itr = Quoridor.getGameState().getPawns().iterator();
        while(itr.hasNext()) {
            if(Quoridor.getGameState().hasWon())
                return true;
            Pawn p = itr.next();
            String move = p.getMove().trim();
            if(move.equals("ERROR")) {
                Quoridor.getGUI().getPanel().writeToConsole("Invalid input.");
                p.boot();
                itr.remove();
            } else if(move.length() == 2) {
                if(!Quoridor.getGameState().movePawn(p, move)) {
                    Quoridor.getGUI().getPanel().writeToConsole("Invalid move.");
                    p.boot();
                    itr.remove();
                }
            } else if(move.length() == 3)
                if(!Quoridor.getGameState().addWall(move)) {
                    Quoridor.getGUI().getPanel().writeToConsole("Invalid wall placement.");
                    p.boot();
                    itr.remove();
                }
            Quoridor.getGUI().getPanel().update();
        }
        return false;
    }

    @Override
    public State transition(boolean b) {
        return transitions.get(b);
    }

    void setTransition(boolean b, State s) {
        transitions.put(b, s);
    }

}
