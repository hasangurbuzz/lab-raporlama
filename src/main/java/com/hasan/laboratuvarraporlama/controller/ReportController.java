package com.hasan.laboratuvarraporlama.controller;

import com.hasan.laboratuvarraporlama.dto.ReportRequest;
import com.hasan.laboratuvarraporlama.mapper.ReportMapper;
import com.hasan.laboratuvarraporlama.model.Laborant;
import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {


    private final ReportService reportService;
    private final ReportMapper reportMapper;


    public ReportController(ReportService reportService,
                            ReportMapper reportMapper) {
        this.reportService = reportService;
        this.reportMapper = reportMapper;

    }


    @GetMapping
    public String getAllReports(Model model) {

        List<Report> reportList = reportService.getReports();

        model.addAttribute("reportList", reportList);

        return "report";

    }

    @GetMapping("/{fileNum}")
    public String getWithFileNum(@PathVariable Integer fileNum, Model model) {
        Report report = reportService.getReportById(fileNum);

        model.addAttribute("report", report);
        return "editReport";
    }


    @GetMapping("/add")
    public String reportPanel() {
        return "addReport";
    }


    @PostMapping(value = "/add")
    public RedirectView createReport(
            @Validated ReportRequest reportRequest
    ) throws IOException {

        Report report = reportMapper.mapToReport(reportRequest);

        reportService.saveReport(report);

        return new RedirectView("/report");
    }

    @PostMapping("/update")
    public RedirectView updateReport(
            @Validated ReportRequest reportRequest
    ) throws IOException {

        Report report = reportMapper.mapToReport(reportRequest);

        reportService.updateReport(report, reportRequest.getFileNum());


        return new RedirectView("/report");
    }

    @PostMapping("/{fileNum}")
    public RedirectView deleteReport(
            @PathVariable Integer fileNum
    ) {

        reportService.deleteReportById(fileNum);

        return new RedirectView("/report");

    }


}
