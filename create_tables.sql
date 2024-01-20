CREATE TABLE genres (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE labels (
    ID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE artists (
    ID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    status VARCHAR(7) CHECK (status='active' OR status='retired'),
    type VARCHAR(8) CHECK (type='band' OR type='musician' OR type='composer'),
    country VARCHAR(30),
    primary_genre VARCHAR(30),
    monthly_listeners INT DEFAULT 0,
    label_ID VARCHAR(10),
    FOREIGN KEY(primary_genre) 
        REFERENCES genres(name)
        ON DELETE SET NULL
        ON UPDATE SET NULL,
    FOREIGN KEY(label_ID) 
        REFERENCES labels(ID)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE albums (
    ID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    edition VARCHAR(15)
);

CREATE TABLE songs (
    ID VARCHAR(10) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    album_ID VARCHAR(10) REFERENCES albums(ID),
    play_count INT DEFAULT 0,
    release_date DATE NOT NULL,
    release_country VARCHAR(30),
    language VARCHAR(30),
    royalty_rate DECIMAL(10,2) DEFAULT 0,
    royalty_paid BIT DEFAULT 0,
    label_ID VARCHAR(10),
    FOREIGN KEY(album_ID) 
        REFERENCES albums(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY(label_ID) 
        REFERENCES labels(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE playCountHistory(
    song_ID VARCHAR(10),
    month INT CHECK (month>0 AND month<13),
    year INT,
    play_count INT DEFAULT 0,
    PRIMARY KEY (song_ID, month, year),
    FOREIGN KEY (song_ID)
        REFERENCES songs(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE royaltyPaymentHistory(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    label_ID VARCHAR(10),
    month INT CHECK (month>0 AND month<13),
    year INT,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (label_ID)
        REFERENCES labels(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE artistPaymentHistory(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    artist_ID VARCHAR(10),
    month INT CHECK (month>0 AND month<13),
    year INT,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (artist_ID)
        REFERENCES artists(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);



CREATE TABLE hosts (
    ID VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(30),
    email VARCHAR(50),
    city VARCHAR(50)
);

CREATE TABLE podcasts (
    ID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    host_ID VARCHAR(10),
    language VARCHAR(30),
    country VARCHAR(30),
    ep_count INT DEFAULT 0,
    ep_fee DECIMAL DEFAULT 0,
    total_subs INT DEFAULT 0,
    avg_rating DECIMAL(10,2) DEFAULT 0,
    total_ratings INT DEFAULT 0,
    FOREIGN KEY(host_ID) 
        REFERENCES hosts(ID)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE episodes (
    ID VARCHAR(10) PRIMARY KEY,
    podcast_ID VARCHAR(10),
    ep_num INT,
    title VARCHAR(100) NOT NULL,
    duration INT,
    release_date DATE,
    listen_count INT DEFAULT 0,
    ad_count INT DEFAULT 0,
    host_paid BIT DEFAULT 0,
    FOREIGN KEY(podcast_ID)
        REFERENCES podcasts(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE sponsors (
    ID INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE guests (
    ID VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE listeningCountHistory(
    ep_ID VARCHAR(10),
    month INT CHECK (month > 0 AND month < 13),
    year INT,
    play_count INT DEFAULT 0,
    PRIMARY KEY (ep_ID, month, year),
    FOREIGN KEY (ep_ID)
        REFERENCES episodes(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE ratingHistory(
    podcast_ID VARCHAR(10),
    month INT CHECK (month > 0 AND month < 13),
    year INT,
    avg_rating DECIMAL(10,2) DEFAULT 0,
    PRIMARY KEY(podcast_ID, month, year),
    FOREIGN KEY(podcast_ID)
        REFERENCES podcasts(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE hostPaymentHistory(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    host_ID VARCHAR(10),
    month INT CHECK (month>0 AND month<13),
    year INT,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (host_ID)
        REFERENCES hosts(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE users(
    ID VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    registration_date DATE,
    sub_status CHAR CHECK (sub_status='S' OR sub_status='U'),
    sub_fee DECIMAL(10,2) DEFAULT 10
);

CREATE TABLE userPaymentHistory(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    user_ID VARCHAR(10),
    month INT CHECK (month>0 AND month<13),
    year INT,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (user_ID)
        REFERENCES users(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE artistsIn(
    song_ID VARCHAR(10),
    artist_ID VARCHAR(10),
    role VARCHAR(15) CHECK (role='main' OR role='collaborator'),
    PRIMARY KEY (song_ID, artist_ID),
    FOREIGN KEY (song_ID)
        REFERENCES songs(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        FOREIGN KEY (artist_ID)
        REFERENCES artists(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE albumBy(
    artist_ID VARCHAR(10),
    album_ID VARCHAR(10),
    PRIMARY KEY (artist_ID, album_ID),
    FOREIGN KEY (artist_ID)
        REFERENCES artists(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (album_ID)
        REFERENCES albums(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE songsInAlbum(
    song_ID VARCHAR(10),
    album_ID VARCHAR(10),
    track_num INT,
    PRIMARY KEY (song_ID, album_ID),
    FOREIGN KEY (song_ID)
        REFERENCES songs(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (album_ID)
        REFERENCES albums(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE sponsoredBy(
    sponsor_ID INT,
    podcast_ID VARCHAR(10),
    PRIMARY KEY (sponsor_ID, podcast_ID),
    FOREIGN KEY (sponsor_ID)
        REFERENCES sponsors(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (podcast_ID)
        REFERENCES podcasts(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE episodeGuests(
    ep_ID VARCHAR(10),
    guest_ID VARCHAR(10), 
    PRIMARY KEY (ep_ID, guest_ID),
    FOREIGN KEY (ep_ID)
        REFERENCES episodes(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (guest_ID)
        REFERENCES guests(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE artistSubscriptions(
    user_ID VARCHAR(10),
    artist_ID VARCHAR(10),
    PRIMARY KEY (user_ID, artist_ID),
    FOREIGN KEY (user_ID)
        REFERENCES users(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (artist_ID)
        REFERENCES artists(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE podcastSubscriptions(
    user_ID VARCHAR(10),
    podcast_ID VARCHAR(10),
    PRIMARY KEY (user_ID, podcast_ID),
    FOREIGN KEY (user_ID)
        REFERENCES users(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (podcast_ID)
        REFERENCES podcasts(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE songGenre(
    song_ID VARCHAR(10),
    genre_name VARCHAR(30),
    PRIMARY KEY (song_ID, genre_name),
    FOREIGN KEY (song_ID)
        REFERENCES songs(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (genre_name)
        REFERENCES genres(name)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE podcastGenre(
    podcast_ID VARCHAR(10),
    genre_name VARCHAR(30),
    PRIMARY KEY (podcast_ID, genre_name),
    FOREIGN KEY (podcast_ID)
        REFERENCES podcasts(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (genre_name)
        REFERENCES genres(name)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
