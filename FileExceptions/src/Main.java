import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		System.out.println("Current working directory (cwd) = " +
				new File("").getAbsolutePath());

		//testFile(filename);
		//testFile2(null);

		String filename = "files/testing.csv"; 		// currentPath/files/testing.csv
		//String filename = "/files/testing.csv"; 	// root/files/testing.csv

		//check if file is exist and do operation by result
		//this approach call as LBYL
		File file = new File(filename); //if parent parameter is '.' this mean is start from currentDirectory, '/' this mean is start from root
		System.out.println(file.getAbsolutePath());
		if(!file.exists())
		{
			System.out.println("file does not exist");
			return;
		}
		System.out.println("I am good");

	}

	private static void testFile(String filename) {
		//if an error throws like filename does not exist, catch part handle it
		//this approach call as EAFP

		Path path = Paths.get(filename);

		FileReader reader = null; //this must define here because when closing in finally block it must be reachable
		try {
			//List<String> lines = Files.readAllLines(path); //we can do that with FileReader
			reader = new FileReader(filename);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if(reader != null) { //we try close the reader but we can do this with try-with-resource, this is more readable
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println("Maybe I'd log something either way...");
		}
		System.out.println("File exists and able to use as a resource");
	}

	// this function same as testFile function just difference is this use try-with-resource
	private static void testFile2(String filename)
	{
		try (FileReader reader = new FileReader(filename)) {
		} catch (FileNotFoundException e) {
			System.out.println("file '" + filename + "' does not exist");
			throw new RuntimeException(e);
		} catch (NullPointerException | IllegalArgumentException badData )
		{
			System.out.println("User has added bad data " + badData.getMessage());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			System.out.println("maybe I'd log something either way");
		}
		System.out.println("everything all right");
	}

}