package com.example.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User extends GenericDomain{
<<<<<<< HEAD
    @Id@Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
>>>>>>> minamina
    private Integer id;
    @Getter@Setter
    private String name;
    @Getter@Setter
    private String password;
    @Getter@Setter
    private String profile_picture;

    @Override
    protected Object keyObject() {
        return getId();
    }
}
