import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		try(DatagramSocket datagramSocket = new DatagramSocket();){
			InetAddress address = InetAddress.getLocalHost();

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