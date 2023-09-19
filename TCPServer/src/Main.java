import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		// port no can be 0 - 65535
		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			while (true) {
				new Echoer(serverSocket.accept()).start();
			}

		} catch (IOException e) {
			System.out.println("Server exception " + e.getMessage());
			e.printStackTrace();
		}

	}
}