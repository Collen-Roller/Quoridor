

//package Client;

import java.util.*;
import javax.swing.*;
import java.awt.*;


public class GameBoard extends JFrame {

	private JFrame frame;
	private JPanel gameboard;
    private JPanel infoboard;
    private JPanel console;
    private HashMap<String,JPanel> squares;
	
	public GameBoard(){
		this.frame = new JFrame("Quoridor");
		this.gameboard = new JPanel();
        this.infoboard = new JPanel();
        this.console = new JPanel();
        this.squares = new HashMap<String, JPanel>();
		setBackgroundAndFrame();
		setGameBoardOnFrame();
        setInfoPanelOnFrame();
        setConsoleOnFrame();
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

	public void setGameBoardOnFrame(){
		gameboard.setLayout(new GridLayout(9,9,10,10));
		
		//create actual buttons later
		
		for(int i = 97; i < 106; i++){
			String s = Character.toString((char)i);
			for(int j = 1; j < 10; j++){
				s += j;
				squares.put(s, new JPanel());
				gameboard.add(squares.get(s));
			}
		}
		System.out.println("Size of Map " + squares.size());
		gameboard.setBorder(BorderFactory.createRaisedBevelBorder());
		gameboard.setBackground(Color.BLACK);
		gameboard.setBounds(20,20,550,550);
		getContentPane().add(gameboard);
	}

    public void setInfoPanelOnFrame(){
        infoboard.setLayout(null);
        infoboard.setBorder(BorderFactory.createRaisedBevelBorder());
        infoboard.setBackground(new Color(0,0,0,65));
        infoboard.setBounds(590,20,400,550);
        
        //put play info into info panel
        
        getContentPane().add(infoboard);
    }

    public void setConsoleOnFrame(){
        console.setLayout(null);
        console.setBorder(BorderFactory.createRaisedBevelBorder());
        console.setBackground(new Color(0,0,0,65));
        console.setBounds(20,590,974,138);
        getContentPane().add(console); 
   	}
   	
	
	public static void main(String [] args){
		new GameBoard();
		
		
	}
}

