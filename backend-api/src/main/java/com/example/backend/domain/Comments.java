package com.example.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
public class Comments extends GenericDomain{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    private Integer id;
    @Getter@Setter
    private Integer user_id;
    @Getter@Setter
    private Integer postid;
    @Getter@Setter
    private String comment;
    @Override
    protected Object keyObject() {
        return getId();
    }
}



