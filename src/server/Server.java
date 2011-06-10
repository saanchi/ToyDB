package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import table.Table;

import network.Action;
import network.Pool;

public class Server {
	private Pool clientPool;
	private Map<String, Table> tables = new HashMap<String, Table>();

	public Server()
	throws IOException {
		clientPool = new Pool();
		Thread thread = new Thread(clientPool);
		thread.start();
	}
	
	public void addClient(String client) {
		clientPool.addClient(client);
	}
	
	public void createTable(Table table) {
		if (!tables.containsKey(table.getName())) {
			tables.put(table.getName(), table);
			try {
				Table.save(table);
				clientPool.action(Action.CREATE_TABLE, table);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// TODO: Throw error "table already exists"
		}
	}
	
	public static void main(String[] args)
	throws IOException {
		Server server = new Server();
	}
}
