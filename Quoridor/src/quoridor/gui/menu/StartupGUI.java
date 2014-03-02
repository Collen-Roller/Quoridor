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

import quoridor.gui.interfaces.GUIPanel;
import quoridor.main.Quoridor;

@SuppressWarnings("serial")
public class StartupGUI extends JPanel implements GUIPanel {

	private final Image background;

	private final Image quoridorImg;

	private final Image quoridorSel;

	private final Image aboutImg;

	private final Image aboutSel;

	private ActionListener a1, a2;

	private JButton quoridorBtn, aboutBtn;

	public StartupGUI() {
		background = Toolkit.getDefaultToolkit().createImage(
				"res/TitleScreen.png");
		quoridorImg = Toolkit.getDefaultToolkit().createImage(
				"res/Quoridor_button.png");
		quoridorSel = Toolkit.getDefaultToolkit().createImage(
				"res/Quoridor_button_select.png");
		aboutImg = Toolkit.getDefaultToolkit().createImage("res/About_button.png");
		aboutSel = Toolkit.getDefaultToolkit().createImage("res/About_button_select.png");
		setLayout(null);
		quoridorBtn = new JButton(new ImageIcon(quoridorImg));
		quoridorBtn.setBorderPainted(false);
		quoridorBtn.setContentAreaFilled(false);
		quoridorBtn.setFocusPainted(false);
		quoridorBtn.setOpaque(false);
		quoridorBtn.setSize(new Dimension(512, 154));
		quoridorBtn.setLocation((Quoridor.WIDTH - quoridorBtn.getWidth()) / 2,
				Quoridor.HEIGHT / 3);
		quoridorBtn.addActionListener(a1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Quoridor.getGUI().setPanel(new NPlayerSelectMenu());
			}
		});
		quoridorBtn.setRolloverIcon(new ImageIcon(quoridorSel));

		aboutBtn = new JButton(new ImageIcon(aboutImg));
		aboutBtn.setBorderPainted(false);
		aboutBtn.setContentAreaFilled(false);
		aboutBtn.setFocusPainted(false);
		aboutBtn.setOpaque(false);
		aboutBtn.setSize(new Dimension(512, 154));
		aboutBtn.setLocation((Quoridor.WIDTH - aboutBtn.getWidth()) / 2,
				Quoridor.HEIGHT / 3 * 2);
		aboutBtn.addActionListener(a2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Quoridor.getGUI().setPanel(new AboutMenu());
			}
		});
		aboutBtn.setRolloverIcon(new ImageIcon(aboutSel));

		setLayout(null);
		add(quoridorBtn);
		add(aboutBtn);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, Quoridor.WIDTH, Quoridor.HEIGHT, this);
	}

	@Override
	public void update() {
	}

	@Override
	public void kill() {
		quoridorBtn.removeActionListener(a1);
		a1 = null;
		aboutBtn.removeActionListener(a2);
		a2 = null;
	}

	@Override
	public void writeToConsole(String s) {
	}

}
