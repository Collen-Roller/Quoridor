package quoridor.gui.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import quoridor.gui.interfaces.GUIPanel;
import quoridor.gui.menu.StartupGUI;
import quoridor.main.Quoridor;

@SuppressWarnings("serial")
public class GUI extends JFrame {

    private GUIPanel panel;

    public GUI() {
        setTitle(Quoridor.TITLE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Quoridor.WIDTH, Quoridor.HEIGHT));
        setResizable(false);
        panel = new StartupGUI();
        setLocation(d.width / 2 - Quoridor.WIDTH / 2, d.height / 2
                - Quoridor.HEIGHT / 2);
        getContentPane().add((JPanel) panel);
        pack();
        setVisible(true);
    }

    public GUIPanel getPanel() {
        return panel;
    }

    public void setPanel(GUIPanel p) {
        // TODO: Fix memory leak in this method, memory leak has to do with
        // the garbage collecting not freeing memory from the removed panels.
        getContentPane().remove((JPanel) panel);
        panel.kill();
        panel = p;
        getContentPane().add((JPanel) panel);
        pack();
        System.gc();
    }
    
    public void update() {
        paintComponents(getGraphics());
        panel.update();
    }

}
