import model.Artist;
import model.DataSource;
import model.SongArtist;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {

		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "\\data.db");Statement statement = conn.createStatement();){

			//when call execute method it takes sql query, holds it, and it executes the query at the end
			//here we close the executes at the end, in the later we'll do that manually
			//conn.setAutoCommit(false);
			statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
			statement.execute("INSERT INTO contacts (name, phone, email)" +
					"VALUES('Jane', 9677563, 'jane@gmail.com')");


			if(statement.execute("SELECT * FROM contacts"))
			{
				// we can take selected elements with this method
				// every statements can hava only one ResultSet, so if there is an opened ResultSet when getResultSet() called again,
				// first one will close, and it will create new one
				// that's why when we want working on more than one ResultSet, we must create another statement 
				ResultSet results = statement.getResultSet();
				while (results.next())
				{
					System.out.println("%-15s".formatted(results.getString("name")) +
							" | " + "%-15s".formatted(results.getString("phone")) +
							" | " + "%-15s".formatted(results.getString("email"))
					);
				}

				results.close();
			}

			statement.executeQuery(""); //this can use instead of statement.execute(), this will return a ResultSet



		} catch (SQLException e)
		{
			System.out.println("Something went wrong: " + e.getMessage());
		}


		/* examples with DataSource class which I wrote*/

		DataSource datasource = new DataSource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}

		List<Artist> artists = datasource.queryArtists(5);
		if(artists == null) {
			System.out.println("No artists!");
			return;
		}

		for(Artist artist : artists) {
			System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
		}

		List<String> albumsForArtist =
				datasource.queryAlbumsForArtist("Carole King", DataSource.ORDER_BY_ASC);

		for(String album : albumsForArtist) {
			System.out.println(album);
		}

		List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
		if(songArtists == null) {
			System.out.println("Couldn't find the artist for the song");
			return;
		}

		for(SongArtist artist : songArtists) {
			System.out.println("Artist name = " + artist.getArtistName() +
					" Album name = " + artist.getAlbumName() +
					" Track = " + artist.getTrack());
		}

		datasource.querySongsMetadata();

		int count = datasource.getCount(DataSource.TABLE_SONGS);
		System.out.println("Number of songs is: " + count);

		datasource.createViewForSongArtists();


		/*Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a song title: ");
		String title = scanner.nextLine();

		songArtists = datasource.querySongInfoView(title);
		if(songArtists.isEmpty()) {
			System.out.println("Couldn't find the artist for the song");
			return;
		}*/


		for(SongArtist artist : songArtists) {
			System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
					" Album name = " + artist.getAlbumName() +
					" Track number = " + artist.getTrack());
		}

		//this is a transaction
		datasource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 7);
		datasource.close();

		datasource.close();

	}
}