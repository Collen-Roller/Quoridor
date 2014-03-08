package quoridor.backend.states;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import quoridor.backend.containers.Player;
import quoridor.backend.pieces.Pawn;
import quoridor.gui.board.GameBoard;
import quoridor.main.Quoridor;

public class Init implements State {

    private HashMap<Boolean, State> transitions;
    private final String[] startingPos = {"E1", "E9", "A5", "I5"};
    private Image [] pawns = new Image[4];

    public Init() {
        transitions = new HashMap<Boolean, State>();
        transitions.put(true, new Turn());
        pawns[0] = Toolkit.getDefaultToolkit().createImage("res/pawn_dark.png");
		pawns[1] = Toolkit.getDefaultToolkit().createImage("res/pawn_light.png");
		pawns[2] = Toolkit.getDefaultToolkit().createImage("res/pawn_blue.png");
		pawns[3] = Toolkit.getDefaultToolkit().createImage("res/pawn_red.png");
    }

    @Override
    public boolean execute() {
        Quoridor.newGameState();
        // TODO: Some indication that it is trying to connect to hosts
        Queue<String> rejected = new LinkedList<String>();
        for(int i = 0; i < Quoridor.getHosts().length; i++) {
            Pawn p = new Pawn(startingPos[i],pawns[i]);
            if(p.startNetwork(Quoridor.getHosts()[i])){
                Quoridor.getGameState().addPawn(p);
                p.getClient().sendString("Enter Player Name");
                Player player = new Player(p.getClient().getString(),p.getPosition().toString(),Quoridor.getHosts().length);
                Quoridor.getGameState().addPlayer(player);
            }
            else
                rejected.add(Quoridor.getHosts()[i]);
        }
        Quoridor.getGUI().setPanel(new GameBoard(Quoridor.getHosts().length));
        while(!rejected.isEmpty())
            Quoridor.getGUI().getPanel().writeToConsole("Connection to " +
                                                         rejected.remove() +
                                                         " could not be established.");
        return true;
    }

    @Override
    public State transition(boolean b) {
        return transitions.get(b);
    }

}
