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

public class WallTest extends TestCase{
	
	private Walls walls;
	private Position posA;
	private Position posB;
	private Position posC;
	
	
	public void setUp() {
	    walls = new Walls();
	    posA = new Position("e5");
	    posB = new Position("f5");
	    posC = new Position("e6");
	    
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
	
	/*
	@Test
	public void testAddingAWall(){
		
		
	}
	*/
	
		
	
}

