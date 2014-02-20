import java.io.*;
import java.net.*;


//Basic client for an echo server
class Client {
	
	public static void main(String[] args) throws IOException {
        //Checks to see if the user put in the command line the host and the port
		//If not the client throws an exception 
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
 		
		//Stores the host name taken from the commandl ine
        String hostName = args[0];
		//Stores the port number from the command line
        int portNumber = Integer.parseInt(args[1]);
		//Initializes the string for user input
		String sentence = "";
 
		//This block makses sure that a connection can be made to the server
		//And sets up a socket, output stream, and an input stream from the server and client
        try {
			
            Socket echoSocket = new Socket(hostName, portNumber);
			
            DataOutputStream outToServer = new DataOutputStream(echoSocket.getOutputStream());
            
			BufferedReader inFromServer = new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            
			BufferedReader inFromUser = new BufferedReader(
                    new InputStreamReader(System.in));
			
			//Grabs the promt from the server, the client number and the instructions
        	System.out.println(inFromServer.readLine());
			
			//Grabs the user input to send to the server
			sentence = inFromUser.readLine();
			
			//If the user inputs a '.' then the socket closes.
            while (sentence.charAt(0) != '.') {
				
				//Sends sentence to the server
                outToServer.writeBytes(sentence + '\n');
				
				//Prints out the response from the server
                System.out.println("echo: " + inFromServer.readLine());
				
				//Gets input from the user
				sentence = inFromUser.readLine();
            }
		//Error for host name
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}
