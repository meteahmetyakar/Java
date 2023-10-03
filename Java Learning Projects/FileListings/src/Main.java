import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {

		Path path = Path.of("");
		System.out.println("cwd = " + path.toAbsolutePath());

		/*
		try{
			//here we take a warning as "'Stream<Path>' used without 'try'-with-resources statement"
			//Streams execute with terminal operation in later (lazy),
			//methods which returns Stream they cannot both opening and closing files, so we must use try-with-resource
			Stream<Path> paths = Files.list(path)
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		*/

		//Files.list method returns content of directory (path or path.getFileName())
		//this is not recursive, so it cannot show sub contents such as src/main
		try (Stream<Path> paths = Files.list(path)) {
			paths
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-".repeat(20));

		//Files.walk method same as Files.list but this can show sub contents with maxDepth parameter
		try (Stream<Path> paths = Files.walk(path, 2)) {
			paths
					.filter(Files::isRegularFile)
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-".repeat(20));

		//we can search with Files.find in path
		try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
				(p, attr) -> attr.isRegularFile() && attr.size() > 300
		)) {
			paths
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		//absolute path = C:\Users\amete\IdeaProjects\FileListings
		path = path.resolve(".idea");
		System.out.println("=".repeat(15) + " Directory Stream " + "=".repeat(15));
		try (var dirs = Files.newDirectoryStream(path, p -> p.getFileName().toString().endsWith(".xml")))
		{
			dirs.forEach(d -> System.out.println(Main.listDir(d)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static String listDir(Path path) {
		try {
			boolean isDir = Files.isDirectory(path);
			FileTime dateField = Files.getLastModifiedTime(path);
			LocalDateTime modDT = LocalDateTime.ofInstant(
					dateField.toInstant(), ZoneId.systemDefault()
			);
			return "%tD %tT %-5s %12s %s".formatted(modDT, modDT, (isDir ? "<DIR>" : ""),
					(isDir ? "" : Files.size(path)), path);
		} catch (IOException e) {
			System.out.println("wrong " + path);
			return path.toString();
		}
	}

}