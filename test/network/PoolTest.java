package network;

import java.io.IOException;

import junit.framework.TestCase;

public class PoolTest
extends TestCase {
	public void testConnection()
	throws IOException {
		Pool pool = new Pool();
		
		pool.addClient("127.0.0.1:1234");
		
		pool.testAll();
	}
}
