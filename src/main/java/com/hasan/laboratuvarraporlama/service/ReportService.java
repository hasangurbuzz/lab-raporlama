package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.model.Report;

import java.util.List;


public interface ReportService {

    void saveReport(Report report);

    List<Report> getReports();


}
