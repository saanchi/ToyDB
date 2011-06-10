package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import table.Table;

import network.Pool;

public class Server {
	private Pool clientPool;
	private Map<String, Table> tables = new HashMap<String, Table>();

	public Server()
	throws IOException {
		clientPool = new Pool();
	}
	
	public void createTable(Table table) {
		if (!tables.containsKey(table.getName())) {
			tables.put(table.getName(), table);
			try {
				Table.save(table);
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
