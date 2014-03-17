/**
 * 
 */
package quoridor.backendTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import quoridor.backend.containers.Player;

/**
 * @author croller
 *
 */
public class PlayerTest {

	public void testCreatingPlayersWithNames(){
		Player[] p = new Player[4];
		p[0] = new Player("James", "e1", p.length);
		p[1] = new Player("Mike", "i5", p.length);
		p[2] = new Player("Nick", "e9", p.length);
		p[3] = new Player("George", "a5", p.length);
		
		System.out.println("4 Players created");
		for(int i = 4; i<100; i++){
			
		}
	}
	
	public void testCreatingPlayersWithoutNames(){
		Player[] p = new Player[4];
		p[0] = new Player("", "e1", p.length);
		p[1] = new Player("", "i5", p.length);
		p[2] = new Player("", "e9", p.length);
		p[3] = new Player("", "a5", p.length);
		
		System.out.println("4 Players created");
	}
	public boolean checkLocation(Player p, String test){
		if(p.getLocation().equals(test))
			return true;
		return false;
	}
	
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
