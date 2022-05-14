package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void saveReport(Report report) {

        reportRepository.save(report);

    }

    @Override
    public List<Report> getReports() {
        return reportRepository.findAll();
    }
}
