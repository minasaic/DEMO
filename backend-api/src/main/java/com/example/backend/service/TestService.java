package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

   
}
