package quoridor.network.server;

import java.awt.Image;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import quoridor.backend.ai.AI;
import quoridor.backend.containers.Position;
import quoridor.backend.managers.GameState;
import quoridor.backend.pieces.Pawn;
import quoridor.main.Quoridor;

/* multi-threaded server */

public class AIServer extends Thread {

    // default server port
    private static final int defaultServerPort = 4099;

    // number of client threads started
    private static int nclient = 0;

    // server thread uses this socket
    private Socket s = null;

    // The output buffer of this server.
    private PrintStream out;

    // The input buffer of this server.
    private Scanner in;

    // The AI this server provides communication for.
    private AI ai;

    // The number of walls this server has left to place.
    private int nWalls;

    // The game state as recorded by this server.
    private GameState gameState;

    // The initial positions for a 4 player game.
    private String[] positions4p = { "E1", "I5", "E9", "A5" };

    // The initial positions for a 2 player game.
    private String[] positions2p = { "E1", "E9" };

    // Name of the AI
    private String name;

    // The current turn
    private int turn;

    // server thread:
    // create a server instance that uses this socket endpoint
    public AIServer(Socket s) {
        this.s = s;
        gameState = new GameState();
        turn = 0;
    }

    // start a service dispatcher on the server port
    // and create server threads for each incoming connection
    private static void startService(int serverPort) throws Exception {
        ServerSocket svr = new ServerSocket(serverPort);
        while (true && nclient < 5) {
            Socket s = svr.accept();
            new AIServer(s).start();
        }
        svr.close();
    }

    // server thread code:
    // this is executed in a separate thread for each incoming connection
    // allowing for multiple simultaneous server threads
    public void run() {
        nclient++;
        try {
            in = new Scanner(s.getInputStream());
            out = new PrintStream(s.getOutputStream());
            name = "Cripple";
            out.println("HELLO " + name);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println("CLIENT TO " + name + ": " + line);
                String s = parse(line);
                System.out.println(name + ": " + s);
                if (s.equals("DONE"))
                    break;
                if (!s.equals("ACK") && !s.equals("Unknown Command"))
                    out.println(s);
            }
            s.close();
            nclient--;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Parses the incoming messages from the client. Checks the type of command
     * and generates the appropriate outbound message to the client.
     * 
     * @param s The message sent from the client.
     * @return The message to potentially send in reply.
     */
    private String parse(String s) {
        String[] command = s.split(" ");
        if (command[0].equals("MOVE?")) {
            return "MOVE " + ai.genMove();
        } else if (command[0].equals("QUORIDOR")) {
            if (command.length == 3)
                for (String pos : positions2p)
                    gameState.addPawn(new Pawn(pos, (Image) null));
            else
                for (String pos : positions4p)
                    gameState.addPawn(new Pawn(pos, (Image) null));
            ai = new AI(this, gameState.getPawns().get(
                    (Integer.parseInt(command[1])) - 1), "");
            nWalls = 20 / (command.length == 3 ? 2 : 4);
            System.out.println(nWalls);
            return "READY " + name;
        } else if (command[0].equals("MOVED")) {
            if (command[2].length() == 3) {
                String s1 = command[2].toUpperCase().trim();
                Position a = new Position(s1.substring(0, 2));
                Position b;
                if (s1.charAt(2) == 'H')
                    b = new Position(a.x, a.y + 1);
                else
                    b = new Position(a.x + 1, a.y);
                gameState.getWalls().add(a, b);
            } else {
                gameState.getPawns().get(turn % gameState.getPawns().size())
                        .setPosition(new Position(command[2]));
            }
            turn++;
            return "ACK";
        } else if (command[0].equals("REMOVED")) {
            gameState.getPawns().remove(
                    gameState.getPawns()
                            .get(turn % gameState.getPawns().size()));
            return "ACK";
        } else if (command[0].equals("WINNER")) {
            return "DONE";
        } else
            return "Unknown Command";
    }

    /**
     * @return The game state of this AI server.
     */
    public GameState getGameState() {
        return gameState;
    }

    // create a server dispatcher on the specified server port
    // (defaults to defaultServerPort)
    public static void main(String[] args) throws Exception {
        int nargs = args.length;
        int serverPort = defaultServerPort;
        if (nargs == 1)
            serverPort = Integer.parseInt(args[0]);
        startService(serverPort);
    }

    /**
     * @return The number of walls this AI server has left to place.
     */
    public int getNWalls() {
        return nWalls;
    }

    /**
     * Removes a potential wall from the AI server.
     */
    public void deductWall() {
        nWalls--;
    }
}
