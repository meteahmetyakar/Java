import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 5000)) {

			socket.setSoTimeout(5000); //throw a timeout if don't take any response from server

			BufferedReader echoes = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

			Scanner scanner = new Scanner(System.in);
			String echoString;
			String response;

			do {
				System.out.println("Enter string to be echoed: ");
				echoString = scanner.nextLine();

				stringToEcho.println(echoString);

				if (!echoString.equals("exit")) {
					response = echoes.readLine();
					System.out.println(response);
				}
			} while (!echoString.equals("exit"));

		} catch (SocketTimeoutException e)
		{
			System.out.println("The socket timed out");
		} catch (IOException e) {
			System.out.println("Client Error: " + e.getMessage());
			e.printStackTrace();
		}

	}
}