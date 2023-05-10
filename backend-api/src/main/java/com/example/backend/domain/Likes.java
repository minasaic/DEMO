package com.example.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "likes")
public class Likes extends GenericDomain{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    private Integer id;
    @Getter@Setter
    private  Integer postid;
    @Getter@Setter
    private Integer userid;

    @Override
    protected Object keyObject() {
        return getId();
    }
}



