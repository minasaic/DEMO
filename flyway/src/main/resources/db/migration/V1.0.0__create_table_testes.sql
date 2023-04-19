CREATE TABLE user(
	id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
	profile_picture VARCHAR(255),
	PRIMARY KEY (id)
);
CREATE TABLE posts(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    image varchar(255) NOT NULL,
    caption varchar(255) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    likes int default 0,
    PRIMARY KEY (id)
);
CREATE TABLE comments(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    post_id int NOT NULL,
    comment varchar(255) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE follows(
    id int NOT NULL AUTO_INCREMENT,
    follower_id int NOT NULL,
    following_id int NOT NULL,
    PRIMARY KEY (id)
);