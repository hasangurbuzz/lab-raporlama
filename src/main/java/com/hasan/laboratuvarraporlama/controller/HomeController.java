package com.hasan.laboratuvarraporlama.controller;

import com.hasan.laboratuvarraporlama.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ReportService reportService;


    public HomeController(ReportService reportService ) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }



}
