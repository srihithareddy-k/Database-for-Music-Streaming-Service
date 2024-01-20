import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
//import java.sql.Savepoint;

/*
Team Y CSC540 Project 3 Demo Code
Chandler James - Unity ID: cwjames2@ncsu.edu
Sarika Vishwanatham - Unity ID: svishwa2@ncsu.edu
Srihitha Reddy Kaalam - Unity ID: skaalam@ncsu.edu

This directory should work directly with Eclipse.
You will probably need to update the path to the mariadb-java-client (in referenced libraries)
Right click, configure build path..., edit
The jar file is in this folder
YOU MUST FILL IN "unityID" and "PASSWORD" with your credentials!

After the program starts, it drops all the tables that exist, creates tables in the database, and populates them with demo data
User interface:
1 - Information Processing
2 - Maintaining Metadata and Records
3 - Maintaining Payments
4 - Reports
0 - Exit
Select numbers to perform corresponding operations.

Function documentations:
1.initialize function: Initializes the functions numbered 2, 3, 4, 5 when the program starts
2.connectToDatabase function: establish connection
3.clear_tables function: drops all the existing tables in the database
4.create_tables function: creates all the tables when the program starts
5.insert_sample_data function: populates all the created tables with demo data
6.main function: initializes, prints a welcome message, shows the menu, listens to inputs and performs database CRUD, close and quit

High-level/Design decisions:
1. User Interface: The code provides a text-based menu-driven interface for users to interact with the database. It uses the Scanner class to accept user input for menu selections and other inputs.
2. Statement and ResultSet for Database Operations: The code uses Statement and ResultSet objects for executing SQL queries and processing query results, respectively. While Statement is susceptible to SQL injection attacks and may not be the most secure way to interact with a database, it can be appropriate for simple applications with limited security requirements.
3. Error Handling: The code includes exception handling to handle potential errors, such as SQL query failures, invalid inputs, and database connection errors. It catches SQLException and other exceptions and displays appropriate error messages to the user.
4. Input Validation: The code validates user input, such as menu selections and IDs, to ensure that they are valid and prevent SQL injection attacks. It uses techniques such as input parsing, type conversion, and parameterized queries to sanitize user inputs and protect against SQL injection vulnerabilities.
 */

public class p3_test {
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cwjames2";
	// Put your oracle ID and password here

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;
	private static Scanner input = new Scanner(System.in);
	
	private static int episode_fee = 50;
	private static int fee_per_ad = 10;

