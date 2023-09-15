import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args)
	{
		//these try-with-resources lines actually doing same thing, but they use different Input/Output operations
//		try (Scanner scanner = new Scanner(new File("fixedWidth.txt"))) //NIO2
//		try (Scanner scanner = new Scanner(Path.of("fixedWidth.txt"))) //NIO2
//		try (Scanner scanner = new Scanner(new FileReader("fixedWidth.txt"))) //IO
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("fixedWidth.txt")))) //IO
		{
									/* 13 - 29 part for file.txt*/
			//basic read operation
//			while (scanner.hasNextLine())
//				System.out.println(scanner.nextLine());

//			//$ mean is "match the end of a string" in regex so if our delimiter $ string split with EOF
//			//and we take one string which is whole text
//			scanner.useDelimiter("$");
//			scanner.tokens() //tokens returns stream with split text by delimiter
//					.forEach(System.out::println);

//			scanner.findAll("[A-Za-z]{10,}")
//					.map(MatchResult::group)
//					.distinct()
//					.sorted()
//					.forEach(System.out::println);
//
			//'.*' is using for don't take an error if there are extra characters
			var result = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
					.skip(1) //skip for header line
					.map(m -> m.group(3).trim())
					.distinct()
					.sorted()
					.toArray(String[]::new);

			System.out.println(Arrays.toString(result));

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}