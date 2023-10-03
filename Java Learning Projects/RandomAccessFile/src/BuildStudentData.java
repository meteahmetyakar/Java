import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.RandomAccess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {

	/***
	 * if the separeteIndex is false this function save records and file-pointers in the same file
	 * <p>
	 * 	Structure of .dat File (if separeteIndex false)
	 * <p>
	 * 	(int) length of records --> 4 bytes
	 * 	(long) RecordId, (long) Record Position --> 8 + 8 = 16 bytes
	 * 	.
	 * 	.	length of records times record
	 * 	.
	 * 	File Pointer <-- 4 + (1000 * 16)
	 * 	Record 0 with variable bytes
	 * 	Record 1 with variable bytes
	 * 	Record 2 with variable bytes
	 * 	.
	 * 	.
	 * 	.
	 * 	Record 999 with variable bytes
	 *
	 * 	if the separeteIndex true this function save records and file-pointers in different files
	 *
	 */

	public static void build(String datFileName, boolean separateIndex) {
		Path studentJson = Path.of("students.json");
		String dataFile = datFileName + ".dat";
		Map<Long, Long> indexedIds = new LinkedHashMap<>();

		try {
			Files.deleteIfExists(Path.of(dataFile));
			String data = Files.readString(studentJson);
			data = data.replaceFirst("^(\\[)", "")
					.replaceFirst("(\\])$", "");

			var records = data.split(System.lineSeparator());
			System.out.println("# of records = " + records.length);

			long startingPos = separateIndex ? 0 : 4 + (16L * records.length);

			Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");
			try (RandomAccessFile ra = new RandomAccessFile(dataFile, "rw")) {
				ra.seek(startingPos);
				for(String record : records)
				{
					Matcher m = idPattern.matcher(record);
					if(m.find())
					{
						long id = Long.parseLong(m.group(1));
						indexedIds.put(id, ra.getFilePointer());
						ra.writeUTF(record);
					}
				}
				writeIndex(
						(separateIndex) ? new RandomAccessFile(datFileName + ".idx", "rw") : ra,
						indexedIds);
			}

		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	//this method writes Indexes of records to given RandomAccessFile
	private static void writeIndex(RandomAccessFile ra, Map<Long,Long> indexMap)
	{
		try {
			ra.seek(0);
			ra.writeInt(indexMap.size());
			for(var studentIdx : indexMap.entrySet()) {
				ra.writeLong(studentIdx.getKey());
				ra.writeLong(studentIdx.getValue());

			}
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

}
