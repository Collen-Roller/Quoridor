
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class IntroScreen extends JFrame{

    private boolean isOpen;
    public int players;
    private JPanel panel;
    private JButton play;
    private Dimension winSize;

    public IntroScreen(){
	Dimension ScnSize  = Toolkit.getDefaultToolkit().getScreenSize();
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocation(ScnSize.width/3, ScnSize.height/3);
	winSize = new Dimension(300, 100);
	setPreferredSize(winSize);
	panel = new JPanel();
	panel.setPreferredSize(winSize);
	pack();
	setResizable(false);
	setVisible(true);
	setTitle("4 Men and a Cripple Present");
	play = new JButton("Quoridor");
	play.setPreferredSize(new Dimension(200, 50));
	panel.add(play);
	panel.setVisible(true);
	add(panel);
	play.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	       openPlayers();  // code to execute when button is pressed
	    }
	});
    }

    private void openPlayers(){
	remove(panel);
	panel = new JPanel();
	panel.setPreferredSize(winSize);
	setTitle("How Many Players?");
	JButton two = new JButton("2 players");
	JButton four = new JButton("4 players");
	two.setPreferredSize(new Dimension(100, 50));
	four.setPreferredSize(new Dimension(100, 50));
	pack();
	panel.add(two);
	panel.add(four);
	add(panel);
	two.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		twoPlayers();  // code to execute when button is pressed
	    }
       	});
	four.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		fourPlayers();  // code to execute when button is pressed
	    }
       	});
    }

    private void twoPlayers(){
	players = 2;
	setVisible(false);
	System.out.println(players);
    }

    private void fourPlayers(){
	players = 4;
	setVisible(false);
	System.out.println(players);
    }
}


