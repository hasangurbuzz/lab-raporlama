package com.hasan.laboratuvarraporlama.controller;

import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {


    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/all")
    public String getAllData(Model model) {
        List<Report> allData = reportService.getReports();
        model.addAttribute("allReports", allData);

        return "report";


    }

}
