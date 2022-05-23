package com.hasan.laboratuvarraporlama.service;

import com.hasan.laboratuvarraporlama.exception.IdentityNumberException;
import com.hasan.laboratuvarraporlama.exception.ReportNotFoundException;
import com.hasan.laboratuvarraporlama.exception.ReportNotNullException;
import com.hasan.laboratuvarraporlama.model.Patient;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.repository.LaborantRepository;
import com.hasan.laboratuvarraporlama.repository.PatientRepository;
import com.hasan.laboratuvarraporlama.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class PatientReportServiceTest {


    private PatientReportService patientReportService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private LaborantRepository laborantRepository;


    @BeforeEach
    void setUp() {
        patientReportService = new PatientReportServiceImpl(
                reportRepository,
                patientRepository,
                laborantRepository);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void canSaveReport() {
        Report report = new Report();
        report.setReportDate(LocalDate.of(2002, 12, 12));
        report.setImageData("data");
        report.setDiagnosisTitle("diagtitle");
        report.setDiagnosisDetail("diagdetail");


        patientReportService.saveReport(report);

        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);

        verify(reportRepository).save(captor.capture());

        Report captured = captor.getValue();

        assertThat(report).isEqualTo(captured);

    }

    @Test
    void canUpdateReport() {
        Report report = new Report();
        report.setFileNum(1);
        report.setReportDate(LocalDate.of(2002, 12, 12));
        report.setImageData("data");
        report.setDiagnosisTitle("diagtitle");
        report.setDiagnosisDetail("diagdetail");

        given(reportRepository.findById(report.getFileNum()))
                .willReturn(Optional.of(report));

        patientReportService.updateReport(report);

        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);

        verify(reportRepository).save(captor.capture());

        Report captured = captor.getValue();

        assertThat(report).isEqualTo(captured);


    }

    @Test
    void canProtectStoredReportImageInDb_IfImageStringIsEmpty_WhileUpdating() {
        Report report = new Report();
        report.setImageData("");

        Report stored = new Report();
        stored.setImageData("storedimagedata");

        given(reportRepository.findById(anyInt()))
                .willReturn(Optional.of(stored));

        patientReportService.updateReport(report);

        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);

        verify(reportRepository).save(captor.capture());

        Report captured = captor.getValue();


        assertThat(stored.getImageData()).isEqualTo(captured.getImageData());

    }

    @Test
    void canThrowException_WhenSavingNullReport() {

        Report report = null;

        ReportNotNullException exception = assertThrows(ReportNotNullException.class,
                () -> patientReportService.saveReport(report));

        verify(reportRepository, never()).save(any());

        assertTrue(exception.getMessage().contains("Given report is null"));

    }

    @Test
    void canThrowError_WhileSearchingReportsByPatientInfo_WhenNotFoundAnyReport() {

        String patientName = anyString(), patientLastName = anyString();

        given(reportRepository
                .findReportsByPatientNameAndPatientLastName(patientName, patientLastName))
                .willReturn(Optional.empty());

        ReportNotFoundException exception = assertThrows(ReportNotFoundException.class,
                () -> patientReportService
                        .getReportsByPatientInfo(patientName, patientLastName));

        verify(reportRepository)
                .findReportsByPatientNameAndPatientLastName(patientName, patientLastName);

        assertTrue(exception.getMessage().contains("Reports not found with patient info"));

    }

    @Test
    void canThrowError_WhileSearchingReportsByLaborantInfo_WhenNotFoundAnyReport() {

        String labName = anyString(), labLastName = anyString();

        given(reportRepository
                .findReportsByLaborant_NameAndLaborant_LastName(labName, labLastName))
                .willReturn(Optional.empty());

        ReportNotFoundException exception = assertThrows(ReportNotFoundException.class,
                () -> patientReportService
                        .getReportsByLaborantInfo(labName, labLastName));

        verify(reportRepository)
                .findReportsByLaborant_NameAndLaborant_LastName(labName, labLastName);

        assertTrue(exception.getMessage().contains("Reports not found with laborant info"));

    }

    @Test
    void canThrowError_WhileSearchingReportsByPatientIdentityNum_WhenNotFoundAnyReport() {

        Long patientIdentityNum = anyLong();

        given(reportRepository
                .findReportsByPatientIdentityNum(patientIdentityNum))
                .willReturn(Optional.empty());

        ReportNotFoundException exception = assertThrows(ReportNotFoundException.class,
                () -> patientReportService
                        .getReportsByPatientIdentityNum(patientIdentityNum));

        verify(reportRepository)
                .findReportsByPatientIdentityNum(patientIdentityNum);

        assertTrue(exception.getMessage().contains("Reports not found with patient identity num"));

    }


    @Test
    void canReturnActualReportList_WhenSearchingByPatientInfo() {

        String patientName = anyString(), patientLastName = anyString();

        given(reportRepository
                .findReportsByPatientNameAndPatientLastName(patientName, patientLastName))
                .willReturn(Optional.of(List.of()));

        patientReportService.getReportsByPatientInfo(patientName, patientLastName);

        verify(reportRepository)
                .findReportsByPatientNameAndPatientLastName(patientName, patientLastName);

    }

    @Test
    void canReturnActualReportList_WhenSearchingByLaborantInfo() {

        String labName = anyString(), labLastName = anyString();

        given(reportRepository
                .findReportsByLaborant_NameAndLaborant_LastName(labName, labLastName))
                .willReturn(Optional.of(List.of()));

        patientReportService.getReportsByLaborantInfo(labName, labLastName);

        verify(reportRepository)
                .findReportsByLaborant_NameAndLaborant_LastName(labName, labLastName);

    }

    @Test
    void canReturnActualReportList_WhenSearchingByPatientIdentityNum() {

        Long patientIdentityNum = anyLong();

        given(reportRepository
                .findReportsByPatientIdentityNum(patientIdentityNum))
                .willReturn(Optional.of(List.of()));

        patientReportService.getReportsByPatientIdentityNum(patientIdentityNum);

        verify(reportRepository)
                .findReportsByPatientIdentityNum(patientIdentityNum);

    }


    @Test
    void canThrowError_WhenIncorrectIdentityNumberCaptured() {

        Long wrongIdentityNum = 12345678910L;
        Patient patient = new Patient();
        patient.setIdentityNum(wrongIdentityNum);

        Report report = new Report();

        report.setPatient(patient);

        IdentityNumberException exception = assertThrows(IdentityNumberException.class,
                () -> patientReportService.saveReport(report));


        verify(reportRepository, never()).save(report);

        assertTrue(exception.getMessage().contains("Given identity number does not fit algorithm"));

    }

    @Test
    void canSaveReport_WithRightPatientIdentityNumber() {
        Long rightIdentityNum = 84518029760L;
        Patient patient = new Patient();
        patient.setIdentityNum(rightIdentityNum);

        Report report = new Report();

        report.setPatient(patient);

        patientReportService.saveReport(report);

        verify(reportRepository).save(report);

    }


}