	public static void main(String[] args) {
		
		
		initialize();
		System.out.println("Initialization complete.\n");
		System.out.println("Welcome to Wolfmedia!\n");
		
		while ( main_menu() ) ; //call main menu until user exits

		/*try {

		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		// ***********************************************************************
		close();
	}
	
	private static boolean main_menu() {
		int select;
		
		System.out.print("What would you like to do? (input the number to select)\n"
				+ "1 - Information Processing\n"
				+ "2 - Maintaining Metadata and Records\n"
				+ "3 - Maintaining Payments\n"
				+ "4 - Reports\n"
				+ "0 - Exit\n"
				+ "\nSelect: ");		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		
		try {
		switch (select) {
			case 1:
				while (info_proc_menu()) ;
				break;
			case 2:
				while (metadata_menu()) ;
				break;
			case 3:
				while (payments_menu()) ;
				break;
			case 4:
				while (reports_menu()) ;
				break;
			case 0:
				System.out.println("Goodbye!\n");
				return false; //exit the program
			default:
				System.out.println("Please try again.  Enter only the number.\n");
				break;
			}
		} catch (SQLException e) {
			System.out.println("SQL Query Failed. Please verify your input.");
		}
		
		return true;
	}
	
	private static boolean info_proc_menu() throws SQLException {
		int select;
		String song_ID, album_ID, track_num, artist_ID, label_ID, ep_ID, pod_ID, host_ID;
		
		System.out.print("\nInformation Processing: What would you like to do?\n"
						  + "1 - Enter Information\n" 
						  + "2 - Update Information\n" 
						  + "3 - Delete Information\n"
						  + "4 - Assign Songs to Album\n"
						  + "5 - Assign Artist to Album\n"
						  + "6 - Assign Artists to Record Labels\n"
						  + "7 - Assign Podcast Episodes to Podcasts\n"
						  + "8 - Assign Podcast Hosts to Podcasts\n"
						  + "0 - Exit\n"
						  + "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		switch (select) {
		case 1:
			while (enter_info_menu()) ;
			break;
		case 2:
			while (update_info_menu()) ;
			break;
		case 3:
			while (delete_info_menu()) ;
			break;
		case 4:
			System.out.print("Enter Song ID: ");
			song_ID = input.nextLine();
			
			System.out.print("Enter Album ID: ");
			album_ID = input.nextLine();
			
			System.out.print("Enter Track Number: ");
			track_num = input.nextLine();
			
			statement.executeUpdate("INSERT INTO songsInAlbum VALUES "
						+ "('" + song_ID + "', '" + album_ID + "'," + track_num + ")");
			
			statement.executeUpdate("UPDATE songs SET album_ID = '"  + album_ID + "' WHERE ID = '" + song_ID + "'");
			
			break;
		case 5:
			System.out.print("Enter Artist ID: ");
			artist_ID = input.nextLine();
			
			System.out.print("Enter Album ID: ");
			album_ID = input.nextLine();
			
			statement.executeUpdate("INSERT INTO albumBy VALUES "
						+ "('" + artist_ID + "', '" + album_ID + "')");
			
			break;
		case 6:
			System.out.print("Enter Artist ID: ");
			artist_ID = input.nextLine();
			
			System.out.print("Enter Label ID: ");
			label_ID = input.nextLine();
			
			statement.executeUpdate("UPDATE artists SET label_ID = '" + label_ID + "' WHERE ID = '" + artist_ID +"'");
			
			break;
		case 7:
			System.out.print("Enter Episode ID: ");
			ep_ID = input.nextLine();
			
			System.out.print("Enter Podcast ID: ");
			pod_ID = input.nextLine();
			
			statement.executeUpdate("UPDATE episodes SET podcast_ID = '" + pod_ID + "' WHERE ID = '" + ep_ID + "'");
			
			break;
		case 8:
			System.out.print("Enter Podcast ID: ");
			pod_ID = input.nextLine();
			
			System.out.print("Enter Host ID: ");
			host_ID = input.nextLine();
			
			statement.executeUpdate("UPDATE podcasts SET host_ID = '" + host_ID + "' WHERE ID = '" + pod_ID + "'");
			
			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
	}
	
	private static boolean update_info_menu() throws SQLException {
		int select;
		String str1, str2, str3, str4;

		System.out.print("\nEnter Information for?\n"
						  + "1 - Update Song\n" 
						  + "2 - Update Album\n" 
						  + "3 - Update Artist\n"
						  + "4 - Update Podcast Episode\n"
						  + "5 - Update Podcast Host\n"
						  + "6 - Update Podcast\n"
						  + "0 - Exit\n"
						  + "\nSelect: ");
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		switch (select) {
		case 1: //song
			System.out.println("Enter song ID: ");
			str1 = input.nextLine();
			System.out.println("ID/title/album_ID/play_count/release_date/release_country/language/royalty_rate/royalty_paid/label_ID");
			System.out.println("Enter field to be updated: ");
			str2 = input.nextLine();
			System.out.println("Enter new value: ");
			str3 = input.nextLine();
			statement.executeQuery("UPDATE songs SET "+str2+" = '" + str3 + "' WHERE "
					+ "ID = '" + str1 +"'");
			break;
		case 2: //album
			
			System.out.println("Enter album ID: ");
			str1 = input.nextLine();
			System.out.println("ID/name/year/edition");
			System.out.println("Enter field to be updated: ");
			str2 = input.nextLine();
			System.out.println("Enter new value: ");
			str3 = input.nextLine();
			statement.executeQuery("UPDATE albums SET "+str2+" = '" + str3 + "' WHERE "
					+ "ID = '" + str1 +"'");
			break;
		case 3: //artist
			System.out.println("Enter artist ID: ");
			str1 = input.nextLine();
			System.out.println("ID/name/status/type/country/primary_genre/monthly_listeners/label_ID");
			System.out.println("Enter field to be updated: ");
			str2 = input.nextLine();
			System.out.println("Enter new value: ");
			str3 = input.nextLine();
			statement.executeQuery("UPDATE artists SET "+str2+" = '" + str3 + "' WHERE "
					+ "ID = '" + str1 +"'");
			
			break;
		case 4: //podcast episode
			System.out.println("Enter episode ID: ");
			str1 = input.nextLine();
			System.out.println("ID/podcast_ID/ep_num/title/duration/release_date/listen_count/ad_count/host_paid");
			System.out.println("Enter field to be updated: ");
			str2 = input.nextLine();
			System.out.println("Enter new value: ");
			str3 = input.nextLine();
			statement.executeQuery("UPDATE episodes SET "+str2+" = '" + str3 + "' WHERE "
					+ "ID = '" + str1 +"'");
			break;
		case 5: //host
			System.out.println("Enter host ID: ");
			str1 = input.nextLine();
			System.out.println("ID/first_name/last_name/phone/email/city");
			System.out.println("Enter field to be updated: ");
			str2 = input.nextLine();
			System.out.println("Enter new value: ");
			str3 = input.nextLine();
			statement.executeQuery("UPDATE hosts SET "+str2+" = '" + str3 + "' WHERE "
					+ "ID = '" + str1 +"'");
			break;
		case 6: //Podcast
			System.out.println("Enter podcast ID: ");
			str1 = input.nextLine();
			System.out.println("ID/name/host_ID/language/country/ep_count/ep_fee/total_subs/avg_rating/total_ratings");
			System.out.println("Enter field to be updated: ");
			str2 = input.nextLine();
			System.out.println("Enter new value: ");
			str3 = input.nextLine();
			statement.executeQuery("UPDATE podcasts SET "+str2+" = '" + str3 + "' WHERE "
					+ "ID = '" + str1 +"'");
			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
	}
	
	private static boolean delete_info_menu() throws SQLException {
		int select;
		String ID;
		
		System.out.print("\nDelete Information for?\n"
				  + "1 - Delete Song\n" 
				  + "2 - Delete Artist\n"
				  + "3 - Delete Episode\n"
				  + "4 - Delete Host\n"
				  + "5 - Delete Album\n"
				  + "6 - Delete Podcast\n"
				  + "0 - Exit\n"
				  + "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		
		switch (select) {
		case 1:
			System.out.print("Enter Song ID: (use 'help' if needed) ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, title, play_count FROM songs");
				printResultSet(result, true);
			}
			else {
				statement.executeUpdate("DELETE FROM songs WHERE ID = '" + ID + "'");
			}
			break;
		case 2:
			System.out.print("Enter Artist ID: (use 'help' if needed) ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM artists");
				printResultSet(result, true);
			}
			else {
				statement.executeUpdate("DELETE FROM artists WHERE ID = '" + ID + "'");
			}
			break;
		case 3:
			System.out.print("Enter Episode ID: (use 'help' if needed) ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, podcast_ID, ep_num, title FROM episodes");
				printResultSet(result, true);
			}
			else {
				statement.executeUpdate("DELETE FROM episodes WHERE ID = '" + ID + "'");
			}
			break;
		case 4:
			System.out.print("Enter Host ID: (use 'help' if needed) ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, first_name, last_name FROM hosts");
				printResultSet(result, true);
			}
			else {
				statement.executeUpdate("DELETE FROM hosts WHERE ID = '" + ID + "'");
			}
			break;
		case 5: //album
			System.out.print("Enter Album ID: (use 'help' if needed) ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, first_name, last_name FROM hosts");
				printResultSet(result, true);
			}
			else {
				statement.executeUpdate("DELETE FROM albums WHERE ID = '" + ID + "'");
			}
			break;
		case 6:
			System.out.print("Enter Podcast ID: (use 'help' if needed) ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, first_name, last_name FROM hosts");
				printResultSet(result, true);
			}
			else {
				statement.executeUpdate("DELETE FROM podcasts WHERE ID = '" + ID + "'");
			}
			break;
			
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
	}
	
	private static boolean enter_info_menu() throws SQLException {
		int select;
		String str1, str2, str3, str4, str5, str6, str7, str8, str9, str10;

		System.out.print("\nEnter Information for?\n"
						  + "1 - New Song\n" 
						  + "2 - New Album\n" 
						  + "3 - New Artist\n"
						  + "4 - New Label\n"
						  + "5 - New Genre\n"
						  + "6 - New Podcast Episode\n"
						  + "7 - New Podcast Host\n"
						  + "8 - New Podcast\n"
						  + "9 - New Podcast Guest\n"
						  + "10 - New Podcast Sponsor\n"
						  + "11 - New User\n"
						  + "0 - Exit\n"
						  + "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		switch (select) {
		case 1: //NEW SONG
			System.out.println("Enter new song ID (must be unique): ");
			str1 = input.nextLine();
			System.out.println("Enter new song title: ");
			str2 = input.nextLine();
			System.out.println("Enter new song album_ID (All songs must be part of an existing album): ");
			str3 = input.nextLine();
			System.out.println("Enter new song play_count: ");
			str4 = input.nextLine();
			System.out.println("Enter new song release date (YYYY-MM-DD): ");
			str5 = input.nextLine();
			System.out.println("Enter new song release country: ");
			str6 = input.nextLine();
			System.out.println("Enter new song language: ");
			str7 = input.nextLine();
			System.out.println("Enter new song royalty rate: ");
			str8 = input.nextLine();
			System.out.println("Enter new song royalty payment status (0 or 1): ");
			str9 = input.nextLine();
			System.out.println("Enter new song's owning label ID (must be an existing record label): ");
			str10 = input.nextLine();
			statement.executeUpdate("INSERT INTO songs VALUES"
					+ " ('"+str1+"','"+str2+"','"+str3+"',"+str4+",'"+str5+"', '"+str6+"', "
					+ "'"+str7+"', "+str8+", "+str9+", '"+str10+"')");
			break;
		case 2: //NEW ALBUM
			System.out.println("Enter new album ID (must be unique): ");
			str1 = input.nextLine();
			System.out.println("Enter new album name: ");
			str2 = input.nextLine();
			System.out.println("Enter new album year: ");
			str3 = input.nextLine();
			System.out.println("Enter new album edition: ");
			str4 = input.nextLine();
			statement.executeUpdate("INSERT INTO albums VALUES"
					+ " ('"+str1+"','"+str2+"',"+str3+",'"+str4+"')");

			break;
		case 3: //NEW ARTIST
			System.out.println("Enter new artist ID (must be unique): ");
			str1 = input.nextLine();
			System.out.println("Enter new artist name: ");
			str2 = input.nextLine();
			System.out.println("Enter new artist status (active or retired): ");
			str3 = input.nextLine();
			System.out.println("Enter new artist type (band,musician,composer): ");
			str4 = input.nextLine();
			System.out.println("Enter new artist country: ");
			str5 = input.nextLine();
			System.out.println("Enter new artist primary genre (must be an existing genre): ");
			str6 = input.nextLine();
			System.out.println("Enter new artist monthly listener count (int): ");
			str7 = input.nextLine();
			System.out.println("Enter new artist's label ID (must be an existing label): ");
			str8 = input.nextLine();
			statement.executeUpdate("INSERT INTO artists VALUES"
					+ " ('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"', "
					+ str7+", '"+str8+"')");
			break;
		case 4: //NEW LABEL
			System.out.println("Enter new label ID (must be unique): ");
			str1 = input.nextLine();
			System.out.println("Enter new label name: ");
			str2 = input.nextLine();
			statement.executeUpdate("INSERT INTO labels VALUES"
					+ " ('"+str1+"','"+str2+"')");
			break;
		case 5: //NEW GENRE
			System.out.println("Enter new genre (must be unique): ");
			str1 = input.nextLine();
			statement.executeUpdate("INSERT INTO genres VALUES"
					+ " ('"+str1+"')");
			break;
		case 6: //NEW EPISODE
			System.out.println("Enter new episode ID (must be unique): ");
			str1 = input.nextLine();
			System.out.println("Enter corresponding podcast ID (must already exist): ");
			str2 = input.nextLine();
			System.out.println("Enter new episode number: ");
			str3 = input.nextLine();
			System.out.println("Enter new episode title: ");
			str4 = input.nextLine();
			System.out.println("Enter new episode duration (minutes): ");
			str5 = input.nextLine();
			System.out.println("Enter new episode release date (YYYY-MM-DD): ");
			str6 = input.nextLine();
			System.out.println("Enter new episode listen count: ");
			str7 = input.nextLine();
			System.out.println("Enter new episode ad count: ");
			str8 = input.nextLine();
			System.out.println("Enter new episode host_paid (0 or 1): ");
			str9 = input.nextLine();
			statement.executeUpdate("INSERT INTO episodes VALUES"
					+ " ('"+str1+"','"+str2+"',"+str3+",'"+str4+"',"+str5+",'"+str6+"', "
					+ str7+", "+str8+","+str9+")");
			break;
		case 7: //NEW HOST
			System.out.println("Enter new host ID: ");
			str1 = input.nextLine();
			System.out.println("Enter new host first name: ");
			str2 = input.nextLine();
			System.out.println("Enter new host last name: ");
			str3 = input.nextLine();
			System.out.println("Enter new host phone: ");
			str4 = input.nextLine();
			System.out.println("Enter new email: ");
			str5 = input.nextLine();
			System.out.println("Enter new city: ");
			str6 = input.nextLine();
			statement.executeUpdate("INSERT INTO hosts VALUES"
					+ " ('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"')");
			break;
		case 8: //NEW PODCAST
			System.out.println("Enter new podcast ID: ");
			str1 = input.nextLine();
			System.out.println("Enter new podcast name: ");
			str2 = input.nextLine();
			System.out.println("Enter new podcast host_ID (must already exist): ");
			str3 = input.nextLine();
			System.out.println("Enter new podcast language: ");
			str4 = input.nextLine();
			System.out.println("Enter new podcast country: ");
			str5 = input.nextLine();
			System.out.println("Enter new podcast ep count: ");
			str6 = input.nextLine();
			System.out.println("Enter new podcast ep fee: ");
			str7 = input.nextLine();
			System.out.println("Enter new podcast total subs: ");
			str8 = input.nextLine();
			System.out.println("Enter new podcast avg rating: ");
			str9 = input.nextLine();
			System.out.println("Enter new podcast total ratings: ");
			str10 = input.nextLine();
			statement.executeUpdate("INSERT INTO podcasts VALUES"
				+ " ('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"',"+str6
				+ ","+str7+","+str8+","+str9+","+str10+")");
			break;
		case 9: //NEW GUEST
			break;
		case 10: //NEW SPONSOR
			break;
		case 11: //NEW USER
			System.out.println("Enter new user ID: ");
			str1 = input.nextLine();
			System.out.println("Enter first name: ");
			str2 = input.nextLine();
			System.out.println("Enter last name: ");
			str3 = input.nextLine();
			System.out.println("Enter phone: ");
			str4 = input.nextLine();
			System.out.println("Enter email: ");
			str5 = input.nextLine();
			System.out.println("Enter registration date (YYYY-MM-DD): ");
			str6 = input.nextLine();
			System.out.println("Enter sub status (S or U): ");
			str7 = input.nextLine();
			System.out.println("Enter sub fee: ");
			str8 = input.nextLine();
			statement.executeUpdate("INSERT INTO users VALUES"
				+ " ('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"','"+str7+"',"+str8+")");
			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
		
	}
	
	private static boolean metadata_menu() throws SQLException {
		int select;
		String ID, listenerCount, subscriberCount;
		
		System.out.print("\nMaintaining Metadata and Records: What would you like to do?\n"
						  + "1 - Enter/Update Song Play Count\n" 
						  + "2 - Enter/Update Artist's Monthly Listeners\n" 
						  + "3 - Enter/Update Subscriber Count for Podcast\n"
						  + "4 - Enter/Update Rating for Podcast\n"
						  + "5 - Enter/Update Listening Count for Podcast Episodes\n"
						  + "6 - Find Songs by Artist\n"
						  + "7 - Find Songs by Album\n"
						  + "8 - Find Episodes by Podcast\n"
						  + "0 - Exit\n"
						  + "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		switch (select) {
		case 1:
			while (metadata_play_count_menu()) ;
			break;
		case 2:
			System.out.print("Enter Artist ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM artists");
				printResultSet(result, true);
			}
			else {
				System.out.print("Enter Artist's Monthly Listeners: ");
				listenerCount = input.nextLine();
				
				statement.executeQuery("UPDATE artists SET monthly_listeners = " + listenerCount + " WHERE ID = '" + ID + "'");
			}
			break;
		case 3:
			System.out.print("Enter Podcast ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM podcasts");
				printResultSet(result, true);
			}
			else {
				System.out.print("Enter Subscriber Count for podcast: ");
				subscriberCount = input.nextLine();
				
				statement.executeQuery("UPDATE podcasts SET total_subs = " + subscriberCount + " WHERE ID = '" + ID + "'");
			}
			break;
		case 4:
			while (metadata_rating_menu()) ;
			break;
		case 5:
			while (metadata_listen_count_menu()) ;
			break;
		case 6:
			System.out.print("Enter Artist ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM artists");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT songs.title, artists.name, artistsIn.role "
						+ "FROM songs JOIN artistsIn ON songs.ID = artistsIn.song_ID "
						+ "JOIN artists ON artistsIn.artist_ID = artists.ID "
						+ "WHERE artists.ID = '" + ID + "'");
			
				if (result.next()) System.out.print("song title | artist | role");
				result.beforeFirst();
				printResultSet(result, false);
			}
			break;
		case 7:
			System.out.print("Enter Album ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM albums");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT songs.title, albums.name FROM songs "
												+ "JOIN songsInAlbum ON songs.ID = songsInAlbum.song_ID "
												+ "JOIN albums ON songsInAlbum.album_ID = albums.ID "
												+ "WHERE albums.ID = '" + ID + "'");
				if (result.next()) System.out.print("song title | album");
				result.beforeFirst();
				printResultSet(result, false);
			}
			break;
		case 8:
			System.out.print("Enter Podcast ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM podcasts");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT episodes.title, podcasts.name FROM episodes "
												+ "JOIN podcasts ON episodes.podcast_ID = podcasts.ID "
												+ "WHERE podcasts.ID = '" + ID + "'");
				if (result.next()) System.out.print("episode title | podcast");
				result.beforeFirst();
				printResultSet(result, false);
			}
			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
	}
	
	private static boolean metadata_play_count_menu() throws SQLException {
		int select;
		String ID, month, year, playCount;

		System.out.print("\n What would you like to do?\n" + "1 - Add Monthly Play Count For Song\n"
				+ "2 - Update Monthly Play Count For Song \n" + "3 - Update Total Play Count For Song\n" + "0 - Exit\n"
				+ "\nSelect: ");

		try {
			select = Integer.parseInt(input.nextLine());
		} catch (Exception e) {
			select = -1;
		}

		try {
			connection.setAutoCommit(false);
			try {
				switch (select) {
				case 1:
					System.out.print("Enter Song ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, title, play_count FROM songs");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Month between 1 to 12: ");
						month = input.nextLine();

						System.out.print("Enter Year: ");
						year = input.nextLine();

						System.out.print("Enter Play Count: ");
						playCount = input.nextLine();

						statement.executeQuery("INSERT INTO playCountHistory VALUES " + "('" + ID + "'," + month + ","
								+ year + "," + playCount + ")");
					}
					break;
				case 2:
					System.out.print("Enter Song ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, title, play_count FROM songs");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Month between 1 to 12: ");
						month = input.nextLine();

						System.out.print("Enter Year: ");
						year = input.nextLine();

						System.out.print("Enter PLay Count: ");
						playCount = input.nextLine();

						statement.executeQuery("UPDATE playCountHistory SET play_count = " + playCount + " WHERE "
								+ "song_ID = '" + ID + "' AND month = " + month + " AND year = " + year);
					}
					break;
				case 3:
					System.out.print("Enter Song ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, title, play_count FROM songs");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Play Count: ");
						playCount = input.nextLine();

						statement.executeQuery(
								"UPDATE songs SET play_count = " + playCount + " WHERE ID = '" + ID + "'");
					}
					break;
				case 0:
					return false; // exit this menu
				default:
					System.out.println("Please try again.  Enter only the number.\n");
					break;
				}
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean metadata_rating_menu() throws SQLException {
		int select;
		String ID, month, year, rating;

		System.out.print("\n What would you like to do?\n" + "1 - Add Monthly Rating For Podcast\n"
				+ "2 - Update Monthly Rating For Podcast\n" + "3 - Update Total Rating For Podcast\n" + "0 - Exit\n"
				+ "\nSelect: ");

		try {
			select = Integer.parseInt(input.nextLine());
		} catch (Exception e) {
			select = -1;
		}

		try {
			connection.setAutoCommit(false);
			try {
				switch (select) {
				case 1:
					System.out.print("Enter Podcast ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, name, avg_rating FROM podcasts");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Month between 1 to 12: ");
						month = input.nextLine();

						System.out.print("Enter Year: ");
						year = input.nextLine();

						System.out.print("Enter Rating (1-5): ");
						rating = input.nextLine();

						statement.executeQuery("INSERT INTO ratingHistory VALUES " + "('" + ID + "' ," + month + ", "
								+ year + ", " + rating + ")");
					}
					break;
				case 2:
					System.out.print("Enter Podcast ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, name, avg_rating FROM podcasts");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Month between 1 to 12: ");
						month = input.nextLine();

						System.out.print("Enter Year: ");
						year = input.nextLine();

						System.out.print("Enter Rating (1-5): ");
						rating = input.nextLine();

						statement.executeQuery("UPDATE ratingHistory SET avg_rating = " + rating + " WHERE "
								+ "podcast_ID = '" + ID + "' AND month = " + month + " AND year = " + year);
					}
					break;
				case 3:
					System.out.print("Enter Podcast ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, name, avg_rating FROM podcasts");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Rating (1-5): ");
						rating = input.nextLine();

						statement.executeQuery(
								"UPDATE podcasts SET avg_rating = " + rating + " WHERE ID = '" + ID + "'");
					}
					break;
				case 0:
					return false; // exit this menu
				default:
					System.out.println("Please try again.  Enter only the number.\n");
					break;
				}
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean metadata_listen_count_menu() throws SQLException {
		int select;
		String ID, month, year, listen_count;

		System.out.print("\n What would you like to do?\n" + "1 - Add Listening Count For Episode\n"
				+ "2 - Update Listening Count For Episode\n" + "3 - Update Total Listening Count For Episode\n"
				+ "0 - Exit\n" + "\nSelect: ");

		try {
			select = Integer.parseInt(input.nextLine());
		} catch (Exception e) {
			select = -1;
		}

		try {
			connection.setAutoCommit(false);
			try {
				switch (select) {
				case 1:
					System.out.print("Enter Podcast Episode ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, podcast_ID, ep_num, title FROM episodes");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Month between 1 to 12: ");
						month = input.nextLine();

						System.out.print("Enter Year: ");
						year = input.nextLine();

						System.out.print("Enter Listen Count: ");
						listen_count = input.nextLine();

						statement.executeQuery("INSERT INTO listeningCountHistory VALUES " + "('" + ID + "' ," + month
								+ ", " + year + ", " + listen_count + ")");
					}
					break;
				case 2:
					System.out.print("Enter Podcast Episode ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, podcast_ID, ep_num, title FROM episodes");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Month between 1 to 12: ");
						month = input.nextLine();

						System.out.print("Enter Year: ");
						year = input.nextLine();

						System.out.print("Enter Listen Count: ");
						listen_count = input.nextLine();

						statement.executeQuery("UPDATE listeningCountHistory SET play_count = " + listen_count
								+ " WHERE " + "ep_ID = '" + ID + "' AND month = " + month + " AND year = " + year);
					}
					break;
				case 3:
					System.out.print("Enter Podcast Episode ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, podcast_ID, ep_num, title FROM episodes");
						printResultSet(result, true);
					} else {
						System.out.print("Enter Listen Count: ");
						listen_count = input.nextLine();

						statement.executeQuery(
								"UPDATE episodes SET listen_count = " + listen_count + " WHERE ID = '" + ID + "'");
					}
					break;
				case 0:
					return false; // exit this menu
				default:
					System.out.println("Please try again.  Enter only the number.\n");
					break;
				}
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean payments_menu() throws SQLException {
		int select;
		String ID = null, label_ID = null;
		int royalty_paid = 0;
		int host_paid = 0;
		int ad_count = 0;
		double royalty_rate = 0;
		double total_amount = 0;
		double plays_last_month = 0;
		ArrayList<String> artists = new ArrayList<String>();

		System.out.print("\nMaintaining Payments: What would you like to do?\n" + "1 - Make Royalty Payment For Song\n"
				+ "2 - Make Payment To Podcast Host \n" + "3 - Collect Subscriber Payment\n" + "0 - Exit\n"
				+ "\nSelect: ");

		try {
			select = Integer.parseInt(input.nextLine());
		} catch (Exception e) {
			select = -1;
		}
		switch (select) {
		case 1:
			try {
				connection.setAutoCommit(false);
				try {
					System.out.print("Enter Song ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, title FROM songs");
						printResultSet(result, true);
					} else {
						// get royalty rate and record label
						result = statement.executeQuery(
								"SELECT royalty_rate, royalty_paid, label_ID FROM songs " + "WHERE ID = '" + ID + "'");
						while (result.next()) { // calculate total amount to be paid for song
							royalty_rate = Double.parseDouble(result.getString(1));
							royalty_paid = Integer.parseInt(result.getString(2));
							label_ID = result.getString(3);
						}
						if (royalty_paid == 1) {
							System.out.println("The monthly royalties have already been paid for this song.");
							return true;
						}
						// get artists who are receiving payments
						result = statement
								.executeQuery("SELECT artist_ID FROM artistsIn " + "WHERE song_ID = '" + ID + "'");
						while (result.next())
							artists.add(result.getString(1));
						// get last month's play count
						result = statement.executeQuery("SELECT play_count FROM playCountHistory "
								+ "WHERE month = MONTH(CURDATE()) - 1 AND song_ID = '" + ID + "'");
						while (result.next()) {
							plays_last_month = Double.parseDouble(result.getString(1));
						}
						total_amount = royalty_rate * plays_last_month;
						// pay artists
						for (int i = 0; i < artists.size(); i++) {
							statement.executeUpdate("INSERT INTO artistPaymentHistory (artist_ID, month, year, amount)"
									+ " VALUES('" + artists.get(i) + "', MONTH(CURDATE()), YEAR(CURDATE()), "
									+ ((0.7 * total_amount) / artists.size()) + ")");
						}
						// pay record label
						statement.executeUpdate("INSERT INTO royaltyPaymentHistory(label_ID, month, year, amount)"
								+ "VALUES('" + label_ID + "', MONTH(CURDATE()), YEAR(CURDATE()), "
								+ (0.3 * total_amount) + ")");
						// set royalty as paid for this month
						statement.executeUpdate("UPDATE songs SET royalty_paid = 1 WHERE ID = '" + ID + "'");
					}
					connection.commit();
				} catch (SQLException e) {
					connection.rollback();
					e.printStackTrace();
				} finally {
					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				connection.setAutoCommit(false);
				try {
					System.out.print("Enter Episode ID (use 'help' if needed): ");
					ID = input.nextLine();
					if (ID.equals("help")) {
						result = statement.executeQuery("SELECT ID, title FROM episodes");
						printResultSet(result, true);
					} else {
						// get ad count and determine if episode has been paid
						result = statement
								.executeQuery("SELECT ad_count, host_paid FROM episodes WHERE ID = '" + ID + "'");
						while (result.next()) {
							ad_count = Integer.parseInt(result.getString(1));
							host_paid = Integer.parseInt(result.getString(2));
						}
						if (host_paid == 1) {
							System.out.println("The host fee has already been paid for this episode.");
							return true;
						}
						// add new payment to host
						statement.executeUpdate("INSERT INTO hostPaymentHistory (host_ID, month, year, amount)"
								+ " SELECT p.host_ID, MONTH(CURDATE()), YEAR(CURDATE()), "
								+ (episode_fee + fee_per_ad * ad_count)
								+ " FROM episodes e JOIN podcasts p ON p.ID = e.podcast_ID WHERE e.ID = '" + ID + "'");
						// set host_paid to 1
						statement.executeUpdate("UPDATE episodes SET host_paid = 1 WHERE ID = '" + ID + "'");
					}
					connection.commit();
				} catch (SQLException e) {
					connection.rollback();
					e.printStackTrace();
				} finally {
					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.print("Enter User ID (use 'help' if needed): ");
			ID = input.nextLine();
			if (ID.equals("help")) {
				result = statement.executeQuery("SELECT ID, first_name, last_name FROM users");
				printResultSet(result, true);
			} else {
				result = statement.executeQuery("INSERT INTO userPaymentHistory (user_ID, month, year, amount) "
						+ "SELECT u.ID, MONTH(CURDATE()), YEAR(CURDATE()), u.sub_fee " + "FROM users u WHERE u.ID = '"
						+ ID + "'");
			}
			break;
		case 0:
			return false; // exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
	}
	
	private static boolean reports_menu() throws SQLException {
		int select;
		String ID;
		int startMonth, endMonth, startYear, endYear;
		
		System.out.print("\nReports: What would you like to do?\n"
						  + "1 - Play Count Report\n" 
						  + "2 - Outgoing Payment Report\n" 
						  + "3 - Total Revenue Report\n"
						  + "4 - Songs By Artist\n"
						  + "5 - Songs In Album\n"
						  + "6 - Episodes From Podcast\n"
						  + "0 - Exit\n"
						  + "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		switch (select) {
		case 1:
			while (play_count_menu()) ;
			break;
		case 2:
			while (generate_payments()) ;
			break;
		case 3:
			
			System.out.print("Enter Start Month: ");
			startMonth = Integer.parseInt(input.nextLine());
			
			System.out.print("Enter Start Year: ");
			startYear = Integer.parseInt(input.nextLine());
			
			System.out.print("Enter End Month: ");
			endMonth = Integer.parseInt(input.nextLine());
			
			System.out.print("Enter End Year: ");
			endYear = Integer.parseInt(input.nextLine());
			
			pay_history_time_period("user", startMonth, startYear, endMonth, endYear, false, null, null);
			/*if (startYear == endYear) {
				result = statement.executeQuery("SELECT month, year, SUM(amount) AS revenue FROM userPaymentHistory"
					+ " WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year = " + startYear 
					+ " GROUP BY month, year "
					+ "ORDER BY year, month");
				printResultSet(result, true);
				
				result = statement.executeQuery("SELECT SUM(amount) as total_revenue FROM userPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year = " + startYear);
				
				printResultSet(result, true);
			}
			
			if (startYear < endYear) {
				result = statement.executeQuery("SELECT month, year, SUM(amount) AS revenue FROM userPaymentHistory"
						+ " WHERE (year = " + startYear + " AND month >= " + startMonth + ")"
						+ " OR (year = " + endYear + " AND month <= " + endMonth + ")"
						+ " OR (year > " + startYear + " AND year < " + endYear + ")"
						+ " GROUP BY month, year "
						+ " ORDER BY year, month");
				printResultSet(result, true);
				
				result = statement.executeQuery("SELECT SUM(amount) as total_revenue FROM userPaymentHistory "
						+ " WHERE (year = " + startYear + " AND month >= " + startMonth + ")"
						+ " OR (year = " + endYear + " AND month <= " + endMonth + ")"
						+ " OR (year > " + startYear + " AND year < " + endYear + ")"
						);
				printResultSet(result, true);
			} */
			break;
		case 4:
			System.out.print("Enter Artist ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM artists");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT songs.title, artists.name, artistsIn.role "
						+ "FROM songs JOIN artistsIn ON songs.ID = artistsIn.song_ID "
						+ "JOIN artists ON artistsIn.artist_ID = artists.ID "
						+ "WHERE artists.ID = '" + ID + "'");
			
