package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.exception.IdentityNumberException;
import com.hasan.laboratuvarraporlama.exception.ReportNotFoundException;
import com.hasan.laboratuvarraporlama.exception.ReportNotNullException;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.repository.LaborantRepository;
import com.hasan.laboratuvarraporlama.repository.PatientRepository;
import com.hasan.laboratuvarraporlama.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientReportServiceImpl implements PatientReportService {

    private final ReportRepository reportRepository;
    private final PatientRepository patientRepository;
    private final LaborantRepository laborantRepository;

    public PatientReportServiceImpl(ReportRepository reportRepository,
                                    PatientRepository patientRepository,
                                    LaborantRepository laborantRepository) {
        this.reportRepository = reportRepository;
        this.patientRepository = patientRepository;
        this.laborantRepository = laborantRepository;
    }


    @Override
    public void saveReport(Report report) {
        if (report == null) throw new ReportNotNullException("Given report is null");

        boolean isCorrect = isIdentityNumCorrect(report.getPatient().getIdentityNum());

        if (!isCorrect)
            throw new IdentityNumberException(
                    "Given identity number does not fit algorithm:" + report.getPatient().getIdentityNum());

        patientRepository.save(report.getPatient());
        laborantRepository.save(report.getLaborant());
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
    public void updateReport(Report report) {
        boolean isCorrect = isIdentityNumCorrect(report.getPatient().getIdentityNum());

        if (!isCorrect)
            throw new IdentityNumberException(
                    "Given identity number does not fit algorithm:" + report.getPatient().getIdentityNum());

        Report storedReport = reportRepository
                .findById(report.getFileNum())
                .orElseThrow(()
                        -> new ReportNotFoundException("Report is not found with specified id=" + report.getFileNum()));

        if (report.getImageData().equals("")) {
            String storedImage = storedReport.getImageData();
            report.setImageData(storedImage);
        }


        patientRepository.save(report.getPatient());
        laborantRepository.save(report.getLaborant());
        reportRepository.save(report);
    }

    @Override
    public List<Report> getReportsByPatientInfo(String patientName, String patientLastName) {
        List<Report> reportList = reportRepository
                .findReportsByPatientNameAndPatientLastName(patientName, patientLastName)
                .orElseThrow(()
                        -> new ReportNotFoundException("Reports not found with patient info"));


        return reportList;
    }

    @Override
    public List<Report> getReportsByLaborantInfo(String laborantName, String laborantLastName) {
        List<Report> reportList = reportRepository
                .findReportsByLaborant_NameAndLaborant_LastName(laborantName, laborantLastName)
                .orElseThrow(()
                        -> new ReportNotFoundException("Reports not found with laborant info"));


        return reportList;
    }

    @Override
    public List<Report> getReportsByPatientIdentityNum(Long patientIdentityNum) {
        List<Report> reportList = reportRepository
                .findReportsByPatientIdentityNum(patientIdentityNum)
                .orElseThrow(() ->
                        new ReportNotFoundException("Reports not found with patient identity num"));


        return reportList;
    }

    @Override
    public void deleteReportById(Integer id) {
        reportRepository.deleteById(id);

    }

    @Override
    public List<Report> getReportsOrderedByDate(boolean isAscending) {
        List<Report> orderedReports;
        if (isAscending) {
            orderedReports = reportRepository
                    .findAllByOrderByReportDateAsc()
                    .orElseThrow(() -> new ReportNotFoundException("Not found any report"));
        } else {
            orderedReports = reportRepository
                    .findAllByOrderByReportDateDesc()
                    .orElseThrow(() -> new ReportNotFoundException("Not found any report"));
        }
        return orderedReports;
    }

    boolean isIdentityNumCorrect(Long identityNum) {
        String identityString = identityNum.toString();
        int sumOfEvenDigits = 0;
        int sumOfOddDigits = 0;
        int multipleOfAllDigits = 0;


        for (int i = 0; i < identityString.length() - 2; i++) {
            if (i % 2 == 0) {
                sumOfOddDigits += Integer.parseInt(identityString.substring(i, i + 1));
            }
            if (i % 2 != 0) {
                sumOfEvenDigits += Integer.parseInt(identityString.substring(i, i + 1));
            }

        }

        int tenthDigit = ((sumOfOddDigits * 7) - sumOfEvenDigits) % 10;

        for (int i = 0; i < identityString.length() - 1; i++) {
            multipleOfAllDigits += Integer.parseInt(identityString.substring(i, i + 1));
        }

        int eleventhDigit = multipleOfAllDigits % 10;

        String resultIdentityNum = identityString.substring(0, 9) + tenthDigit + eleventhDigit;

        return identityString.equals(resultIdentityNum);


    }
}
