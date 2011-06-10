package table.data;

public class Helper {
	public static byte[] intToByteArray(int i) {
		byte[] byteArray = new byte[4];
		byteArray[3] = (byte)(i << 24 >> 24);
		byteArray[2] = (byte)(i << 16 >> 24);
		byteArray[1] = (byte)(i << 8 >> 24);
		byteArray[0] = (byte)(i >> 24);

		return byteArray;
	}

	public static int unsignedByte(byte thisByte) {
		return thisByte & 0xFF;
	}
	
	public static int byteArrayToInt(byte[] byteArray) {
		return (((unsignedByte(byteArray[0]) << 8) + unsignedByte(byteArray[1]) << 8) + unsignedByte(byteArray[2]) << 8) + unsignedByte(byteArray[3]);
	}
}
