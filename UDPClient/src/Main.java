import java.io.IOException;
import java.net.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		try {
			InetAddress address = InetAddress.getLocalHost();
			DatagramSocket datagramSocket = new DatagramSocket();

			Scanner scanner = new Scanner(System.in);
			String echoString;

			do{

				System.out.println("Enter string to be echoed: ");
				echoString = scanner.nextLine();

				byte[] buffer = echoString.getBytes();

				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
				datagramSocket.send(packet);


			} while(!echoString.equals("exit"));

		} catch (SocketTimeoutException e)
		{
			System.out.println("The socket timed out");
		} catch (IOException e)
		{
			System.out.println("Client error: " + e.getMessage());
		}

	}
}