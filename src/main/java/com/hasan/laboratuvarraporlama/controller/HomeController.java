package com.hasan.laboratuvarraporlama.controller;

import com.hasan.laboratuvarraporlama.model.Report;
import com.hasan.laboratuvarraporlama.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final ReportService reportService;

    public HomeController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }


    @PostMapping(value = "/")
    public String postMethod(
            @ModelAttribute("report") Report report
    ) {
        reportService.saveReport(report);

        return "index";
    }
}
