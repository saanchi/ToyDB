package table;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Table
implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Map<String, ColumnType> columns = new HashMap<String, ColumnType>();
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
    
    public static Table load(String name)
    throws IOException, ClassNotFoundException {
    	FileInputStream fin = new FileInputStream("resources/" + name + ".hdr");
    	ObjectInputStream in = new ObjectInputStream(fin);
        
        return (Table)in.readObject();
    }
    
    public static void save(Table table)
    throws IOException {
    	FileOutputStream fout = new FileOutputStream("resources/" + table.getName() + ".hdr");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(table);
        out.flush();
        out.close();
    }
}
