package com.acs560.restaurantsales.restaurant_sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

/**
 * REST Controller responsible for handling sales analysis-related requests.
 * Provides endpoints for calculating average sales, minimum sales, maximum sales, and retrieving the most selling product.
 */
@RestController
@RequestMapping("/api/v1/salesAnalysis")
public class SalesAnalysisController {

    @Autowired
    private SalesAnalysisService salesAnalysisService;

    /**
     * Calculates the average sales for a specific month.
     * 
     * @param month - The month for which to calculate the average sales (1-12).
     * @return The average sales for the specified month.
     */
    @GetMapping("/average/{month}")
    public double getAverageSales(@PathVariable int month) {
        return salesAnalysisService.calculateAverageSales(month);
    }

    /**
     * Retrieves the minimum sales amount for a specific month.
     * 
     * @param month - The month for which to retrieve the minimum sales (1-12).
     * @return The minimum sales amount for the specified month.
     */
    @GetMapping("/min/{month}")
    public double getMinSales(@PathVariable int month) {
        return salesAnalysisService.getMinSales(month);
    }

    /**
     * Retrieves the maximum sales amount for a specific month.
     * 
     * @param month - The month for which to retrieve the maximum sales (1-12).
     * @return The maximum sales amount for the specified month.
     */
    @GetMapping("/max/{month}")
    public double getMaxSales(@PathVariable int month) {
        return salesAnalysisService.getMaxSales(month);
    }

    /**
     * Retrieves the most selling product.
     * 
     * @return The name of the most selling product.
     */
    @GetMapping("/most-selling")
    public String getMostSellingProduct() {
        return salesAnalysisService.getMostSellingProduct();
    }
}
