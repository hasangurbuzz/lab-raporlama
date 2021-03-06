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
@Table(name = "LABORANT")
public class Laborant {
    @Id
    @Column(length = 7)
    private Integer identityNum;
    private String name;
    private String lastName;

    @OneToMany(mappedBy = "laborant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports;


}
