package com.example.backend.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.domain.Comments;
import com.example.backend.domain.Follows;
import com.example.backend.domain.Posts;
import com.example.backend.domain.User;
import com.example.backend.service.TestService;

@RestController
@CrossOrigin
class TestController{
    @Autowired
    TestService testService;

    // ログイン
    @PostMapping(path = "/login")
    public User loginUser(@RequestBody User user) {
        if(testService.loginUser(user.getName(),user.getPassword())){
         user.setId(testService.getIdByName(user.getName()));
         user.setProfile_picture(testService.getProfileById(user.getId()));
        return user;
        }
        return user;
        }

    // 新規登録
    @PostMapping(path = "/create")
    public User createUser(@RequestBody User user) {
        if(testService.createUser(user.getName(), user.getPassword())){
            user.setId(testService.getIdByName(user.getName()));
            user.setProfile_picture(testService.getProfileById(user.getId()));
            return user;
        }
        return user;
        }
    
    //ホーム画面
    @PostMapping(path="/home")
    public List<Posts> homepage(@RequestBody Integer id){
        return testService.getPosts(id);
    }

    //マイページ
    @PostMapping(path="/mypage")
    public List<Posts> mypage(@RequestBody Integer id){
        return testService.mypage(id);
    }

    //アカウント編集・変更
    @PostMapping(path = "/update")
    public boolean update(
        @RequestParam ("file") MultipartFile file,
        @RequestParam ("id") Integer id,
        @RequestParam ("name") String name, 
        @RequestParam ("password") String password) {
        try {
            String filePath = "/Users/saimina/project/ojt-training/DEMO/frontend/src/assets/profile/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            String staticPath = file.getOriginalFilename(); //データベースに保存するファイルネーム
            return testService.update(staticPath,id,name,password);
        } catch (IOException e) {
            return false;
        }
    }

    //投稿数を取得
    @PostMapping(value="/postdata")
    public Integer getpost(@RequestBody Integer id) {
        return testService.getPostCount(id);
    }

    // 新規投稿
    @PostMapping(path = "/post")
    public String newPost(@RequestParam ("file") MultipartFile file,@RequestParam ("id") Integer id,@RequestParam("text") String text) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            String filePath = "/Users/saimina/project/ojt-training/DEMO/frontend/src/assets/post/" 
                + timestamp 
                + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            String staticPath = timestamp + file.getOriginalFilename(); //データベースに保存するファイルネーム
            testService.createPost(id,staticPath,text);
            return staticPath;
        } catch (IOException e) {
            return "sdfgh";
        }
    }
    
    @PostMapping(path="/test")
    public String sai(@RequestBody Integer id){
        return testService.getPath(id);
    }

    //コメントを表示させる
    @PostMapping(path="/getcom")
    public List<Comments> getcc(@RequestBody Integer id){
        return testService.getCom(id);
    }

    // コメント投稿
    @PostMapping(path = "/comment")
    public boolean newComment(@RequestBody Comments com) {
        System.out.println(com.getUser_id());
        System.out.println(com.getPostid());
        return testService.createComment(com.getUser_id(),com.getPostid(),com.getComment());
    }

    //フォロワーとフォロー数を取得
    @PostMapping(path = "/followdata")
    public List<Integer> getFollow(@RequestBody Integer id) {
        return testService.getFollowCount(id);
    }

    //投稿を検索
    @PostMapping(path = "/search")
    public List<Posts> search(@RequestBody String keyword){
        return testService.search(keyword);
    }

    // フォロー
    @PostMapping(path = "/follow")
    public Integer follow(@RequestBody Follows fol) {
        return testService.followUser(fol.getFollowerid(),fol.getFollowingid());
    }

    // フォロー解除
    @PostMapping(path = "/unfollow")
    public boolean unfollow(@RequestBody Integer id) {
        return testService.unfollow(id);
    }

    // いいね
    @PostMapping(path = "/like")
    public Integer like(@RequestBody Integer id) {
        System.out.println("aaあああああああああああああああああ");
        return testService.like(id);
    }

    //フォローしてるかどうかの判断
    @PostMapping(path="/followJudge")
    public Integer aaa(@RequestBody Follows fol){
        return testService.judge(fol.getFollowerid(),fol.getFollowingid());
    }

    // 投稿削除
    @PostMapping(path = "/deletepost")
    public boolean deletePost(@RequestBody Integer id) {
        String imagePath = "/Users/saimina/project/ojt-training/DEMO/frontend/src/assets/post/" +testService.getPath(id);
        File file = new File(imagePath);
        if (file.exists()) {
            file.delete();
            testService.deletePost(id);
            return true;
        } else {
            return false;
        }
    }
}
