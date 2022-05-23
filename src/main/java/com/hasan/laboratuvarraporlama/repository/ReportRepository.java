package com.hasan.laboratuvarraporlama.repository;

import com.hasan.laboratuvarraporlama.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    Optional<List<Report>> findReportsByLaborant_NameAndLaborant_LastName(String laborantName, String laborantLastName);

    Optional<List<Report>> findReportsByPatientNameAndPatientLastName(String patientName, String patientLastName);

    Optional<List<Report>> findReportsByPatientIdentityNum(Long patient_identityNum);

    Optional<List<Report>> findAllByOrderByReportDateAsc();

    Optional<List<Report>> findAllByOrderByReportDateDesc();

}
