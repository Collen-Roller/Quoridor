package quoridor.gui.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import quoridor.gui.interfaces.GUIPanel;
import quoridor.gui.menu.StartupGUI;
import quoridor.main.Quoridor;

/**
 * @author Team 4 Men And A Cripple
 *
 * This method represents the frame the game of Quoridor runs in.
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {

    private GUIPanel panel;

    /**
     * Constructs a new GUI.
     */
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

    /**
     * @return The panel currently displayed in the GUI.
     */
    public GUIPanel getPanel() {
        return panel;
    }

    /**
     * @param p The panel to replace the current panel with.
     */
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
    
    /**
     * Repaints the GUI and updates the panel currently displayed.
     */
    public void update() {
        paintComponents(getGraphics());
        panel.update();
    }

}
