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
		//createSimpleDatabase();

		//Table userTable = loadSimpleDatabase();
		// System.out.println(userTable.getColumnNames());

		//saveData(1, 2);
		//saveData(200, 200);
		//saveData(11010, 1231);
		
		loadData(2);
		
		//truncateTable();
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

	private static void saveData(int fName, int lName)
	throws IOException, ClassNotFoundException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", fName);
		data.put("lastName", lName);

		Table userTable = loadSimpleDatabase();
		TableData row = new TableData(userTable);
		row.createNew(data);
	}

	private static void loadData(int id)
	throws IOException, ClassNotFoundException {
		Table userTable = loadSimpleDatabase();
		TableData row = new TableData(userTable);
		Map<String, Object> data = row.load(id);
		System.out.println(data);
	}
	
	public static void truncateTable()
	throws IOException, ClassNotFoundException {
		Table userTable = loadSimpleDatabase();
		Table.truncate(userTable);
	}
}
