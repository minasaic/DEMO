package com.example.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "testes")
public class Testes extends GenericDomain{
    @Id@Getter@Setter
    private Integer id;
    @Getter@Setter
    private String name;

    @Override
    protected Object keyObject() {
        return getId();
    }
}
