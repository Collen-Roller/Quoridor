/*
 *	Author: John Payton
 */

package backendTesting.pieces;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;
import junit.framework.*;


import quoridor.backend.containers.Position;
import quoridor.main.Quoridor;
import quoridor.network.client.NetworkClient;
import quoridor.backend.pieces.Pawn;


public class PawnTest extends TestCase{

	public Pawn pawn1;
	public Pawn pawn2;
	public Image pawn1Image;
	public Image pawn2Image;
	public Position pos1 = new Position("E1");
	public Position pos2 = new Position("E9");
	
	
	
	public void setUp(){
		
		pawn1Image = Toolkit.getDefaultToolkit().createImage("res/pawn_dark.png");
		pawn1 = new Pawn("E1", pawn1Image);
		
		pawn2Image = Toolkit.getDefaultToolkit().createImage("res/pawn_light.png");
		pawn2 = new Pawn("E9", pawn2Image);		
		
	}
	
	public void testImageOfPawn(){
		
		Image pawnTestImage = pawn1.getPawn();
		assertEquals(pawn1Image, pawnTestImage);			
	}
	
	public void testSetWinCondition(){
		pawn1.setWinCondition("E1");
		String winRegex = "([a-i][9])";
		assertEquals(winRegex, pawn1.getWinCondition());
		pawn1.setWinCondition("E9");
		winRegex = "([a-i][1])";
		assertEquals(winRegex, pawn1.getWinCondition());
		pawn1.setWinCondition("I5");
		winRegex = "([a][1-9])";
		assertEquals(winRegex, pawn1.getWinCondition());
		pawn1.setWinCondition("A5");
		winRegex = "([i][1-9])";
		assertEquals(winRegex, pawn1.getWinCondition());
	}
	
	public void testSetPosition(){
		pawn1.setPosition(pos1);
		assertEquals(pos1, pawn1.getPosition());
	}
	
	public void testDidPawnWinTrue(){
		pawn1.setWinCondition("E1");
		pawn1.setPosition(pos2);
		boolean expectedResultOfDidPawnWin = true;
		boolean result = pawn1.didPawnWin();
		assertEquals(expectedResultOfDidPawnWin, result);
	}
	
	public void testDidPawnWinFalse(){
		pawn1.setWinCondition("E1");
		pawn1.setPosition(pos1);
		boolean expectedResultOfDidPawnWin = false;
		boolean result = pawn1.didPawnWin();
		assertEquals(expectedResultOfDidPawnWin, result);
	}
	
	public void testOfIsTurnAndCurrentTurnTrue(){
		pawn1.isTurn(true);
		boolean expectedResultOfCurrentTurn = true;
		boolean result = pawn1.currentTurn();
		assertEquals(expectedResultOfCurrentTurn, result);
			
	}
	
	public void testOfIsTurnAndCurrentTurnFalse(){
		pawn1.isTurn(false);
		boolean expectedResultOfCurrentTurn = false;
		boolean result = pawn1.currentTurn();
		assertEquals(expectedResultOfCurrentTurn, result);
				
	}
	
	
	
	
}