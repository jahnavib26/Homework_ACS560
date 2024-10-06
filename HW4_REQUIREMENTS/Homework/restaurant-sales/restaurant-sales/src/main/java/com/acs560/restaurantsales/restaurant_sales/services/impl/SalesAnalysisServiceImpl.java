package com.acs560.restaurantsales.restaurant_sales.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

@Service
public class SalesAnalysisServiceImpl implements SalesAnalysisService {

    /**
     * Calculate the average sales transaction amount for a specific month.
     *
     * @param month the month for which the average sales should be calculated
     * @return the average sales transaction amount for the specified month
     * @throws NoSuchElementException if no sales data is found for the specified month
     */
    @Override
    public double calculateAverageSales(int month) {
        List<Sales> salesList = SalesRepository.getSales();

        OptionalDouble average = salesList.stream()
                        .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
                        .mapToDouble(Sales::getTransactionAmount)
                        .average();

        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month));
    }

    /**
     * Calculate the average sales transaction amount for a range of months centered on a given month.
     *
     * @param month the center month for the range
     * @param range the range of months before and after the specified month
     * @return the average sales transaction amount for the specified range of months
     * @throws NoSuchElementException if no sales data is found for the specified range of months
     */
    @Override
    public double calculateAverageSalesRange(int month, int range) {
        List<Sales> salesList = SalesRepository.getSales();

        OptionalDouble average = salesList.stream()
                        .filter(sales -> {
                            int salesMonth = getMonthFromYearMonth(sales.getYearMonth());
                            return salesMonth >= (month - range) && salesMonth <= (month + range);
                        })
                        .mapToDouble(Sales::getTransactionAmount)
                        .average();

        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for the specified range"));
    }

    /**
     * Get the minimum sales transaction amount for a specific month.
     *
     * @param month the month for which the minimum sales should be retrieved
     * @return the minimum sales transaction amount for the specified month
     * @throws NoSuchElementException if no sales data is found for the specified month
     */
    @Override
    public double getMinSales(int month) {
        List<Sales> salesList = SalesRepository.getSales();

        return salesList.stream()
                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
                .min(Comparator.comparingDouble(Sales::getTransactionAmount))
                .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
                .getTransactionAmount();
    }

    /**
     * Get the maximum sales transaction amount for a specific month.
     *
     * @param month the month for which the maximum sales should be retrieved
     * @return the maximum sales transaction amount for the specified month
     * @throws NoSuchElementException if no sales data is found for the specified month
     */
    @Override
    public double getMaxSales(int month) {
        List<Sales> salesList = SalesRepository.getSales();

        return salesList.stream()
                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
                .max(Comparator.comparingDouble(Sales::getTransactionAmount))
                .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
                .getTransactionAmount();
    }

    /**
     * Get the most selling product based on the highest total transaction amount.
     *
     * @return the name of the most selling product
     * @throws NoSuchElementException if no sales data is found
     */
    public String getMostSellingProduct() {
        List<Sales> salesList = SalesRepository.getSales();
        if (salesList.isEmpty()) {
            return "No sales data available.";
        }

        return salesList.stream()
            .collect(Collectors.groupingBy(Sales::getItemName, Collectors.summingDouble(Sales::getTransactionAmount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow(() -> new NoSuchElementException("No sales data found"))
            .getKey();
    }

    /**
     * Helper method to extract the month from a 'Year-Month' formatted string (e.g., "2023-03").
     *
     * @param yearMonth the 'Year-Month' string
     * @return the month as an integer
     */
    private int getMonthFromYearMonth(String yearMonth) {
        return Integer.parseInt(yearMonth.split("-")[1]);
    }
}
