package util;

import java.io.Serializable;

public class Pair<T, U>
implements Serializable {
	private static final long serialVersionUID = 1L;

	private T item1;
	private U item2;

	public Pair() {
	}

	public Pair(T item1, U item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	public T getItem1() {
		return item1;
	}

	public U getItem2() {
		return item2;
	}

	@Override
	public String toString() {
		return "(" + item1 + "," + item2 + ")";
	}
}
