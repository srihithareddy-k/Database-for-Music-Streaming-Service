public class Informationprocessing {
    public class Main {
        // Prepared Statements pre-declared.
	    // Information Processing
        private static PreparedStatement prepAddSong;
        private static PreparedStatement prepGetSong;
        private static PreparedStatement prepUpdateSongTitle;
        private static PreparedStatement prepUpdateSongAlbum;
        private static PreparedStatement prepUpdateSongPlayCount;
        private static PreparedStatement prepUpdateSongRelDate;
        private static PreparedStatement prepUpdateSongRelCountry;
        private static PreparedStatement prepUpdateSongLang;
        private static PreparedStatement prepUpdateSongRoyRate;
        private static PreparedStatement prepUpdateSongRoyPaid;
        private static PreparedStatement prepUpdateSongLabel;
        private static PreparedStatement prepDeleteSong;
        private static PreparedStatement prepAddArtist;
        private static PreparedStatement prepGetArtist;
        private static PreparedStatement prepUpdateArtistName;
        private static PreparedStatement prepUpdateArtistStatus;
        private static PreparedStatement prepUpdateArtistType;
        private static PreparedStatement prepUpdateArtistCountry;
        private static PreparedStatement prepUpdateArtistGenre;
        private static PreparedStatement prepUpdateArtistListeners;
        private static PreparedStatement prepUpdateArtistLabel;
        private static PreparedStatement prepDeleteArtist;
        private static PreparedStatement prepAddHost;
        private static PreparedStatement prepGetHost;
        private static PreparedStatement prepUpdateHostFName;
        private static PreparedStatement prepUpdateHostLName;
        private static PreparedStatement prepUpdateHostPhone;
        private static PreparedStatement prepUpdateHostEmail;
        private static PreparedStatement prepUpdateHostCity;
        private static PreparedStatement prepDeleteHost;
        private static PreparedStatement prepAddEpisode;
        private static PreparedStatement prepGetEpisode;
        private static PreparedStatement prepUpdateEpisodePodID;
        private static PreparedStatement prepUpdateEpisodeNum;
        private static PreparedStatement prepUpdateEpisodeTitle;
        private static PreparedStatement prepUpdateEpisodeDuration;
        private static PreparedStatement prepUpdateEpisodeRelDate;
        private static PreparedStatement prepUpdateEpisodeListenCount;
        private static PreparedStatement prepUpdateEpisodeAdCount;
        private static PreparedStatement prepDeleteEpisode;
        private static PreparedStatement prepAssignSongToAlbum;
        private static PreparedStatement prepAssignArtistToAlbum;
        private static PreparedStatement prepAssignArtistToRLabel;
        private static PreparedStatement prepAssignPodEpToPod;
        private static PreparedStatement prepAssignHostToPod;
        private static PreparedStatement prepAddEpisodeGuests;
        
        private static PreparedStatement prepGetSongID;
        private static PreparedStatement prepGetAlbumID;
        private static PreparedStatement prepInsertArtistIn;
    
        public static void generatePreparedStatements() {
            try {
                String sql;
                
                // Information Processing
                sql = "INSERT INTO songs (ID, title, album_ID, play_count, release_date, release_country, language, royalty_rate, royalty_paid, label_ID, track_num)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                prepAddSong = connection.prepareStatement(sql);

                sql = "SELECT * FROM songs WHERE ID = ?;";
			    prepGetSong = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET title = ? WHERE ID = ?;";
                prepUpdateSongTitle = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET album_ID = ? WHERE ID = ?;";
                prepUpdateSongAlbum = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET play_count = ? WHERE ID = ?;";
                prepUpdateSongPlayCount = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET release_date = ? WHERE ID = ?;";
                prepUpdateSongRelDate = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET release_country = ? WHERE ID = ?;";
                prepUpdateSongRelCountry = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET language = ? WHERE ID = ?;";
                prepUpdateSongLang = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET royalty_rate = ? WHERE ID = ?;";
                prepUpdateSongRoyRate = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET royalty_paid = ? WHERE ID = ?;";
                prepUpdateSongRoyPaid = connection.prepareStatement(sql);
                
                sql = "UPDATE songs SET label_ID = ? WHERE ID = ?;";
                prepUpdateSongLabel = connection.prepareStatement(sql);
                
                sql = "DELETE FROM songs WHERE ID = ?;";
                prepDeleteSong = connection.prepareStatement(sql);
                
                sql = "INSERT INTO artists (ID, name, status, type, country, primary_genre, monthly_listeners, label_ID, album_ID)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                prepAddArtist = connection.prepareStatement(sql);

                sql = "SELECT * FROM artists WHERE ID = ?;";
			    prepGetArtist = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET name = ? WHERE ID = ?;";
                prepUpdateArtistName = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET status = ? WHERE ID = ?;";
                prepUpdateArtistStatus = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET type = ? WHERE ID = ?;";
                prepUpdateArtistType = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET country = ? WHERE ID = ?;";
                prepUpdateArtistCountry = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET primary_genre = ? WHERE ID = ?;";
                prepUpdateArtistGenre = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET monthly_listeners = ? WHERE ID = ?;";
                prepUpdateArtistListeners = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET label_ID = ? WHERE ID = ?;";
                prepUpdateArtistLabel = connection.prepareStatement(sql);
                
                sql = "DELETE FROM artists WHERE ID = ?;";
                prepDeleteArtist = connection.prepareStatement(sql);
                
                sql = "INSERT INTO hosts (ID, first_name, last_name, phone, email, city)"
                        + "VALUES (?, ?, ?, ?, ?, ?);";
                prepAddHost = connection.prepareStatement(sql);

                sql = "SELECT * FROM hosts WHERE ID = ?;";
			    prepGetHost = connection.prepareStatement(sql);
                
                sql = "UPDATE hosts SET first_name = ? WHERE ID = ?;";
                prepUpdateHostFName = connection.prepareStatement(sql);
                
                sql = "UPDATE hosts SET last_name = ? WHERE ID = ?;";
                prepUpdateHostLName = connection.prepareStatement(sql);
                
                sql = "UPDATE hosts SET phone = ? WHERE ID = ?;";
                prepUpdateHostPhone = connection.prepareStatement(sql);
                
                sql = "UPDATE hosts SET email = ? WHERE ID = ?;";
                prepUpdateHostEmail = connection.prepareStatement(sql);
                
                sql = "UPDATE hosts SET city = ? WHERE ID = ?;";
                prepUpdateHostCity = connection.prepareStatement(sql);
                
                sql = "DELETE FROM hosts WHERE ID = ?;";
                prepDeleteHost = connection.prepareStatement(sql);
                
                sql = "INSERT INTO episodes (ID, podcast_ID, ep_num, title, duration, release_date, listen_count, ad_count)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
                        + "INSERT INTO episodeGuests(ep_ID, guest_ID) VALUES (?, ?);"
                prepAddEpisode = connection.prepareStatement(sql);

                sql = "SELECT * FROM episodes WHERE ID = ?;";
			    prepGetEpisode = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET podcast_ID = ? WHERE ID = ?;";
                prepUpdateEpisodePodID = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET ep_num = ? WHERE ID = ?;";
                prepUpdateEpisodeNum = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET title = ? WHERE ID = ?;";
                prepUpdateEpisodeTitle = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET duration = ? WHERE ID = ?;";
                prepUpdateEpisodeDuration = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET release_date = ? WHERE ID = ?;";
                prepUpdateEpisodeRelDate = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET listen_count = ? WHERE ID = ?;";
                prepUpdateEpisodeListenCount = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET ad_count = ? WHERE ID = ?;";
                prepUpdateEpisodeAdCount = connection.prepareStatement(sql);
                
                sql = "DELETE FROM episodes WHERE ID = ?;";
                prepDeleteEpisode = connection.prepareStatement(sql);
                
                sql = "INSERT INTO songsInAlbum (song_ID, album_ID, track_num)VALUES (?, ?, ?);";
                prepAssignSongToAlbum = connection.prepareStatement(sql);
                
                sql = "INSERT INTO albumBy (artist_ID, album_ID) VALUES (?, ?);";
                prepAssignArtistToAlbum = connection.prepareStatement(sql);
                
                sql = "UPDATE artists SET label_ID = ? WHERE ID = ?;";
                prepAssignArtistToRLabel = connection.prepareStatement(sql);
                
                sql = "UPDATE episodes SET podcast_ID = ? WHERE ID = ?;";
                prepAssignPodEpToPod = connection.prepareStatement(sql);
                
                sql = "UPDATE podcasts SET host_ID = ? WHERE ID = ?;";
                prepAssignHostToPod = connection.prepareStatement(sql);


                sql = "SELECT ID FROM songs WHERE title = ?;";
                prepGetSongID = connection.prepareStatement(sql);
    
                sql = "SELECT ID FROM albums WHERE name = ?;";
                prepGetAlbumID = connection.prepareStatement(sql);
    
                sql = "INSERT INTO artistsIn (song_ID, artist_ID, role) VALUES (?, ?, ?)";
                prepInsertArtistIn = connection.prepareStatement(sql);
    
    
    
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Some useful helper functions for processing resultsets
    
        //Show a row of a song
        private static void printSongRow(ResultSet rs) {
            try {
                String ID = rs.getString("ID");
                String title = rs.getString("title");
                String album_ID = rs.getString("album_ID");
                int play_count = rs.getInt("play_count");
                String release_date = rs.getDate("release_date").toString();
                String release_country = rs.getString("release_country");
                String language = rs.getString("language");
                BigDecimal royalty_rate = rs.getBigDecimal("royalty_rate").toString();
                Boolean royalty_paid = rs.getBoolean("royalty_paid");
                String label_ID = rs.getString("label_ID");
                System.out.println("ID: " + ID + ", title: " + title + ", album ID: " + album_ID + ", play count: " + play_count
                        + ", release date: " + release_date + ", release country: " + release_country + ", language: " + language
                        + ", royalty rate: " + royalty_rate + ", royalty paid: " + royalty_paid + ", label ID: " + label_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        //Show a row of an Artist
        private static void printArtistRow(ResultSet rs) {
            try {
                String ID = rs.getString("ID");
                String name = rs.getString("name");
                String status = rs.getString("status");
                String type = rs.getString("type");
                String country = rs.getString("country");
                String primary_genre = rs.getString("primary_genre");
                int monthly_listeners = rs.getInt("monthly_listeners");
                String label_ID = rs.getString("label_ID");
                System.out.println("ID: " + ID + ", name: " + name + ", status: " + status + ", type: " + type
                        + ", country: " + country + ", primary genre: " + primary_genre + ", monthly listeners: " + monthly_listeners
                        + ", label ID: " + label_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        //Show a row of an podcast host
        private static void printHostRow(ResultSet rs) {
            try {
                String ID = rs.getString("ID");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String city = rs.getString("city");
                System.out.println("ID: " + ID + ", first name: " + first_name + ", last name: " + last_name
                        + ", phone: " + phone + ", email: " + email + ", city: " + city);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        //Show a row of an podcast episode
        private static void printEpisodeRow(ResultSet rs) {
            try {
                String ID = rs.getString("ID");
                String podcast_ID = rs.getString("podcast_ID");
                int ep_num = rs.getInt("ep_num");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String release_date = rs.getDate("release_date").toString();
                int listen_count = rs.getInt("listen_count");
                int ad_count = rs.getInt("ad_count");
                System.out.println("ID: " + ID + ", podcast ID: " + podcast_ID + ", episode number: " + ep_num
                        + ", title: " + title + ", duration: " + duration + ", release date: " + release_date
                        + ", listen count: " + listen_count + ", ad count: " + ad_count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
    
        // Functions used to interact with database
    
        // Add a new song
        public static void addSong(String ID, String title, String album_ID, String play_count, String release_date,
                String release_country, String language, String royalty_rate, String royalty_paid, String label_ID, int track_num) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAddSong.setString(1, ID);
                    prepAddSong.setString(2, title);
                    prepAddSong.setString(3, album_ID);
                    prepAddSong.setInt(4, Integer.parseInt(play_count));
                    prepAddSong.setDate(5, java.sql.Date.valueOf(release_date));
                    prepAddSong.setString(6, release_country);
                    prepAddSong.setString(7, language);
                    prepAddSong.setBigDecimal(8, new BigDecimal(royalty_rate));
                    prepAddSong.setBoolean(9, Boolean.parseBoolean(royalty_paid));
                    prepAddSong.setString(10, label_ID);
                    prepAddSong.setInt(11, track_num);
                    prepAddSong.executeUpdate();
    
                    //Add song to the songsInAlbum table
                    prepAssignSongToAlbum.setString(1, ID);
                    prepAssignSongToAlbum.setString(2, album_ID);
                    prepAssignSongToAlbum.setInt(3, track_num);
    
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
        }
    
        public static void UserSongAdd() {
            String ID;
            String title;
            String album_ID;
            String play_count;
            String release_date;
            String release_country;
            String language;
            String royalty_rate;
            String royalty_paid;
            String label_ID;
            int track_num;
        
            try {
                // Get ID
                System.out.println("Enter the ID of the new song:");
                ID = scanner.nextLine();
                // Get title
                System.out.println("Enter the title of the new song:");
                title = scanner.nextLine();
                // Get album ID
                System.out.println("Enter the album ID of the new song:");
                album_ID = scanner.nextLine();
                // Get play count
                System.out.println("Enter the play count of the new song:");
                play_count = scanner.nextLine();
                // Get release date
                System.out.println("Enter the release date of the new song (yyyy-MM-dd):");
                release_date = scanner.nextLine();
                // Get release country
                System.out.println("Enter the release country of the new song:");
                release_country = scanner.nextLine();
                // Get language
                System.out.println("Enter the language of the new song:");
                language = scanner.nextLine();
                // Get royalty rate
                System.out.println("Enter the royalty rate of the new song:");
                royalty_rate = scanner.nextLine();
                // Get royalty paid
                System.out.println("Enter the royalty paid status of the new song (true/false):");
                royalty_paid = scanner.nextLine();
                // Get label ID
                System.out.println("Enter the label ID of the new song:");
                label_ID = scanner.nextLine();
                // Get track number
                System.out.println("Enter the track number of the new song:");
                track_num = Integer.parseInt(scanner.nextLine());
        
                // Call function that interacts with the database
                addSong(ID, title, album_ID, play_count, release_date, release_country, language, royalty_rate, royalty_paid, label_ID, track_num);
                
                // Ask user if they want to add artists for the song
                System.out.println("Do you want to add artists for the song? (yes/no):");
                String addArtists = scanner.nextLine();
                if (addArtists.equalsIgnoreCase("yes")) {
                    System.out.println("Enter the number of artists you want to add:");
                    int numArtists = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numArtists; i++) {
                        System.out.println("Enter the ID of artist " + (i + 1) + ":");
                        String artistID = scanner.nextLine();
                        System.out.println("Enter the role of artist " + (i + 1) + " (main/collaborator):");
                        String artistRole = scanner.nextLine();
                        // Insert artist into the artistsIn table
                        prepInsertArtistIn.setString(1, ID);
                        prepInsertArtistIn.setString(2, artistID);
                        prepInsertArtistIn.setString(3, artistRole);
                        prepInsertArtistIn.executeUpdate();
                    }
                }
                
                System.out.println("A new song has been added successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
    
        // Get song info
        public static void getSong(String ID) {
            try {
                prepGetSong.setString(1, ID);
                ResultSet rs = prepGetSong.executeQuery();
                if (rs.next()) {
                    printSongRow(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        // Update the value of a field in the songs table
        public static void updateSong(String ID, String attributeToChange, String newValue) {
            try {
                connection.setAutoCommit(false);
                try {
                    switch (attributeToChange.toUpperCase()) {
        
                        case "TITLE":
                            prepUpdateSongTitle.setString(1, newValue);
                            prepUpdateSongTitle.setString(2, ID);
                            prepUpdateSongTitle.executeUpdate();
                            break;
                        case "ALBUM_ID":
                            prepUpdateSongAlbum.setString(1, newValue);
                            prepUpdateSongAlbum.setString(2, ID);
                            prepUpdateSongAlbum.executeUpdate();
                            break;
                        case "PLAY_COUNT":
                            prepUpdateSongPlayCount.setInt(1, Integer.parseInt(newValue));
                            prepUpdateSongPlayCount.setString(2, ID);
                            prepUpdateSongPlayCount.executeUpdate();
                            break;
                        case "RELEASE_DATE":
                            prepUpdateSongRelDate.setDate(1, java.sql.Date.valueOf(newValue));
                            prepUpdateSongRelDate.setString(2, ID);
                            prepUpdateSongRelDate.executeUpdate();
                            break;
                        case "RELEASE_COUNTRY":
                            prepUpdateSongRelCountry.setString(1, newValue);
                            prepUpdateSongRelCountry.setString(2, ID);
                            prepUpdateSongRelCountry.executeUpdate();
                            break;
                        case "LANGUAGE":
                            prepUpdateSongLang.setString(1, newValue);
                            prepUpdateSongLang.setString(2, ID);
                            prepUpdateSongLang.executeUpdate();
                            break;
                        case "ROYALTY_RATE":
                            prepUpdateSongRoyRate.setBigDecimal(1, new BigDecimal(newValue));
                            prepUpdateSongRoyRate.setString(2, ID);
                            prepUpdateSongRoyRate.executeUpdate();
                            break;
                        case "ROYALTY_PAID":
                            prepUpdateSongRoyPaid.setBoolean(1, Boolean.parseBoolean(newValue));
                            prepUpdateSongRoyPaid.setString(2, ID);
                            prepUpdateSongRoyPaid.executeUpdate();
                            break;
                        case "LABEL_ID":
                            prepUpdateSongLabel.setString(1, newValue);
                            prepUpdateSongLabel.setString(2, ID);
                            prepUpdateSongLabel.executeUpdate();
                            break;
                        default:
                            System.out.println("Cannot update the field " + attributeToChange + " for song with ID: " + ID + ".");
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
        }
    
        public static void userSongUpdate() {
    
            String songID;
            String attributeToChange;
            String newValue;
            try {
                // Get song ID from user
                System.out.println("Enter the ID of the song you want to update:");
                songID = scanner.nextLine();
        
                // Get attribute to change from user
                System.out.println("Enter the attribute you wish to update [TITLE, ALBUM_ID, PLAY_COUNT, RELEASE_DATE, RELEASE_COUNTRY, LANGUAGE, ROYALTY_RATE, ROYALTY_PAID, LABEL_ID]:");
                attributeToChange = scanner.nextLine();
    
                // Print the song information you plan to update
                System.out.println("\nThe staff information you have chosen:\n");
                getSong(songID);
        
                // Get new value from user
                System.out.println("Enter the new value:");
                newValue = scanner.nextLine();
                // Call method that interacts with the Database
                updateSong(songID, attributeToChange, newValue);
                System.out.println("The song is updated successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
    
        // delete a song
        public static void deleteSong(String ID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepDeleteSong.setString(1, ID);
                    prepDeleteSong.executeUpdate();
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
        }
    
        public static void userSongDelete() {
            // Declare local variables
            String songID;
            try {
                // Get staff id
                System.out.println("\nEnter the song id of the song you want to delete:\n");
                songID = scanner.nextLine();
                // Print the song information you plan to delete
                System.out.println("\nThe song information you have chosen:\n");
                getSong(songID);
                // Call method that interacts with the Database
                deleteSong(songID);
                System.out.println("The song is deleted successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
    
           // Add a new artist
        public static void addArtist(String ID, String name, String status, String type, String country, String primary_genre, String monthly_listeners, String label_ID, String album_ID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAddArtist.setString(1, ID);
                    prepAddArtist.setString(2, name);
                    prepAddArtist.setString(3, status);
                    prepAddArtist.setString(4, type);
                    prepAddArtist.setString(5, country);
                    prepAddArtist.setString(6, primary_genre);
                    prepAddArtist.setInt(7, Integer.parseInt(monthly_listeners));
                    prepAddArtist.setString(8, label_ID);
                    prepAddArtist.setString(9, album_ID);
                    prepAddArtist.executeUpdate();

                    // Update the artist in the albumBy table
                    prepAssignArtistToAlbum.setString(1, ID);
                    prepAssignArtistToAlbum.setString(2, album_ID); // Provide the album_ID for which the artist needs to be updated
                    prepAssignArtistToAlbum.executeUpdate();
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
        }

        public static void userArtistAdd() {
            String ID;
            String name;
            String status;
            String type;
            String country;
            String primary_genre;
            String monthly_listeners;
            String label_ID;
            String album_ID;
            
            try {
                // Get ID
                System.out.println("Enter the ID of the new artist:");
                ID = scanner.nextLine();
                // Get name
                System.out.println("Enter the name of the new artist:");
                name = scanner.nextLine();
                // Get status
                System.out.println("Enter the status of the new artist:");
                status = scanner.nextLine();
                // Get type
                System.out.println("Enter the type of the new artist:");
                type = scanner.nextLine();
                // Get country
                System.out.println("Enter the country of the new artist:");
                country = scanner.nextLine();
                // Get primary genre
                System.out.println("Enter the primary genre of the new artist:");
                primary_genre = scanner.nextLine();
                // Get monthly listeners
                System.out.println("Enter the monthly listeners of the new artist:");
                monthly_listeners = scanner.nextLine();
                // Get label ID
                System.out.println("Enter the label ID of the new artist:");
                label_ID = scanner.nextLine();
                // Get album ID
                System.out.println("Enter the album ID for which the artist needs to be updated:");
                album_ID = scanner.nextLine();
                
                // Call function that interacts with the database
                addArtist(ID, name, status, type, country, primary_genre, monthly_listeners, label_ID, album_ID);
                
                System.out.println("A new artist has been added successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
    
        //Get artist info
        public static void getArtist(String ID) {
            try {
                prepGetArtist.setString(1, ID);
                ResultSet rs = prepGetArtist.executeQuery();
                if (rs.next()) {
                    printArtistRow(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        //Update the value of a field in the artist table
        public static void updateArtist(String ID, String attributeToChange, String newValue) {
            try {
                connection.setAutoCommit(false);
                try {
                    switch (attributeToChange.toUpperCase()) {
                        case "NAME":
                            prepUpdateArtistName.setString(1, newValue);
                            prepUpdateArtistName.setString(2, ID);
                            prepUpdateArtistName.executeUpdate();
                            break;
                        case "STATUS":
                            prepUpdateArtistStatus.setString(1, newValue);
                            prepUpdateArtistStatus.setString(2, ID);
                            prepUpdateArtistStatus.executeUpdate();
                            break;
                        case "TYPE":
                            prepUpdateArtistType.setString(1, newValue);
                            prepUpdateArtistType.setString(2, ID);
                            prepUpdateArtistType.executeUpdate();
                            break;
                        case "COUNTRY":
                            prepUpdateArtistCountry.setString(1, newValue);
                            prepUpdateArtistCountry.setString(2, ID);
                            prepUpdateArtistCountry.executeUpdate();
                            break;
                        case "PRIMARY_GENRE":
                            prepUpdateArtistGenre.setString(1, newValue);
                            prepUpdateArtistGenre.setString(2, ID);
                            prepUpdateArtistGenre.executeUpdate();
                            break;
                        case "MONTHLY_LISTENERS":
                            prepUpdateArtistListeners.setInt(1, Integer.parseInt(newValue));
                            prepUpdateArtistListeners.setString(2, ID);
                            prepUpdateArtistListeners.executeUpdate();
                            break;
                        case "LABEL_ID":
                            prepUpdateArtistLabel.setString(1, newValue);
                            prepUpdateArtistLabel.setString(2, ID);
                            prepUpdateArtistLabel.executeUpdate();
                            break;
                        default:
                            System.out.println("Cannot update the field " + attributeToChange + " for artist with ID: " + ID + ".");
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
        }

        public static void UserArtistUpdate() {
            String artistID;
            String attributeToChange;
            String newValue;
            try {
                // Get artist ID
                System.out.println("\nEnter the artist ID of the artist you want to update:\n");
                artistID = scanner.nextLine();
        
                // Print the artist information you plan to update
                System.out.println("\nThe artist information you have chosen:\n");
                getArtist(artistID);
        
                // Get attribute to change
                // Print all possible attributes that can be changed
                System.out.println("\nPlease select the attribute you wish to update [NAME, STATUS, TYPE, COUNTRY, PRIMARY_GENRE, MONTHLY_LISTENERS, LABEL_ID]:\n");
                attributeToChange = scanner.nextLine();
        
                // Get new value
                System.out.println("\nEnter the new value:\n");
                newValue = scanner.nextLine();
        
                // Call method that interacts with the database
                updateArtist(artistID, attributeToChange, newValue);
                System.out.println("The artist details have been updated successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
    
        //Delete an artist
        public static void deleteArtist(String ID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepDeleteArtist.setString(1, ID);
                    prepDeleteArtist.executeUpdate();
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
        }

        public static void userArtistDelete() {
            String artistID;
            try {
                // Get artistID
                System.out.println("\nEnter the ID of the artist you want to delete:\n");
                artistID = scanner.nextLine();
                // Print the artist information you plan to delete
                System.out.println("\nThe artist information you have chosen:\n");
                getArtist(artistID);
                // Call method that interacts with the Database
                deleteArtist(artistID);
                System.out.println("The artist is deleted successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
    
        //Add a new podcast host
        public static void addHost(String ID, String first_name, String last_name, String phone, String email, String city) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAddHost.setString(1, ID);
                    prepAddHost.setString(2, first_name);
                    prepAddHost.setString(3, last_name);
                    prepAddHost.setString(4, phone);
                    prepAddHost.setString(5, email);
                    prepAddHost.setString(6, city);
                    prepAddHost.executeUpdate();
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
        }

        public static void userHostAdd() {

            String hostID;
            String firstName;
            String lastName;
            String phone;
            String email;
            String city;
            try {
    
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter host ID: ");
                hostID = scanner.nextLine();
                System.out.print("Enter first name: ");
                firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                lastName = scanner.nextLine();
                System.out.print("Enter phone number: ");
                phone = scanner.nextLine();
                System.out.print("Enter email: ");
                email = scanner.nextLine();
                System.out.print("Enter city: ");
                city = scanner.nextLine();
        
                // Call addHost function
                addHost(hostID, firstName, lastName, phone, email, city);
                System.out.println("New host is added successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
        //Get podcast host info
        public static void getHost(String ID) {
            try {
                prepGetHost.setString(1, ID);
                ResultSet rs = prepGetHost.executeQuery();
                if (rs.next()) {
                    printHostRow(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Update the value of a field in the podcast host table
        public static void updateHost(String ID, String attributeToChange, String newValue) {
            try {
                connection.setAutoCommit(false);
                try {
                    switch (attributeToChange.toUpperCase()) {
                        case "FIRST_NAME":
                            prepUpdateHostFName.setString(1, newValue);
                            prepUpdateHostFName.setString(2, ID);
                            prepUpdateHostFName.executeUpdate();
                            break;
                        case "LAST_NAME":
                            prepUpdateHostLName.setString(1, newValue);
                            prepUpdateHostLName.setString(2, ID);
                            prepUpdateHostLName.executeUpdate();
                            break;
                        case "PHONE":
                            prepUpdateHostPhone.setString(1, newValue);
                            prepUpdateHostPhone.setString(2, ID);
                            prepUpdateHostPhone.executeUpdate();
                            break;
                        case "EMAIL":
                            prepUpdateHostEmail.setString(1, newValue);
                            prepUpdateHostEmail.setString(2, ID);
                            prepUpdateHostEmail.executeUpdate();
                            break;
                        case "CITY":
                            prepUpdateHostCity.setString(1, newValue);
                            prepUpdateHostCity.setString(2, ID);
                            prepUpdateHostCity.executeUpdate();
                            break;
                        default:
                            System.out.println("Cannot update the field " + attributeToChange + " for host with ID: " + ID + ".");
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
        }

        public static void userHostUpdate() {
            String hostID;
            String attrToChange;
            String newValue;
            try {
                // Get host ID
                System.out.println("Enter the host ID of the host you want to update:");
                hostID = scanner.nextLine();
        
                // Print the host information you plan to update
                System.out.println("\nThe host information you have chosen:");
                getHost(hostID); 
        
                // Get attribute to change
                // Print all possible attributes that can be changed
                System.out.println("\nPlease select the attribute you wish to update [FIRST_NAME, LAST_NAME, PHONE, EMAIL, CITY]:");
                attrToChange = scanner.nextLine();
        
                // Get value to change
                System.out.println("Enter the new value:");
                newValue = scanner.nextLine();
        
                updateHost(hostID, attrToChange, newValue);
                System.out.println("The host is updated successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
    
        //Delete a podcast host
        public static void deleteHost(String ID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepDeleteHost.setString(1, ID);
                    prepDeleteHost.executeUpdate();
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
        }

        public static void userHostDelete() {
            // Declare local variables
            String hostID;
            try {
                // Get hostID
                System.out.println("\nEnter the host ID of the host you want to delete:\n");
                hostID = scanner.nextLine();
                // Print the host information you plan to delete
                System.out.println("\nThe host information you have chosen:\n");
                getHost(hostID);
                // Call method that interacts with the Database
                deleteHost(hostID);
                System.out.println("The host is deleted successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        

        // Add a new episode
        public static void addEpisode(String ID, String podcast_ID, String ep_num, String title, String duration,
                String release_date, String listen_count, String ad_count, String guest_ID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAddEpisode.setString(1, ID);
                    prepAddEpisode.setString(2, podcast_ID);
                    prepAddEpisode.setInt(3, Integer.parseInt(ep_num));
                    prepAddEpisode.setString(4, title);
                    prepAddEpisode.setInt(5, Integer.parseInt(duration));
                    prepAddEpisode.setDate(6, java.sql.Date.valueOf(release_date));
                    prepAddEpisode.setInt(7, Integer.parseInt(listen_count));
                    prepAddEpisode.setInt(8, Integer.parseInt(ad_count));
                    prepAddEpisode.setString(9, ID); // Set ep_ID
                    prepAddEpisode.setString(10, guest_ID); // Set guest_ID
                    prepAddEpisode.executeUpdate();

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
        }

        public static void userAddEpisode() {
            String episodeID;
            String podcastID;
            String episodeNumber;
            String title;
            String duration;
            String releaseDate;
            String listenCount;
            String adCount;
            String guestID;
            try {
                // Get episode ID for the new episode
                System.out.println("\nEnter the episode ID of the new episode:\n");
                episodeID = scanner.nextLine();
                // Get podcast ID
                System.out.println("\nEnter the podcast ID of the new episode:\n");
                podcastID = scanner.nextLine();
                // Get episode number
                System.out.println("\nEnter the episode number of the new episode:\n");
                episodeNumber = scanner.nextLine();
                // Get title
                System.out.println("\nEnter the title of the new episode:\n");
                title = scanner.nextLine();
                // Get duration
                System.out.println("\nEnter the duration of the new episode (in minutes):\n");
                duration = scanner.nextLine();
                // Get release date
                System.out.println("\nEnter the release date of the new episode (YYYY-MM-DD):\n");
                releaseDate = scanner.nextLine();
                // Get listen count
                System.out.println("\nEnter the listen count of the new episode:\n");
                listenCount = scanner.nextLine();
                // Get ad count
                System.out.println("\nEnter the ad count of the new episode:\n");
                adCount = scanner.nextLine();
                // Get guest ID
                System.out.println("\nEnter the guest ID for the new episode:\n");
                guestID = scanner.nextLine();
        
                // Call the method to add the new episode
                addEpisode(episodeID, podcastID, episodeNumber, title, duration, releaseDate, listenCount, adCount, guestID);
                System.out.println("A new episode is added successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
        
        //Get podcast episode info
        public static void getEpisode(String ID) {
            try {
                prepGetEpisode.setString(1, ID);
                ResultSet rs = prepGetEpisode.executeQuery();
                if (rs.next()) {
                    printEpisodeRow(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        // Update the value of a field in the episodes table
        public static void updateEpisode(String ID, String attributeToChange, String newValue) {
            try {
                connection.setAutoCommit(false);
                try {
                    switch (attributeToChange.toUpperCase()) {
        
                        case "PODCAST_ID":
                            prepUpdateEpisodePodID.setString(1, newValue);
                            prepUpdateEpisodePodID.setString(2, ID);
                            prepUpdateEpisodePodID.executeUpdate();
                            break;
                        case "EP_NUM":
                            prepUpdateEpisodeNum.setInt(1, Integer.parseInt(newValue));
                            prepUpdateEpisodeNum.setString(2, ID);
                            prepUpdateEpisodeNum.executeUpdate();
                            break;
                        case "TITLE":
                            prepUpdateEpisodeTitle.setString(1, newValue);
                            prepUpdateEpisodeTitle.setString(2, ID);
                            prepUpdateEpisodeTitle.executeUpdate();
                            break;
                        case "DURATION":
                            prepUpdateEpisodeDuration.setInt(1, Integer.parseInt(newValue));
                            prepUpdateEpisodeDuration.setString(2, ID);
                            prepUpdateEpisodeDuration.executeUpdate();
                            break;
                        case "RELEASE_DATE":
                            prepUpdateEpisodeRelDate.setDate(1, java.sql.Date.valueOf(newValue));
                            prepUpdateEpisodeRelDate.setString(2, ID);
                            prepUpdateEpisodeRelDate.executeUpdate();
                            break;
                        case "LISTEN_COUNT":
                            prepUpdateEpisodeListenCount.setInt(1, Integer.parseInt(newValue));
                            prepUpdateEpisodeListenCount.setString(2, ID);
                            prepUpdateEpisodeListenCount.executeUpdate();
                            break;
                        case "AD_COUNT":
                            prepUpdateEpisodeAdCount.setInt(1, Integer.parseInt(newValue));
                            prepUpdateEpisodeAdCount.setString(2, ID);
                            prepUpdateEpisodeAdCount.executeUpdate();
                            break;
                        default:
                            System.out.println("Cannot update the field " + attributeToChange + " for episode with ID: " + ID + ".");
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
        }

        public static void userEpisodeUpdate() {
    
            String episodeID;
            String attributeToChange;
            String newValue;
            try {
                // Get episode ID
                System.out.println("\nEnter the episode ID of the episode you want to update:\n");
                episodeID = scanner.nextLine();
        
                // Print the episode information you plan to update
                System.out.println("\nThe episode information you have chosen:\n");
                getEpisode(episodeID);
        
                // Get attribute to change
                // Print all possible attributes that can be changed
                System.out.println("\nPlease select the attribute you wish to update [PODCAST_ID, EP_NUM, TITLE, DURATION, RELEASE_DATE, LISTEN_COUNT, AD_COUNT]:\n");
                attributeToChange = scanner.nextLine();
        
                // Get new value
                System.out.println("\nEnter the new value:\n");
                newValue = scanner.nextLine();
        
                // Call the method that interacts with the Database
                updateEpisode(episodeID, attributeToChange, newValue);
                System.out.println("The episode is updated successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
        
        //Delete a podcast episode
        public static void deleteEpisode(String ID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepDeleteEpisode.setString(1, ID);
                    prepDeleteEpisode.executeUpdate();
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
        }

        public static void userEpisodeDelete() {
            String episodeID;
            try {
                // Get episodeID
                System.out.println("\nEnter the episode ID of the episode you want to delete:\n");
                episodeID = scanner.nextLine();
                // Print the episode information you plan to delete
                System.out.println("\nThe episode information you have chosen:\n");
                getEpisode(episodeID);
                // Call method that interacts with the Database
                deleteEpisode(episodeID);
                System.out.println("The episode is deleted successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
    
    
        //Assign songs to albums
        public static void assignSongToAlbum(String songTitle, String albumName, int trackNum) {
            try {
                connection.setAutoCommit(false)
                try {
                    prepGetSongID.setString(1, songTitle);
                    ResultSet songIdResult = prepGetSongID.executeQuery();
                    prepGetAlbumID.setString(1, albumName);
                    ResultSet albumIdResult = prepGetAlbumID.executeQuery();
                    
                    if(songIdResult.next() && albumIdResult.next()) {
                        String songId = songIdResult.getString("ID");
                        String albumId = albumIdResult.getString("ID");
    
                        //Update songsInAlbum table
                        prepAssignSongToAlbum.setString(1, songId);
                        prepAssignSongToAlbum.setString(2, albumId);
                        prepAssignSongToAlbum.setInt(3, trackNum);
                        prepAssignSongToAlbum.executeUpdate();
    
                        //Update the songs table
                        prepUpdateSongAlbum.setString(1, albumId);
                        prepUpdateSongAlbum.setString(2, songId);
                        prepUpdateSongAlbum.executeUpdate();
                        connection.commit();
                    } else {
                        System.out.println("Song or album not found. Please check your input and try again.");
                    }
                
                } catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid track number. Please enter a valid integer for the track number.");
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public static void userSongToAlbumAssign() {
            String songTitle;
            String albumName;
            int trackNum;
            try {
                System.out.println("Please enter the title of the song to be assigned to an album:");
                songTitle = scanner.nextLine();
                System.out.println("Please enter the name of the album to which the song will be assigned:");
                albumName = scanner.nextLine();
                System.out.println("Please enter the track number of the song in the album:");
                trackNum = Integer.parseInt(scanner.nextLine());
                assignSongToAlbum(songTitle, albumName, trackNum);
                System.out.println("Song with title \"" + songTitle + "\" has been assigned to album \"" + albumName
                                + "\" with track number " + trackNum + " successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
            
        }
    
        //Assign Artists to Albums
        public static void assignArtistToAlbum(String artistID, String albumID) {
            try {
                connection.setAutoCommit(false)
                try {
                    prepAssignArtistToAlbum.setString(1, artistID);
                    prepAssignArtistToAlbum.setString(2, albumID);
                    prepAssignArtistToAlbum.executeUpdate();
                    connection.commit(); 
                
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    
    
        public static void userArtistToAlbumAssign() {
            String artistID;
            String albumID;
            try {
                System.out.println("Please enter the ID of the artist to be assigned to an album:");
                artistID = scanner.nextLine();
                System.out.println("Please enter the ID of the album to which the artist will be assigned:");
                albumID = scanner.nextLine();
                assignArtistToAlbum(artistID, albumID);
                System.out.println("Artist with ID \"" + artistID + "\" has been assigned to album with ID \"" + albumID
                            + "\" successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
              
        //Assign artists to record labels
    
        public static void assignArtistToRecordLabel(String artistID, String labelID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAssignArtistToRLabel.setString(1, labelID);
                    prepAssignArtistToRLabel.setString(2, artistID);
                    prepAssignArtistToRLabel.executeUpdate();
                    
                    connection.commit(); 
    
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public static void userArtistToRecordLabelAssign() {
            String artistID;
            String labelID;
            try {
                System.out.println("Please enter the ID of the artist to be assigned to a label:");
                artistID = scanner.nextLine();
                System.out.println("Please enter the ID of the label to which the artist will be assigned:");
                labelID = scanner.nextLine();
                assignArtistToRecordLabel(artistID, labelID);
                System.out.println("Artist with ID \"" + artistID + "\" has been assigned to label with ID \"" + labelID
                        + "\" successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
    
        //Assign Podcast episodes to podcasts
    
        public static void assignPodcastEpisodeToPodcast(String episodeID, String podcastID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAssignPodEpToPod.setString(1, podcastID);
                    prepAssignPodEpToPod.setString(2, episodeID);
                    prepAssignPodEpToPod.executeUpdate();
                    
                    connection.commit(); 
    
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public static void userPodcastEpisodeToPodcastAssign() {
            String episodeID;
            String podcastID;
            try {
                System.out.println("Please enter the ID of the episode to be assigned to a podcast:");
                episodeID = scanner.nextLine();
                System.out.println("Please enter the ID of the podcast to which the episode will be assigned:");
                podcastID = scanner.nextLine();
                assignPodcastEpisodeToPodcast(episodeID, podcastID);
                System.out.println("Episode with ID \"" + episodeID + "\" has been assigned to podcast with ID \"" + podcastID
                        + "\" successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        
        //Assign Podcast Hosts to Podcasts:
        
        public static void assignHostToPodcast(String hostID, String podcastID) {
            try {
                connection.setAutoCommit(false);
                try {
                    prepAssignHostToPod.setString(1, hostID);
                    prepAssignHostToPod.setString(2, podcastID);
                    prepAssignHostToPod.executeUpdate();
                    
                    connection.commit(); 
    
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public static void userHostToPodcastAssign() {
            String hostID;
            String podcastID;
            try {
                System.out.println("Please enter the ID of the host to be assigned to a podcast:");
                hostID = scanner.nextLine();
                System.out.println("Please enter the ID of the podcast to which the host will be assigned:");
                podcastID = scanner.nextLine();
                assignHostToPodcast(hostID, podcastID);
                System.out.println("Host with ID \"" + hostID + "\" has been assigned to podcast with ID \"" + podcastID
                        + "\" successfully!");
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }    
    }
    
}
