/*Author:Collen Roller
  Team: 4 Men and a Cripple
  Date: 2/16/2014
*/
package code.Player;

public class Player {

	int wallsplaced;
	int remainingwalls;
	int movecount;
	String location;
	String name;
	
	//Possilby have an array store wall locations?
	//might be unecessary....


	//Possibly make it so that you can choose a pawn?
	//and when one player chooses a pawn the option for that pawn
	//for other players goes away? 
	public Player(String name, String location){
		this.wallsplaced = 0;
		this.remainingwalls = 8;
		this.movecount = 0;
		this.location  = location;
		this.name = name;	
	}
	
	//Mutator Medoth to increment wallsplaced / decrement remaningwalls
	public void updateWalls(){
		wallsplaced++;
		remainingwalls--;
	}
	
	//Mutator method to increment movecount
	public void updateMove(){
		movecount++;
	}
	
	//Mutator method for players location on board
	public void updateLocation(String loc){
		location = loc;
		updateMove();
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
	
	//Accessor method for player location
	public String getLocation(){
		return location;
	}
	
	//Accessor method for player name
	public String getName(){
		return name;
	}		
	
}
