package com.hasan.laboratuvarraporlama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReportRequest {
    private Integer fileNum;
    private String patientIdentityNum;
    private String patientName;
    private String patientLastName;
    private String laborantIdentityNum;
    private String laborantName;
    private String laborantLastName;
    private String diagnosisTitle;
    private String diagnosisDetail;
    private String reportDate;
    private MultipartFile imageFile;

}
