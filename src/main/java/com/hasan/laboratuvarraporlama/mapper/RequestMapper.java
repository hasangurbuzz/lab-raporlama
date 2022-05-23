package com.hasan.laboratuvarraporlama.mapper;

import com.hasan.laboratuvarraporlama.dto.ReportRequest;
import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.model.Patient;
import com.hasan.laboratuvarraporlama.model.Report;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

@Component
public class RequestMapper {


    public Report mapToReport(ReportRequest reportRequest) throws IOException {
        String encodedImage = mapToEncodedImageString(reportRequest.getImageFile());
        Patient patient = new Patient();
        patient.setName(reportRequest.getPatientName().toLowerCase());
        patient.setLastName(reportRequest.getPatientLastName().toLowerCase());
        patient.setIdentityNum(Long.parseLong(reportRequest.getPatientIdentityNum()));

        Laborant laborant = new Laborant();
        laborant.setName(reportRequest.getLaborantName().toLowerCase());
        laborant.setLastName(reportRequest.getLaborantLastName().toLowerCase());
        laborant.setIdentityNum(Integer.parseInt(reportRequest.getLaborantIdentityNum()));


        Report report = new Report();
        report.setFileNum(reportRequest.getFileNum());
        report.setDiagnosisTitle(reportRequest.getDiagnosisTitle());
        report.setDiagnosisDetail(reportRequest.getDiagnosisDetail());
        report.setReportDate(LocalDate.parse(reportRequest.getReportDate()));
        report.setImageData(encodedImage);
        report.setPatient(patient);

        report.setLaborant(laborant);

        return report;

    }


    String mapToEncodedImageString(MultipartFile imageFile) throws IOException {
        return Base64
                .getEncoder()
                .encodeToString(imageFile.getBytes());
    }


}
