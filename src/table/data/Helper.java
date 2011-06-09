package table.data;

public class Helper {
	public static char[] intToCharArray(int i) {
		char[] charArray = new char[4];
		charArray[3] = (char) (i << 24 >> 24);
		charArray[2] = (char) (i << 16 >> 24);
		charArray[1] = (char) (i << 8 >> 24);
		charArray[0] = (char) (i >> 24);
		
		return charArray;
	}
}
