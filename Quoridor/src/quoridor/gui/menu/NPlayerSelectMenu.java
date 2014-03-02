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
public class NPlayerSelectMenu extends JPanel implements GUIPanel {

	private final Image background;

	private final Image twoPlayerImg;

	private final Image fourPlayerImg;

	private final Image twoPlayerSel;

	private final Image fourPlayerSel;

	private ActionListener a1, a2;

	private JButton twoPlayerBtn, fourPlayerBtn;

	public NPlayerSelectMenu() {
		background = Toolkit.getDefaultToolkit()
				.createImage("res/MenuBack.png");
		twoPlayerImg = Toolkit.getDefaultToolkit().createImage(
				"res/2PlayerBtn.png");
		fourPlayerImg = Toolkit.getDefaultToolkit().createImage(
				"res/4PlayerBtn.png");
		twoPlayerSel = Toolkit.getDefaultToolkit().createImage(
				"res/2PlayerSel.png");
		fourPlayerSel = Toolkit.getDefaultToolkit().createImage(
				"res/4PlayerSel.png");
		twoPlayerBtn = new JButton(new ImageIcon(twoPlayerImg));
		twoPlayerBtn.setBorderPainted(false);
		twoPlayerBtn.setContentAreaFilled(false);
		twoPlayerBtn.setFocusPainted(false);
		twoPlayerBtn.setOpaque(false);
		twoPlayerBtn.setSize(new Dimension(512, 154));
		twoPlayerBtn.setLocation(
				(Quoridor.WIDTH - twoPlayerBtn.getWidth()) / 2,
				Quoridor.HEIGHT / 3);
		twoPlayerBtn.addActionListener(a1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Quoridor.getGUI().setPanel(new HostInputMenu(2));
			}
		});
		twoPlayerBtn.setRolloverIcon(new ImageIcon(twoPlayerSel));

		fourPlayerBtn = new JButton(new ImageIcon(fourPlayerImg));
		fourPlayerBtn.setBorderPainted(false);
		fourPlayerBtn.setContentAreaFilled(false);
		fourPlayerBtn.setFocusPainted(false);
		fourPlayerBtn.setOpaque(false);
		fourPlayerBtn.setSize(new Dimension(512, 154));
		fourPlayerBtn.setLocation(
				(Quoridor.WIDTH - fourPlayerBtn.getWidth()) / 2,
				Quoridor.HEIGHT / 3 * 2);
		fourPlayerBtn.addActionListener(a2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Quoridor.getGUI().setPanel(new HostInputMenu(4));
			}
		});
		fourPlayerBtn.setRolloverIcon(new ImageIcon(fourPlayerSel));

		setLayout(null);
		add(twoPlayerBtn);
		add(fourPlayerBtn);
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
		twoPlayerBtn.removeActionListener(a1);
		a1 = null;
		fourPlayerBtn.removeActionListener(a2);
		a2 = null;
	}

	@Override
	public void writeToConsole(String s) {
	}

}
