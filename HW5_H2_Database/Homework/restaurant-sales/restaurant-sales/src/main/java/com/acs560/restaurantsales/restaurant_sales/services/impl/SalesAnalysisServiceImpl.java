package com.acs560.restaurantsales.restaurant_sales.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

/**
 * Implementation of the {@link SalesAnalysisService} interface that provides business logic
 * for analyzing sales data, such as calculating minimum, maximum, and average sales, and finding the most selling product.
 */
@Service
public class SalesAnalysisServiceImpl implements SalesAnalysisService {

    @Autowired
    SalesRepository salesRepository;

    /**
     * Retrieves the minimum sales transaction amount for a specific month.
     *
     * @param month - The month for which to retrieve the minimum sales (1-12).
     * @return The minimum transaction amount for the given month.
     * @throws NoSuchElementException if no sales data is found for the specified month.
     */
    @Override
    public double getMinSales(int month) {
        List<SalesEntity> salesList = salesRepository.findAll();

        return salesList.stream()
                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
                .min(Comparator.comparingDouble(SalesEntity::getTransactionAmount))
                .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
                .getTransactionAmount();
    }

    /**
     * Retrieves the maximum sales transaction amount for a specific month.
     *
     * @param month - The month for which to retrieve the maximum sales (1-12).
     * @return The maximum transaction amount for the given month.
     * @throws NoSuchElementException if no sales data is found for the specified month.
     */
    @Override
    public double getMaxSales(int month) {
        List<SalesEntity> salesList = salesRepository.findAll();

        return salesList.stream()
                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
                .max(Comparator.comparingDouble(SalesEntity::getTransactionAmount))
                .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
                .getTransactionAmount();
    }

    /**
     * Retrieves the most selling product based on the total transaction amount.
     *
     * @return The name of the most selling product.
     * @throws NoSuchElementException if no sales data is available.
     */
    @Override
    public String getMostSellingProduct() {
        List<SalesEntity> salesList = salesRepository.findAll();
        if (salesList.isEmpty()) {
            return "No sales data available.";
        }

        return salesList.stream()
                .collect(Collectors.groupingBy(sales -> sales.getId().getItemName(),
                        Collectors.summingDouble(SalesEntity::getTransactionAmount)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("No sales data found"))
                .getKey();
    }

    /**
     * Calculates the average sales transaction amount for a specific month.
     *
     * @param month - The month for which to calculate the average sales (1-12).
     * @return The average transaction amount for the given month.
     * @throws NoSuchElementException if no sales data is found for the specified month.
     */
    @Override
    public double calculateAverageSales(int month) {
        List<SalesEntity> salesList = salesRepository.findAll();

        OptionalDouble average = salesList.stream()
                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
                .mapToDouble(SalesEntity::getTransactionAmount)
                .average();

        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month));
    }

    /**
     * Helper method to extract the month from a "year-month" formatted string.
     *
     * @param yearMonth - The string in the format "YYYY-MM".
     * @return The month as an integer.
     */
    private int getMonthFromYearMonth(String yearMonth) {
        return Integer.parseInt(yearMonth.split("-")[1]);
    }
}
