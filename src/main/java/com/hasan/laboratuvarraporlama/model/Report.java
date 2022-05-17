package com.hasan.laboratuvarraporlama.model;

import javax.persistence.*;

@Entity
@Table(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_num")
    private Integer fileNum;

    @Column(length = 11, name = "patient_identity_num")
    private String patientIdentityNum;

    private String patientName;

    private String patientLastName;


    private String diagnosisTitle;

    @Lob
    private String imageData;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_identity", nullable = false)
    private Laborant laborant;


    public Report() {
    }

    public Report(String patientIdentityNum, String patientName, String patientLastName,
                  String diagnosisTitle, String imageData) {

        this.patientIdentityNum = patientIdentityNum;
        this.patientName = patientName;
        this.patientLastName = patientLastName;
        this.diagnosisTitle = diagnosisTitle;
        this.imageData = imageData;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public String getPatientIdentityNum() {
        return patientIdentityNum;
    }

    public void setPatientIdentityNum(String patientIdentityNum) {
        this.patientIdentityNum = patientIdentityNum;
    }

    public Laborant getLaborant() {
        return laborant;
    }

    public void setLaborant(Laborant laborant) {
        this.laborant = laborant;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }


    public String getDiagnosisTitle() {
        return diagnosisTitle;
    }

    public void setDiagnosisTitle(String diagnosis_title) {
        this.diagnosisTitle = diagnosis_title;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
