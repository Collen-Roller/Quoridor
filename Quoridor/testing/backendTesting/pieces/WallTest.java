/*
 *	Author: John Payton
 */


package backendTesting.pieces;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import junit.framework.*;


import quoridor.backend.containers.Position;
import quoridor.backend.pieces.Walls;
import quoridor.main.Quoridor;

public class WallTest extends TestCase{
	
	private Walls walls;
	private Position posA;
	private Position posB;
	private Position posC;
	
	
	public void setUp() {
	    Quoridor.newGameState();
		walls = new Walls();
	    posA = new Position("e5");
	    posB = new Position("f5");
	    posC = new Position("e6");
	    walls.add(posA, posB);
	    walls.add(posA, posC);
	}
	
	
	public void testIsVerticalTrue(){
		boolean result = walls.isVertical(posA,posB);
		boolean expectedResultOfIsVertical = true;
		assertEquals(expectedResultOfIsVertical, result);
	}
	
	
	public void testIsVerticalFalse(){
		boolean result = walls.isVertical(posA,posC);
		boolean expectedResultOfIsVertical = false;
		assertEquals(expectedResultOfIsVertical, result);
	}
	
	
	
	public void testCanAddAWallTrue(){
		boolean expectedResultOfCanAdd = true;
		boolean result = walls.canAdd(new Position("a6"), new Position("b6"), Quoridor.getGameState());
		assertEquals(expectedResultOfCanAdd, result);
	}
	
	public void testCanAddAWallFalse(){
		boolean expectedResultOfCanAdd = false;
		boolean result = walls.canAdd(new Position("e5"), new Position("e6"), Quoridor.getGameState());
		assertEquals(expectedResultOfCanAdd, result);
	}
	
	public void testAddingAWallVertical(){
		
		Walls testWall = new Walls();
		testWall.add(posA, posB);
		assertEquals(walls.getWallsVertical(), testWall.getWallsVertical());
				
	}
	
	public void testAddingAWallHorizontal(){
		Walls testWall = new Walls();
		testWall.add(posA, posC);
		assertEquals(walls.getWallsHorizontal(), testWall.getWallsHorizontal());
	}
	
	public void testIsBlockedTrue(){
		boolean expectedResultOfIsBlocked = true;
		boolean result = walls.isBlocked(posA, posB);
		assertEquals(expectedResultOfIsBlocked, result);
	}
	
	public void testIsBlockedFalse(){
		boolean expectedResultOfIsBlocked = false;
		boolean result = walls.isBlocked(new Position("a1"), new Position("b1") );
		assertEquals(expectedResultOfIsBlocked, result);
	}
		
}

