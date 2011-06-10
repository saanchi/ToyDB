package table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import table.data.Helper;

public class TableData {
	private Table tableHeader;

	public TableData(Table tableHeader) {
		this.tableHeader = tableHeader;
	}

	public void createNew(Map<String, Object> data)
	throws IOException {
		if (!validateData(data)) {
			// TODO: Throw exception
		}
		data.put("id", tableHeader.incrementId());
		saveData(data);
	}

	private boolean validateData(Map<String, Object> data) {
		Set<String> expectedColumnNames = tableHeader.getColumnNames();
		Set<String> columnNames = data.keySet();

		return expectedColumnNames.contains(columnNames);
	}

	private void saveData(Map<String, Object> data)
	throws IOException {
		FileOutputStream fout = new FileOutputStream("resources/" + tableHeader.getName() + ".dta", true);
		for (Entry<String, ColumnType> field : tableHeader.getColumns().entrySet()) {
			Object dataValue = data.get(field.getKey()); 
			if (field.getValue().equals(ColumnType.INTEGER) || field.getValue().equals(ColumnType.ID)) {
				fout.write(Helper.intToByteArray((Integer)dataValue));
			}
			else if (field.getValue().equals(ColumnType.STRING)) {
				File file = new File("resources/" + tableHeader.getName() + ".str");
				int position = (int)file.length();
				int size = ((String)dataValue).length();
				
				FileOutputStream stringFout = new FileOutputStream("resources/" + tableHeader.getName() + ".str", true);
				stringFout.write(((String)dataValue).getBytes());
				
				fout.write(Helper.intToByteArray(position));
				fout.write(Helper.intToByteArray(size));
			}
		}
		fout.close();
	}

	private Map<String, Object> loadData(int id)
	throws IOException {
		FileInputStream fin = new FileInputStream("resources/" + tableHeader.getName() + ".dta");

		Map<String, Object> data = new HashMap<String, Object>();

		byte[] buffer = new byte[4];
		fin.skip(tableHeader.getSizeOfData() * id);
		for (Entry<String, ColumnType> field : tableHeader.getColumns().entrySet()) {
			if (field.getValue().equals(ColumnType.INTEGER) || field.getValue().equals(ColumnType.ID) ) {
				fin.read(buffer, 0, 4);
				data.put(field.getKey(), Helper.byteArrayToInt(buffer));
			}
			else if (field.getValue().equals(ColumnType.STRING)) {
				fin.read(buffer, 0, 4);
				int position = Helper.byteArrayToInt(buffer);
				fin.read(buffer, 0, 4);
				int size = Helper.byteArrayToInt(buffer);
				
				FileInputStream stringFin = new FileInputStream("resources/" + tableHeader.getName() + ".str");
				stringFin.skip(position);
				byte[] string = new byte[size];
				stringFin.read(string);
				data.put(field.getKey(), new String(string));
			}
		}
		fin.close();

		return data;
	}

	public Map<String, Object> load(int id)
	throws IOException {
		return loadData(id);
	}

}
