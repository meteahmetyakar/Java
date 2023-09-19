import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		try {

//			URI uri = new URI("db://username:password@myserver.com:5000/catologue/phones?os=android#samsung");
//			URI uri2 = new URI("http://username:password@myserver.com:5000/catologue/phones?os=android#samsung");
//
//			System.out.println("Scheme = " + uri2.getScheme());
//			System.out.println("Scheme-specific part = " + uri2.getSchemeSpecificPart());
//			System.out.println("Authority = " + uri2.getAuthority());
//			System.out.println("User info = " + uri2.getUserInfo());
//			System.out.println("Host = " + uri2.getHost());
//			System.out.println("Port = " + uri2.getPort());
//			System.out.println("Path = " + uri2.getPath());
//			System.out.println("Query = " + uri2.getQuery());
//			System.out.println("Fragment = " + uri2.getFragment());
//
//			//this throw an error because our uri is not a valid url
//			//URL url = uri.toURL();
//
//			//valid url
//			URL url2 = uri2.toURL();
//			System.out.println(url2);
//
//			//URI can be relative but a URL must be absolute, so this will throw an error
////			URI uri3 = new URI("/catologue/phones?os=android#samsung");
//			//so, how can we use like this thing. It is possible with resolve method.
//
//
//			URI baseUri = new URI("http://username:password@myserver.com:5000");
//			URI uri4 = new URI("/catologue/phones?os=android#samsung");
//			URI uri5 = new URI("/catologue/tvs?manufacturer=samsung");
//			URI uri6 = new URI("/stores/location?zip=12345");
//
//			URI resolvedUri1 = baseUri.resolve(uri4);
//			URI resolvedUri2 = baseUri.resolve(uri5);
//			URI resolvedUri3 = baseUri.resolve(uri6);
//
//
//			URL url3 = resolvedUri1.toURL();
//			URL url4 = resolvedUri2.toURL();
//			URL url5 = resolvedUri3.toURL();
//
//			System.out.println(url3);
//			System.out.println(url4);
//			System.out.println(url5);
//
//			//we can take relative part from a URI
//			URI relativeURI = baseUri.relativize(resolvedUri2);
//			System.out.println(relativeURI);
//
//
//
//
//
			URL url = new URL("http://example.org");

			/* Read from website with url.openStream() */

			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(url.openStream() /* it will pull the html */));

			String line = "";
			while (line != null) {
				line = inputStream.readLine();
				System.out.println(line);
			}
			inputStream.close();

			/* Read end */

			//we can read with URLConnection class too
			/* Read from website with URLConnection */
			URLConnection urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.connect();

			BufferedReader inputStream2 = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()));

			String line2 = "";
			while (line2 != null) {
				line2 = inputStream2.readLine();
				System.out.println(line2);
			}
			inputStream2.close();

			/* Read end */

			/* Read from website with HttpURLConnection */
			URL url2  = new URL("http://example.org");
			HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Chrome");
			connection.setReadTimeout(30000);

			int responseCode = connection.getResponseCode();
			System.out.println("Response code: " + responseCode);

			if(responseCode != 200) {
				System.out.println("Error reading web page");
				return;
			}

			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			String line3;

			while ((line3 = inputReader.readLine()) != null)
				System.out.println(line3);

			/* Read end */

			/* Read from website with HttpURLConnection, another example */
			URL url3  = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags?cats");
			HttpURLConnection connection2 = (HttpURLConnection) url3.openConnection();
			connection2.setRequestMethod("GET");
			connection2.setRequestProperty("User-Agent", "Chrome");
			connection2.setReadTimeout(30000);

			int responseCode2 = connection2.getResponseCode();
			System.out.println("Response code: " + responseCode2);

			if(responseCode2 != 200) {
				System.out.println("Error reading web page");
				return;
			}

			BufferedReader inputReader2 = new BufferedReader(
					new InputStreamReader(connection2.getInputStream()));

			String line4;

			while ((line4 = inputReader2.readLine()) != null)
				System.out.println(line4);

		}
		catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}




	}
}