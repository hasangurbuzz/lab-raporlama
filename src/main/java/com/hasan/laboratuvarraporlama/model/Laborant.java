package com.hasan.laboratuvarraporlama.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LABORANT")
public class Laborant {
    @Id
    @Column(length = 7)
    private Integer identityNum;
    private String name;
    private String lastName;

    @OneToMany(mappedBy = "laborant", fetch = FetchType.LAZY)
    private Set<Report> reports;

    public Laborant() {
    }

    public Laborant(Integer identityNum, String name, String lastName) {
        this.identityNum = identityNum;
        this.name = name;
        this.lastName = lastName;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Integer getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(Integer identity_num) {
        this.identityNum = identity_num;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }


}
