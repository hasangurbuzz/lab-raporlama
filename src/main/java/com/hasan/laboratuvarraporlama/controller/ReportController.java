package com.hasan.laboratuvarraporlama.controller;

import com.hasan.laboratuvarraporlama.dto.ReportRequest;
import com.hasan.laboratuvarraporlama.dto.SearchRequest;
import com.hasan.laboratuvarraporlama.mapper.RequestMapper;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.service.PatientReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/report")
public class ReportController {


    private final PatientReportService patientReportService;
    private final RequestMapper requestMapper;


    public ReportController(PatientReportService patientReportService,
                            RequestMapper requestMapper) {
        this.patientReportService = patientReportService;
        this.requestMapper = requestMapper;

    }


    @GetMapping
    public String getAllReports(
            @RequestParam(name = "orderAsc", required = false) Optional<Boolean> orderAsc,
            Model model
    ) {
        List<Report> orderedReports;
        boolean orderStatus;

        if (orderAsc.isPresent()) {
            orderedReports = patientReportService
                    .getReportsOrderedByDate(orderAsc.get());
            orderStatus = orderAsc.get();
        } else {
            orderedReports = patientReportService.getReportsOrderedByDate(false);
            orderStatus = false;
        }

        model.addAttribute("reportList", orderedReports);

        model.addAttribute("orderStatus", orderStatus);

        return "reports";
    }

    @GetMapping("/add")
    public String reportPanel() {
        return "addreport";
    }


    @PostMapping(value = "/add")
    public RedirectView createReport(
            @Validated ReportRequest reportRequest
    ) throws IOException {

        Report report = requestMapper.mapToReport(reportRequest);

        patientReportService.saveReport(report);
        return new RedirectView("/report");
    }


    @GetMapping("/detail/{fileNum}")
    public String getReportDetails(
            @PathVariable Integer fileNum,
            @RequestParam(name = "editmode", required = false) Optional<String> editMode,
            Model model
    ) {
        Report report = patientReportService.getReportById(fileNum);

        model.addAttribute("report", report);


        if (editMode.isPresent()) return "reporteditor";
        else return "reportdetail";

    }


    @PostMapping("/update")
    public RedirectView updateReport(
            @Validated ReportRequest reportRequest
    ) throws IOException {

        Report report = requestMapper.mapToReport(reportRequest);

        patientReportService.updateReport(report);


        return new RedirectView("/report/detail/" + reportRequest.getFileNum());
    }


    @PostMapping("/delete/{fileNum}")
    public RedirectView deleteReport(
            @PathVariable Integer fileNum
    ) {

        patientReportService.deleteReportById(fileNum);

        return new RedirectView("/report");

    }

    @GetMapping("/search")
    public String getSearchForm() {
        return "searchreport";
    }


    @PostMapping("/search")
    public String searchReport(
            @Validated SearchRequest request,
            Model model
    ) {
        List<Report> queryResult = Collections.emptyList();

        if (request.getLaborantName() != null & request.getLaborantLastName() != null) {
            queryResult = patientReportService.getReportsByLaborantInfo(
                    request.getLaborantName().toLowerCase(),
                    request.getLaborantLastName().toLowerCase()
            );
        } else if (request.getPatientName() != null & request.getPatientLastName() != null) {
            queryResult = patientReportService.getReportsByPatientInfo(
                    request.getPatientName().toLowerCase(),
                    request.getPatientLastName().toLowerCase()
            );
        } else if (request.getPatientIdentityNum() != null) {
            queryResult = patientReportService.getReportsByPatientIdentityNum(
                    Long.parseLong(request.getPatientIdentityNum())
            );
        }

        model.addAttribute("reportList", queryResult);


        return ("searchresult");
    }


}


