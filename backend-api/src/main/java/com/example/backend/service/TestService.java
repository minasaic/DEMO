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

    public User getUser(Integer id) {
        User testesOptional = urepo.findById(id).get();
        System.out.println(testesOptional.getName());
        return testesOptional;
    }

    // ログインチェック
    public boolean loginUser(String name, String password) {
        User user = urepo.findByName(name);
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Integer getIdByName(String username) {
        return urepo.findByName(username).getId();
    }

    public String getProfileById(Integer id) {
        return urepo.findById(id).get().getProfile_picture();
    }

    // 新規ユーザー登録
    public boolean createUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        urepo.save(user);
        return true;
    }

    // ホームページ
    public List<Posts> getPosts(Integer id) { // ログインしたアカウントのidをコントローラーから受け取った
        List<Posts> post = new ArrayList<>(); // インスタンス化
        List<Follows> follow = frepo.findByFollowingid(id); // Followsテーブルからカラム(Following)※idがフォローしているアカウントのレコードを取ってくる
        for (int i = 0; i < follow.size(); i++) { // for文でlistの要素全てのFollowerをxに入れる
            post.addAll(prepo.findByUserid(follow.get(i).getFollowerid()));
        }
        return post;
    } // serect * from follows where followingid = id

    // マイページ
    public List<Posts> mypage(Integer id) {
        return prepo.findByUserid(id);
    }

    // アカウント編集 変更
    public boolean update(String staticPath, Integer id, String name, String password) {
        User user = urepo.findById(id).get();
        user.setProfile_picture(staticPath);
        user.setName(name);
        user.setPassword(password);
        urepo.save(user);
        return true;
    }

    // 新規投稿
    public boolean createPost(Integer id, String image, String caption) {
        Posts post = new Posts();
        post.setUserid(id);
        post.setImage(image);
        post.setCaption(caption);
        post.setLikes(0);
        prepo.save(post);
        return true;
    }

    // コメントを表示させる
    public List<Comments> getCom(Integer id) {
        return crepo.findByPostid(id);
    }

    // コメント投稿
    public boolean createComment(Integer user_id, Integer post_id, String comment) {
        Comments com = new Comments();
        User user = urepo.findById(user_id).get();
        com.setPostid(post_id);
        com.setUser_id(user_id);
        com.setComment(comment);
        com.setName(user.getName());
        com.setProfile(user.getProfile_picture());
        crepo.save(com);
        return true;
    }

    // 投稿数を取得
    public Integer getPostCount(Integer id) {
        Integer postCount = prepo.countByUserid(id);
        return postCount;
    }

    // フォロワー数とフォロー数の取得
    public List<Integer> getFollowCount(Integer id) {
        List<Integer> followCount = new ArrayList<>();
        followCount.add(frepo.countByFollowingid(id));
        followCount.add(frepo.countByFollowerid(id));
        return followCount;
    }

    // ユーザー情報取得
    public User aaa(Integer id) {
        return urepo.findById(id).get();
    }

    // 投稿を検索する
    public List<Posts> search(String keyword) {
        List<Posts> a = prepo.findByCaptionLike("%" + keyword.replaceAll("\"", "") + "%");
        if (a != null) {
            return a;
        }
        return null;

    }

    // フォロー
    public Integer followUser(Integer followerid, Integer followingid) {
        Follows follow = new Follows();
        follow.setFollowerid(followerid);
        follow.setFollowingid(followingid);
        frepo.save(follow);
        return frepo.findByFolloweridAndFollowingid(followerid, followingid).get().getId();
    }

    // フォロワー一覧
    public List<User> minamina(Integer id) {
        List<Follows> mina = frepo.findByFollowerid(id);
        List<User> minamina = new ArrayList<>();
        for (int i = 0; i < mina.size(); i++) {
            minamina.add(urepo.findById(mina.get(i).getFollowingid()).get());
        }
        return minamina;
    }

    // フォロー一覧
    public List<User> saisai(Integer id) {
        List<Follows> sai = frepo.findByFollowingid(id);
        List<User> saisai = new ArrayList<>();
        for (int i = 0; i < sai.size(); i++) {
            saisai.add(urepo.findById(sai.get(i).getFollowerid()).get());
        }
        return saisai;
    }

    // フォロー解除
    public boolean unfollow(Integer id) {
        frepo.deleteById(id);
        return true;
    }

    // フォロー判断
    public Integer judge(Integer followerid, Integer followingid) {
        System.out.println("ああああああああああああああっっっっっっっっっk");
        if (frepo.findByFolloweridAndFollowingid(followerid, followingid).isPresent()) {
            Follows asa = frepo.findByFolloweridAndFollowingid(followerid, followingid).get();
            System.out.println("ああああああああああああああoooooooooooooo");
            return asa.getId();
        } else {
            return 10000;
        }
    }

    // いいね機能 修正後
    public Integer like(Integer id) {
        Posts post = prepo.findById(id).get();
        post.setLikes(post.getLikes() + 1);
        prepo.save(post);
        Posts a = prepo.findById(id).get();
        return a.getLikes();
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

    // ユーザーのプロフィール画像とアカウント名を取ってくる
    public User getUserNameAndImage(Integer id) {

        return urepo.findById(id).get();

    }

}
