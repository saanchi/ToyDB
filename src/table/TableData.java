package table;

import java.util.Map;
import java.util.Set;

public class TableData {
    private Table tableHeader;

    public TableData(Table tableHeader) {
        this.tableHeader = tableHeader;
    }

    public void createNew(Map<String, Object> data) {
        Set<String> expectedColumnNames = tableHeader.getColumnNames();
        Set<String> columnNames = data.keySet();

        boolean matches = expectedColumnNames.contains(columnNames);
    }
}
