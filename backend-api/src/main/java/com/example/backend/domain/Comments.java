package com.example.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
public class Comments extends GenericDomain{
    @Id@Getter@Setter
    private Integer id;
    @Getter@Setter
    private Integer user_id;
    @Getter@Setter
    private Integer post_id;
    @Getter@Setter
    private String comment;
    @Override
    protected Object keyObject() {
        return getId();
    }
}



