package quoridor.backend.managers;

import java.util.ArrayList;

import quoridor.backend.containers.Player;
import quoridor.backend.containers.Position;
import quoridor.backend.pieces.Pawn;
import quoridor.backend.pieces.Walls;

public class GameState {

	private ArrayList<Player> players;
    private ArrayList<Pawn> pawns;
    private Walls walls;

    public GameState() {
    	players = new ArrayList<Player>();
        pawns = new ArrayList<Pawn>();
        walls = new Walls();
    }

    public ArrayList<Player> getPlayer() {
    	return players;
    }
    
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    public Walls getWalls() {
        return walls;
    }

    public boolean addWall(Position a, Position b) {
        if (walls.canAdd(a, b)) {
            walls.add(a, b);
            return true;
        }
        return false;
    }

    public boolean addWall(String s) {
        s = s.toUpperCase();
        Position a = new Position(s.substring(0, 2));
        Position b;
        if (s.charAt(2) == 'H')
            b = new Position(a.x, a.y + 1);
        else
            b = new Position(a.x + 1, a.y);
        return addWall(a, b);
    }

    public boolean hasWon() {
        if(pawns.size() < 2)
            return true;
        return false;
    }

    public boolean addPawn(Pawn p) {
        pawns.add(p);
        return true;
    }
    
    public boolean addPlayer(Player p){
    	players.add(p);
    	return true;
    }

    public boolean movePawn(Pawn p, String move) {
        Position pos = new Position(move);
        boolean neighborJump = false;
        boolean neighborAttack = false;
        for(int i=0; i<pawns.size(); i++){
        	if(p.calcMoves().contains(pawns.get(i).getPosition())){
        		if(pawns.get(i).getPosition().equals(pos))
        			neighborAttack = true;
        		if(pawns.get(i).calcMoves().contains(pos))
        			neighborJump = true;
        	}
        }
        if(((p.calcMoves().contains(pos) && !neighborAttack)|| neighborJump) 
        		&& !walls.isBlocked(p.getPosition(), pos)) {
            p.setPosition(pos);
            return true;
        }
        return false;
    }

}


