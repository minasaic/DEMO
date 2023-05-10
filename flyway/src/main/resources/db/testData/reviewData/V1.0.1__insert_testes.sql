INSERT INTO user(id,name,password,profile_picture)VALUES
(1,'森上','pass','que-14237809059.jpeg'),
(2,'qqq','qqq','imagesooo.png'),
(3,'崔','pass','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(4,'矢口','pass','yaguchyan.png'),
(5,'稲垣','pass','images2.jpeg'),
(6,'三間','pass','toris-1024x1024.png'),
(7,'中村','pass','pkjhjkl.jpeg'),
(8,'長澤','pass','pp.jpeg'),
(9,'加藤','pass','images.jpeg'),
(10,'宮澤','pass','aa.png'),
(11,'古郡','pass','images.png');


INSERT INTO posts(id,userid,image,caption,likes)VALUES
(1,1,'images1.jpeg','プロのエンジニアだぞ！',1),
(2,3,'KJ_20201215_02_01.jpg','瀬戸内海楽しい！！！〜〜',2),
(3,4,'atami2.jpeg','熱海行ってきた！',3),
(4,5,'jkl.jpeg','私めちゃくちゃ可愛い！',4),
(5,6,'DSC06150.jpg','世界最強の！',5),
(6,7,'lkjhgfd.jpeg','美人！！',6),
(7,8,'1100x770_ea1e9d427fb5664c32c517a73e421e58_6caa40561048704c2c3dd9eafcd154384ef8fca6.jpg','旅行いいな！',7),
(8,9,'kafe2.jpeg','仕事頑張ろう！',8),
(9,10,'9d09f489ee0edc4845f30ab5ccf1283c.png','焼肉食べたい！！',9),
(10,11,'article_img_19121701_25.jpg','刺身食べたい！！',10);


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

INSERT INTO comments(id,userid,postid,comment,name,profile)VALUES
(1,1,1,'はじめてのコメントだよ','森上','que-14237809059.jpeg');