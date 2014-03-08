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

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements GUIPanel {

	private Canvas board;

	private JPanel infoBoard;

	private JPanel consolePane;
	
	private ArrayList <JLabel> playerInfo;

	private JTextArea console;

	private final Image background;

	private final Image tileA;

	private final Image tileB;
	
	private final Image wallv;

	private final Image wallh;

	private Image newBuffer;

	private Graphics offscreen;
	
	private int numberOfPlayers;

	public GameBoard(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		UIManager.put("Tree.rendererFillBackground", false);
		background = Toolkit.getDefaultToolkit().createImage("res/image1.png");
		tileA = Toolkit.getDefaultToolkit().createImage("res/lightwood.png");
		tileB = Toolkit.getDefaultToolkit().createImage("res/darkwood.png");
		wallv = Toolkit.getDefaultToolkit().createImage("res/WallV.png");
		wallh = Toolkit.getDefaultToolkit().createImage("res/WallH.png");
		setLayout(null);
		setGameBoard();
		setInfoPanel();
		setConsole();
		setBackground(Color.MAGENTA);
	}

	public void setGameBoard() {
		board = new Canvas() {

			// Determining Location: n * 44 + 6 * n + 1
			@Override
			public void paint(Graphics g) {
				offscreen.setColor(Color.BLACK);
				offscreen.clearRect(0, 0, 450, 450);
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
				for (Pawn p : Quoridor.getGameState().getPawns())
						offscreen.drawImage(p.getPawn(),
							p.getPosition().x * 44 + 6 * p.getPosition().x + 1,
							p.getPosition().y * 44 + 6 * p.getPosition().y + 1,
							44, 44, this);
				for (Position p : Quoridor.getGameState().getWalls()
						.getWallsHorizontal().keySet()) {
					offscreen.drawImage(wallh, p.x * 44 + 6 * p.x + 1, p.y * 44
							+ 6 * p.y + 10, 44, 44, this);
				}
				for (Position p : Quoridor.getGameState().getWalls()
						.getWallsVertical().keySet()) {
					offscreen.drawImage(wallv, p.x * 44 + 6 * p.x + 10, p.y
							* 44 + 6 * p.y + 1, 44, 44, this);
				}
				g.drawImage(newBuffer, 0, 0, 450, 450, board);
			}

		};
		board.setSize(new Dimension(450, 450));
		board.setLocation(10, 10);
		board.setBackground(Color.BLACK);
		add(board);
	}
	
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
	
	public void updateNumberOfPlayers(int n){
		numberOfPlayers = n;
	}

	public void paintComponent(Graphics g) {
		if (newBuffer == null || offscreen == null) {
			newBuffer = board.createImage(450, 450);
			offscreen = newBuffer.getGraphics();
		}
		g.drawImage(background, 0, 0, this);
	}

	public void updateInfo(){
		remove(infoBoard);
		infoBoard = null;
		playerInfo = null;
		setInfoPanel();
		revalidate();
		repaint();
	}
	
	@Override
	public void update() {
		updateInfo();
		board.repaint();		
	}

	@Override
	public void writeToConsole(String text) {
		console.append(' ' + text + '\n');
		console.setCaretPosition(console.getDocument().getLength());
	}

	@Override
	public void kill() {
	}

}
