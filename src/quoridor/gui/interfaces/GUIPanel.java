package quoridor.gui.interfaces;

/**
 * @author Team 4 Men And A Cripple
 *
 */
public interface GUIPanel {

    /**
     * Updates and redraws the GUIPanel.
     */
    public void update();
    
    /**
     * Writes a string to the console of a GUIPanel if one exists.
     * 
     * @param s The string to write to the console.
     */
    public void writeToConsole(String s);

    /**
     * A method for properly stopping a GUIPanel.
     */
    public void kill();
    
}
