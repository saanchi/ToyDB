package table;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Table
implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Map<String, ColumnType> columns = new HashMap<String, ColumnType>();
    private String fileName = "";
    private int id = 0;
    
    public Table() {
        columns.put("id", ColumnType.ID);
    }
    
    public void addColumn(String name, ColumnType column) {
        if (name.equals("id") || column.equals(ColumnType.ID)) {
            // TODO: Throw exception
        }
        
        columns.put(name, column);
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public Set<String> getColumnNames() {
        return columns.keySet();
    }
}
