package com.acs560.restaurantsales.restaurant_sales.services.impl;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesAnalysisServiceImpl implements SalesAnalysisService {

    private final SalesRepository salesRepository;

    public SalesAnalysisServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public double calculateAverageSalesByItem(String itemName, String month) {
        List<Sales> sales = salesRepository.getSales()
            .stream()
            .filter(sale -> sale.getItemName().equalsIgnoreCase(itemName)
                         && sale.getYearMonth().equals(month))
            .collect(Collectors.toList());

        return sales.stream()
            .mapToDouble(Sales::getTransactionAmount)
            .average()
            .orElse(0.0);
    }

    @Override
    public double calculateAverageSalesByItem(String itemName, String month, int range) {
        List<Sales> sales = salesRepository.getSales()
            .stream()
            .filter(sale -> sale.getItemName().equalsIgnoreCase(itemName)
                         && sale.getYearMonth().equals(month))
            .limit(range)
            .collect(Collectors.toList());

        return sales.stream()
            .mapToDouble(Sales::getTransactionAmount)
            .average()
            .orElse(0.0);
    }

    // New implementation for max sales by item and month
    @Override
    public double calculateMaxSalesByItem(String itemName, String month) {
        List<Sales> sales = salesRepository.getSales()
            .stream()
            .filter(sale -> sale.getItemName().equalsIgnoreCase(itemName)
                         && sale.getYearMonth().substring(5).equals(month))  // Extract month from 'YYYY-MM'
            .collect(Collectors.toList());

        return sales.stream()
            .mapToDouble(Sales::getTransactionAmount)
            .max()
            .orElse(0.0);  // Return 0.0 if no sales are found
    }


    // New implementation for min sales by item and month
    @Override
    public double calculateMinSalesByItem(String itemName, String month) {
        List<Sales> sales = salesRepository.getSales()
            .stream()
            .filter(sale -> sale.getItemName().equalsIgnoreCase(itemName)
                         && sale.getYearMonth().substring(5).equals(month))  // Extract month from 'YYYY-MM'
            .collect(Collectors.toList());

        return sales.stream()
            .mapToDouble(Sales::getTransactionAmount)
            .min()
            .orElse(0.0);  // Return 0.0 if no sales are found
    }

}
