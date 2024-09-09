package com.acs560.bills_analyzer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acs560.bills_analyzer.services.BillsAnalysisService;

import lombok.NoArgsConstructor;

@RestController()
@RequestMapping("/api/v1/billsAnalysis")
@NoArgsConstructor
public class BillsAnalysisController {

    @Autowired
    private BillsAnalysisService billsAnalysisService;

    @GetMapping("/average/{name}/{month}")
    public double getAverage(String name, int month) {
        return billsAnalysisService.calculateAverage(name, month);
    }

    @GetMapping("/average/{name}/{month}/{range}")
    public double getAverage(String name, int month, int range) {
        return billsAnalysisService.calculateAverage(name, month, range);
    }

    @GetMapping("/mostSellingItem")
    public String getMostSellingItemReport() {
        return billsAnalysisService.getMostSellingItemReport();
    }
}
