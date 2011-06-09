package demo;

import java.io.IOException;

import table.ColumnType;
import table.Table;

public class Demo {
	public static void main(String args[])
	throws IOException, ClassNotFoundException {
		createSimpleDatabase();
		
		Table userTable = loadSimpleDatabase();
		System.out.println(userTable.getColumnNames());
	}

	private static void createSimpleDatabase()
	throws IOException {
		Table userTable = new Table("user");
		userTable.addColumn("firstName", ColumnType.INTEGER);
		userTable.addColumn("lastName", ColumnType.INTEGER);
		
		Table.save(userTable);
	}
	
	private static Table loadSimpleDatabase()
	throws IOException, ClassNotFoundException {
		return Table.load("user");
	}
}
