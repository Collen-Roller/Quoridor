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
import javax.swing.JTextField;

import quoridor.gui.interfaces.GUIPanel;
import quoridor.main.Quoridor;

@SuppressWarnings("serial")
public class HostInputMenu extends JPanel implements GUIPanel {

	private final Image background;

	private final Image startImg;

	private final Image startSel;

	private JTextField[] fields;

	private ActionListener a1;

	private JButton startBtn;

	// Creates the input for the connections to the players/AI
	public HostInputMenu(int players) {
		background = Toolkit.getDefaultToolkit()
				.createImage("res/MenuBack.png");
		startImg = Toolkit.getDefaultToolkit().createImage("res/Start_button.png");
		startSel = Toolkit.getDefaultToolkit().createImage("res/Start_button_select.png");
		fields = new JTextField[players];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(
					(i < Quoridor.getHosts().length) ? Quoridor.getHosts()[i]
							: "");
			fields[i].setSize(200, 24);
			fields[i].setLocation((Quoridor.WIDTH - fields[i].getWidth()) / 2,
					Quoridor.HEIGHT / 3 + i * 30);
			add(fields[i]);
		}
		startBtn = new JButton(new ImageIcon(startImg));
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		startBtn.setOpaque(false);
		startBtn.setSize(new Dimension(512, 154));
		startBtn.setLocation((Quoridor.WIDTH - startBtn.getWidth()) / 2,
				Quoridor.HEIGHT / 3 * 2);
		startBtn.addActionListener(a1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int validCount = 0;
				for (JTextField t : fields)
					if (!t.getText().equals(""))
						validCount++;
				String[] newHosts = new String[validCount];
				validCount = 0;
				for (int i = 0; i < fields.length; i++)
					if (!fields[i].getText().equals(""))
						newHosts[i] = fields[validCount++].getText();
				
				Quoridor.setHosts(newHosts);
				Quoridor.getGUI().setPanel(new Loading());
			}
		});
		startBtn.setRolloverIcon(new ImageIcon(startSel));

		setLayout(null);
		add(startBtn);
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
		startBtn.removeActionListener(a1);
		a1 = null;
	}

	@Override
	public void writeToConsole(String s) {
	}

}
