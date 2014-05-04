package quoridor.backend.pieces;

import java.awt.Image;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quoridor.backend.containers.Player;
import quoridor.backend.containers.Position;
import quoridor.backend.managers.ShortestPath;
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
    private final String moveRegex = "([a-h][1-8][h,v])|([a-i][1-9])";

    /**
     * The graphic to be drawn on screen when this pawn is painted.
     */
    private Image pawn;
    
    private Position[] currentMoves;
    
    private String winRegex;

    private ShortestPath path;

    /**
     * Constructs a new pawn with the given position and associated graphic.
     * 
     * @param pos A string encoding of this pawns initial position.
     * @param p The graphic representing this pawn.
     */
    public Pawn(String pos, Image p) {
    	setWinCondition(pos);
    	this.currentTurn = false;
        this.pos = new Position(pos);
        this.pawn = p;
    }
    
    /**
     *
     * Declares the REGEX for such player to win a game
     * 
     * @param pos
     */
    public void setWinCondition(String pos){
    	if(pos.equals("E1")){
    		this.winRegex = "([a-i][9])";
    	}else if(pos.equals("E9")){
    		this.winRegex = "([a-i][1])";
    	}else if(pos.equals("I5")){
    		this.winRegex = "([a][1-9])";
    	}else if(pos.equals("A5")){
    		this.winRegex = "([i][1-9])";
    	}
    }
    
    /**
    *
    * Returns the winRegex. Used for testing purposes
    * 
    * @param pos
    */
    public String getWinCondition(){
    	return winRegex;
    }
    
    /**
     * Checks if this pawn has reached its endzone.
     * 
     * @return Whether this pawn has reached its endzone.
     */
    public boolean didPawnWin(){
        Pattern r = Pattern.compile(winRegex);
        Matcher m = r.matcher(pos.toString());
        if(m.find()){
        	return true;
        }else 
        	return false;
    }
    
    /**
     * Sends a string message to the server this pawn represents.
     * 
     * @param s The message to send.
     */
    public void sendMessageToPlayer(String s){
    	networkClient.sendString(s);
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
     * Generates the shortest path to this pawns endzone.
     * 
     * @param w The walls to consider in path-finding.
     * @param pawns The other pawns that exist in this pawn's game state.
     * @return The shortest path to this pawns endzone.
     */
    public ArrayList<Position> genPath(Walls w, ArrayList<Pawn> pawns) {
        path = new ShortestPath(this, (Position) null, w, pawns);
        if(path != null)
            return path.getPath();
        return null;
    }

    /**
     * Calls this pawns calcMoves method with the typical client parameters.
     * Does NOT work for calling calc moves on the server.
     * 
     * @return The calculated set of positions this pawn can move to.
     */
    public Set<Position> calcMoves() {
        return calcMoves(Quoridor.getGameState().getWalls(),
                Quoridor.getGameState().getPawns());
    }

    /**
     * @return The moves this pawn can possibly make.
     */
    public Set<Position> calcMoves(Walls w, ArrayList<Pawn> pawns) {
        Queue<Position> toAdd = new LinkedList<Position>();
        Set<Position> moves = new TreeSet<Position>();
        moves.add(new Position(pos.x + 1, pos.y));
        moves.add(new Position(pos.x, pos.y + 1));
        moves.add(new Position(pos.x - 1, pos.y));
        moves.add(new Position(pos.x, pos.y - 1));
        Iterator<Position> itr = moves.iterator();
        while(itr.hasNext()) {
            Position p = itr.next();
            if(p.x < 0 || p.y < 0 || p.x > 8 || p.y > 8
                    || w.isBlocked(pos, p))
                itr.remove();
        }
        itr = moves.iterator();
        while(itr.hasNext()) {
            Position p = itr.next();
            for(Pawn pa : pawns)
                if(!pa.equals(this) && pa.pos.equals(p)) {
                    itr.remove();
                    ArrayList<Pawn> po = new ArrayList<Pawn>();
                    po.add(this);
                    po.add(pa);
                    toAdd.addAll(pa.calcMovesRecurse(po, w, pawns));
                }
        }
        moves.addAll(toAdd);
        moves.toArray(currentMoves = new Position[moves.size()]);
        return moves;
    }

    /**
     * Calculates the moves this pawn can possibly make.
     * 
     * @param po The position being considered.
     * @param w The wall configuration of the game state.
     * @param pawns The other pawns in this pawn's game state.
     * @return The set of positions this pawn can move to.
     */
    public Set<Position> calcMovesRecurse(ArrayList<Pawn> po, Walls w, ArrayList<Pawn> pawns) {
        Queue<Position> toAdd = new LinkedList<Position>();
        Set<Position> moves = new TreeSet<Position>();
        moves.add(new Position(pos.x + 1, pos.y));
        moves.add(new Position(pos.x, pos.y + 1));
        moves.add(new Position(pos.x - 1, pos.y));
        moves.add(new Position(pos.x, pos.y - 1));
        Iterator<Position> itr = moves.iterator();
        while(itr.hasNext()) {
            Position p = itr.next();
            if(p.x < 0 || p.y < 0 || p.x > 8 || p.y > 8
                    || w.isBlocked(pos, p))
                itr.remove();
        }
        itr = moves.iterator();
        while(itr.hasNext()) {
            Position p = itr.next();
            for(Pawn pa : pawns)
                if(pa.pos.equals(p)) {
                    itr.remove();
                    if(!po.contains(pa)) {
                        po.add(pa);
                        toAdd.addAll(pa.calcMovesRecurse(po, w, pawns));
                    }
                    break;
            }
        }
        moves.addAll(toAdd);
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
     * @return The move, passed up from the network, for this pawn to execute.
     */
    public String getMove(String name) {
    	//sendPossibleMoves(name);
        networkClient.sendString("MOVE?");
        String s = networkClient.getString();
        Pattern r = Pattern.compile(moveRegex);
        try {
        Matcher m = r.matcher(s);
        if(m.find())
            return s;
        } catch(NullPointerException e) {
        	return "ERROR";
        }
        return "ERROR";
    }

    /**
     * Sends players possible moves to the player
     * @param name
     */
    public void sendPossibleMoves(String name){
    	networkClient.sendString(name + " here are your possible moves to"
    	                     + " make : " + Arrays.toString(getCurrentMoves()));
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
    public void boot(Player pl) {
        for(Pawn p : Quoridor.getGameState().getPawns())
            p.networkClient.sendString("REMOVED " + pl.getName());
        networkClient.close();
    }

    /**
     * @return The current position of this pawn.
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * Determines if this pawn and another are the same pawn.
     * 
     * @param p The pawn that may be the same pawn.
     * @return Whether these pawns are considered the same.
     */
    public boolean equals(Pawn p) {
        return pos.equals(p.pos);
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

    /**
     * Gets the current generated possible moves for this pawn.
     * 
     * @return The possible moves this pawn can make.
     */
    public Position[] getCurrentMoves() {
        return currentMoves;
    }

}


