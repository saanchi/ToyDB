package network;

public enum Action {
	CREATE_TABLE (1),
	INSERT_INTO_TABLE (2),
	QUERY_TABLE (3);
	
	final int id;
	
	private Action(int id) {
		this.id = id;
	}
}
