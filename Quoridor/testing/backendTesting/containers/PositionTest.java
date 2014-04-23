/*
 * This series of tests was written by Neil Kasson 
 */

package backendTesting;

//import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import quoridor.backend.containers.Position;

@SuppressWarnings("deprecation")
public class PositionTest {

	
	@Test
	public void testEqualsSameIntParameters() {
		Position a = new Position(3, 2);
		Position b = new Position(3, 2);
		Assert.assertTrue(a.equals(b));
	}
	
	@Test
	public void testEqualsIntVsStringParameters(){
		Position a = new Position(3,2);
		Position b = new Position("D2");
		Assert.assertTrue(a.equals(b));
	}
	
	@Test
	public void testEqualsUpperVsLowerCaseStringParams(){
		Position a = new Position("D2");
		Position b = new Position("d2");
		Assert.assertTrue(a.equals(b));
	}
	
	@Test
	public void testNonEqualIntParameters(){
		Position a = new Position(3,2);
		Position b = new Position(2,3);
		Assert.assertFalse(a.equals(b));
	}
	
	@Test
	public void testNonEqualIntVsStringParameters(){
		Position a = new Position(3,2);
		Position b = new Position("C2");
		Assert.assertFalse(a.equals(b));
	}
	
	@Test
	public void testToString(){
		String a = "d2";
		Position b = new Position(a);
		Assert.assertEquals(a, b.toString());
	}
	
	@Test
	public void testToStringFromIntParam(){
		Position a = new Position(3, 2);
		Assert.assertEquals("d2", a.toString());
	}
	
	@Test
	public void testCompareToWithEqualIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(3,2);
		int zero = a.compareTo(b);
		Assert.assertEquals(0, zero);
	}
	
	@Test 
	public void testCompareToWithLowerXValAndEqualYValIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(2,2);
		int num = a.compareTo(b);
		Assert.assertEquals(-1, num);
	}
	
	@Test 
	public void testCompareToWithGreaterXValAndEqualYValIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(4,2);
		int num = a.compareTo(b);
		Assert.assertEquals(1, num);
	}
	
	@Test 
	public void testCompareToWithGreaterYValAndEqualXValIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(3,3);
		int num = a.compareTo(b);
		Assert.assertEquals(1, num);
	}
	
	@Test 
	public void testCompareToWithLowerYValAndEqualXValIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(3,1);
		int num = a.compareTo(b);
		Assert.assertEquals(1, num);
	}
	
	@Test 
	public void testCompareToWithLowerXValAndGreaterYValIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(2,3);
		int num = a.compareTo(b);
		Assert.assertEquals(1, num);
	}
	
	@Test 
	public void testCompareToWithGreaterXValAndLowerYValIntParams(){
		Position a = new Position(3,2);
		Position b = new Position(4,1);
		int num = a.compareTo(b);
		Assert.assertEquals(1, num);
	}
}

