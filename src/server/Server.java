package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import table.ColumnType;
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
	throws IOException, InterruptedException {
		Server server = new Server();
		
		Thread.sleep(10000);
		
		server.createTable(createBasicTable());
	}
	
	public static Table createBasicTable() {
		Table table = new Table("user");
		table.addColumn("fName", ColumnType.STRING);
		table.addColumn("lName", ColumnType.STRING);
		table.addColumn("age", ColumnType.INTEGER);
		
		return table;
		
	}
}
