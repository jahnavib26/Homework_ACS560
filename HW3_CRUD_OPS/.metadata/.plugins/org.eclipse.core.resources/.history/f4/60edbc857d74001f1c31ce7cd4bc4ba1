package com.acs560.restaurantsales.restaurant_sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

import lombok.NoArgsConstructor;

@RestController()
@RequestMapping("/api/v1/salesAnalysis")
@NoArgsConstructor
public class SalesAnalysisController {
    
    @Autowired
    private SalesAnalysisService salesAnalysisService;

    public SalesAnalysisController(SalesAnalysisService salesAnalysisService) {
        this.salesAnalysisService = salesAnalysisService;
    }

    // Route to get average sales by item and month
    @GetMapping("/average/{itemName}/{month}")
    public double getAverage(@PathVariable String itemName, @PathVariable String month) {
        return salesAnalysisService.calculateAverageSalesByItem(itemName, month);
    }

    // Route to get average sales by item, month, and range
    @GetMapping("/average/{itemName}/{month}/{range}")
    public double getAverageWithRange(@PathVariable String itemName, @PathVariable String month,
                                      @PathVariable int range) {
        return salesAnalysisService.calculateAverageSalesByItem(itemName, month, range);
    }

    // Route to get max sales by item and month
    @GetMapping("/max/{itemName}/{month}")
    public double getMaxSales(@PathVariable String itemName, @PathVariable String month) {
        return salesAnalysisService.calculateMaxSalesByItem(itemName, month);
    }

    // Route to get min sales by item and month
    @GetMapping("/min/{itemName}/{month}")
    public double getMinSales(@PathVariable String itemName, @PathVariable String month) {
        return salesAnalysisService.calculateMinSalesByItem(itemName, month);
    }
}
