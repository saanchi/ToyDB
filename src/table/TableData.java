package table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
		FileWriter fw = new FileWriter("resources/" + tableHeader.getName() + ".dta", true);
		BufferedWriter out = new BufferedWriter(fw);
		for (Object field : data.values()) {
			out.write(Helper.intToCharArray((Integer)field));
		}
		out.close();
	}

	private Map<String, Object> loadData(int id)
	throws IOException {
		FileReader fr = new FileReader("resources/" + tableHeader.getName() + ".dta");
		BufferedReader in = new BufferedReader(fr);

		Map<String, Object> data = new HashMap<String, Object>();

		char[] buffer = new char[4];
		in.skip(tableHeader.getSizeOfData() * id);
		for (String field : tableHeader.getColumnNames()) {
			in.read(buffer, 0, 4);
			data.put(field, Helper.charArrayToInt(buffer));
		}
		in.close();

		return data;
	}

	public Map<String, Object> load(int id)
	throws IOException {
		return loadData(id);
	}

}
