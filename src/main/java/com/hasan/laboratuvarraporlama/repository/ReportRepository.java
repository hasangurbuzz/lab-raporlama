package com.hasan.laboratuvarraporlama.repository;

import com.hasan.laboratuvarraporlama.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> getReportsByLaborant_NameAndLaborant_LastName(String laborantName, String laborantLastName);

    List<Report> getReportByPatientNameAndPatientLastName(String patientName, String patientLastName);

    List<Report> getReportByPatientIdentityNum(String patientIdentityNum);


}
