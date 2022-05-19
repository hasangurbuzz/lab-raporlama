package com.hasan.laboratuvarraporlama.mapper;

import com.hasan.laboratuvarraporlama.dto.ReportRequest;
import com.hasan.laboratuvarraporlama.model.Report;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

@Component
public class ReportMapper {


    public Report mapToReport(ReportRequest reportRequest) throws IOException {
        String encodedImage = mapToEncodedImageString(reportRequest.getImageFile());


        return new Report(
                reportRequest.getPatientIdentityNum(),
                reportRequest.getPatientName(),
                reportRequest.getPatientLastName(),
                reportRequest.getDiagnosisTitle(),
                reportRequest.getDiagnosisDetail(),
                LocalDate.parse(reportRequest.getReportDate()),
                encodedImage
        );

    }


    String mapToEncodedImageString(MultipartFile imageFile) throws IOException {
        return Base64
                .getEncoder()
                .encodeToString(imageFile.getBytes());
    }


}
