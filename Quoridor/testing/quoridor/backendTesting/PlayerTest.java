package quoridor.backendTesting;

import junit.framework.*;
import quoridor.backend.containers.*;

/**
 * @author croller
 *
 */
public class PlayerTest extends TestCase{

	private Player player1;
	
	private Position pos = new Position("e1");
	
	public void setUp(){
		player1 = new Player("James 1", "e1", 4);
	}

	public void testPlayerName(){
		String n = "James";
		assertEquals(n, player1.getName());
	}
	
	public void testPosition(){
		Position testPos = new Position(player1.getLocation());
		assertEquals(pos, testPos);
	}
	
	public void testNumberOfWalls(){
		assertEquals(5, player1.getRemainingWalls());
	}
}
	
	
