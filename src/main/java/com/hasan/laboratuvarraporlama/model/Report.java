package com.hasan.laboratuvarraporlama.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_num")
    private Integer fileNum;

    @Column(length = 30)
    private String diagnosisTitle;

    @Column(length = 500)
    private String diagnosisDetail;

    private LocalDate reportDate;

    @Lob
    private String imageData;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_identity", nullable = false)
    private Laborant laborant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_identity", nullable = false)
    private Patient patient;



    public String getFormattedDate() {
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(reportDate);

    }

}
