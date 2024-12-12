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

    /**
     * Calculates the average sales transaction amount for a specific month.
     *
     * @param month - the month for which the average sales is calculated
     * @return the average sales transaction amount for the specified month
     * @throws NoSuchElementException if no sales data is found for the given month
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
     * Calculates the average sales transaction amount over a range of months.
     *
     * @param month - the starting month for the range
     * @param range - the range of months to include in the calculation
     * @return the average sales transaction amount over the specified range
     * @throws NoSuchElementException if no sales data is found for the given range
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
     * Retrieves the minimum sales transaction amount for a specific month.
     *
     * @param month - the month for which the minimum sales is retrieved
     * @return the minimum sales transaction amount for the specified month
     * @throws NoSuchElementException if no sales data is found for the given month
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
     * Retrieves the maximum sales transaction amount for a specific month.
     *
     * @param month - the month for which the maximum sales is retrieved
     * @return the maximum sales transaction amount for the specified month
     * @throws NoSuchElementException if no sales data is found for the given month
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

 
    /*
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
    */

    /**
     * Extracts the month as an integer from the Year-Month string.
     *
     * @param yearMonth - the Year-Month string (e.g., "2023-03")
     * @return the month as an integer
     */
    private int getMonthFromYearMonth(String yearMonth) {
        return Integer.parseInt(yearMonth.split("-")[1]);
    }
}
