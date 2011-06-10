package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Pool {
	ServerSocket socket;
	private Set<String> clients = new HashSet<String>();
	
	public Pool()
	throws IOException {
		socket = new ServerSocket(1234);
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
}
