import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		try(DatagramSocket socket = new DatagramSocket(5000);){

			while (true) {
				byte[] buffer = new byte[50];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);

				System.out.println("Text received is: " + new String(buffer, 0, packet.getLength()));

				String returnString = "echo: " + new String(buffer, 0, packet.getLength());
				System.out.println(returnString);

			}

		} catch (SocketException e){
			System.out.println("SocketException: " + e.getMessage());
		} catch (IOException e)
		{
			System.out.println("IOException: " + e.getMessage());
		}

	}
}