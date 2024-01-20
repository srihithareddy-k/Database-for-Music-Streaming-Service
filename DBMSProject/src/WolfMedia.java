import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class WolfMedia{
	public static void main(String args[]) {
		Connection connection = null;
		String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
		String user = "username";
		String pswd = "password";
		try {
			connection = DriverManager.getConnection(url, user, pswd);
		} catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("Successfully connected to DB");
	}

	// Strings for designing user menu
	private static final String CMD_MAIN = "MAIN";
	private static final String CMD_INFORMATION_PROCESSING = "INFORMATION PROCESSING";
	private static final String CMD_METADATA_RECORDS = "MAINTAINING METADATA AND RECORDS";
	private static final String CMD_PAYMENTS = "MAINTAINING PAYMENTS";
	private static final String CMD_REPORTS = "REPORTS";
	private static final String CMD_QUIT = "QUIT";

	private static final String CMD_SONG_ADD = "ADD SONG";
	private static final String CMD_SONG_UPDATE = "UPDATE SONG";
	private static final String CMD_SONG_DELETE = "DELETE SONG";

	private static final String CMD_ARTIST_ADD = "ADD ARTIST";
	private static final String CMD_ARTIST_UPDATE = "UPDATE ARTIST";
	private static final String CMD_ARTIST_DELETE = "DELETE ARTIST";

	private static final String CMD_PODCAST_HOST_ADD = "ADD PODCAST HOST";
	private static final String CMD_PODCAST_HOST_UPDATE = "UPDATE PODCAST HOST";
	private static final String CMD_PODCAST_HOST_DELETE = "DELETE PODCAST HOST";

	private static final String CMD_PODCAST_EPISODE_ADD = "ADD PODCAST EPISODE";
	private static final String CMD_PODCAST_EPISODE_UPDATE = "UPDATE PODCAST EPISODE";
	private static final String CMD_PODCAST_EPISODE_DELETE = "DELETE PODCAST EPISODE";

	private static final String CMD_SONG_TO_ALBUM_ASSIGN = "ASSIGN SONG TO ALBUM";
	private static final String CMD_ARTIST_TO_ALBUM_ASSIGN = "ASSIGN ARTIST TO ALBUM";
	private static final String CMD_ARTIST_TO_RECORD_LABEL_ASSIGN = "ASSIGN ARTIST TO RECORD LABEL";
	private static final String CMD_PODCAST_EPISODE_TO_PODCAST_ASSIGN = "ASSIGN PODCAST EPISODE TO PODCAST";
	private static final String CMD_PODCAST_HOST_TO_PODCAST_ASSIGN = "ASSIGN PODCAST HOST TO PODCAST";

	private static final String CMD_SONG_PLAY_COUNT_UPDATE = "UPDATE PLAY COUNT OF SONG";
    private static final String CMD_ARTIST_MONTHLY_LISTENERS_UPDATE = "UPDATE MONTHLY LISTENERS OF ARTIST";
	private static final String CMD_PODCAST_SUB_COUNT_AND_RATINGS_UPDATE = "UPDATE SUBSCRIBER COUNT AND RATINGS OF PODCAST ";
	private static final String CMD_PODCAST_EPISODE_LISTENING_COUNT_UPDATE = "UPDATE LISTENING COUNT OF PODCAST EPISODE";
	private static final String CMD_SONG_GIVEN_ARTIST_GET = "GET SONG GIVEN ARTIST";
	private static final String CMD_SONG_GIVEN_ALBUM_GET = "GET SONG GIVEN ALBUM";
	private static final String CMD_PODCAST_EPISODE_GIVEN_PODCAST_GET = "GET PODCAST EPISODE GIVEN PODCAST";

	private static final String CMD_ARTIST_ROYALTY_PAYMENT = "ROYALTY PAYMENT TO ARTIST";
	private static final String CMD_RECORD_LABEL_ROYALTY_PAYMENT = "ROYALTY PAYMENT TO RECORD LABEL";
	private static final String CMD_PODCAST_HOST_PAYMENT = "PAYMENT TO PODCAST HOST";
	private static final String CMD_RECEIVE_SUBSCRIBER_PAYMENT = "PAYMENT FROM SUBSCRIBER";

	private static final String CMD_SONG_MONTHLY_PLAY_COUNT_REPORT = "MONTHLY PLAYCOUNT OF SONG";
	private static final String CMD_ALBUM_MONTHLY_PLAY_COUNT_REPORT = "MONTHLY PLAYCOUNT OF ALBUM";
	private static final String CMD_ARTIST_MONTHLY_PLAY_COUNT_REPORT = "MONTHLY PLAYCOUNT OF ARTIST";
	private static final String CMD_HOST_BY_TIME_TOTAL_PAYMENT_REPORT = "TOTAL PAYMENTS TO HOST";
    private static final String CMD_ARTIST_BY_TIME_TOTAL_PAYMENT_REPORT = "TOTAL PAYMENTS TO ARTIST ";
	private static final String CMD_RECORD_LABEL_BY_TIME_TOTAL_PAYMENT_REPORT = "TOTAL PAYMENTS TO RECORD LABELS";
	private static final String CMD_TOTAL_REVENUE_BY_MONTH_BY_YEAR_REPORT = "TOTAL REVENUE OF THE STREAMING SERVICE";
	private static final String CMD_SONGS_GIVEN_ARTIST_REPORT = "SONGS BY AN ARTIST";
	private static final String CMD_SONGS_GIVEN_ALBUM_REPORT = "SONGS IN AN ALBUM";
	private static final String CMD_PODCAST_EPISODES_GIVEN_PODCAST_REPORT = "PODCAST EPISODES IN A PODCAST";



    private static Scanner scanner;
	private static String currentMenu;
	private static Statement statement;
	private static ResultSet result;

	// Function used to print commands for each menu
	public static void printCommands(String menu) {
		System.out.println(menu);
		System.out.println("Available Commands:");
		switch (menu) {
		case CMD_MAIN:
			System.out.println("1 - " + CMD_INFORMATION_PROCESSING);
			System.out.println("\t-- process information");
			System.out.println("2 - " + CMD_METADATA_RECORDS);
			System.out.println("\t-- maintain metadata and records");
			System.out.println("3 - " + CMD_PAYMENTS);
			System.out.println("\t-- manage payments");
			System.out.println("4 - " + CMD_REPORTS);
			System.out.println("\t-- generate reports");
			System.out.println("5 - " + CMD_QUIT);
			System.out.println("\t-- exit the program");
			break;
		case CMD_INFORMATION_PROCESSING:
			System.out.println("1 - " + CMD_SONG_ADD);
			System.out.println("\t-- add a new song");
			System.out.println("2 - " + CMD_SONG_UPDATE);
			System.out.println("\t-- update a song");
			System.out.println("3 - " + CMD_SONG_DELETE);
			System.out.println("\t-- delete a song");
			System.out.println("4 - " + CMD_ARTIST_ADD);
			System.out.println("\t-- add a new artist");
			System.out.println("5 - " + CMD_ARTIST_UPDATE);
			System.out.println("\t-- update an artist");
			System.out.println("6 - " + CMD_ARTIST_DELETE);
			System.out.println("\t-- delete an artist");
			System.out.println("7 - " + CMD_PODCAST_HOST_ADD);
			System.out.println("\t-- add a new podcast host");
			System.out.println("8 - " + CMD_PODCAST_HOST_UPDATE);
			System.out.println("\t-- update a podcast host");
			System.out.println("9 - " + CMD_PODCAST_HOST_DELETE);
			System.out.println("\t-- delete a podcast host");
            System.out.println("10 - " + CMD_PODCAST_EPISODE_ADD);
			System.out.println("\t-- add a new podcast episode");
			System.out.println("11 - " + CMD_PODCAST_EPISODE_UPDATE);
			System.out.println("\t-- update a podcast episode");
			System.out.println("12 - " + CMD_PODCAST_EPISODE_DELETE);
			System.out.println("\t-- delete a podcast episode");
			System.out.println("13 - " + CMD_SONG_TO_ALBUM_ASSIGN);
			System.out.println("\t-- assign songs to albums");
			System.out.println("14 - " + CMD_ARTIST_TO_ALBUM_ASSIGN);
			System.out.println("\t-- assign artists to albums");
			System.out.println("15 - " + CMD_ARTIST_TO_RECORD_LABEL_ASSIGN);
			System.out.println("\t-- assign artists to record labels");
			System.out.println("16 - " + CMD_PODCAST_EPISODE_TO_PODCAST_ASSIGN);
			System.out.println("\t-- assign podcast episode to podcast");
			System.out.println("17 - " + CMD_PODCAST_HOST_TO_PODCAST_ASSIGN);
			System.out.println("\t-- assign podcast host to podcast");
			System.out.println("18 - " + CMD_MAIN);
			System.out.println("\t-- go back to main menu");
			System.out.println("19 - " + CMD_QUIT);
			System.out.println("\t-- exit the program");
			break;
        case CMD_METADATA_RECORDS:
			System.out.println("1 - " + CMD_SONG_PLAY_COUNT_UPDATE);
			System.out.println("\t-- update playcount for songs");
			System.out.println("2 - " + CMD_ARTIST_MONTHLY_LISTENERS_UPDATE);
			System.out.println("\t-- update monthly listeners for artists");
			System.out.println("3 - " + CMD_PODCAST_SUB_COUNT_AND_RATINGS_UPDATE);
			System.out.println("\t-- update total count of subscribers and ratings for podcast");
			System.out.println("4 - " + CMD_PODCAST_EPISODE_LISTENING_COUNT_UPDATE);
			System.out.println("\t-- update listening count for podcast episodes");
			System.out.println("5 - " + CMD_SONG_GIVEN_ARTIST_GET);
			System.out.println("\t-- find songs given artist");
			System.out.println("6 - " + CMD_SONG_GIVEN_ALBUM_GET);
			System.out.println("\t-- find songs given album");
			System.out.println("7 - " + CMD_PODCAST_EPISODE_GIVEN_PODCAST_GET);
			System.out.println("\t-- find podcast episodes given podcast");
			System.out.println("8 - " + CMD_MAIN);
			System.out.println("\t-- go back to main menu");
			System.out.println("9 - " + CMD_QUIT);
			System.out.println("\t-- exit the program");
			break;
		case CMD_PAYMENTS:
			System.out.println("1 - " + CMD_ARTIST_ROYALTY_PAYMENT);
			System.out.println("\t-- make royalty payments to artists for a given song");
			System.out.println("2 - " + CMD_RECORD_LABEL_ROYALTY_PAYMENT);
			System.out.println("\t-- make royalty payments to record labels for a given song");
			System.out.println("3 - " + CMD_PODCAST_HOST_PAYMENT);
			System.out.println("\t-- make payment to podcast host");
			System.out.println("4 - " + CMD_RECEIVE_SUBSCRIBER_PAYMENT);
			System.out.println("\t-- receive payment from subscribers");
			System.out.println("5 - " + CMD_MAIN);
			System.out.println("\t-- go back to main menu");
			System.out.println("6 - " + CMD_QUIT);
			System.out.println("\t-- exit the program");
			break;
		case CMD_REPORTS:
			System.out.println("1 - " + CMD_SONG_MONTHLY_PLAY_COUNT_REPORT);
			System.out.println("\t-- report monthly playcount per song");
			System.out.println("2 - " + CMD_ALBUM_MONTHLY_PLAY_COUNT_REPORT);
			System.out.println("\t-- report monthly playcount per album");
			System.out.println("3 - " + CMD_ARTIST_MONTHLY_PLAY_COUNT_REPORT);
			System.out.println("\t-- report monthly playcount per artist");
			System.out.println("4 - " + CMD_HOST_BY_TIME_TOTAL_PAYMENT_REPORT);
			System.out.println("\t-- report total payments made out to host per a given time period");
			System.out.println("5 - " + CMD_ARTIST_BY_TIME_TOTAL_PAYMENT_REPORT);
			System.out.println("\t-- report total payments made out to artist per a given time period");
			System.out.println("6 - " + CMD_RECORD_LABEL_BY_TIME_TOTAL_PAYMENT_REPORT);
			System.out.println("\t-- report total payments made out to record labels per a given time period");
			System.out.println("7 - " + CMD_TOTAL_REVENUE_BY_MONTH_BY_YEAR_REPORT);
			System.out.println("\t-- report total revenue of the streaming service per month, per year");
			System.out.println("8 - " + CMD_SONGS_GIVEN_ARTIST_REPORT);
			System.out.println("\t-- report all songs given an artist");
            System.out.println("9 - " + CMD_SONGS_GIVEN_ALBUM_REPORT);
			System.out.println("\t-- report all songs given an album");
            System.out.println("10 - " + CMD_PODCAST_EPISODES_GIVEN_PODCAST_REPORT);
			System.out.println("\t-- report all podcast episodes given a podcast");
			System.out.println("11 - " + CMD_MAIN);
			System.out.println("\t-- go back to main menu");
			System.out.println("12 - " + CMD_QUIT);
			System.out.println("\t-- exit the program");
		}
	}

	// Establish connection
	public static void connectToDatabase() {
		try {
			// Loading the driver. This creates an instance of the driver
			// and calls the registerDriver method to make MySql(MariaDB) Thin available to
			// clients.
			Class.forName("org.mariadb.jdbc.Driver");
			connection = null;
			statement = null;
			result = null;

			try {
				// Get a connection instance from the first driver in the
				// DriverManager list that recognizes the URL jdbcURL
				connection = DriverManager.getConnection(jdbcURL, user, password);
				// Create a statement instance that will be sending
				// your SQL statements to the DBMS
				statement = connection.createStatement();
			} finally {
				System.out.println("Database connected");
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	// Drop all existing tables before populating tables
	public static void dropAllExistingTables() {
		try {
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			for (String name : tableNames) {
				System.out.println("dropping " + name + "...");
				statement.executeUpdate("DROP TABLE " + name + ";");
			}
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}


	//Add all the code above. The below code is the last part of the project
	public static void main(String[] args) {
		try {
			// Declare local variables
			boolean quit = false;
			String command;

			// Print welcome
			System.out.println("\nWelcome to WolfMedia");
			connectToDatabase();
			generatePreparedStatements();
			dropAllExistingTables();
			generateTables();
			populateSongTable();
            // populate all tables

			printCommands(CMD_MAIN);

			// Watch for user input
			currentMenu = CMD_MAIN;
			scanner = new Scanner(System.in);
			while (quit == false) {
				System.out.print("user > ");
				command = scanner.nextLine();
				switch (currentMenu) {
				case CMD_MAIN:
					// Check user's input (case insensitively)
					switch (command.toUpperCase()) {
					case "1":
						// Tell the user their options in this new menu
						printCommands(CMD_INFORMATION_PROCESSING);
						// Remember what menu we're in
						currentMenu = CMD_INFORMATION_PROCESSING;
						break;
					case "2":
						// Tell the user their options in this new menu
						printCommands(CMD_METADATA_RECORDS);
						// Remember what menu we're in
						currentMenu = CMD_METADATA_RECORDS;
						break;
					case "3":
                        // Tell the user their options in this new menu
						printCommands(CMD_PAYMENTS);
                        // Remember what menu we're in
						currentMenu = CMD_PAYMENTS;
						break;
					case "4":
						// Tell the user their options in this new menu
						printCommands(CMD_REPORTS);
						// Remember what menu we're in
						currentMenu = CMD_REPORTS;
						break;
					case "5":
						quit = true;
						break;
					default:
						// Remind the user about what commands are available
						System.out.println("\nCommand not recognized");
						printCommands(CMD_MAIN);
						break;
					}
					break;
                case CMD_INFORMATION_PROCESSING:
					switch (command.toUpperCase()) {
					case "1":
						userSongAdd();
						break;
					case "2":
						userSongUpdate();
						break;
					case "3":
						userSongDelete();
						break;
					case "4":
						userArtistAdd();
						break;
					case "5":
						userArtistUpdate();
						break;
					case "6":
						userArtistDelete();
						break;
					case "7":
						userPodcastHostAdd();
						break;
					case "8":
						userPodcastHostUpdate();
						break;
					case "9":
						userPodcastHostDelete();
						break;
					case "10":
						userPodcastEpisodeAdd();
						break;
					case "11":
						userPodcastEpisodeUpdate();
						break;
					case "12":
						userPodcastEpisodeDelete();
						break;
					case "13":
						userSongToAlbumAssign();
						break;
					case "14":
						userArtistToAlbumAssign();
						break;
					case "15":
						userArtistToRecordLabelAssign();
						break;
					case "16":
						userPodcastEpisodeToPodcastAssign();
						break;
					case "17":
						userPodcastHostToPodcastAssign();
						break;
					case "18":
						printCommands(CMD_MAIN);
						currentMenu = CMD_MAIN;
						break;
					case "19":
						quit = true;
						break;
					default:
						System.out.println("\nCommand not found");
						printCommands(CMD_INFORMATION_PROCESSING);
						break;
					}
					break;
				case CMD_METADATA_RECORDS:
					switch (command.toUpperCase()) {
					case "1":
						userSongPlayCountUpdate();
						break;
					case "2":
						userArtistMonthlyListenersUpdate();
						break;
					case "3":
						userPodcastTotalSubscribersAndRatingsUpdate();
						break;
					case "4":
						userPodcastEpisodeListeningCountUpdate();
						break;
					case "5":
						userSongGivenArtistGet();
						break;
					case "6":
						userSongGivenAlbumGet();
						break;
					case "7":
						userPodcastEpisodeGivenPodcastGet();
						break;
					case "8":
						printCommands(CMD_MAIN);
						currentMenu = CMD_MAIN;
						break;
					case "9":
						quit = true;
						break;
					default:
						// Remind the user about what commands are available
						System.out.println("\nCommand not recognized");
						printCommands(CMD_METADATA_RECORDS);
						break;
					}
					break;
				case CMD_PAYMENTS:
					switch (command.toUpperCase()) {
					case "1":
						userArtistRoyaltyPayment();
						break;
					case "2":
						userRecordLabelRoyaltyPayment();
						break;
					case "3":
						userPodcastHostPayment();
						break;
					case "4":
						userReceiveSubscriberPayment();
						break;
					case "5":
						printCommands(CMD_MAIN);
						currentMenu = CMD_MAIN;
						break;
					case "6":
						quit = true;
						break;
					default:
						System.out.println("\nCommand not recognized");
						printCommands(CMD_PAYMENTS);
						break;
					}
					break;
				case CMD_REPORTS:
					switch (command.toUpperCase()) {
					case "1":
						userSongMonthlyPlayCountReport();
						break;
					case "2":
						userAlbumMonthlyPlayCountReport();
						break;
					case "3":
						userArtistMonthlyPlayCountReport();
						break;
					case "4":
						userHostByTimeTotalPaymentReport();
						break;
					case "5":
						userArtistByTimeTotalPaymentReport();
						break;
					case "6":
						userRecordLabelByTimeTotalPaymentReport();
						break;
					case "7":
						userTotalRevenueReport();
						break;
					case "8":
						userSongsGivenArtistReport();
						break;
                    case "9":
						userSongsGivenAlbumReport();
						break;  
                    case "10":
						userPodcastEpisodesGivenPodcastReport();
						break; 
					case "11":
						printCommands(CMD_MAIN);
						currentMenu = CMD_MAIN;
						break;
					case "12":
						quit = true;
						break;
					default:
						System.out.println("\nCommand not recognized");
						printCommands(CMD_REPORTS);
						break;
					}
					break;
				default:
					break;
				}
			}
		} catch (Throwable err) {
			err.printStackTrace();
		} finally {
			close(connection);
			close(statement);
			close(result);
		}
	}

	static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (Throwable whatever) {
			}
		}
	}
}


