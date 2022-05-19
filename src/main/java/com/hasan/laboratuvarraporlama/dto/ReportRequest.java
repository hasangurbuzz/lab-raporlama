package com.hasan.laboratuvarraporlama.dto;

import org.springframework.web.multipart.MultipartFile;

public class ReportRequest {
    private Integer fileNum;
    private String patientIdentityNum;
    private String patientName;
    private String patientLastName;
    private String diagnosisTitle;
    private String diagnosisDetail;
    private String reportDate;
    private MultipartFile imageFile;

    public ReportRequest(String patientIdentityNum, String patientName, String patientLastName,
                         String diagnosisTitle, String diagnosisDetail, String reportDate, MultipartFile imageFile) {
        this.patientIdentityNum = patientIdentityNum;
        this.patientName = patientName;
        this.patientLastName = patientLastName;
        this.diagnosisTitle = diagnosisTitle;
        this.diagnosisDetail = diagnosisDetail;
        this.reportDate = reportDate;
        this.imageFile = imageFile;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getPatientIdentityNum() {
        return patientIdentityNum;
    }

    public void setPatientIdentityNum(String patientIdentityNum) {
        this.patientIdentityNum = patientIdentityNum;
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

    public void setDiagnosisTitle(String diagnosisTitle) {
        this.diagnosisTitle = diagnosisTitle;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }


    public String getDiagnosisDetail() {
        return diagnosisDetail;
    }

    public void setDiagnosisDetail(String diagnosisDetail) {
        this.diagnosisDetail = diagnosisDetail;
    }
}
