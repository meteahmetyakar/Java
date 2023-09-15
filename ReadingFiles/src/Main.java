import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		try(myFileReader reader = new myFileReader("file.txt")){
			char[] buffer = new char[1000];
			int data;

			//here we fill the buffer till 1000 character, every while loop if is not EOF then buffer fill 1000 character
			while ((data = reader.read(buffer)) != -1) {
				String content = new String(buffer, 0, data);
				System.out.printf("---> [%d chars] %s%n", data, content);
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("-".repeat(20));

		try(BufferedReader bufferedReader = new BufferedReader(
				new FileReader("file.txt"))) {

//			String line;
//			while((line = bufferedReader.readLine()) != null )
//			{
//				System.out.println(line);
//			}

			bufferedReader.lines().forEach(System.out::println); //same as above while block

		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static class myFileReader extends FileReader
	{
		@Override
		public int read(char[] cbuf) throws IOException {
			System.out.println("called");
			return super.read(cbuf);
		}

		public myFileReader(String fileName) throws FileNotFoundException {
			super(fileName);
		}

		public myFileReader(File file) throws FileNotFoundException {
			super(file);
		}

		public myFileReader(FileDescriptor fd) {
			super(fd);
		}

		public myFileReader(String fileName, Charset charset) throws IOException {
			super(fileName, charset);
		}

		public myFileReader(File file, Charset charset) throws IOException {
			super(file, charset);
		}
	}
}