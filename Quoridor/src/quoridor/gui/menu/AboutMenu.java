package quoridor.gui.menu;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import quoridor.gui.interfaces.GUIPanel;
import quoridor.main.Quoridor;

@SuppressWarnings("serial")
public class AboutMenu extends JPanel implements GUIPanel {

	private final Image background;

	private final Image backImg;

	private final Image backSel;

	private ActionListener a1;

	private JButton backBtn;
	
	private JLabel info;
	
	private JScrollPane scroll;

	public AboutMenu() {
		background = Toolkit.getDefaultToolkit()
				.createImage("res/MenuBack.png");
		backImg = Toolkit.getDefaultToolkit().createImage("res/Back_button.png");
		backSel = Toolkit.getDefaultToolkit().createImage("res/Back_button_select.png");
		backBtn = new JButton(new ImageIcon(backImg));
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.setOpaque(false);
		backBtn.setSize(new Dimension(512, 154));
		backBtn.setLocation((Quoridor.WIDTH - backBtn.getWidth()) / 2,
				Quoridor.HEIGHT / 3 * 2);
		backBtn.addActionListener(a1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Quoridor.getGUI().setPanel(new StartupGUI());
			}
		});
		backBtn.setRolloverIcon(new ImageIcon(backSel));
		info = new JLabel(getText(), JLabel.CENTER);
		info.setVisible(true);
		setLayout(null);
		scroll = new JScrollPane(info);
		scroll.setSize(250, 170);
		scroll.setVisible(true);
		System.out.println(scroll.toString());
		scroll.setLocation((Quoridor.WIDTH - scroll.getWidth())/2, 225);

		add(scroll);
		add(backBtn);
	}
	
	
	private String getText(){
		return "<html><u>Welcome to Quoridor!</u><br><br><br>This program was " +
				"written by<br><i>4 Men and a Cripple:</i><br><br>-Benjamin Sladewski" +
				"<br>-Collen Roller<br>-John Payton Jr.<br>-Ian Madigan" +
				"<br>-Neil Kasson<br><br>Here's some space<br>where we can" +
				"<br>put more<br>words<br>in<br></html>";

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, Quoridor.WIDTH, Quoridor.HEIGHT, this);
	}

	@Override
	public void update() {
	}

	/*
	 * TODO: This might be the key to fixing that memory leak...
	 */
	@Override
	public void kill() {
		backBtn.removeActionListener(a1);
		a1 = null;
	}

	@Override
	public void writeToConsole(String s) {
	}

}

