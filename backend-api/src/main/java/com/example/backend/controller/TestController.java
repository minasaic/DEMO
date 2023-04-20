package com.example.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.domain.CommentRequest;
import com.example.backend.domain.Comments;
import com.example.backend.domain.FollowRequest;
import com.example.backend.domain.LoginRequest;
import com.example.backend.domain.Posts;
import com.example.backend.service.TestService;

@RestController
@CrossOrigin
class TestController{
    @Autowired
    TestService testService;

    // ログイン
    @PostMapping(path = "/login")
    public LoginRequest loginUser(@RequestBody LoginRequest login) {
        LoginRequest log = new LoginRequest();
        if(testService.loginUser(login.getUsername(),login.getPassword())){
         log.setBool(true);
         log.setId(testService.getIdByName(login.getUsername()));
        return log;
        }
        log.setBool(false);
        return log;
    }

    // 新規登録
    @PostMapping(path = "/create")
    public LoginRequest createUser(@RequestBody LoginRequest login) {
        LoginRequest log = new LoginRequest();
        if(testService.createUser(login.getUsername(), login.getPassword())){
            log.setBool(true);
            log.setId(testService.getIdByName(login.getUsername()));
            return log;
        }
        log.setBool(false);
        return log;
    }

    //ホーム画面
    @PostMapping(path="/home")
    public List<Posts> homepage(@RequestBody Integer id){
        return testService.getPosts(id);
    }

    //マイページ
    @PostMapping(path="/mypage")
    public List<Posts> mypage(@RequestBody Integer id){
        System.out.println("あああああああああああああああああああああああああああああ");
        return testService.mypage(id);
    }
    // 新規投稿
    @PostMapping(path = "/post")
    public String newPost(@RequestParam ("file") MultipartFile file,@RequestParam ("id") Integer id,@RequestParam("text") String text) {
        try {
            String filePath = "/Users/saimina/project/ojt-training/DEMO/frontend/src/assets/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            String staticPath = file.getOriginalFilename(); //データベースに保存するファイルネーム
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
    public boolean newComment(@RequestBody CommentRequest com) {
        System.out.println("ああああああああああ");
        System.out.println(com.getPostid());
        return testService.createComment(com.getUser_id(),com.getPostid(),com.getComment());
    }
    // フォロー
    @PostMapping(path = "/follow")
    public boolean follow(@RequestBody FollowRequest fol) {
        return testService.followUser(fol.getFollower_id(),fol.getFollowing_id());
    }

    // フォロー解除
    @PostMapping(path = "/unfollow")
    public boolean unfollow(@RequestBody Integer id) {
        return testService.unfollow(id);
    }

    // いいね
    @PostMapping(path = "/like")
    public boolean like(@RequestBody Integer id) {
        return testService.like(id);
    }

    // 投稿削除
    @DeleteMapping(path = "/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        String imagePath = testService.getPath(id);
        File file = new File(imagePath);
        if (file.exists()) {
            file.delete();
            testService.deletePost(id);
            return ResponseEntity.ok("投稿を削除しました。");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("指定された投稿が見つかりませんでした。");
        }

    }

}
