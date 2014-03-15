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

    // Creates the GameState object
    public GameState() {
    	players = new ArrayList<Player>();
        pawns = new ArrayList<Pawn>();
        walls = new Walls();
    }

    // Returns the arraylist of player objects
    public ArrayList<Player> getPlayer() {
    	return players;
    }
    
    // Returns the arraylist of pawn objects
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    // Returns the walls object
    public Walls getWalls() {
        return walls;
    }

    // Checks to see if the current placement of the wall
    // is a valid placement, if so returns true
    public boolean addWall(Position a, Position b) {
        if (walls.canAdd(a, b)) {
            walls.add(a, b);
            return true;
        }
        return false;
    }

    // Passed a move s and uppercases the move then stores the first part
    // part of the move string in a
    // Then checks to see if the third character of the string
    // is either an H or a V and stores that in position b
    // Then calls the addWall Method 
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

    // Checks to see if the game has ended
    public boolean hasWon() {
        if(pawns.size() < 2)
            return true;
        return false;
    }

    // Adds a pawn object
    public boolean addPawn(Pawn p) {
        pawns.add(p);
        return true;
    }
    
    // Adds a player object
    public boolean addPlayer(Player p){
    	players.add(p);
    	return true;
    }

    // Passed a pawn object and the move string
    // Checks to see if the move is valid
    // if it is, returns true
    // Checks for jumping, and wall blocks
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


