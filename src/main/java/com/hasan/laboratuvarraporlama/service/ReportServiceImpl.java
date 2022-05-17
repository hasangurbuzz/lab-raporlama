package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.mapper.ReportMapper;
import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.repository.LaborantRepository;
import com.hasan.laboratuvarraporlama.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    static final Integer userId = 1;
    static Laborant laborant = null;

    private final LaborantRepository laborantRepository;
    private final ReportRepository reportRepository;


    public ReportServiceImpl(LaborantRepository laborantRepository, ReportRepository reportRepository, ReportMapper reportMapper) {
        this.laborantRepository = laborantRepository;
        this.reportRepository = reportRepository;
        laborant = laborantRepository.getById(userId);

    }

    @Override
    public void saveReport(Report report) {
        report.setLaborant(laborant);
        reportRepository.save(report);

    }

    @Override
    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report getReportById(Integer id) {
        return reportRepository.getById(id);
    }

    @Override
    public void updateReport(Report report, Integer id) {
        report.setLaborant(laborant);
        report.setFileNum(id);


        if (report.getImageData().equals("")) {
            String savedImage = reportRepository.getById(id).getImageData();
            report.setImageData(savedImage);
        }
        reportRepository.save(report);
    }


    @Override
    public void deleteReportById(Integer id) {
        reportRepository.deleteById(id);

    }
}
