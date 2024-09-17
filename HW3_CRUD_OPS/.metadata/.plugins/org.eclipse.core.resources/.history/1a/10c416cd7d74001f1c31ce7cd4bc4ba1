package com.acs560.restaurantsales.restaurant_sales.controllers;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/sales")
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/sales/{date}")
    public Sales getSaleByDate(@PathVariable String date) {
        return salesService.getSaleByDate(date);
    }

    @GetMapping("/sales/item")
    public List<Sales> getSalesByItemName(@RequestParam String itemName) {
        return salesService.getSalesByItemName(itemName);
    }

    @GetMapping("/sales/mean")
    public double getMeanSales() {
        return salesService.calculateMeanSales();
    }
}
