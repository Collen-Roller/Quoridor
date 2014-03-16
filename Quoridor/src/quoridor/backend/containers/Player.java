package quoridor.backend.containers;

/**
 * @author Team 4 Men And A Cripple
 *
 * This class stores information relevant to a given player in a game of
 * Quoridor.
 */
public class Player {

    
	/**
	 * The number of walls this player has placed.
	 */
	int wallsplaced;

	/**
	 * The number of walls this player can still place.
	 */
	int remainingwalls;

	/**
	 * The number of moves this player has made.
	 */
	int movecount;

	/**
	 * The string encoding of the player's current location.
	 */
	String location;

	/**
	 * The name associated with this player.
	 */
	String name;

	/**
	 * Constructs a new Player object with the specified information.
	 * 
	 * @param name The name this player should be associated with.
	 * @param loc The initial location of this player.
	 * @param nop The total number of players in the current game.
	 */
	public Player(String name, String loc, int nop){
		this.wallsplaced = 0;
		this.remainingwalls = 20/nop;
		this.movecount = 0;
		this.location = loc;
		this.name = name;
		//TODO: how to implement possible moves! do this when AI is made
	}

	/**
	 * Updates the number of walls placed and remaining walls. If a player
	 * places a wall wallsplaced is incremented and remainingwalls is
	 * decremented.
	 */
	public void updateWalls(){
		wallsplaced++;
		remainingwalls--;
		updateMove();
	}

	/**
	 * Updates the number of moves a player has made. If a player makes a move
	 * movecount is incremented.
	 */
	public void updateMove(){
		movecount++;
	}

	/**
	 * Updates the location of the player. If a player's position changes the
	 * old location is replaced by the new location.
	 * 
	 * @param loc A string encoding of this player's new location.
	 */
	public void updateLocation(String loc){
		location = loc;
	}
	
	/**
	 * @return The current location of this player.
	 */
	public String getLocation(){
		return location;
	}

	/**
	 * @return The number of walls placed by this player.
	 */
	public int getWallsPlaced(){
		return wallsplaced;
	}

	/**
	 * @return The number of walls this player can still place.
	 */
	public int getRemainingWalls(){
		return remainingwalls;
	}

	/**
	 * @return The number of moves this player has made.
	 */
	public int getMoveCount(){
		return movecount;
	}

	/**
	 * @return The name associated with this player.
	 */
	public String getName(){
		return name;
	}
	
	// TODO: Testing the player class. We need a test that constructs several
	// players, inputs a theoretical set of moves, then checks if the stored
	// information matches what would be expected.

}


