package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.model.Report;

import java.util.List;

public interface LaborantService {

    void saveLaborant(Laborant laborant);

    void saveReport(Report report);

    List<Report> getReports();

    Report getReportById(Integer id);

    void updateReport(Report report, Integer id);

    List<Report> getReportsByPatientInfo(String patientName, String patientLastName);

    List<Report> getReportsByLaborantInfo(String laborantName, String laborantLastName);

    List<Report> getReportsByPatientIdentityNum(String patientIdentityNum);

    void deleteReportById(Integer id);
}
