INSERT INTO user(id,name,password,profile_picture,introduction,sex,birthday )VALUES
(1,'morigami','pass','que-14237809059.jpeg','モーリやで','男性','1996-06-06'),
(2,'qqq','qqq','imagesooo.png','私は誰?','答えたくない','2000-01-01'),
(3,'sai','pass','11b274c6-fa3a-4947-9612-333ebcccb750.jpg','日本語普通に話せるよー！','女性','1996-06-29'),
(4,'yaguchi','pass','yaguchyan.png','世界中の貧しい子どもたちが幸せになりますように。','男性','1996-03-28'),
(5,'inagaki','pass','images2.jpeg','犬大好き!ワンちゃん!','女性','1996-05-31'),
(6,'mitsuma','pass','toris-1024x1024.png','最近髪赤く染めた','女性','1997-10-09'),
(7,'nakamura','pass','pkjhjkl.jpeg','30歳までに結婚したい!!','女性','1997-11-17'),
(8,'nagasawa','pass','pp.jpeg','神栖から来ました。都会の人仲良くしてください','男性','1997-08-16'),
(9,'katou','pass','images.jpeg','俺は九州男児だから偉い','男性','1997-01-02'),
(10,'miyazawa','pass','aa.png','筋肉増強中 プロテイン！生卵！','男性','1997-03-03'),
(11,'furugori','pass','images.png','俺は1課!!','男性','1995-06-02');


INSERT INTO posts(id,userid,image,caption,likes)VALUES
(1,1,'images1.jpeg','ああああああああ',0),
(2,3,'IMG_5900.jpg,IMG_2488.jpg,IMG_2483.jpg,IMG_0680.jpg','熱海の思い出とうまい火鍋！',0),
(3,4,'atami2.jpeg','熱海行ってきた！',0),
(4,5,'jkl.jpeg','私めちゃくちゃ可愛い！',0),
(5,6,'DSC06150.jpg','世界最強の！',0),
(6,7,'lkjhgfd.jpeg','美人！！',0),
(7,8,'1100x770_ea1e9d427fb5664c32c517a73e421e58_6caa40561048704c2c3dd9eafcd154384ef8fca6.jpg','旅行いいな！',0),
(8,9,'kafe2.jpeg','仕事頑張ろう！',8),
(9,10,'9d09f489ee0edc4845f30ab5ccf1283c.png','焼肉食べたい！！',9),
(10,11,'article_img_19121701_25.jpg','刺身食べたい！！',10),
(11,1,'IMG_3148.jpg,IMG_3157.jpg,IMG_3159.jpg,51BD417F-EE5A-4A3A-9D6F-08A204F58A52.jpg','熱海のMOA美術館!',0),
(12,1,'IMG_3187.jpg','熱海の夜景綺麗だ',0),
(13,3,'IMG_6902.jpg','みなとみらい赤レンガ！',0),
(14,3,'IMG_1228.jpg,IMG_1227.jpg,IMG_1226.jpg,IMG_1225.jpg','スノボ行ってきた',0),
(15,4,'4806962197715623907.04b6857a6e75965db33c9420b6033ee1.22101805.jpg','電車大好き！',0),
(16,4,'IMG_5808.jpg,IMG_5806.jpg','村神様！三冠王おめでとう！！',0),
(17,5,'IMG_5790.jpg','リーグ優勝！！',0),
(18,5,'IMG_4191.jpg,IMG_4719.jpg','母の日に胡蝶蘭！',0),

(19,6,'IMG_0057.jpg,IMG_0062.png','飛行機かっこいい',10),
(20,6,'IMG_0124.jpg,IMG_0198.png','油そば',10),
(21,7,'IMG_0267.jpg,','私のゲーミングパソコン！',10),
(22,7,'IMG_0732.png','波だ！危ない！',10),
(23,8,'IMG_1146.jpg','パンケーキ食べて癒されたわ',10),
(24,8,'IMG_1647.jpg,IMG_0408.jpg','仕事終わりのラーメン',10),

(25,9,'IMG_4194.jpg,IMG_4187.jpg,IMG_4186.jpg,IMG_4192.jpg,','刺身食べたい！！',0),
(26,9,'IMG_8882.jpg,IMG_8391.jpg,IMG_4115.jpg,IMG_2391.jpg,','〜九州日記〜',0),
(27,9,'article_img_19121701_25.jpg','刺身食べたい！！,',0),
(28,10,'IMG_7831.jpg,IMG_7819.jpg,IMG_7796.jpg,IMG_9601.jpg,','春のバーベキュー最高だぜ',0),
(29,10,'IMG_1545.jpg,IMG_1564.jpg,IMG_1979.jpg','彼女とのデート',0),
(30,10,'article_img_19121701_25.jpg','刺身食べたい！！',0),
(31,11,'IMG_4121.jpg,IMG_4123.jpg,IMG_4138.jpg,IMG_4143.jpg','美術館の日',0),
(32,11,'IMG_2347.jpg,IMG_2346.jpg,IMG_3534.jpg','海鮮うまいな〜',0),
(33,3,'IMG_2774.jpg','研修３ヶ月楽しかった！',0),
(34,5,'IMG_3671.jpg','私が作ったよ',0),
(35,6,'IMG_3674.jpg','珍しいサイダー',0),
(36,7,'IMG_3585.jpg','お酒お酒',0),
(37,3,'IMG_3708.jpg,IMG_3898.jpg,IMG_3936.jpg','楽しかった！',0),
(38,3,'IMG_8369.jpg,IMG_8373.jpg,IMG_8387.jpg,IMG_8378.jpg','楽しかった！',0);


