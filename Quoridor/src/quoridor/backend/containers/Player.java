package quoridor.backend.containers;

public class Player {

	int wallsplaced;
	int remainingwalls;
	int movecount;
	String location;
	String name;
	//String [] possiblemoves;

	//Possibly make it so that you can choose a pawn?
	//and when one player chooses a pawn the option for that pawn
	//for other players goes away? 
	public Player(String name, String loc, int nop){
		this.wallsplaced = 0;
		this.remainingwalls = 20/nop;
		this.movecount = 0;
		this.location = loc;
		this.name = name;
		//TODO: how to implement possible moves! do this when AI is made
	}
	
	//Mutator Method to increment wallsplaced / decrement remaningwalls
	public void updateWalls(){
		wallsplaced++;
		remainingwalls--;
	}
	
	//Mutator method to increment movecount
	public void updateMove(){
		movecount++;
	}
	
	//Mutator method
	public void updateLocation(String loc){
		location = loc;
	}
	
	public String getLocation(){
		return location;
	}
	
	//Accessor method for wallsplaced
	public int getWallsPlaced(){
		return wallsplaced;
	}
	
	// Accessor method for remaining walls
	public int getRemainingWalls(){
		return remainingwalls;
	}
	
	//Accessor method for # of moves made
	public int getMoveCount(){
		return movecount;
	}
	
	//Accessor method for player name
	public String getName(){
		return name;
	}		
	
}


