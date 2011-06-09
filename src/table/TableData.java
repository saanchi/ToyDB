package table;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    
    public void load(int id) {
    }
}
