package quoridor.gui.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;

import quoridor.gui.interfaces.GUIPanel;
import quoridor.main.Quoridor;

@SuppressWarnings("serial")
public class Loading extends JPanel implements GUIPanel {

	private final Image background;

	private final Image loading_but;

	private final Image spin;

	private JLabel movement, load; 
	
	// Creates a loading screen while names are input
	public Loading(){
	    background = Toolkit.getDefaultToolkit()
		.createImage("res/MenuBack.png");
	    loading_but = Toolkit.getDefaultToolkit()
		.createImage("res/Loading.png");
	    spin = Toolkit.getDefaultToolkit()
		.createImage("res/spin.gif");

	    //new JLabels
	    movement = new JLabel(new ImageIcon(spin));	    
	    load = new JLabel(new ImageIcon(loading_but));
	    movement.setOpaque(true);
	    movement.setBackground(new Color(0,0,0,0));
	    load.setOpaque(true);
	    load.setBackground(new Color(0,0,0,0));
	    
	    
	    setLayout(null);
	    //movement.setSize(new Dimension(512, 154));
	    movement.setBounds(150,125,400,400);
	    
	    //load.setSize(new Dimension(800,800));
	    load.setBounds(115,450,512,154);
	    add(load);
	    add(movement);

	    //Figure out how to trigger all of the players being done;
	    Quoridor.newStateMachine();

	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, Quoridor.WIDTH, Quoridor.HEIGHT, this);
	}
	
	@Override
	public void update() {
	}
	@Override
	public void writeToConsole(String s) {

	}
	@Override
	public void kill() {
		removeAll();
		repaint();
		load=null;
		movement=null;
	}
}