				if (result.next()) System.out.print("song title | artist | role");
				result.beforeFirst();
				printResultSet(result, false);
			}
			break;
		case 5:
			System.out.print("Enter Album ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM albums");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT songs.title, albums.name FROM songs "
						+ "JOIN songsInAlbum ON songs.ID = songsInAlbum.song_ID "
						+ "JOIN albums ON songsInAlbum.album_ID = albums.ID "
						+ "WHERE albums.ID = '" + ID + "'");
				if (result.next()) System.out.print("song title | album");
				result.beforeFirst();
				printResultSet(result, false);
			}
			break;
		case 6:
			System.out.print("Enter Podcast ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM podcasts");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT episodes.title, podcasts.name FROM episodes "
						+ "JOIN podcasts ON episodes.podcast_ID = podcasts.ID "
						+ "WHERE podcasts.ID = '" + ID + "'");
				if (result.next()) System.out.print("episode title | podcast");
				result.beforeFirst();
				printResultSet(result, false);
			}
			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
	}
	
	private static boolean play_count_menu() throws SQLException{
		int select;
		String ID;
		
//		System.out.println("Monthly Play Count Report: \n"
//				+ "1 - Song\n2 - Album\n3 - Artist\n"
//				+ "4 - Episode\n5 - Podcast\n6 - Host\n0 - Back\n"
//				+ "\nSelect: ");
		
		System.out.println("Monthly Play Count Report: \n"
				+ "1 - Song\n2 - Album\n3 - Artist\n"
				+ "4 - Episode\n0 - Back\n"
				+ "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		
		switch (select) {
		case 1:
			System.out.print("Enter Song ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, title FROM songs");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT * FROM playCountHistory "
											  + "WHERE song_ID = '" + ID + "'");
				printResultSet(result, true);
			}
			break;
		case 2:
			System.out.print("Enter Album ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM albums");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT album_ID, month, year, SUM(p.play_count) as play_count\n"
						+ "FROM playCountHistory p JOIN songs s ON s.ID = p.song_ID\n"
						+ "WHERE album_ID = '" + ID + "'\n"
						+ "GROUP BY album_ID, month, year");
				printResultSet(result, true);
			}
			break;
		case 3:
			System.out.print("Enter Artist ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM artists");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT artist_ID, month, year, SUM(p.play_count) as play_count\n"
						+ "FROM playCountHistory p JOIN artistsIn a ON a.song_ID = p.song_ID\n"
						+ "WHERE artist_ID = '" + ID + "'\n"
						+ "GROUP BY artist_ID, month, year");
				printResultSet(result, true);
			}
			break;
		case 4:
			System.out.print("Enter Episode ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, title FROM episodes");
				printResultSet(result, true);
			}
			else {
				result = statement.executeQuery("SELECT * FROM listeningCountHistory "
						+ "WHERE ep_ID = '" + ID + "'");
				printResultSet(result, true);
			}
			break;
