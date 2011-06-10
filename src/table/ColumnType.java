package table;

public enum ColumnType {
	ID(0), INTEGER(1), CHARACTER(2), STRING(3);

	private final int id;

	ColumnType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
