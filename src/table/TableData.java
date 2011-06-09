package table;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Set;

public class TableData {
    private Table tableHeader;

    public TableData(Table tableHeader) {
        this.tableHeader = tableHeader;
    }

    public void createNew(Map<String, Object> data) {
        if (!validateData(data)) {
        	// TODO: Throw exception
        }
        
        saveData(data);
    }
    
    private boolean validateData(Map<String, Object> data) {
        Set<String> expectedColumnNames = tableHeader.getColumnNames();
        Set<String> columnNames = data.keySet();

        return expectedColumnNames.contains(columnNames);
    }
    
    private void saveData(Map<String, Object> data) {
    	// TODO: Save to file
    }
    
    public void load(int id) {
    }
}
