package demo;

import java.io.IOException;

import server.Server;
import table.ColumnType;
import table.Table;

public class DemoServer {
	public static void main(String[] args)
	throws IOException {
		Server server = new Server();
		server.addClient("150.101.16.17:1234");
	}
	
	public static Table createBasicTable() {
		Table table = new Table("user");
		table.addColumn("fName", ColumnType.STRING);
		table.addColumn("lName", ColumnType.STRING);
		table.addColumn("age", ColumnType.INTEGER);
		
		return table;
		
	}
}
