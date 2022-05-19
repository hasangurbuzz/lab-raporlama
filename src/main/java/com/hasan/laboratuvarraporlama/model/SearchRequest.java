package com.hasan.laboratuvarraporlama.model;

public class SearchRequest {
    private String patientName;
    private String patientLastName;
    private String patientIdentityNum;
    private String laborantName;
    private String laborantLastName;


    public SearchRequest() {
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

    public String getLaborantName() {
        return laborantName;
    }

    public void setLaborantName(String laborantName) {
        this.laborantName = laborantName;
    }

    public String getLaborantLastName() {
        return laborantLastName;
    }

    public void setLaborantLastName(String laborantLastName) {
        this.laborantLastName = laborantLastName;
    }

    public String getPatientIdentityNum() {
        return patientIdentityNum;
    }

    public void setPatientIdentityNum(String patientIdentityNum) {
        this.patientIdentityNum = patientIdentityNum;
    }
}
