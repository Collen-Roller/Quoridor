

//package Client;

import javax.swing.*;
import java.awt.*;


public class GameBoard extends JFrame {

	private JFrame frame;
	
	public GameBoard(){
		this.frame = new JFrame("Quoridor");
		setBackgroundAndFrame();
		
	}
	public void setBackgroundAndFrame() {
		setTitle("Quoridor");
		setSize(1024,768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("../Images/image1.jpg")));
		setLayout(new FlowLayout());
		setSize(1024,768);
		setSize(1024,768);
		setVisible(true);
	}
	
	
	public static void main(String [] args){
		new GameBoard();
	}
}

