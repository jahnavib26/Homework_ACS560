package com.acs560.restaurantsales.restaurant_sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

@RestController
@RequestMapping("/api/v1/salesAnalysis")
public class SalesAnalysisController {

    @Autowired
    private SalesAnalysisService salesAnalysisService;

    // Calculate average sales for a specific month
    @GetMapping("/average/{month}")
    public double getAverageSales(@PathVariable int month) {
        return salesAnalysisService.calculateAverageSales(month);
    }

    // Calculate average sales over a range of months
    @GetMapping("/average/{month}/{range}")
    public double getAverageSalesRange(@PathVariable int month, @PathVariable int range) {
        return salesAnalysisService.calculateAverageSalesRange(month, range);
    }

    // Get the minimum sales amount for a specific month
    @GetMapping("/min/{month}")
    public double getMinSales(@PathVariable int month) {
        return salesAnalysisService.getMinSales(month);
    }

    // Get the maximum sales amount for a specific month
    @GetMapping("/max/{month}")
    public double getMaxSales(@PathVariable int month) {
        return salesAnalysisService.getMaxSales(month);
    }
    
//    @GetMapping("/most-selling")
//    public String getMostsellingProduct() {
//        return salesAnalysisService.getMostSellingProduct();
//    }
}

