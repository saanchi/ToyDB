package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import util.Pair;

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
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	public void action(final Action action, final Object obj) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				Pair<Action, Object> dataToSend = new Pair<Action, Object>(action, obj);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
					oos.writeObject(dataToSend);
					oos.flush();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