INSERT INTO follows(id,followerid,followingid)VALUES
(1,4,1),
(2,3,1),
(3,2,1),
(4,1,3),
(5,9,3),
(6,6,3),
(7,5,3),
(8,8,4),
(9,6,4),
(10,6,5),
(11,9,5),
(12,4,5),
(13,3,5),
(14,7,5),
(15,1,6),
(16,3,6),
(17,4,6),
(18,5,6),
(19,11,7),
(20,10,7),
(21,9,7),
(22,11,8),
(23,9,8),
(24,5,8),
(25,4,8),
(26,3,9),
(27,1,9),
(28,11,10),
(29,6,10),
(30,9,10),
(31,10,11),
(32,5,11),
(33,7,11),
(34,1,11);

INSERT INTO comments(id,userid,postid,comment,name,profile)VALUES
(1,1,36,'えええええええええええ！','morigami','que-14237809059.jpeg'),
(2,1,35,'美味しいそう!飲みたい','morigami','que-14237809059.jpeg'),
(3,1,34,'すごい！アイスも作れるんだ！！！！','morigami','que-14237809059.jpeg'),
(4,3,33,'まじ楽しかったわ','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(5,3,32,'美味しそう！！！','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(6,3,31,'おしゃれだな今度私も行く！','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(7,4,30,'はじめてのコメントだよ','yaguchi','yaguchyan.png'),
(8,4,29,'はじめてのコメントだよ','yaguchi','yaguchyan.png'),
(9,4,28,'はじめてのコメントだよ','yaguchi','yaguchyan.png'),
(10,5,27,'うわー','inagaki','images2.jpeg'),
(11,5,26,'九州に行きたい〜','inagaki','images2.jpeg'),
(12,5,25,'美味しそう！どこのお店なの','inagaki','images2.jpeg'),
(13,6,24,'美味そうな食ってんな〜','mitsuma','toris-1024x1024.png'),
(14,6,23,'星乃珈琲店の？','mitsuma','toris-1024x1024.png'),
(15,6,22,'気をつけてね','mitsuma','toris-1024x1024.png'),
(16,1,28,'えええええええええええ！','morigami','que-14237809059.jpeg'),
(17,1,30,'飲みたい','morigami','que-14237809059.jpeg'),
(18,3,28,'どこどこ教えて〜','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(19,7,28,'お肉だ！','nakamura','pkjhjkl.jpeg'),
(20,6,28,'一緒に行こう！','nagasawa','pp.jpeg'),
(21,9,28,'行きたい行きたい！','katou','images.jpeg'),
(22,10,32,'海鮮は健康的、しかもタンパク質がたくさん入ってるよ','miyazawa','aa.png'),
(23,11,32,'牡蠣大好き','furugori','images.png'),
(24,3,21,'いくらした？高そう、羨ましいわ','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(25,7,20,'西宮行ったら食べるわ😆','nakamura','pkjhjkl.jpeg'),
(26,6,19,'どこ行くの','nagasawa','pp.jpeg'),
(27,9,18,'よく出来た息子だね✨','katou','images.jpeg'),
(28,10,17,'感動したね😭','miyazawa','aa.png'),
(29,11,16,'日本人シーズン最多ホームランもおめでとう！！！','furugori','images.png'),
(30,3,15,'ローカルな感じがいいね👍','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(31,7,14,'かっこいいいいいいいい！','nakamura','pkjhjkl.jpeg'),
(32,6,13,'夜光るんだね！！','nagasawa','pp.jpeg'),
(33,9,12,'綺麗すぎる！','katou','images.jpeg'),
(34,10,11,'海綺麗！','miyazawa','aa.png'),
(35,11,15,'天気いいね〜','古furugori郡','images.png'),
(36,3,18,'お母さん羨ましい＾＾','sai','11b274c6-fa3a-4947-9612-333ebcccb750.jpg'),
(37,7,19,'どこ行くの！？','nakamura','pkjhjkl.jpeg'),
(38,7,13,'この前行ってきた！おしゃれな倉庫','nakamura','pkjhjkl.jpeg'),
(39,7,12,'ゴールデンウィークに行けてよかったね','nakamura','pkjhjkl.jpeg'),
(40,7,14,'めっちゃいいね！','nakamura','pkjhjkl.jpeg');
