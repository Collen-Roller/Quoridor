package quoridor.main;

import quoridor.backend.managers.GameState;
import quoridor.backend.managers.StateMachine;
import quoridor.gui.main.GUI;
import quoridor.gui.menu.StartupGUI;

public class Quoridor {

    public static final int WIDTH = 728, HEIGHT = 728, BOUND = 9;

    public static final String TITLE = "Quoridor - 4 Men And A Cripple";

    private static String[] hosts;

    private static GUI mainWindow;

    private static GameState gameState;

    private static StateMachine stateMachine;

    public static void endGame() {
        gameState = null;
        stateMachine = null;
        mainWindow.setPanel(new StartupGUI());
        System.gc();
    }

    public static void startGame() {
        if (mainWindow == null)
            mainWindow = new GUI();
    }

    public static String[] getHosts() {
        return hosts;
    }
    
    public static void setHosts(String[] newHosts) {
        hosts = newHosts;
    }

    public static GUI getGUI() {
        return mainWindow;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void newGameState() {
        gameState = new GameState();
    }

    public static StateMachine getStateMachine() {
        return stateMachine;
    }

    public static void newStateMachine() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                stateMachine = new StateMachine();
                stateMachine.run();
            }

        }).start();
    }

    public static void main(String[] args) {
        hosts = args;
        startGame();
    }

}
