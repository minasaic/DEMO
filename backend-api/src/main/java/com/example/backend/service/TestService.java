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
        urepo.save(user);
        return true;
    }

   // ホームページ
   public List<Posts> getPosts(Integer id) { //ログインしたアカウントのidをコントローラーから受け取った
    List<Posts> post = new ArrayList<>(); //インスタンス化
    List<Follows> follow = frepo.findByFollowingid(id); //Followsテーブルからカラム(Following)※idがフォローしているアカウントのレコードを取ってくる
    for(int i = 0 ; i < follow.size(); i++){ //for文でlistの要素全てのFollowerをxに入れる
        post.addAll(prepo.findByUserid(follow.get(i).getFollowerid())); 
    }
    return post;
}    //serect　* from follows where followingid = id

    //マイページ
    public List<Posts> mypage(Integer id){
        return prepo.findByUserid(id);
    }

    //アカウント編集　変更
    public boolean update(String staticPath, Integer id, String name, String password){
        User user = new User();
        user.setId(id);
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

    //コメントを表示させる
    public List<Comments> getCom(Integer id){
        return crepo.findByPostid(id);
    }
    // コメント投稿
    public boolean createComment(Integer user_id, Integer post_id, String comment) {
        Comments com = new Comments();
        System.out.println(user_id);
        System.out.println(post_id);
        System.out.println(comment);
        com.setUser_id(user_id);
        com.setPostid(post_id);
        com.setComment(comment);
        crepo.save(com);
        return true;
    }

    //フォロワー数とフォロー数の取得
    public  List<Integer> getFollowCount(Integer id){
        List<Integer> followCount= new ArrayList<>();
        followCount.add(frepo.countByFollowingid(id));
        followCount.add(frepo.countByFollowerid(id));
            return followCount;
    }

    //投稿を検索する
    public List<Posts> search(String keyword){
        List<Posts> a = prepo.findByCaptionLike( "%" + keyword.replaceAll("\"", "") + "%");
        if(a != null){
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
        return frepo.findByFolloweridAndFollowingid(followerid,followingid).get().getId();
    }

    // フォロー解除
    public boolean unfollow(Integer id) {
        frepo.deleteById(id);
        return true;
    }
    //フォロー判断
    public Integer judge(Integer followerid, Integer followingid){
        Follows asa = frepo.findByFolloweridAndFollowingid(followerid,followingid).get();
        System.out.println("ああああああああああああああっっっっっっっっっk");

        if( asa!= null){
           return  asa.getId();
        }else{
            return null;
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

}
