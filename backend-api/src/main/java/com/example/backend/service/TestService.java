package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.domain.Comments;
import com.example.backend.domain.Follows;
import com.example.backend.domain.Posts;
import com.example.backend.domain.User;
import com.example.backend.repository.UserRepository;

import com.example.backend.repository.PostsRepository;
import com.example.backend.repository.FollowsRepository;
import com.example.backend.repository.CommentsRepository;


@Service
public class TestService {
    @Autowired
    UserRepository urepo;
    @Autowired
    PostsRepository prepo;
    @Autowired
    FollowsRepository frepo;
    @Autowired
    CommentsRepository crepo;
    

    public User getUser(Integer id){
        User testesOptional =urepo.findById(id).get();
        System.out.println(testesOptional.getName());
        
            return testesOptional;
    }

    //ログインチェック
    public boolean loginUser(String name,String password){
        User user = urepo.findByName(name);
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Integer getIdByName(String username) {
        return urepo.findByName(username).getId();
    }

    // 新規ユーザー登録
    public boolean createUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        // user.setProfile_picture(profile_picture);
        urepo.save(user);
        return true;
    }

    // ホームページ
    public List<Posts> getPosts(Integer id) {
        // Follows aa = frepo.findByFollowingd(id).get();
        List<Posts> post = new ArrayList<>();
        // for (int i = 0; i < aa.length; i++) {
        //     post.add(prepo.findById(aa[i]).get());
        // }
        return post;
    }

    //マイページ
    public List<Posts> mypage(Integer id){
        System.out.println("あああああああああああああああああああああああああああああ");
        return prepo.findByUserid(id);
    }

    // 新規投稿
    public boolean createPost(Integer id, String image, String caption) {
        Posts post = new Posts();
        post.setUserid(id);
        post.setImage(image);
        post.setCaption(caption);
        prepo.save(post);
        return true;
    }

    //コメントを表示させる
    public List<Comments> getCom(Integer id){
        return crepo.findByPostid(id);
    }
    // コメント投稿
    public boolean createComment(Integer user_id, Integer post_id, String comment) {
        Comments com = new Comments();
        System.out.println("kikikikik聞き聞き聞き聞き聞き聞き聞き聞き聞きキキキキキキっっき行き一気聞き行き");
        System.out.println(user_id);
        System.out.println(post_id);
        System.out.println(comment);
        com.setUser_id(user_id);
        com.setPostid(post_id);
        com.setComment(comment);
        crepo.save(com);
        return true;
    }

    // フォロー
    public boolean followUser(Integer followId, Integer followedId) {
        Follows follow = new Follows();
        follow.setFollower_id(followId);
        follow.setFollowing_id(followedId);
        frepo.save(follow);
        return true;
    }

    // フォロー解除
    public boolean unfollow(Integer id) {
        frepo.deleteById(id);
        return true;
    }

    // いいね機能
    public boolean like(Integer id) {
        Posts plus = prepo.findById(id).get();
        Posts pos = new Posts();
        pos.setId(id);
        pos.setLikes(plus.getLikes() + 1);
        prepo.save(pos);
        return true;
    }

    // 投稿削除
    public boolean deletePost(Integer id) {
        prepo.deleteById(id);
        return true;
    }

    // 投稿削除のためのパスをゲットする
    public String getPath(Integer id) {
         return prepo.findById(id).get().getImage();
    }

}
