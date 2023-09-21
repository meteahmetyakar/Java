import java.io.*;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;

public class Main {
	public static void main(String[] args)
	{
		//This part rename the oldFile with the name of newFile
		//File class is a Java.io class

//		File oldFile = new File("students.json");
//		File newFile = new File("students-activity.json");
//
//		if(oldFile.exists()) {
//			oldFile.renameTo(newFile);
//			System.out.println("File renamed successfully");
//		} else {
//			System.out.println("File does not exist!");
//		}



		//Here we do the same process as above with the Path and Files classes in the Java.NIO library.

//		Path newPath = Path.of("files/student-activity.json");
//		Path oldPath = Path.of("students.json");
//
//		try {
//			//here we must create a directory because Files.move() method cannot create a directory,
//			//and we don't have a directory named files
//			Files.createDirectories(newPath.subpath(0, newPath.getNameCount()-1));
//
//			Files.move(oldPath,newPath);
//			System.out.println(oldPath + " moved (renamed to) --> " + newPath);
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}


//		Path fileDir = Path.of("files");
//		Path resourceDir = Path.of("resources");
//
//		try {
//			Files.move(fileDir, resourceDir, StandardCopyOption.REPLACE_EXISTING);
//			System.out.println("Directory renamed");
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}

		//deep copy and delete operation with own recursion methods
//		Path fileDir = Path.of("files");
//		Path resourceDir = Path.of("resources");
//
//		try {
//			recurseDelete(resourceDir);
//			recurseCopy(fileDir, resourceDir);
//			System.out.println("Directory renamed");
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}

		//transfer from api to console
		String urlString = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";
		URI uri = URI.create(urlString);
		try (var urlInputStream = uri.toURL().openStream()) {
			urlInputStream.transferTo(System.out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		//transfer from api to txt file
		Path jsonPath = Path.of("USPopulationByState.txt");
		try (var reader = new InputStreamReader(uri.toURL().openStream());
			 var writer = Files.newBufferedWriter(jsonPath)) {
			reader.transferTo(writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try (var reader = new InputStreamReader(uri.toURL().openStream());
			 PrintWriter writer = new PrintWriter("USPopulationByState.csv")) {

			reader.transferTo(new Writer() {
				@Override
				public void write(char[] cbuf, int off, int len) throws IOException {
					String jsonString = new String(cbuf, off, len).trim();
					jsonString = jsonString.replace('[', ' ').trim();
					jsonString = jsonString.replaceAll("\\]", "");
					writer.write(jsonString);
				}

				@Override
				public void flush() throws IOException {
					writer.flush();
				}

				@Override
				public void close() throws IOException {
					writer.close();
				}
			});

		} catch (IOException e) {
			throw new RuntimeException(e);
		}



	}

	public static void recurseCopy(Path source, Path target) throws IOException {

		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		if(Files.isDirectory(source))
		{
			try(var children = Files.list(source))
			{
				children.toList().forEach(
					p -> {
						try {
							Main.recurseCopy(
								p, target.resolve(p.getFileName())
							);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
				);
			} catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}

	}

	public static void recurseDelete(Path target) throws IOException {

		if (Files.isDirectory(target)) {
			try (var children = Files.list(target)) {
				children.toList().forEach(
						p -> {
							try {
								Main.recurseDelete(p);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}

						});
			}
		}
		Files.delete(target);
	}
}