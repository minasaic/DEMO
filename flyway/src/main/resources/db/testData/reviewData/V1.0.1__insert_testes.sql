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


INSERT INTO posts(userid,image,caption,likes)VALUES
(1,'images1.jpeg','プロのエンジニアだぞ！',0),
(3,'KJ_20201215_02_01.jpg','瀬戸内海楽しい！！！〜〜',0),
(4,'atami2.jpeg','熱海行ってきた！',0),
(5,'jkl.jpeg','私めちゃくちゃ可愛い！',0),
(6,'DSC06150.jpg','世界最強の！',0),
(7,'lkjhgfd.jpeg','美人！！',0),
(8,'1100x770_ea1e9d427fb5664c32c517a73e421e58_6caa40561048704c2c3dd9eafcd154384ef8fca6.jpg','旅行いいな！',0),
(9,'kafe2.jpeg','仕事頑張ろう！',0),
(10,'9d09f489ee0edc4845f30ab5ccf1283c.png','焼肉食べたい！！',0),
(11,'article_img_19121701_25.jpg','刺身食べたい！！',0),
(1,'Autumn-trees-lake-house-park-Japan_1920x1200.jpg','ああああ',0),
(1,'Night-sky-stars-starry_2560x1440.jpg','ああああああああああああああああああああ',0),
(1,'Philadelphia-Delaware-river-Benjamin-Franklin-Bridge-USA-night-lights_1920x1200.jpg','ああああああ',0),
(1,'Blonde-girl-autumn-leaves_3840x2160.jpg','嗚呼ああ',0),
(1,'New-York-old-town-street-colorful-lights-night-USA_1920x1200.jpg','あああああ',0),
(1,'Whip-Snake_1920x1440.jpg','あああああああ',0),
(2,'Turtle-front-view-ground_1920x1200.jpg','あああああああ',0),
(2,'Three-tulips-flowers-cloth-background_1920x1200.jpg','ああああああ',0),
(2,'Tomatoes-vegetables-juice-glass-cup_3840x2160.jpg','ああああああ',0),
(2,'Two-guns-weapon_1920x1440.jpg','ああああああ',0),
(2,'Pink-rose-and-bud-green-background_3840x2160.jpg','あああ',0),
(2,'Beach-sea-sunset-city-skyscrapers-night_1920x1080.jpg','あああ',0),
(2,'Asian-girl-heavy-rain-umbrella_1920x1080.jpg','あああ',0),
(3,'アーニャイエッサー.jpg','あああ',0),
(3,'2cde853ca5f0138eb9498cdb170a3bed.png','あああ',0),
(3,'05a60a4029542f809a7be0124007fe73.png','あああ',0),
(3,'Netherlands-river-windmill-grass-beautiful-scenery_1920x1080.jpg','あああ',0),
(3,'3d481d0cd307567edf359b5f70f41730.png','あああ',0),
(4,'f55400b9140273fcbab4fead55147a5b.png','あああ',0),
(4,'ee595fe6b57f698ea1659a36872d3fde.png','ああああ',0),
(4,'42062cbeca16839af3efe8a7d61ceb27a30f758b.png','あああ',0),
(4,'91c067049a22890550a4e9823c046282.png','あああああ',0),
(4,'4986a33f760c4fd1f13416d1d0b131a9.png','ああああ',0),
(4,'Blonde-girl-autumn-leaves_3840x2160.jpg','ああああ',0),
(5,'a6e90225ab9058e6c6268441a48636fe.png','ああああ',0),
(5,'FTSz33FaIAE5Eis.jpeg','ああああ',0),
(5,'d14276-158-1e9f119437078c220e26-0.jpg','ああああ',0),
(5,'img_f944e7d3040fd983ad5fb017054e18c01924172.jpg','ああああ',0);



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