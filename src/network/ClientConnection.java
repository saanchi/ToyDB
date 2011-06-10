package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection
implements Runnable {
	String client;
	
	Socket connection;
	
	public ClientConnection(String client) {
		this.client = client;
	}
	
	public boolean testConnection() {
		String[] clientData = client.split(":");
		return testConnection(clientData[0], Integer.parseInt(clientData[1]));
	}
	
	private boolean testConnection(String clientIP, int clientPort) {
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
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
			}
		}).start();*/
	}
	
	public void setConnection(Socket connection) {
		this.connection = connection;
	}
	
	public void action(final Action action, Object obj) {
		if (action.equals(Action.CREATE_TABLE)) {
			// Send
			new Thread(new Runnable() {
				@Override
				public void run() {
					if (action.equals(Action.CREATE_TABLE)) {
						String[] clientData = client.split(":");
						Socket clientSocket = null;
						try {
							clientSocket = new Socket(clientData[0], Integer.parseInt(clientData[1]));
						}
						catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						PrintWriter out = null;
						try {
							out = new PrintWriter(clientSocket.getOutputStream(), true);
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.write(1);
					}
				}
			}).start();
		}
	}
}
