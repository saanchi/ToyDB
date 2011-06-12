package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Pool
extends Thread {
	ServerSocket listeningSocket;
	private Set<ClientConnection> clients = new HashSet<ClientConnection>();
	
	public Pool()
	throws IOException {
		listeningSocket = new ServerSocket(1234);
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Socket accepted = listeningSocket.accept();
				System.out.println(accepted.getRemoteSocketAddress().toString());
				
				ClientConnection newClient = new ClientConnection(accepted.getRemoteSocketAddress().toString());
				newClient.setConnection(accepted);
				
				clients.add(newClient);
				System.out.println(clients.size() + " clients connected");
				
				new Thread(newClient).start();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void testAll() {
		Set<ClientConnection> newClients = new HashSet<ClientConnection>();
		
		for (ClientConnection client : clients) {
			if (client.testConnection()) {
				newClients.add(client);
			}
		}
		
		clients = newClients;
	}
	
	public void addClient(String client) {
		ClientConnection newClient = new ClientConnection(client);
		if (newClient.testConnection()) {
			clients.add(newClient);
		}
	}
	
	public void action(Action action, Object obj)
	throws NumberFormatException, UnknownHostException, IOException {
		for (ClientConnection client : clients) {
			client.action(action, obj);
		}
	}
}
