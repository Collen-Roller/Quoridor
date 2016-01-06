package quoridor.backend.managers;

import java.util.ArrayList;

import quoridor.backend.containers.Player;
import quoridor.backend.containers.Position;
import quoridor.backend.pieces.Pawn;
import quoridor.backend.pieces.Walls;

/**
 * @author Team 4 Men And A Cripple
 * 
 * This class represents the current game state of a game of Quoridor.
 */
public class GameState {

	/**
	 * A list of all players currently in the game of Quoridor.
	 */
	private ArrayList<Player> players;
	
    /**
     * A list of all pawns belonging to players in the game of Quoridor.
     */
    private ArrayList<Pawn> pawns;
    
    /**
     * A reference to the walls in the game of Quoridor.
     */
    private Walls walls;
    
    private Player winner;
    
   // private Map<Pawn, String> names;

    /**
     * Constructs a new, clean game state.
     */
    public GameState() {
        winner = null;
    	players = new ArrayList<Player>();
        pawns = new ArrayList<Pawn>();
        walls = new Walls();
        //names = new TreeMap<Pawn, String>();
    }

    /**
     * @return The list of all players currently in the game of Quoridor.
     */
    public ArrayList<Player> getPlayer() {
    	return players;
    }

    /**
     * @return The list of all pawns currently in the game of Quoridor.
     */
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    /**
     * @return The reference to the walls in the game of Quoridor.
     */
    public Walls getWalls() {
        return walls;
    }

    /**
     * Attempts to add a new wall to the game state given 2 positions.
     * 
     * @param a The position identifying the upper-left most position the wall
     *          is blocking.
     * @param b The position used to determine if the wall is horizontal or
     *          vertical.
     * @return Whether the wall was able to be successfully placed.
     */
    public boolean addWall(Position a, Position b, GameState gs) {
        if (walls.canAdd(a, b, gs)) {
            walls.add(a, b);
            return true;
        }
        return false;
    }

    /**
     * Attempts to add a new wall to the game state given the string encoding
     * of a wall. Converts the string encoding to two positions and calls the
     * other addWall method.
     * 
     * @param s The string encoding of the wall placement.
     * @return Whether the wall could be successfully placed.
     */
    public boolean addWall(String s) {
        s = s.toUpperCase();
        Position a = new Position(s.substring(0, 2));
        Position b;
        if (s.charAt(2) == 'H')
            b = new Position(a.x, a.y + 1);
        else
            b = new Position(a.x + 1, a.y);
        return addWall(a, b, this);
    }

    /**
     * @return Whether a player has won the game.
     * 
     */
    public boolean hasWon(Pawn p) {
    	if(p.didPawnWin()){
    		return true;
    	}if(pawns.size() < 2)
            return true;
        return false;
    }

    /**
     * Attempts to add a pawn to the game state.
     * 
     * @param p The pawn to potentially be placed.
     * @return Whether the pawn was successfully placed.
     */
    public boolean addPawn(Pawn p) {
        pawns.add(p);
        return true;
    }

    /**
     * Adds a player to the game state.
     * 
     * @param p The player to add to the game state.
     * 
     */
    public void addPlayer(Player p){
    	players.add(p);
    }

    /**
     * Attempts to move a given pawn to a given position.
     * 
     * @param p The pawn to be moved.
     * @param move The move the pawn is attempting to execute.
     * @return Whether the pawn could be moved.
     */
    public boolean movePawn(Pawn p, String move) {
        Position pos = new Position(move);
        for(Position po : p.getCurrentMoves())
            if(pos.equals(po)) {
                p.setPosition(pos);
                return true;
            }
        return false;
    }

    /**
     * Name a player winner of the game.
     * 
     * @param p The player that has won the game.
     */
    public void setWinner(Player p) {
        winner = p;
    }
    
    /**
     * @return Return the player representing the winner of the game.
     */
    public Player getWinner() {
        return winner;
    }

}


