

//package Client;

import javax.swing.*;
import java.awt.*;


public class GameBoard extends JFrame {

	private JFrame frame;
	private JPanel board;
	//private BufferedImage image;
	
	public GameBoard(){
		this.frame = new JFrame("Quoridor");
		this.board = new JPanel();
		//this.image = new BufferedImage("../Image/image1.png");
		setBackgroundAndFrame();
		setGameBoardOnFrame();
		pack();
		setVisible(true);
		
	}
	public void setBackgroundAndFrame() {
		setTitle("Quoridor");
		setPreferredSize(new Dimension(1024,768));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new JLabel(new ImageIcon("../Images/image1.png")));
		setLayout(null);
		setPreferredSize(new Dimension(1024,768));
		setPreferredSize(new Dimension(1024,768));
	}

	
	/*public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(100,100,500,500);
	}
	*/
	public void setGameBoardOnFrame(){
		board.setLayout(new GridLayout(9,9,10,10));
		for(int i = 0; i < 81; i++){
			board.add(new JPanel());
		}
		board.setBorder(BorderFactory.createRaisedBevelBorder());
		board.setPreferredSize(new Dimension(500,500));
		board.setBackground(Color.BLACK);
		board.setBounds(20,20,550,550);
		getContentPane().add(board);
	}
	
	
	public static void main(String [] args){
		new GameBoard();
	}
}

