package table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Table
implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map<String, ColumnType> columns = new LinkedHashMap<String, ColumnType>();

	private String name;

	private int id = 0;

	public Table(String name) {
		this.name = name;
		columns.put("id", ColumnType.ID);
	}

	public void addColumn(String name, ColumnType column) {
		if (name.equals("id") || column.equals(ColumnType.ID)) {
			// TODO: Throw exception
		}

		columns.put(name, column);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<String> getColumnNames() {
		return columns.keySet();
	}

	public int getSizeOfData() {
		int sizeOfRow = 0;
		for (ColumnType columnType : columns.values()) {
			switch (columnType) {
			case STRING:
				sizeOfRow += 8;
				break;
			case ID:
			case INTEGER:
				sizeOfRow += 4;
				break;
			case CHARACTER:
				sizeOfRow += 1;
				break;
			}
		}
		return sizeOfRow;
	}

	public int incrementId()
	throws IOException {
		int tempId = id;
		id++;
		Table.save(this);
		return tempId;
	}

	public int getId() {
		return id;
	}
	
	public static Table load(String name)
	throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream("resources/" + name + ".hdr");
		ObjectInputStream in = new ObjectInputStream(fin);

		Table table = (Table)in.readObject();
		
		in.close();
		fin.close();
		
		return table;
	}

	public static void save(Table table)
	throws IOException {
		FileOutputStream fout = new FileOutputStream("resources/" + table.getName() + ".hdr");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		out.writeObject(table);
		out.flush();
		out.close();
		fout.close();
	}
	
	public static void truncate(Table table) {
		// Delete header
		File file = new File("resources/" + table.getName() + ".hdr");
		file.delete();
		
		// Delete data
		file = new File("resources/" + table.getName() + ".dta");
		file.delete();
		
		// Delete string
		file = new File("resources/" + table.getName() + ".str");
		file.delete();
	}

	public Map<String, ColumnType> getColumns() {
		return columns;
	}
}
