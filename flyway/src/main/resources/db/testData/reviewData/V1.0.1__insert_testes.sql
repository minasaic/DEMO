INSERT INTO user(id,name,password,profile_picture)VALUES
(1,'森上','pass','homeimg4.jpeg'),
(2,'qqq','qqq','homeimg4.jpeg'),
(3,'崔','pass','homeimg4.jpeg'),
(4,'矢口','pass','homeimg4.jpeg'),
(5,'稲垣','pass','homeimg4.jpeg'),
(6,'三間','pass','homeimg4.jpeg'),
(7,'中村','pass','homeimg4.jpeg'),
(8,'長澤','pass','homeimg4.jpeg'),
(9,'加藤','pass','homeimg4.jpeg'),
(10,'宮澤','pass','homeimg4.jpeg'),
(11,'古郡','pass','homeimg4.jpeg');


INSERT INTO posts(id,userid,image,caption,likes)VALUES
(1,1,'homeimg1.jpeg','post',1),
(2,3,'homeimg1.jpeg','post',2),
(3,4,'homeimg1.jpeg','post',3),
(4,5,'homeimg1.jpeg','post',4),
(5,6,'homeimg1.jpeg','post',5),
(6,7,'homeimg1.jpeg','post',6),
(7,8,'homeimg1.jpeg','post',7),
(8,9,'homeimg1.jpeg','post',8),
(9,10,'homeimg1.jpeg','post',9),
(10,11,'homeimg1.jpeg','post',10);


INSERT INTO follows(id,followerid,followingid)VALUES
(1,4,1),
(2,1,5),
(3,1,6),
(4,1,9),
(5,1,2),
(6,6,4),
(7,5,8),
(8,8,9),
(9,6,9);

INSERT INTO comments(id,user_id,postid,comment)VALUES
(1,1,1,'post');