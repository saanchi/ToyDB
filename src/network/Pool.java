package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Pool
extends Thread {
	ServerSocket socket;
	private Set<Socket> connectedClients = new HashSet<Socket>();
	private Set<String> clients = new HashSet<String>();
	
	public Pool()
	throws IOException {
		socket = new ServerSocket(1234);
	}
	
	public Set<String> getClients() {
		return clients;
	}
	
	public void run() {
		while (true) {
			try {
				Socket accepted = socket.accept();
				connectedClients.add(accepted);
				System.out.println(connectedClients.size() + " clients connected");
				BufferedReader in = new BufferedReader(new InputStreamReader(accepted.getInputStream()));
				
				// TODO: move this into a thread
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void testAll() {
		Set<String> newClients = new HashSet<String>();
		
		for (String client : clients) {
			String[] clientData = client.split(":");
			if (test(clientData[0], Integer.parseInt(clientData[1]))) {
				newClients.add(client);
			}
		}
		
		clients = newClients;
	}
	
	private boolean test(String clientIP, int clientPort) {
		Socket testSocket = null;
		
		boolean canConnect = true;
		try {
			testSocket = new Socket(clientIP, clientPort);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
			canConnect = false;
		}
		catch (IOException e) {
			e.printStackTrace();
			canConnect = false;
		}
		finally {
			try {
				testSocket.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (NullPointerException e) {
			}
		}

		return canConnect;
	}
	
	public void addClient(String client) {
		String[] clientData = client.split(":");
		if (test(clientData[0], Integer.parseInt(clientData[1]))) {
			clients.add(client);
		}
	}
	
	public void action(Action action, Object object)
	throws NumberFormatException, UnknownHostException, IOException {
		for (String client : clients) {
			if (action.equals(Action.CREATE_TABLE)) {
				String[] clientData = client.split(":");
				Socket clientSocket = new Socket(clientData[0], Integer.parseInt(clientData[1]));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.write(1);
			}
		}
	}
}
