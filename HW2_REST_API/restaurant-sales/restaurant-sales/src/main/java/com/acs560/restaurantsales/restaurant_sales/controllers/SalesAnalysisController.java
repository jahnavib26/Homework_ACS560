package com.acs560.restaurantsales.restaurant_sales.controllers;

import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/salesAnalysis")
public class SalesAnalysisController {

    private final SalesAnalysisService salesAnalysisService;

    public SalesAnalysisController(SalesAnalysisService salesAnalysisService) {
        this.salesAnalysisService = salesAnalysisService;
    }

    @GetMapping("/sales/analysis/average")
    public double getAverageSales(@RequestParam String itemName) {
        return salesAnalysisService.calculateAverageSalesByItem(itemName);
    }

    @GetMapping("/sales/analysis/max")
    public double getMaxSales(@RequestParam String itemName) {
        return salesAnalysisService.calculateMaxSalesByItem(itemName);
    }

    @GetMapping("/sales/analysis/min")
    public double getMinSales(@RequestParam String itemName) {
        return salesAnalysisService.calculateMinSalesByItem(itemName);
    }
}