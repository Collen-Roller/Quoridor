import java.util.*;
import java.net.*;
import java.io.*;

// multi-threaded echo server

public class Server extends Thread {

    // number of client threads started
    public static int nclient = 0;
    // default server port
    public static final int defaultServerPort = 3007;

    // server thread uses this socket
    public Socket s = null;

    // create a server instance that uses this socket endpoint
    public Server(Socket s) {
		this.s = s;
    }

    // start a service dispatcher on the server port
    // and create server threads for each incoming connection
    public static void startService(int serverPort) throws Exception {

		ServerSocket svr = new ServerSocket(serverPort);

		while(true) {
	    	Socket s = svr.accept();
	    	new Server(s).start();
		}
    }

    // this is executed in a separate thread for each incoming connection
    // allowing for multiple simultaneous server threads
    public void run() {
		nclient++;
        System.out.println("Starting echo server thread #" + nclient);
		System.setProperty("line.terminator", "\r\n");
		try {
	    	Scanner in = new Scanner(s.getInputStream());
	    	PrintStream out = new PrintStream(s.getOutputStream());
	    	out.println("Enter lines to echo.  To quit, enter a line with only '.' ");
	    	while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.equals("."))
		    		break;
				out.println(line);
	    	}
	    	s.close();
		} catch (Exception e) {
		
		}
    }
	

    // create an echo server dispatcher on the specified server port
    // (defaults to defaultServerPort)
    public static void main(String [] args) {
		int nargs = args.length;
		int serverPort = defaultServerPort;
		if (nargs == 1)
	    	serverPort = Integer.parseInt(args[0]);
      
		try {
	    	startService(serverPort);
		} catch (Exception e) {
	    	System.err.println(e);
	    	System.exit(1);
		}
    }

}