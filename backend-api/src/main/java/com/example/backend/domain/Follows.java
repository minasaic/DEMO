package com.example.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "follows")
public class Follows extends GenericDomain{
    @Id@Getter@Setter
    private Integer id;
    @Getter@Setter
    private  Integer follower_id;
    @Getter@Setter
    private Integer following_id;

    @Override
    protected Object keyObject() {
        return getId();
    }
}



