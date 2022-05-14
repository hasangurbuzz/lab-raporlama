package com.hasan.laboratuvarraporlama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Report {

    @Id
    @Column(length = 11)
    private Integer patientIdentityNum;
    private String fileNum;
    private String patientName;
    private String patientLastName;
    private String diagnosisTitle;
    @Lob
    private byte[] image;


    public Report() {
    }

    public Report(String fileNum, String patientName, String patientLastName,
                  Integer patientIdentityNum, String diagnosisTitle, byte[] image) {
        this.fileNum = fileNum;
        this.patientIdentityNum = patientIdentityNum;
        this.patientName = patientName;
        this.patientLastName = patientLastName;
        this.diagnosisTitle = diagnosisTitle;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String id) {
        this.fileNum = id;
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

    public Integer getPatientIdentityNum() {
        return patientIdentityNum;
    }

    public void setPatientIdentityNum(Integer patient_identity_num) {
        this.patientIdentityNum = patient_identity_num;
    }

    public String getDiagnosisTitle() {
        return diagnosisTitle;
    }

    public void setDiagnosisTitle(String diagnosis_title) {
        this.diagnosisTitle = diagnosis_title;
    }
}
