import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		useFile("testfile.txt");
		usePath("pathfile.txt");

	}

	//this method use File class
	private static void useFile(String fileName)
	{

		File file = new File(fileName);

		boolean fileExists = file.exists();

		System.out.printf("File '%s' %s%n", fileName,
				fileExists ? "exists." : "does not exist.");

		if(fileExists)
		{
			System.out.println("Deleting File: " + fileName);
			fileExists = !file.delete();
		}

		if(!fileExists)
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Something went wrong");
				throw new RuntimeException(e);
			}
			System.out.println("Created File: " + fileName);
			if(file.canWrite())
			{
				System.out.println("Would write to file here");
			}
		}

	}

	//this method use Files and Path classes
	private static void usePath(String fileName)
	{
		Path path = Path.of(fileName);

		boolean fileExists = Files.exists(path);

		System.out.printf("File '%s' %s%n", fileName,
				fileExists ? "exists." : "does not exist.");

		if(fileExists)
		{
			System.out.println("Deleting File: " + fileName);
			try {
				Files.delete(path);
				fileExists = false;
			} catch (IOException e) {
				//throw new RuntimeException(e); we can use that but
				e.printStackTrace(); // This is more effective because the printStackTrace function tells us exactly where the exception occurred.
			}
		}

		if(!fileExists)
		{
			try {
				Files.createFile(path);
				System.out.println("Created File: " + fileName);

				if(Files.isWritable(path))
				{
					Files.writeString(path, """
						Here is some data,
						For my file,
						just to prove,
						Using the Files class and path are better
						""");
				}

				System.out.println("And I can read too");
				System.out.println("------------------------");
				Files.readAllLines(path).forEach(System.out::println);

			} catch (IOException e) {
				System.out.println("Something went wrong");
				throw new RuntimeException(e);
			}
		}

	}
}