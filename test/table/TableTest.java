package table;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class TableTest
extends TestCase {
    public void testSeraliziable()
    throws IOException, ClassNotFoundException {
        Table table = new Table("test");
        
        table.addColumn("name", ColumnType.INTEGER);
        table.addColumn("test", ColumnType.CHARACTER);
        
        FileOutputStream fos = new FileOutputStream("test.log");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(table);
        out.flush();
        out.close();
        
        FileInputStream fin = new FileInputStream("test.log");
        ObjectInputStream in = new ObjectInputStream(fin);
        
        Table table2 = (Table) in.readObject();
        System.out.println(table2.getName());
    }
}
