package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.repository.LaborantRepository;
import com.hasan.laboratuvarraporlama.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaborantServiceImpl implements LaborantService {


    private final ReportRepository reportRepository;
    private final LaborantRepository laborantRepository;

    public LaborantServiceImpl(ReportRepository reportRepository, LaborantRepository laborantRepository) {
        this.reportRepository = reportRepository;
        this.laborantRepository = laborantRepository;
    }


    @Override
    public void saveLaborant(Laborant laborant) {
        laborantRepository.save(laborant);
    }

    @Override
    public void saveReport(Report report) {
        Laborant lab = laborantRepository.getById(1);
        report.setLaborant(lab);
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
        Laborant lab = laborantRepository.getById(1);
        report.setLaborant(lab);
        report.setFileNum(id);
        if (report.getImageData().equals("")) {
            String storedImage = reportRepository.getById(id).getImageData();
            report.setImageData(storedImage);
        }
        reportRepository.save(report);
    }

    @Override
    public List<Report> getReportsByPatientInfo(String patientName, String patientLastName) {
        return reportRepository
                .getReportByPatientNameAndPatientLastName(patientName, patientLastName);
    }

    @Override
    public List<Report> getReportsByLaborantInfo(String laborantName, String laborantLastName) {
        return reportRepository
                .getReportsByLaborant_NameAndLaborant_LastName(laborantName, laborantLastName);

    }

    @Override
    public List<Report> getReportsByPatientIdentityNum(String patientIdentityNum) {
        return reportRepository
                .getReportByPatientIdentityNum(patientIdentityNum);
    }

    @Override
    public void deleteReportById(Integer id) {
        reportRepository.deleteById(id);

    }
}
