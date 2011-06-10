package table;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		for (Object field : data.values()) {
			fout.write(Helper.intToByteArray((Integer)field), 0, 4);
		}
		fout.close();
	}

	private Map<String, Object> loadData(int id)
	throws IOException {
		FileInputStream fin = new FileInputStream("resources/" + tableHeader.getName() + ".dta");

		Map<String, Object> data = new HashMap<String, Object>();

		byte[] buffer = new byte[4];
		fin.skip(tableHeader.getSizeOfData() * id);
		for (String field : tableHeader.getColumnNames()) {
			fin.read(buffer, 0, 4);
			data.put(field, Helper.byteArrayToInt(buffer));
		}
		fin.close();

		return data;
	}

	public Map<String, Object> load(int id)
	throws IOException {
		return loadData(id);
	}

}
