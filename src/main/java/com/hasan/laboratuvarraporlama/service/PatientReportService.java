package com.hasan.laboratuvarraporlama.service;


import com.hasan.laboratuvarraporlama.model.Report;

import java.util.List;

public interface PatientReportService {


    void saveReport(Report report);

    List<Report> getReports();

    Report getReportById(Integer id);

    void updateReport(Report report);

    List<Report> getReportsByPatientInfo(String patientName, String patientLastName);

    List<Report> getReportsByLaborantInfo(String laborantName, String laborantLastName);

    List<Report> getReportsByPatientIdentityNum(Long patientIdentityNum);

    void deleteReportById(Integer id);

    List<Report> getReportsOrderedByDate(boolean isAscending);

}
