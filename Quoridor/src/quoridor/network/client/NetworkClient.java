package quoridor.network.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import quoridor.main.Quoridor;

public class NetworkClient {

	private Socket clientSocket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;

	public NetworkClient(String host) throws NumberFormatException,
			UnknownHostException, IOException {
		if (host.contains(":")) {
			String[] hostname = host.split(":");
			clientSocket = new Socket(hostname[0],
					Integer.parseInt(hostname[1]));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			// TODO: add a greeting
		} else
			throw new UnknownHostException();
	}

	public void sendString(String s) {
		try {
			Quoridor.getGUI().getPanel().writeToConsole("Client: " + s);
			outToServer.writeBytes(s + '\n');
		} catch (IOException e) {
			System.err.println("Error sending message");
		}
	}

	public String getString() {
		try {
			String s = inFromServer.readLine();
			Quoridor.getGUI().getPanel().writeToConsole("Server: " + s);
			return s;
		} catch (IOException e) {
			Quoridor.getGUI().getPanel()
					.writeToConsole("Error recieving message");
		}
		return "ERROR";
	}

	public void close() {
		try {
			outToServer.close();
			inFromServer.close();
		} catch (IOException e) {
			System.err.println("Some error message..."); // TODO: Proper error messages
		}
	}

}
