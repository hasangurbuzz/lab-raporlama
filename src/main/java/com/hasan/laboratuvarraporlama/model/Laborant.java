package com.hasan.laboratuvarraporlama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Laborant {
    @Id
    @Column(length = 7)
    private String identity_num;
    private String name;
    private String last_name;


    public Laborant() {
    }

    public Laborant(String name, String last_name, String identity_num) {

        this.name = name;
        this.last_name = last_name;
        this.identity_num = identity_num;
    }

    public String getIdentity_num() {
        return identity_num;
    }

    public void setIdentity_num(String identity_num) {
        this.identity_num = identity_num;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


}
