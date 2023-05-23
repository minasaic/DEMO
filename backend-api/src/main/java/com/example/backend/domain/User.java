// package com.example.backend.domain;

// import java.sql.Date;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Table(name = "user")
// public class User extends GenericDomain{

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Getter@Setter
//     private Integer id;
//     @Getter@Setter
//     private String name;
//     @Getter@Setter
//     private String password;
//     @Getter@Setter
//     private String profile_picture;
//     // @Getter@Setter
//     // private String introduction;
//     // @Getter@Setter
//     // private String sex;
//     // @Getter@Setter
//     // private Date birthday;

//     @Override
//     protected Object keyObject() {
//         return getId();
//     }
// }
package com.example.backend.domain;

import java.sql.Date;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    private Integer id;
    @Getter@Setter
    private String name;
    @Getter@Setter
    private String password;
    @Getter@Setter
    private String profile_picture;
    @Getter@Setter
    private String introduction;
    @Getter@Setter
    private String sex;
    @Getter@Setter
    private Date birthday;

    @Override
    protected Object keyObject() {
        return getId();
    }
}