/*Author: Collen Roller
  Team: 4 Men and a Cripple
*/
package code.Client;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import code.Player.Player;

public class GameBoard extends JFrame {

	private JFrame frame;                  //Frame
	private JPanel gameboard;              //Board Panel
    private JPanel infoboard;              //Info Panel
    private JPanel console;                // Console Panel
    private HashMap<String,JLabel> squares;//squares on board
    private HashMap<String,JLabel> walls;  //walls on board
    private JLabel[] playerinfo;           //Play panels for game info
    private int numberofplayers;           // Number of human players
    private int numberofaiplayers;         //Number of AI players
	
	//Basic Constructor to initalize various fields and call methods
	//to set up frame, panels on client side of things
	public GameBoard(int numbersofplayers, int numberofaiplayers){
		this.numberofplayers = numbersofplayers;
		this.numberofaiplayers = numberofaiplayers;
		this.frame = new JFrame("Quoridor");
		this.gameboard = new JPanel();
        this.infoboard = new JPanel();
        this.console = new JPanel();
        this.squares = new HashMap<String, JLabel>();
        this.walls = new HashMap<String, JLabel>();
        this.playerinfo = new JLabel[4];
		setBackgroundAndFrame();
		setGameBoardOnFrame();
        setInfoPanelOnFrame();
        setConsoleOnFrame();
		pack();
		setResizable(false);
		setVisible(true);	
	}

	//grab picture and add it to the frame
	public void setBackgroundAndFrame() {
		setTitle("Quoridor");
		setPreferredSize(new Dimension(1024,768));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new JLabel(new ImageIcon("Images/image1.png")));
		setLayout(null);
		setPreferredSize(new Dimension(1024,768));
		setPreferredSize(new Dimension(1024,768));
	}

	public void setGameBoardOnFrame(){
		gameboard.setLayout(new GridLayout(9,9,10,10));
		
		int count = 0; //to determine picture selection
		
		//97-105 for lowercase ASCII codes for Hashmap (Columns)
		for(int i = 97; i < 106; i++){
			String s = Character.toString((char)i);
			
			//for numbers to determine (rows)
			for(int j = 1; j < 10; j++){
				s += j;
				
				//Determins to place the jabel in the board square depending on count
				if(count % 2 == 0)
					squares.put(s, new JLabel(new ImageIcon("Images/image3.png")));
				else
					squares.put(s, new JLabel(new ImageIcon("Images/image7.png")));
				count++;
				gameboard.add(squares.get(s));
			}
		}
		
		//Sets size of the board, location, and background color, adds it to board
		gameboard.setBorder(BorderFactory.createRaisedBevelBorder());
		gameboard.setBackground(Color.BLACK);
		gameboard.setBounds(20,20,550,550);
		getContentPane().add(gameboard);
	}

	//Places InfoPanel On Frame
    public void setInfoPanelOnFrame(){
        infoboard.setLayout(null);
        infoboard.setBorder(BorderFactory.createRaisedBevelBorder());
        infoboard.setBackground(new Color(0,0,0,65));
        infoboard.setBounds(590,20,400,550);
        
      	//Adds Player JPanel's to infoPanel
      	//offset to determine Y axis placement 
      	int offset = 30;
      	for(int i=0; i<numberofplayers+numberofaiplayers; i++){
      		playerinfo[i] = new JLabel();
      		playerinfo[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
      		playerinfo[i].setBackground(Color.BLACK);
      		playerinfo[i].setBounds(50,offset,300,100);
      		offset += 130;
      		
      		//possibly update players right here if we pass player[] in...
      		
      		infoboard.add(playerinfo[i]);
      	}
        getContentPane().add(infoboard);
    }
	
	//Places Console Panel on Jframe
	//Console panel shows moves that players make
	//shows if a player is booted, also shows specific location 
	//move takes place (Player2: e4-e5) || (Player3: e6h)
    public void setConsoleOnFrame(){
        console.setLayout(null);
        console.setBorder(BorderFactory.createRaisedBevelBorder());
        console.setBackground(new Color(0,0,0,65));
        console.setBounds(20,590,974,138);
        getContentPane().add(console); 
   	}
   	
   	public int getNumberOfPlayers(){
   		return numberofplayers + numberofaiplayers;
   	}
   	
   	public void updatePlayerInfo(Player [] p){
   		for(int i=0; i< p.length; i++){
   			updateSinglePlayerInfo(p[i], i);
   		}
   	}
   	
   	public void updateSinglePlayerInfo(Player p, int index){
   		JLabel name = new JLabel(p.getName(),JLabel.CENTER);
   		infoboard.add(name);
   		name.setFont(new Font("Verdana", Font.BOLD, 22));
   		name.setForeground(Color.BLACK);
   		name.setOpaque(true);
   		name.setBackground(new Color(0,0,0,0));
   		name.setBounds(70,(130*index),260,100);
   		
    	JLabel loc = new JLabel("Current Location: " + p.getLocation(),JLabel.CENTER);
    	infoboard.add(loc);
    	loc.setFont(new Font("Verdana", Font.BOLD, 14));
    	loc.setForeground(Color.BLACK);
    	loc.setOpaque(true);
   		loc.setBackground(new Color(0,0,0,0));
    	loc.setBounds(70,(130*index)+20,260,100);
    					
    	String walls = "";
    	for(int i=0; i<p.getRemainingWalls(); i++)
    		walls += "|";
    		
    	JLabel wall = new JLabel("Remaining Walls: " + walls,JLabel.CENTER);
    	infoboard.add(wall);
    	wall.setFont(new Font("Verdana", Font.BOLD, 14));
    	wall.setForeground(Color.BLACK);
    	wall.setOpaque(true);
   		wall.setBackground(new Color(0,0,0,0));
    	wall.setBounds(70,(130*index)+40,260,100);
    					
    	JLabel moves = new JLabel("Move Count: " + p.getMoveCount(), JLabel.CENTER);
    	infoboard.add(moves);
    	moves.setFont(new Font("Verdana", Font.BOLD, 14));
    	moves.setForeground(Color.BLACK);
    	moves.setOpaque(true);
   		moves.setBackground(new Color(0,0,0,0));
    	moves.setBounds(70,(130*index)+60,260,100);
    	
    	
    }
    
   	public void updateGameBoardPawn(String position, String player){
   		//move the specific players pawn to the new position
   		//add pawn to spot, remove pawn from old spot
   	}
   	
   	public void updateGameBoardWall(String position){
   		//placewall at valid position
   	}
   	
   	public void updateConsole(String s){
   		//print s to console....
   	}
}

