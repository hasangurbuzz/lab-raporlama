package com.hasan.laboratuvarraporlama.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "PATIENT")
public class Patient {
    @Id
    @Column(length = 11)
    private Long identityNum;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String lastName;



    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports;


}

