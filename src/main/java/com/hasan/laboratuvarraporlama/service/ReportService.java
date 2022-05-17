package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.model.Report;

import java.util.List;


public interface ReportService {

    void saveReport(Report report);

    List<Report> getReports();

    Report getReportById(Integer id);

    void updateReport(Report report,Integer id);


    void deleteReportById(Integer id);


}
