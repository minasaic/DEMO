INSERT INTO user(id,name,password,profile_picture)VALUES
(1,'森上','pass','aaaaa'),
(2,'矢口','yaguchi','asdfgdsf'),
(3,'崔','saisai','safgdr'),
(4,'acbhvnbmkhjguh','asdfg','asdfhghjyftydr');

INSERT INTO posts(id,user_id,image,caption,likes)VALUES
(1,2,'post','post',123);

INSERT INTO follows(id,follower_id,following_id)VALUES
(1,2,123);

INSERT INTO comments(id,user_id,post_id,comment)VALUES
(1,2,567,'post');