//		case 5:
//			
//			break;
//		case 6:
//			
//			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
		//catch (SQLException e) {
		//	System.out.println("Query failed.  Make sure the ID is valid.");
		//}
	}
	
	private static boolean generate_payments() throws SQLException{
		int select;
		String ID;
		int startMonth, endMonth, startYear, endYear;
		
		System.out.println("Generate Payment Report For: \n"
				+ "1 - Host\n2 - Artist\n3 - Record Label\n"
				+ "0 - Back\n"
				+ "\nSelect: ");
		
		try {
			select = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
			select = -1;
		}
		
		switch (select) {
		case 1:
			System.out.print("Enter Host ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, first_name, last_name FROM hosts");
				printResultSet(result, true);
			}
			else {
				System.out.print("Enter Start Month: ");
				startMonth = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter Start Year: ");
				startYear = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter End Month: ");
				endMonth = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter End Year: ");
				endYear = Integer.parseInt(input.nextLine());
				
				pay_history_time_period("host", startMonth, startYear, endMonth, endYear, true, ID, "host");
				/*
				result = statement.executeQuery("SELECT * FROM hostPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year >=" + startYear
						+ " AND year <= " + endYear + " AND host_ID = '" + ID + "'");
				printResultSet(result, true);
				
				result = statement.executeQuery("SELECT SUM(amount) as total_payment FROM hostPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year >=" + startYear 
						+ " AND year <= " + endYear + " AND host_ID = '" + ID + "'");
				printResultSet(result, true); */
			}
			break;
		case 2:
			System.out.print("Enter Artist ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM artists");
				printResultSet(result, true);
			}
			else {
				System.out.print("Enter Start Month: ");
				startMonth = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter Start Year: ");
				startYear = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter End Month: ");
				endMonth = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter End Year: ");
				endYear = Integer.parseInt(input.nextLine());
				
				pay_history_time_period("artist", startMonth, startYear, endMonth, endYear, true, ID, "artist");
				/*
				result = statement.executeQuery("SELECT * FROM artistPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year >=" + startYear
						+ " AND year <= " + endYear + " AND artist_ID = '" + ID + "'");
				printResultSet(result, true);
				
				result = statement.executeQuery("SELECT SUM(amount) as total_payment FROM artistPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year >=" + startYear 
						+ " AND year <= " + endYear + " AND artist_ID = '" + ID + "'");
				printResultSet(result, true);*/
			}
			break;
		case 3:
			System.out.print("Enter Label ID (use 'help' if needed): ");
			ID = input.nextLine();
			if ( ID.equals("help") ) {
				result = statement.executeQuery("SELECT ID, name FROM labels");
				printResultSet(result, true);
			}
			else {
				System.out.print("Enter Start Month: ");
				startMonth = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter Start Year: ");
				startYear = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter End Month: ");
				endMonth = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter End Year: ");
				endYear = Integer.parseInt(input.nextLine());
				
				pay_history_time_period("royalty", startMonth, startYear, endMonth, endYear, true, ID, "label");
				/*
				result = statement.executeQuery("SELECT * FROM royaltyPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year >=" + startYear
						+ " AND year <= " + endYear + " AND label_ID = '" + ID + "'");
				printResultSet(result, true);
				
				result = statement.executeQuery("SELECT SUM(amount) as total_payment FROM royaltyPaymentHistory "
						+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year >=" + startYear 
						+ " AND year <= " + endYear + " AND label_ID = '" + ID + "'");
				printResultSet(result, true); */
			}
			break;
		case 0:
			return false; //exit this menu
		default:
			System.out.println("Please try again.  Enter only the number.\n");
			break;
		}
		return true;
		//catch (SQLException e) {
		//	System.out.println("Query failed.  Make sure the ID is valid.");
		//}
	}
	
	private static void pay_history_time_period(String table, int startMonth, int startYear, 
												int endMonth, int endYear, boolean use_ID, String ID, String ID_type) throws SQLException
	{
		String check_ID;
		if (use_ID) check_ID = " AND "+ID_type+"_ID = '" + ID + "'";
		else check_ID = " ";
		
		if (startYear == endYear) {
			result = statement.executeQuery("SELECT month, year, SUM(amount) AS revenue FROM "+table+"PaymentHistory"
				+ " WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year = " + startYear
				+ check_ID
				+ " GROUP BY month, year "
				+ "ORDER BY year, month");
			printResultSet(result, true);
			
			result = statement.executeQuery("SELECT SUM(amount) as total_revenue FROM "+table+"PaymentHistory "
					+ "WHERE month >= " + startMonth + " AND month <= " + endMonth + " AND year = " + startYear
					+ check_ID);
			
			printResultSet(result, true);
		}
		else if (startYear < endYear) {
			result = statement.executeQuery("SELECT month, year, SUM(amount) AS revenue FROM "+table+"PaymentHistory"
					+ " WHERE (year = " + startYear + " AND month >= " + startMonth + check_ID + ")"
					+ " OR (year = " + endYear + " AND month <= " + endMonth + check_ID + ")"
					+ " OR (year > " + startYear + " AND year < " + endYear + check_ID + ")"
					+ " GROUP BY month, year "
					+ " ORDER BY year, month");
			printResultSet(result, true);
			
			result = statement.executeQuery("SELECT SUM(amount) as total_revenue FROM "+table+"PaymentHistory "
					+ " WHERE (year = " + startYear + " AND month >= " + startMonth + check_ID + ")"
					+ " OR (year = " + endYear + " AND month <= " + endMonth + check_ID + ")"
					+ " OR (year > " + startYear + " AND year < " + endYear + check_ID + ")"
					);
			printResultSet(result, true);
		}
	}
	
	private static void printResultSet(ResultSet rs, boolean print_col) throws SQLException
	{
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int col = rsmd.getColumnCount();
	    if (print_col) {
	    	for (int i = 1; i <= col; i++) {
	    		if (i > 1) System.out.print(" | ");
	    		System.out.print(rsmd.getColumnName(i));
	    	}
	    }
	    System.out.println("");
	    while (rs.next()) {
	        for (int i = 1; i <= col; i++) {
	            if (i > 1) System.out.print(" | ");
	            System.out.print(rs.getString(i));
	        }
	        System.out.println("");
	    }
	    System.out.println("");
	}
	
	private static void initialize() {
		try {
			connectToDatabase();
			clear_tables();
			create_tables();
			insert_sample_data();
			insert_bonus_data();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void connectToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");

		String user = "cwjames2";
		String password = "batcave";

		connection = DriverManager.getConnection(jdbcURL, user, password);
		statement = connection.createStatement();
	}

	private static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void clear_tables() {
		try {
			statement.executeUpdate("drop table if exists podcastGenre");
			statement.executeUpdate("drop table if exists songGenre");
			statement.executeUpdate("drop table if exists podcastSubscriptions");
			statement.executeUpdate("drop table if exists artistSubscriptions");
			statement.executeUpdate("drop table if exists episodeGuests");
			statement.executeUpdate("drop table if exists sponsoredBy");
			statement.executeUpdate("drop table if exists songsInAlbum");
			statement.executeUpdate("drop table if exists albumBy");
			statement.executeUpdate("drop table if exists artistsIn");
			statement.executeUpdate("drop table if exists userPaymentHistory");
			statement.executeUpdate("drop table if exists users");
			statement.executeUpdate("drop table if exists royaltyPaymentHistory");
			statement.executeUpdate("drop table if exists artistPaymentHistory");
			statement.executeUpdate("drop table if exists playCountHistory");
			statement.executeUpdate("drop table if exists artists");
			statement.executeUpdate("drop table if exists songs");
			statement.executeUpdate("drop table if exists albums");
			statement.executeUpdate("drop table if exists labels");
			statement.executeUpdate("drop table if exists genres");
			statement.executeUpdate("drop table if exists sponsors");
			statement.executeUpdate("drop table if exists guests");
			statement.executeUpdate("drop table if exists ratingHistory");
			statement.executeUpdate("drop table if exists hostPaymentHistory");
			statement.executeUpdate("drop table if exists listeningCountHistory");
			statement.executeUpdate("drop table if exists episodes");
			statement.executeUpdate("drop table if exists podcasts");
			statement.executeUpdate("drop table if exists hosts");
		} catch (SQLException e) {
			System.out.println("No tables dropped.  Schema was empty.\n");
		}
	}
	
	private static void insert_bonus_data() throws SQLException {
		try {
			statement.executeUpdate("INSERT INTO listeningCountHistory\n"
					+ "VALUES\n"
					+ "    ('pe7001', 1, 2023, 150),\n"
					+ "    ('pe7001', 2, 2023, 250),\n"
					+ "    ('pe7001', 3, 2023, 350),\n"
					+ "    ('pe7002', 1, 2023, 1050),\n"
					+ "    ('pe7002', 2, 2023, 2050),\n"
					+ "    ('pe7002', 3, 2023, 3050)");
			
			statement.executeUpdate("INSERT INTO userPaymentHistory (user_ID, month, year, amount)\n"
					+ "VALUES\n"
					+ "    ('u8003', 1, 2023, 1111),\n"
				    + "    ('u8003', 2, 2023, 2222),\n"
					+ "    ('u8003', 3, 2023, 3333),\n"
					+ "    ('u8003', 4, 2023, 123000)");
		} catch (SQLException e) {
			System.out.print("SQL error while inputting bonus data.\n");
		}
	}
	
	private static void insert_sample_data() {
		try {
			statement.executeUpdate("INSERT INTO genres VALUES ('Alternative'), ('Pop')");
			statement.executeUpdate("INSERT INTO labels\n"
					+ "			VALUES\n"
					+ "			    ('rl3001', 'Elevate Records'),\n"
					+ "			    ('rl3002', 'Melodic Avenue Music')");
			statement.executeUpdate("INSERT INTO artists\n"
					+ "VALUES\n"
					+ "    ('ar2001', 'Forest', 'active', 'band', 'USA', 'Alternative', 25, 'rl3001'),\n"
					+ "    ('ar2002', 'Rain', 'active', 'band', 'USA', 'Pop', 55, 'rl3002')");
			statement.executeUpdate("INSERT INTO albums\n"
					+ "VALUES\n"
					+ "    ('al4001', 'Electric Oasis', 2018, 'Limited'),\n"
					+ "    ('al4002', 'Lost in the Echoes', 2018, 'Platinum')");
			statement.executeUpdate("INSERT INTO songs\n"
					+ "VALUES\n"
					+ "    ('s1001','Electric Dreamscape','al4001',500,'2018-01-01', 'USA', 'English', 0.10, 0, 'rl3001'),\n"
					+ "    ('s1002','Midnight Mirage','al4001',1000,'2018-01-01', 'USA', 'English', 0.10, 0, 'rl3001'),\n"
					+ "    ('s1003','Echoes of You','al4002',100,'2018-03-01', 'USA', 'English', 0.10, 0, 'rl3002'),\n"
					+ "    ('s1004','Rainy Nights','al4002',200,'2018-03-01', 'USA', 'English', 0.10, 0, 'rl3002')");
			statement.executeUpdate("INSERT INTO playCountHistory\n"
					+ "VALUES\n"
					+ "    ('s1001', 1, 2023, 10),\n"
					+ "    ('s1001', 2, 2023, 20),\n"
					+ "    ('s1001', 3, 2023, 30),\n"
					+ "    ('s1002', 1, 2023, 100),\n"
					+ "    ('s1002', 2, 2023, 200),\n"
					+ "    ('s1002', 3, 2023, 300),\n"
					+ "    ('s1003', 1, 2023, 1000),\n"
					+ "    ('s1003', 2, 2023, 2000),\n"
					+ "    ('s1003', 3, 2023, 3000),\n"
					+ "    ('s1004', 1, 2023, 10000),\n"
					+ "    ('s1004', 2, 2023, 20000),\n"
					+ "    ('s1004', 3, 2023, 30000)");
			statement.executeUpdate("INSERT INTO hosts\n"
					+ "VALUES\n"
					+ "    ('ph6001', 'Matthew', 'Wilson', '123-456-7890', 'matt@mail.com', 'Raleigh')");
			statement.executeUpdate("INSERT INTO podcasts\n"
					+ "VALUES \n"
					+ "    ('p5001', 'Mind Over Matter: Exploring the Power of the Human Mind',"
					+ "     'ph6001', 'English', 'USA', 5, 10, 10, 4.5, 0)");
			statement.executeUpdate("INSERT INTO episodes\n"
					+ "VALUES\n"
					+ "    ('pe7001', 'p5001', 1, 'The Science of Mindfulness', 30, '2023-03-07', 100, 0, 0),\n"
					+ "    ('pe7002', 'p5001', 2, 'Unlocking Your Potential', 30, '2023-03-14', 200, 0, 0)");
			statement.executeUpdate("INSERT INTO users\n"
					+ "VALUES\n"
					+ "    ('u8001', 'Alex', 'A', '111-222-3333', 'alex@mail.com', '2017-01-03', 'S', 10),\n"
					+ "    ('u8002', 'John', 'J', '222-333-4444', 'john@mail.com', '2017-01-04', 'S', 10),\n"
					+ "    ('u8003', 'System', 'J', '111-333-4444', 'system@mail.com', '2017-01-04', 'S', 10)");
			statement.executeUpdate("INSERT INTO artistPaymentHistory(artist_ID, month, year, amount)\n"
					+ "VALUES\n"
					+ "    ('ar2001', 1, 2023, 4.2),\n"
					+ "    ('ar2001', 2, 2023, 8.4),\n"
					+ "    ('ar2001', 3, 2023, 12.6),\n"
					+ "    ('ar2002', 1, 2023, 773.5),\n"
					+ "    ('ar2002', 2, 2023, 1547),\n"
					+ "    ('ar2002', 3, 2023, 2320.5)");
			statement.executeUpdate("INSERT INTO royaltyPaymentHistory(label_ID, month, year, amount)\n"
					+ "VALUES\n"
					+ "    ('rl3001', 1, 2023, 3.3),\n"
					+ "    ('rl3001', 2, 2023, 6.6),\n"
					+ "    ('rl3001', 3, 2023, 9.9),\n"
					+ "    ('rl3002', 1, 2023, 330),\n"
					+ "    ('rl3002', 2, 2023, 660),\n"
					+ "    ('rl3002', 3, 2023, 990)");
			statement.executeUpdate("INSERT INTO hostPaymentHistory(host_ID, month, year, amount)\n"
					+ "VALUES\n"
					+ "    ('ph6001', 1, 2023, 20),\n"
					+ "    ('ph6001', 2, 2023, 30),\n"
					+ "    ('ph6001', 3, 2023, 40)");
			statement.executeUpdate("INSERT INTO artistsIn\n"
					+ "VALUES\n"
					+ "    ('s1001', 'ar2001', 'main'),\n"
					+ "    ('s1002', 'ar2001', 'main'),\n"
					+ "    ('s1002', 'ar2002', 'collaborator'),\n"
					+ "    ('s1003', 'ar2002', 'main'),\n"
					+ "    ('s1004', 'ar2002', 'main')");
			statement.executeUpdate("INSERT INTO albumBy\n"
					+ "VALUES\n"
					+ "    ('ar2001', 'al4001'),\n"
					+ "    ('ar2002', 'al4002')");
			statement.executeUpdate("INSERT INTO songsInAlbum\n"
					+ "VALUES\n"
					+ "    ('s1001', 'al4001', 1),\n"
					+ "    ('s1002', 'al4001', 2),\n"
					+ "    ('s1003', 'al4002', 1),\n"
					+ "    ('s1004', 'al4002', 2)");			
		} catch (SQLException e) {
			System.out.println("Error while inputting data.  Check that tables were initialized.\n");
		}
	}
	
	private static void create_tables() {
		try {
			statement.executeUpdate("CREATE TABLE genres (\n"
					+ "    name VARCHAR(30) PRIMARY KEY\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE labels (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    name VARCHAR(50)\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE artists (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    name VARCHAR(50) NOT NULL,\n"
					+ "    status VARCHAR(7) CHECK (status='active' OR status='retired'),\n"
					+ "    type VARCHAR(8) CHECK (type='band' OR type='musician' OR type='composer'),\n"
					+ "    country VARCHAR(30),\n"
					+ "    primary_genre VARCHAR(30),\n"
					+ "    monthly_listeners INT DEFAULT 0,\n"
					+ "    label_ID VARCHAR(10),\n"
					+ "    FOREIGN KEY(primary_genre) \n"
					+ "        REFERENCES genres(name)\n"
					+ "        ON DELETE SET NULL\n"
					+ "        ON UPDATE SET NULL,\n"
					+ "    FOREIGN KEY(label_ID) \n"
					+ "        REFERENCES labels(ID)\n"
					+ "        ON DELETE SET NULL\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE albums (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    name VARCHAR(50) NOT NULL,\n"
					+ "    year INT NOT NULL,\n"
					+ "    edition VARCHAR(15)\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE songs (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    title VARCHAR(50) NOT NULL,\n"
					+ "    album_ID VARCHAR(10) REFERENCES albums(ID),\n"
					+ "    play_count INT DEFAULT 0,\n"
					+ "    release_date DATE NOT NULL,\n"
					+ "    release_country VARCHAR(30),\n"
					+ "    language VARCHAR(30),\n"
					+ "    royalty_rate DECIMAL(10,2) DEFAULT 0,\n"
					+ "    royalty_paid BIT DEFAULT 0,\n"
					+ "    label_ID VARCHAR(10),\n"
					+ "    FOREIGN KEY(album_ID) \n"
					+ "        REFERENCES albums(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY(label_ID) \n"
					+ "        REFERENCES labels(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE playCountHistory(\n"
					+ "    song_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month>0 AND month<13),\n"
					+ "    year INT,\n"
					+ "    play_count INT DEFAULT 0,\n"
					+ "    PRIMARY KEY (song_ID, month, year),\n"
					+ "    FOREIGN KEY (song_ID)\n"
					+ "        REFERENCES songs(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE royaltyPaymentHistory(\n"
					+ "    ID INT PRIMARY KEY AUTO_INCREMENT,\n"
					+ "    label_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month>0 AND month<13),\n"
					+ "    year INT,\n"
					+ "    amount DECIMAL(10,2) NOT NULL,\n"
					+ "    FOREIGN KEY (label_ID)\n"
					+ "        REFERENCES labels(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE artistPaymentHistory(\n"
					+ "    ID INT PRIMARY KEY AUTO_INCREMENT,\n"
					+ "    artist_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month>0 AND month<13),\n"
					+ "    year INT,\n"
					+ "    amount DECIMAL(10,2) NOT NULL,\n"
					+ "    FOREIGN KEY (artist_ID)\n"
					+ "        REFERENCES artists(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE hosts (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    first_name VARCHAR(50) NOT NULL,\n"
					+ "    last_name VARCHAR(50) NOT NULL,\n"
					+ "    phone VARCHAR(30),\n"
					+ "    email VARCHAR(50),\n"
					+ "    city VARCHAR(50)\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE podcasts (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    name VARCHAR(100),\n"
					+ "    host_ID VARCHAR(10),\n"
					+ "    language VARCHAR(30),\n"
					+ "    country VARCHAR(30),\n"
					+ "    ep_count INT DEFAULT 0,\n"
					+ "    ep_fee DECIMAL DEFAULT 0,\n"
					+ "    total_subs INT DEFAULT 0,\n"
					+ "    avg_rating DECIMAL(10,1) DEFAULT 0,\n"
					+ "    total_ratings INT DEFAULT 0,\n"
					+ "    FOREIGN KEY(host_ID) \n"
					+ "        REFERENCES hosts(ID)\n"
					+ "        ON DELETE SET NULL\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE episodes (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    podcast_ID VARCHAR(10),\n"
					+ "    ep_num INT,\n"
					+ "    title VARCHAR(100) NOT NULL,\n"
					+ "    duration INT,\n"
					+ "    release_date DATE,\n"
					+ "    listen_count INT DEFAULT 0,\n"
					+ "    ad_count INT DEFAULT 0,\n"
					+ "    host_paid BIT DEFAULT 0,\n"
					+ "    FOREIGN KEY(podcast_ID)\n"
					+ "        REFERENCES podcasts(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE sponsors (\n"
					+ "    ID INT PRIMARY KEY,\n"
					+ "    name VARCHAR(50) NOT NULL\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE guests (\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    name VARCHAR(50) NOT NULL\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE listeningCountHistory(\n"
					+ "    ep_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month > 0 AND month < 13),\n"
					+ "    year INT,\n"
					+ "    play_count INT DEFAULT 0,\n"
					+ "    PRIMARY KEY (ep_ID, month, year),\n"
					+ "    FOREIGN KEY (ep_ID)\n"
					+ "        REFERENCES episodes(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE ratingHistory(\n"
					+ "    podcast_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month > 0 AND month < 13),\n"
					+ "    year INT,\n"
					+ "    avg_rating DECIMAL(10,1) DEFAULT 0,\n"
					+ "    PRIMARY KEY(podcast_ID, month, year),\n"
					+ "    FOREIGN KEY(podcast_ID)\n"
					+ "        REFERENCES podcasts(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE hostPaymentHistory(\n"
					+ "    ID INT PRIMARY KEY AUTO_INCREMENT,\n"
					+ "    host_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month>0 AND month<13),\n"
					+ "    year INT,\n"
					+ "    amount DECIMAL(10,2) NOT NULL,\n"
					+ "    FOREIGN KEY (host_ID)\n"
					+ "        REFERENCES hosts(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE users(\n"
					+ "    ID VARCHAR(10) PRIMARY KEY,\n"
					+ "    first_name VARCHAR(50) NOT NULL,\n"
					+ "    last_name VARCHAR(50) NOT NULL,\n"
					+ "    phone VARCHAR(30),\n"
					+ "    email VARCHAR(50) NOT NULL,\n"
					+ "    registration_date DATE,\n"
					+ "    sub_status CHAR CHECK (sub_status='S' OR sub_status='U'),\n"
					+ "    sub_fee DECIMAL(10,2) DEFAULT 10\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE userPaymentHistory(\n"
					+ "    ID INT PRIMARY KEY AUTO_INCREMENT,\n"
					+ "    user_ID VARCHAR(10),\n"
					+ "    month INT CHECK (month>0 AND month<13),\n"
					+ "    year INT,\n"
					+ "    amount DECIMAL(10,2) NOT NULL,\n"
					+ "    FOREIGN KEY (user_ID)\n"
					+ "        REFERENCES users(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE artistsIn(\n"
					+ "    song_ID VARCHAR(10),\n"
					+ "    artist_ID VARCHAR(10),\n"
					+ "    role VARCHAR(15) CHECK (role='main' OR role='collaborator'),\n"
					+ "    PRIMARY KEY (song_ID, artist_ID),\n"
					+ "    FOREIGN KEY (song_ID)\n"
					+ "        REFERENCES songs(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "        FOREIGN KEY (artist_ID)\n"
					+ "        REFERENCES artists(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE albumBy(\n"
					+ "    artist_ID VARCHAR(10),\n"
					+ "    album_ID VARCHAR(10),\n"
					+ "    PRIMARY KEY (artist_ID, album_ID),\n"
					+ "    FOREIGN KEY (artist_ID)\n"
					+ "        REFERENCES artists(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (album_ID)\n"
					+ "        REFERENCES albums(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE songsInAlbum(\n"
					+ "    song_ID VARCHAR(10),\n"
					+ "    album_ID VARCHAR(10),\n"
					+ "    track_num INT,\n"
					+ "    PRIMARY KEY (song_ID, album_ID),\n"
					+ "    FOREIGN KEY (song_ID)\n"
					+ "        REFERENCES songs(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (album_ID)\n"
					+ "        REFERENCES albums(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE sponsoredBy(\n"
					+ "    sponsor_ID INT,\n"
					+ "    podcast_ID VARCHAR(10),\n"
					+ "    PRIMARY KEY (sponsor_ID, podcast_ID),\n"
					+ "    FOREIGN KEY (sponsor_ID)\n"
					+ "        REFERENCES sponsors(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (podcast_ID)\n"
					+ "        REFERENCES podcasts(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE episodeGuests(\n"
					+ "    ep_ID VARCHAR(10),\n"
					+ "    guest_ID VARCHAR(10), \n"
					+ "    PRIMARY KEY (ep_ID, guest_ID),\n"
					+ "    FOREIGN KEY (ep_ID)\n"
					+ "        REFERENCES episodes(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (guest_ID)\n"
					+ "        REFERENCES guests(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE artistSubscriptions(\n"
					+ "    user_ID VARCHAR(10),\n"
					+ "    artist_ID VARCHAR(10),\n"
					+ "    PRIMARY KEY (user_ID, artist_ID),\n"
					+ "    FOREIGN KEY (user_ID)\n"
					+ "        REFERENCES users(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (artist_ID)\n"
					+ "        REFERENCES artists(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE podcastSubscriptions(\n"
					+ "    user_ID VARCHAR(10),\n"
					+ "    podcast_ID VARCHAR(10),\n"
					+ "    PRIMARY KEY (user_ID, podcast_ID),\n"
					+ "    FOREIGN KEY (user_ID)\n"
					+ "        REFERENCES users(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (podcast_ID)\n"
					+ "        REFERENCES podcasts(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE songGenre(\n"
					+ "    song_ID VARCHAR(10),\n"
					+ "    genre_name VARCHAR(30),\n"
					+ "    PRIMARY KEY (song_ID, genre_name),\n"
					+ "    FOREIGN KEY (song_ID)\n"
					+ "        REFERENCES songs(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (genre_name)\n"
					+ "        REFERENCES genres(name)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
			statement.executeUpdate("CREATE TABLE podcastGenre(\n"
					+ "    podcast_ID VARCHAR(10),\n"
					+ "    genre_name VARCHAR(30),\n"
					+ "    PRIMARY KEY (podcast_ID, genre_name),\n"
					+ "    FOREIGN KEY (podcast_ID)\n"
					+ "        REFERENCES podcasts(ID)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE,\n"
					+ "    FOREIGN KEY (genre_name)\n"
					+ "        REFERENCES genres(name)\n"
					+ "        ON DELETE CASCADE\n"
					+ "        ON UPDATE CASCADE\n"
					+ ")");
		} catch (SQLException e) {
			System.out.println("Table creation failed.  Check that schema is empty.\n");
		}
	}

}