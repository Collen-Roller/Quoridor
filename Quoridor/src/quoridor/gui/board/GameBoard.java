package quoridor.gui.board;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import quoridor.backend.containers.Player;
import quoridor.backend.containers.Position;
import quoridor.backend.pieces.Pawn;
import quoridor.gui.interfaces.GUIPanel;
import quoridor.main.Quoridor;

/**
 * @author Team 4 Men And A Cripple
 * @version 2014-05-04
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel implements GUIPanel {

	/**
	 * The canvas the game board is drawn onto.
	 */
	private Canvas board;

	/**
	 * The panel that contains the player information.
	 */
	private JPanel infoBoard;

	/**
	 * The panel that contains the console.
	 */
	private JPanel consolePane;
	
	/**
	 * The label for displaying player information.
	 */
	private ArrayList <JLabel> playerInfo;

	/**
	 * The text area that displays inbound and outbound messages to/from the
	 * servers.
	 */
	private JTextArea console;

	/**
	 * The background image for the game board.
	 */
	private final Image background;

	/**
	 * The images for alternating tiles on the game board.
	 */
	private final Image tileA, tileB;
	
	/**
	 * The image for a vertical wall.
	 */
	private final Image wallv;

	/**
	 * The image for a horizontal wall.
	 */
	private final Image wallh;
	
	/**
	 * The highlighting on a position a pawn can move to.
	 */
	private final Image moveTo;
	
	/**
	 * Images, for endzones, both 2 player and 4 player games
	 */
	private final Image playerBR, playerBL, playerTR, playerTL, playerT,
	    playerB;
	
	/**
	 * The buffer used for double buffering.
	 */
	private Image newBuffer;

	/**
	 * The graphics object drawn to for double buffering.
	 */
	private Graphics offscreen;
	
	/**
	 * The number of players in the game.
	 */
	private int numberOfPlayers;
	
	private final int numberOfPlayersAtStart;

	/** Creates a gameboard object 
	* Sets the background, tiles of the board, and the wall pieces
	* Sets up endzones, and highlight panel as well
	* Sets up the gameboard, infopanel, and console
	*/
	public GameBoard(int numberOfPlayers) {
		this.numberOfPlayersAtStart = numberOfPlayers;
		this.numberOfPlayers = numberOfPlayers;
		UIManager.put("Tree.rendererFillBackground", false);
		background = Toolkit.getDefaultToolkit().createImage("res/image1.png");
		tileA = Toolkit.getDefaultToolkit().createImage("res/lightwood.png");
		tileB = Toolkit.getDefaultToolkit().createImage("res/darkwood.png");
		wallv = Toolkit.getDefaultToolkit().createImage("res/WallV.png");
		wallh = Toolkit.getDefaultToolkit().createImage("res/WallH.png");
		moveTo = Toolkit.getDefaultToolkit().createImage("res/highlight_overlay.png");
		playerBL = Toolkit.getDefaultToolkit().createImage("res/4playerBL.png");
		playerBR = Toolkit.getDefaultToolkit().createImage("res/4playerBR.png");
		playerTL = Toolkit.getDefaultToolkit().createImage("res/4playerTL.png");
		playerTR = Toolkit.getDefaultToolkit().createImage("res/4playerTR.png");
		playerB = Toolkit.getDefaultToolkit().createImage("res/dark_endzone.png");
		playerT = Toolkit.getDefaultToolkit().createImage("res/light_endzone.png");
		setLayout(null);
		setGameBoard();
		setInfoPanel();
		setConsole();
		//setBackground(Color.MAGENTA); Not necessary anymore
	}

	/** Sets up the GUI, that contains the info panel
	* as well as the console, and the game board
	* Creates the board by placing the tiles (every other one in the row 
	* is the opposite)
	*/
	public void setGameBoard() {
		board = new Canvas() {

			// Determining Location: n * 44 + 6 * n + 1
			@Override
			public void paint(Graphics g) {

			    // The fix for the null pointer exception, if the game was cleaned
			    // up the game state will be null so when the buffer tries to use
			    // it for drawing we get a null pointer exception.
				if(Quoridor.getGameState() == null)
				    return;

				if (newBuffer == null || offscreen == null) {
					newBuffer = board.createImage(450, 450);
					offscreen = newBuffer.getGraphics();
				}
				
				
				//Clear the rectangle
				offscreen.clearRect(0, 0, 450, 450);
				
				//Place Wood Tiles On Board
				for (int i = 0; i < 9; i++)
					for (int j = 0; j < 9; j++) {
						if ((i % 2 == 0 && j % 2 == 0) || i % 2 == 1
								&& j % 2 == 1)
							offscreen.drawImage(tileA, i * 44 + 6 * i + 1, j
									* 44 + 6 * j + 1, 44, 44, this);
						else
							offscreen.drawImage(tileB, i * 44 + 6 * i + 1, j
									* 44 + 6 * j + 1, 44, 44, this);
					}
				
				//Place Endzones on Board
				if(numberOfPlayersAtStart == 4){
					offscreen.drawImage(playerTL, 1, 1, 44, 44, this);
					offscreen.drawImage(playerTR, 8 * 44 + 6 * 8 + 1, 1, 44, 44, this);
					offscreen.drawImage(playerBL, 1, 8 * 44 + 6 * 8 + 1, 44, 44, this);
					offscreen.drawImage(playerBR, 8 * 44 + 6 * 8 + 1, 8 * 44 + 6 * 8 + 1, 44, 44, this);
				}else{
					offscreen.drawImage(playerT, 1, 1, 44, 44, this);
					offscreen.drawImage(playerT, 8 * 44 + 6 * 8 + 1, 1, 44, 44, this);
					offscreen.drawImage(playerB, 1, 8 * 44 + 6 * 8 + 1, 44, 44, this);
					offscreen.drawImage(playerB, 8 * 44 + 6 * 8 + 1, 8 * 44 + 6 * 8 + 1, 44, 44, this);
				}

				//Place pawns and highlighting on board
				for (Pawn p : Quoridor.getGameState().getPawns()) {
						offscreen.drawImage(p.getPawn(),
							p.getPosition().x * 44 + 6 * p.getPosition().x + 1,
							p.getPosition().y * 44 + 6 * p.getPosition().y + 1,
							44, 44, this);
						if(p.currentTurn()){
							for(Position pos : p.getCurrentMoves()){
								offscreen.drawImage(moveTo,
										pos.x * 44 + 6 * pos.x + 1,
										pos.y * 44 + 6 * pos.y + 1,
										44, 44, this);
								offscreen.drawString(pos.toString(),
											15+pos.x * 44 + 6 * pos.x + 1,
											25+pos.y * 44 + 6 * pos.y + 1);
							}
						}
						
				}
				//Place horizontal walls on board
				for (Position p : Quoridor.getGameState().getWalls()
						.getWallsHorizontal().keySet()) {
					offscreen.drawImage(wallh, p.x * 44 + 6 * p.x + 1, p.y * 44
							+ 6 * p.y + 10, 44, 44, this);
				}
				//Place vertical walls on board 
				for (Position p : Quoridor.getGameState().getWalls()
						.getWallsVertical().keySet()) {
					offscreen.drawImage(wallv, p.x * 44 + 6 * p.x + 10, p.y
							* 44 + 6 * p.y + 1, 44, 44, this);
				}
				//draw graphics
				g.drawImage(newBuffer, 0, 0, 450, 450, board);
			}

		};
		board.setSize(new Dimension(450, 450));
		board.setLocation(10, 10);
		board.setBackground(Color.BLACK);
		add(board);
	}

	/**
	 * Fills in the player info  in each pane corresponding 
	 * to each player on the game board.
	 * 
	 * @param p
	 */
	public void updatePlayerInfo(ArrayList<Player> p){
		playerInfo = new ArrayList<JLabel>();
		updateNumberOfPlayers(p.size());
		int index = 0;
		for(int i = 0; i<numberOfPlayers; i++){
			playerInfo.add(new JLabel(p.get(i).getName(),JLabel.CENTER));
			infoBoard.add(playerInfo.get(index));
			playerInfo.get(index).setFont(new Font("Verdana", Font.BOLD, 14));
			playerInfo.get(index).setForeground(Color.BLACK);
			playerInfo.get(index).setOpaque(true);
			playerInfo.get(index).setBackground(new Color(0,0,0,0));
			playerInfo.get(index).setBounds(25,(105*i),200,75);
			index++;
	   		
			playerInfo.add(new JLabel("Current Location: " + p.get(i).getLocation(),JLabel.CENTER));
			infoBoard.add(playerInfo.get(index));
			playerInfo.get(index).setFont(new Font("Verdana", Font.BOLD, 12));
			playerInfo.get(index).setForeground(Color.BLACK);
			playerInfo.get(index).setOpaque(true);
			playerInfo.get(index).setBackground(new Color(0,0,0,0));
			playerInfo.get(index).setBounds(25,(105*i)+15,200,75);
			index++;
	    					
			String walls = "";
			for(int j=0; j<p.get(i).getRemainingWalls(); j++)
				walls += "|";
	    		
			playerInfo.add(new JLabel("Remaining Walls: " + walls,JLabel.CENTER));
			infoBoard.add(playerInfo.get(index));
			playerInfo.get(index).setFont(new Font("Verdana", Font.BOLD, 12));
			playerInfo.get(index).setForeground(Color.BLACK);
			playerInfo.get(index).setOpaque(true);
			playerInfo.get(index).setBackground(new Color(0,0,0,0));
			playerInfo.get(index).setBounds(25,(105*i)+30,200,75);
			index++;
	    					
			playerInfo.add(new JLabel("Move Count: " + p.get(i).getMoveCount(), JLabel.CENTER));
			infoBoard.add(playerInfo.get(index));
			playerInfo.get(index).setFont(new Font("Verdana", Font.BOLD, 12));
			playerInfo.get(index).setForeground(Color.BLACK);
			playerInfo.get(index).setOpaque(true);
			playerInfo.get(index).setBackground(new Color(0,0,0,0));
			playerInfo.get(index).setBounds(25,(105*i)+45,200,75);
			index++;
			
		}
	    	
	    	
	}

	/**
	 * Sets up the pane that contains the current position of each player
	 * how many walls they have left, and how many moves they have made
	 */
	public void setInfoPanel() {
		infoBoard = new JPanel();
		infoBoard.setLayout(null);
		infoBoard.setBorder(BorderFactory.createRaisedBevelBorder());
        infoBoard.setBackground(new Color(0,0,0,65));
        infoBoard.setBounds(475,10,245,450);
        
      	//Adds Player JPanel's to infoPanel
      	//offset to determine Y axis placement 
        
      	int offset = 20;
      	updatePlayerInfo(Quoridor.getGameState().getPlayer());
      	JLabel [] playerInfoBoxes = new JLabel[numberOfPlayers];
      	for(int i=0; i<numberOfPlayers; i++){
      		playerInfoBoxes[i] = new JLabel();
      		playerInfoBoxes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
      		playerInfoBoxes[i].setBackground(Color.BLACK);
      		playerInfoBoxes[i].setBounds(23,offset,200,90);
      		offset += 105;
      		infoBoard.add(playerInfoBoxes[i]);
      	}
		add(infoBoard);
	}

	/**
	 * Sets up the console that displays the messages that are sent between the
	 * client and the server.
	 */
	public void setConsole() {
		consolePane = new JPanel();
		consolePane.setSize(new Dimension(705, 218));
		consolePane.setLocation(10, 470);
		consolePane.setOpaque(false);
		consolePane.setLayout(null);
		console = new JTextArea();
		console.setLocation(0, 0);
		console.setSize(new Dimension(705, 218));
		console.setForeground(Color.BLACK);
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.setLocation(0, 0);
		scrollPane.setOpaque(false);
		scrollPane.setSize(new Dimension(705, 218));
		scrollPane.setBorder(BorderFactory.createRaisedBevelBorder());
		consolePane.add(scrollPane);
		add(consolePane);
	}

	/**
	 * Updates the number of players, so that the info pane
	 * displays the correct number of panes
	 * 
	 * @param n The updated number of players.
	 */
	public void updateNumberOfPlayers(int n){
		numberOfPlayers = n;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, this);
	}

	/**
	 * Updates the player info on the info board.
	 */
	public void updateInfo(){
		remove(infoBoard);
		infoBoard = null;
		playerInfo = null;
		setInfoPanel();
		revalidate();
		repaint();
	}
	
	/* (non-Javadoc)
	 * @see quoridor.gui.interfaces.GUIPanel#update()
	 */
	@Override
	public void update() {
		updateInfo();
		board.repaint();		
	}

	/* (non-Javadoc)
	 * @see quoridor.gui.interfaces.GUIPanel#writeToConsole(java.lang.String)
	 */
	@Override
	public void writeToConsole(String text) {
		console.append(' ' + text + '\n');
		console.setCaretPosition(console.getDocument().getLength());
	}

	/* (non-Javadoc)
	 * @see quoridor.gui.interfaces.GUIPanel#kill()
	 */
	@Override
	public void kill() {}
	
}
