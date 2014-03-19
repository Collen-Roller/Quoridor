package quoridor.backend.pieces;

import java.awt.Image;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quoridor.backend.containers.Position;
import quoridor.main.Quoridor;
import quoridor.network.client.NetworkClient;

/**
 * @author Team 4 Men And A Cripple
 * 
 * Represents a pawn in a game of Quoridor.
 */
public class Pawn {
	
	/**
	 * Boolean telling if players turn or not
	 */
	private boolean currentTurn;

    /**
     * The position of this pawn.
     */
    private Position pos;

    /**
     * The object handing the network connection relevant to this pawn.
     */
    private NetworkClient networkClient;

    /**
     * The regex to determine if a move is of the correct format.
     */
    private final String moveRegex = "[a-i][1-9][h,v]|[a-h][1-8]";

    /**
     * The graphic to be drawn on screen when this pawn is painted.
     */
    private Image pawn;

    /**
     * Constructs a new pawn with the given position and associated graphic.
     * 
     * @param pos A string encoding of this pawns initial position.
     * @param p The graphic representing this pawn.
     */
    public Pawn(String pos, Image p) {
    	this.currentTurn = false;
        this.pos = new Position(pos);
        this.pawn = p;
    }
    
    /**
     * Method to change if the player is in a turn
  	 *
     * @param b to set currentTurn to
     */
    public void isTurn(boolean b){
    	currentTurn = b;
    }
    
    /**
     * Method to access if the player is in a turn
     * 
     * @return currentTurn
     */
    public boolean currentTurn(){
    	return currentTurn;
    }
    
    
    
    /**
     * @return The graphic representing this pawn.
     */
    public Image getPawn(){
    	return pawn;
    }

    /**
     * @return The moves this pawn can possibly make.
     * 
     * TODO: Implement all rules and debug.
     */
    public Set<Position> calcMoves() {
        Set<Position> moves = new TreeSet<Position>();
        moves.add(new Position(pos.x + 1, pos.y));
        moves.add(new Position(pos.x, pos.y + 1));
        moves.add(new Position(pos.x - 1, pos.y));
        moves.add(new Position(pos.x, pos.y - 1));
        Iterator<Position> itr = moves.iterator();
        while(itr.hasNext()) {
            Position p = itr.next();
            if(p.x < 0 || p.y < 0 || p.x > 8 || p.y > 8)
                itr.remove();
        }
        return moves;
    }

    /**
     * Attempt to establish a TCP to the given host.
     * 
     * @param host The name of the host to connect to.
     * @return Whether the connection was successfully established.
     */
    public boolean startNetwork(String host) {
        try {
            networkClient = new NetworkClient(host);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @return The object representing the connection relevant to this
     *         pawn.
     */
    public NetworkClient getClient(){
    	return networkClient;
    }

    
    /**
     * @return The move passed up from the network for this pawn to execute.
     */
    public String getMove(String name) {
    	sendPossibleMoves(name);
        networkClient.sendString("MOVE?");
        String s = networkClient.getString();
        Pattern r = Pattern.compile(moveRegex);
        Matcher m = r.matcher(s);
        if(m.find())
            return s;
        return "ERROR";
    }
    /**
     * Sends players possible moves to the player
     * @param name
     */
    public void sendPossibleMoves(String name){
    	Set<Position> p = calcMoves();
    	networkClient.sendString(name + " here are your possible moves to make : " + p.toString());
    }

    /**
     * Updates the position of this pawn.
     * 
     * @param s The new position for this pawn.
     */
    public void move(String s) {
        pos = new Position(s);
    }

    /**
     * Severs the network connection for this pawn.
     */
    public void boot() {
        for(Pawn p : Quoridor.getGameState().getPawns())
            p.networkClient.sendString("BOOTED PLAYER");
        networkClient.close();
    }

    /**
     * @return The current position of this pawn.
     */
    public Position getPosition() {
        return pos;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Pawn at: " + pos;
    }

    /**
     * @param pos The new position for this pawn
     * 
     * TODO: This method and the move method are identical.
     */
    public void setPosition(Position pos) {
        this.pos = pos;
    }

}


