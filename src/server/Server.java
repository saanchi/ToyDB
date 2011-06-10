package server;

import java.io.IOException;

import network.Pool;

public class Server {
	private Pool clientPool;

	public Server()
	throws IOException {
		clientPool = new Pool();
	}
	
	public static void main(String[] args)
	throws IOException {
		Server server = new Server();
	}
}
