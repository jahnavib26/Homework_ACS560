package com.acs560.restaurantsales.restaurant_sales.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

@Service
public class SalesAnalysisServiceImpl implements SalesAnalysisService {

    @Override
    public double calculateAverageSales(int month) {
        List<Sales> salesList = SalesRepository.getSales();
        OptionalDouble average = salesList.stream()
                        .filter(sales -> sales.getMonth() == month)
                        .mapToDouble(Sales::getAmount)
                        .average();

        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month));
    }

    @Override
    public double calculateAverageSalesRange(int month, int range) {
        List<Sales> salesList = SalesRepository.getSales();
        OptionalDouble average = salesList.stream()
                        .filter(sales -> sales.getMonth() >= (month - range) && sales.getMonth() <= (month + range))
                        .mapToDouble(Sales::getAmount)
                        .average();

        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for the specified range"));
    }

    @Override
    public double getMinSales(int month) {
        List<Sales> salesList = SalesRepository.getSales();
        return salesList.stream()
                        .filter(sales -> sales.getMonth() == month)
                        .min(Comparator.comparingDouble(Sales::getAmount))
                        .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
                        .getAmount();
    }

    @Override
    public double getMaxSales(int month) {
        List<Sales> salesList = SalesRepository.getSales();
        return salesList.stream()
                        .filter(sales -> sales.getMonth() == month)
                        .max(Comparator.comparingDouble(Sales::getAmount))
                        .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
                        .getAmount();
    }
}


