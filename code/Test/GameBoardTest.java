/*
  Author: Collen Roller
  Date 2/26/14
*/
package code.Test;

import java.util.*;

import code.Player.Player;
import code.Client.GameBoard;

public class GameBoardTest {

	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		GameBoard board = createNewGameBoard(sc);
		String [] names = getNames(sc, board.getNumberOfPlayers());
		String [] startingpositions = getStartingPositions(sc, board.getNumberOfPlayers());
		testInfopanelPlacement(board, names);
		
	}
	
	public static String[] getNames(Scanner sc, int n){
		String [] names = new String [n];
		int count = 1;
		for(int i=0; i<names.length; i++){
			System.out.print("Player " + count + " Enter Your Name: ");
			String s = sc.next();
			names[i] = s;
			count++;
		}
		return names;
	}
	
	public static String[] getStartingPositions(Scanner sc, int n){
		return new String [0];
	}
	
	public static void testInfopanelPlacement(GameBoard g, String [] names){
		Player [] p= new Player[g.getNumberOfPlayers()];
		
		if(g.getNumberOfPlayers() == 2){
			p[0] = new Player(names[0], "a2");
			p[1] = new Player(names[1], "e6");
		}else{
			p[0] = new Player(names[0], "a2");
			p[1] = new Player(names[1], "e6");
			p[2] = new Player(names[2], "b3");
			p[3] = new Player(names[3], "f7");
		}
		
		//calling the update info panel method which updates the info panel of player info
		g.updatePlayerInfo(p);
		System.out.println("Placement on board is done");
		
	}
	
	public static GameBoard createNewGameBoard(Scanner sc){
		System.out.println("Welcome to Quoridor GameBoard test");
		System.out.print("Enter how many ai players are playing ");
		int aip = Integer.parseInt(sc.next());
		if(aip != 0 && aip != 1 && aip != 2 && aip!= 3 && aip != 4){
			System.out.println("Invalid number of AI players... re-run and try again");
			System.exit(1);
		}
		System.out.print("Enter how many people players are playing ");
		int pp = Integer.parseInt(sc.next());
		if(aip+pp != 2 && aip+pp != 4){
			System.out.println("\ninvalid amount of players, there can only be 2 or 4 players");
			System.out.println("re-run and try again");
			System.exit(1);
		}
		return new GameBoard(pp,aip);
	}

}
