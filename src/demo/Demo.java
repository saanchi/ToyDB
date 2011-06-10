package demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import table.ColumnType;
import table.Table;
import table.TableData;

public class Demo {
	public static void main(String args[])
	throws IOException, ClassNotFoundException {
		long startTime = System.nanoTime();
		createSimpleDatabase();
		System.out.println((System.nanoTime() - startTime) / 1000000 + "ms to create database");

		//Table userTable = loadSimpleDatabase();
		//System.out.println(userTable.getId());

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			saveData("Justin", "Fletcher");
		}
		System.out.println((System.nanoTime() - startTime) / 1000000 + "ms to insert 10000 rows into database");
		
		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			loadData(i);
		}
		System.out.println((System.nanoTime() - startTime) / 1000000 + "ms to load 10000 rows from database");
		
		startTime = System.nanoTime();
		truncateTable();
		System.out.println((System.nanoTime() - startTime) / 1000000 + "ms to truncate database");
	}

	private static void createSimpleDatabase()
	throws IOException {
		Table userTable = new Table("user");
		userTable.addColumn("firstName", ColumnType.STRING);
		userTable.addColumn("lastName", ColumnType.STRING);

		Table.save(userTable);
	}

	private static Table loadSimpleDatabase()
	throws IOException, ClassNotFoundException {
		return Table.load("user");
	}

	private static void saveData(String fName, String lName)
	throws IOException, ClassNotFoundException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", fName);
		data.put("lastName", lName);

		Table userTable = loadSimpleDatabase();
		TableData row = new TableData(userTable);
		row.createNew(data);
	}

	private static Map<String, Object> loadData(int id)
	throws IOException, ClassNotFoundException {
		Table userTable = loadSimpleDatabase();
		TableData row = new TableData(userTable);
		return row.load(id);
	}
	
	public static void truncateTable()
	throws IOException, ClassNotFoundException {
		Table userTable = loadSimpleDatabase();
		Table.truncate(userTable);
	}
}
