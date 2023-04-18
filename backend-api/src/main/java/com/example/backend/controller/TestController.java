package com.example.backend.controller;

<<<<<<< Updated upstream

import org.springframework.beans.factory.annotation.Autowired;
=======
import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.domain.LoginRequest;
import com.example.backend.domain.User;
import com.example.backend.service.TestService;

@RestController
@CrossOrigin
class TestController{
    @Autowired
    TestService testService;

    @GetMapping(path="/testes/{id}")
    public User getUsers(@PathVariable("id") Integer id){
        return testService.getUser(id);
    }

    @PostMapping(path="/aaa")
    public User getUsers2(@RequestBody Integer id){
        return testService.getUser(id);
    }

<<<<<<< Updated upstream
    @PostMapping(path="/login")
    public boolean loginUser(@RequestBody LoginRequest login){
        return testService.loginUser(login.getUsername(), login.getPassword());
    }

=======
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
    // 新規投稿
    @PostMapping(path = "/post")
    public ResponseEntity<String> newPost(@RequestBody PostRequest post) {
        try {
            String filePath = "/asd/asd/asd/asd/" + post.getFile().getOriginalFilename();
            post.getFile().transferTo(new File(filePath));
            testService.createPost(post.getId(),filePath,post.getCaption());
            return ResponseEntity.ok("投稿しました");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ファイルのアップロードに失敗しました。");
        }
    }

    // コメント投稿
    @PostMapping(path = "/comment")
    public boolean newComment(@RequestBody CommentRequest com) {
        return testService.createComment(com.getUser_id(),com.getPost_id(),com.getComment());
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

>>>>>>> Stashed changes
}
//  class Aaa {
//     private boolean bool;
//     private Integer id;

//     // コンストラクタ、getter、setter等

//     // getter, setterの定義
//     public boolean isBool() {
//         return bool;
//     }

//     public void setBool(boolean bool) {
//         this.bool = bool;
//     }

//     public Integer getId() {
//         return id;
//     }

//     public void setId(Integer id) {
//         this.id = id;
//     }
// }
