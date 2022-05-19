package com.hasan.laboratuvarraporlama.controller;

import com.hasan.laboratuvarraporlama.dto.ReportRequest;
import com.hasan.laboratuvarraporlama.mapper.ReportMapper;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.model.SearchRequest;
import com.hasan.laboratuvarraporlama.service.LaborantService;
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


    private final LaborantService laborantService;
    private final ReportMapper reportMapper;


    public ReportController(
            LaborantService laborantService, ReportMapper reportMapper) {
        this.laborantService = laborantService;
        this.reportMapper = reportMapper;

    }


    @GetMapping
    public String getAllReports(
            Model model
    ) {

        List<Report> reportList = laborantService.getReports();

        model.addAttribute("reportList", reportList);

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

        Report report = reportMapper.mapToReport(reportRequest);
        laborantService.saveReport(report);

        return new RedirectView("/report");
    }


    @GetMapping("/{fileNum}")
    public String getReportDetails(
            @PathVariable Integer fileNum,
            @RequestParam(name = "editmode", required = false) Optional<String> editMode,
            Model model
    ) {
        Report report = laborantService.getReportById(fileNum);

        model.addAttribute("report", report);


        if (editMode.isPresent()) return "reporteditor";
        else return "reportdetail";

    }


    @PostMapping("/update")
    public RedirectView updateReport(
            @Validated ReportRequest reportRequest
    ) throws IOException {

        Report report = reportMapper.mapToReport(reportRequest);
        laborantService.updateReport(report, reportRequest.getFileNum());


        return new RedirectView("/report/" + reportRequest.getFileNum());
    }


    @PostMapping("/delete/{fileNum}")
    public RedirectView deleteReport(
            @PathVariable Integer fileNum
    ) {

        laborantService.deleteReportById(fileNum);

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
            queryResult = laborantService.getReportsByLaborantInfo(
                    request.getLaborantName(),
                    request.getLaborantLastName()
            );
        } else if (request.getPatientName() != null & request.getPatientLastName() != null) {
            queryResult = laborantService.getReportsByPatientInfo(
                    request.getPatientName(),
                    request.getPatientLastName()
            );
        } else if (request.getPatientIdentityNum() != null) {
            queryResult = laborantService.getReportsByPatientIdentityNum(
                    request.getPatientIdentityNum()
            );
        }

        model.addAttribute("reportList", queryResult);


        return ("searchresult");
    }


}


