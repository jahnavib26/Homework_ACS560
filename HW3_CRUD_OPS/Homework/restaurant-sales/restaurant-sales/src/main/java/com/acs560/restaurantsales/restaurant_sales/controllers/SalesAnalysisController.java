package com.acs560.restaurantsales.restaurant_sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

@RestController
@RequestMapping("/api/v1/salesAnalysis")
public class SalesAnalysisController {

    @Autowired
    private SalesAnalysisService salesAnalysisService;

    /**
     * Calculates the average sales for a specific month.
     *
     * @param month - the month for which the average sales is calculated
     * @return the average sales for the given month
     */
    @GetMapping("/average/{month}")
    public double getAverageSales(@PathVariable int month) {
        return salesAnalysisService.calculateAverageSales(month);
    }

    /**
     * Calculates the average sales over a range of months.
     *
     * @param month - the starting month for the sales range
     * @param range - the number of months to include in the range
     * @return the average sales over the specified range of months
     */
    @GetMapping("/average/{month}/{range}")
    public double getAverageSalesRange(@PathVariable int month, @PathVariable int range) {
        return salesAnalysisService.calculateAverageSalesRange(month, range);
    }

    /**
     * Retrieves the minimum sales amount for a specific month.
     *
     * @param month - the month for which the minimum sales amount is retrieved
     * @return the minimum sales amount for the given month
     */
    @GetMapping("/min/{month}")
    public double getMinSales(@PathVariable int month) {
        return salesAnalysisService.getMinSales(month);
    }

    /**
     * Retrieves the maximum sales amount for a specific month.
     *
     * @param month - the month for which the maximum sales amount is retrieved
     * @return the maximum sales amount for the given month
     */
    @GetMapping("/max/{month}")
    public double getMaxSales(@PathVariable int month) {
        return salesAnalysisService.getMaxSales(month);
    }
    
    /**
     * Retrieves the most selling product.
     * 
     * @return the name of the most selling product
     */
    // Uncomment this method if you need to implement it
    // @GetMapping("/most-selling")
    // public String getMostsellingProduct() {
    //     return salesAnalysisService.getMostSellingProduct();
    // }
}
