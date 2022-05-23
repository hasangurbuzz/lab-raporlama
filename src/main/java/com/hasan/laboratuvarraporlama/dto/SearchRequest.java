package com.hasan.laboratuvarraporlama.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SearchRequest {
    private String patientName;
    private String patientLastName;
    private String patientIdentityNum;
    private String laborantName;
    private String laborantLastName;


}
