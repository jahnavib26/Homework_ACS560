package com.acs560.restaurantsales.restaurant_sales.services.impl;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesAnalysisServiceImpl implements SalesAnalysisService {

    private final SalesRepository salesRepository;

    public SalesAnalysisServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public double calculateAverageSalesByItem(String itemName) {
        List<Sales> sales = salesRepository.findByItemName(itemName);
        return sales.stream().mapToDouble(Sales::getTransactionAmount).average().orElse(0);
    }

    @Override
    public double calculateMaxSalesByItem(String itemName) {
        List<Sales> sales = salesRepository.findByItemName(itemName);
        return sales.stream().mapToDouble(Sales::getTransactionAmount).max().orElse(0);
    }

    @Override
    public double calculateMinSalesByItem(String itemName) {
        List<Sales> sales = salesRepository.findByItemName(itemName);
        return sales.stream().mapToDouble(Sales::getTransactionAmount).min().orElse(0);
    }
}
