CREATE TABLE user(
	id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
	profile_picture VARCHAR(255),
    introduction VARCHAR(255),
    sex VARCHAR(255),
    birthday DATE,
	PRIMARY KEY (id)
);
CREATE TABLE posts(
    id int NOT NULL AUTO_INCREMENT,
    userid int NOT NULL,
    image varchar(255) NOT NULL,
    caption varchar(255) NOT NULL,
    createdat datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    likes int NOT NULL default 0,
    PRIMARY KEY (id)
);
CREATE TABLE comments(
    id int NOT NULL AUTO_INCREMENT,
    userid int NOT NULL,
    postid int NOT NULL,
    comment varchar(255) NOT NULL,
    name VARCHAR(30) NOT NULL,
    profile VARCHAR(255),
    createdat datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE follows(
    id int NOT NULL AUTO_INCREMENT,
    followerid int NOT NULL,
    followingid int NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE likes(
	id int NOT NULL AUTO_INCREMENT,
    postid int NOT NULL,
    userid int NOT NULL,
	PRIMARY KEY (id)
);