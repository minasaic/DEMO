INSERT INTO user(id,name,password,profile_picture)VALUES
(1,'森上','pass','aaaaa'),
(2,'矢口','pass','asdfgdsf'),
(3,'崔','pass','safgdr'),
(4,'djjdjdjd','asdfg','asdfhghjyftydr');

INSERT INTO posts(id,userid,image,caption,likes)VALUES
(1,2,'post','post',123);

INSERT INTO follows(id,follower_id,following_id)VALUES
(1,2,123);

INSERT INTO comments(id,user_id,postid,comment)VALUES
(1,2,567,'post');