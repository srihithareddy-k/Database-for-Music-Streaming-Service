INSERT INTO genres VALUES ('Alternative'), ('Pop');

INSERT INTO labels
VALUES
    ('rl3001', 'Elevate Records'),
    ('rl3002', 'Melodic Avenue Music');

INSERT INTO artists
VALUES
    ('ar2001', 'Forest', 'active', 'band', 'USA', 'Alternative', 25, 'rl3001'),
    ('ar2002', 'Rain', 'active', 'band', 'USA', 'Pop', 55, 'rl3002');

INSERT INTO albums
VALUES
    ('al4001', 'Electric Oasis', 2018, 'Limited'),
    ('al4002', 'Lost in the Echoes', 2018, 'Platinum');

INSERT INTO songs
VALUES
    ('s1001','Electric Dreamscape','al4001',500,'2018-01-01', 'USA', 'English', 0.10, 0, 'rl3001'),
    ('s1002','Midnight Mirage','al4001',1000,'2018-01-01', 'USA', 'English', 0.10, 0, 'rl3001'),
    ('s1003','Echoes of You','al4002',100,'2018-03-01', 'USA', 'English', 0.10, 0, 'rl3002'),
    ('s1004','Rainy Nights','al4002',200,'2018-03-01', 'USA', 'English', 0.10, 0, 'rl3002');

INSERT INTO playCountHistory
VALUES
    ('s1001', 1, 2023, 10),
    ('s1001', 2, 2023, 20),
    ('s1001', 3, 2023, 30),
    ('s1002', 1, 2023, 100),
    ('s1002', 2, 2023, 200),
    ('s1002', 3, 2023, 300),
    ('s1003', 1, 2023, 1000),
    ('s1003', 2, 2023, 2000),
    ('s1003', 3, 2023, 3000),
    ('s1004', 1, 2023, 10000),
    ('s1004', 2, 2023, 20000),
    ('s1004', 3, 2023, 30000);

INSERT INTO hosts
VALUES
    ('ph6001', 'Matthew', 'Wilson', '123-456-7890', 'matt@mail.com', 'Raleigh');

INSERT INTO podcasts
VALUES 
    ('p5001', 'Mind Over Matter: Exploring the Power of the Human Mind', 'ph6001', 'English', 'USA', 5, 10, 10, 4.5, 0);

INSERT INTO episodes
VALUES
    ('pe7001', 'p5001', 1, 'The Science of Mindfulness', 30, '2023-02-14', 100, 0, 1),
    ('pe7002', 'p5001', 2, 'Unlocking Your Potential', 30, '2023-03-14', 200, 0, 0);
    
INSERT INTO users
VALUES
    ('u8001', 'Alex', 'A', '111-222-3333', 'alex@mail.com', '2017-01-03', 'S', 10),
    ('u8002', 'John', 'J', '222-333-4444', 'john@mail.com', '2017-01-04', 'S', 10);


INSERT INTO artistPaymentHistory(artist_ID, month, year, amount)
VALUES
    ('ar2001', 1, 2023, 4.2),
    ('ar2001', 2, 2023, 8.4),
    ('ar2001', 3, 2023, 12.6),
    ('ar2002', 1, 2023, 773.5),
    ('ar2002', 2, 2023, 1547),
    ('ar2002', 3, 2023, 2320.5);

INSERT INTO royaltyPaymentHistory(label_ID, month, year, amount)
VALUES
    ('rl3001', 1, 2023, 3.3),
    ('rl3001', 2, 2023, 6.6),
    ('rl3001', 3, 2023, 9.9),
    ('rl3002', 1, 2023, 330),
    ('rl3002', 2, 2023, 660),
    ('rl3002', 3, 2023, 990);

INSERT INTO hostPaymentHistory(host_ID, month, year, amount)
VALUES
    ('ph6001', 1, 2023, 20),
    ('ph6001', 2, 2023, 30),
    ('ph6001', 3, 2023, 40);

INSERT INTO artistsIn
VALUES
    ('s1001', 'ar2001', 'main'),
    ('s1002', 'ar2001', 'main'),
    ('s1002', 'ar2002', 'collaborator'),
    ('s1003', 'ar2002', 'main'),
    ('s1004', 'ar2002', 'main');

INSERT INTO albumBy
VALUES
    ('ar2001', 'al4001'),
    ('ar2002', 'al4002');

INSERT INTO songsInAlbum
VALUES
    ('s1001', 'al4001', 1),
    ('s1002', 'al4001', 2),
    ('s1003', 'al4002', 1),
    ('s1004', 'al4002', 2);
