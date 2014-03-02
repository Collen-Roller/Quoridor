package quoridor.gui.board;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;

import quoridor.backend.containers.Position;
import quoridor.backend.pieces.Pawn;
import quoridor.gui.interfaces.GUIPanel;
import quoridor.main.Quoridor;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements GUIPanel {

	private Canvas board;

	private JPanel infoBoard;

	private JPanel consolePane;

	private JTextArea console;

	private JTree infoTree;

	private final Image background;

	private final Image tileA;

	private final Image tileB;

	private final Image pawn;

	private final Image wallv;

	private final Image wallh;

	private Image newBuffer;

	private Graphics offscreen;

	public GameBoard() {
		UIManager.put("Tree.rendererFillBackground", false);
		background = Toolkit.getDefaultToolkit().createImage("res/image1.png");
		tileA = Toolkit.getDefaultToolkit().createImage("res/lightwood.png");
		tileB = Toolkit.getDefaultToolkit().createImage("res/darkwood.png");
		pawn = Toolkit.getDefaultToolkit().createImage("res/Pawn.png");
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
					offscreen.drawImage(pawn,
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

	public void setInfoPanel() {
		infoBoard = new JPanel();
		infoBoard.setSize(new Dimension(238, 450));
		infoBoard.setLocation(470, 10);
		infoBoard.setOpaque(false);
		infoBoard.setLayout(null);
		infoTree = new JTree();
		infoTree.setSize(new Dimension(238, 450));
		infoTree.setLocation(0, 0);
		infoTree.setForeground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(infoTree);
		scrollPane.setLocation(0, 0);
		scrollPane.setOpaque(false);
		scrollPane.setSize(new Dimension(238, 450));
		scrollPane.setBorder(BorderFactory.createRaisedBevelBorder());
		updateTree();
		infoBoard.add(scrollPane);
		add(infoBoard);
	}

	public void setConsole() {
		consolePane = new JPanel();
		consolePane.setSize(new Dimension(698, 218));
		consolePane.setLocation(10, 470);
		consolePane.setOpaque(false);
		consolePane.setLayout(null);
		console = new JTextArea();
		console.setLocation(0, 0);
		console.setSize(new Dimension(698, 218));
		console.setForeground(Color.BLACK);
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.setLocation(0, 0);
		scrollPane.setOpaque(false);
		scrollPane.setSize(new Dimension(698, 218));
		scrollPane.setBorder(BorderFactory.createRaisedBevelBorder());
		consolePane.add(scrollPane);
		add(consolePane);
	}

	public void paintComponent(Graphics g) {
		if (newBuffer == null || offscreen == null) {
			newBuffer = board.createImage(450, 450);
			offscreen = newBuffer.getGraphics();
		}
		g.drawImage(background, 0, 0, this);
	}

	@Override
	public void update() {
		board.repaint();
		updateTree();
	}

	private void updateTree() {
		
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
