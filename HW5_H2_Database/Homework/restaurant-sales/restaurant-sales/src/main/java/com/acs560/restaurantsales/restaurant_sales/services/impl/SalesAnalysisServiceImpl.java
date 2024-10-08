package com.acs560.restaurantsales.restaurant_sales.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

@Service
public class SalesAnalysisServiceImpl implements SalesAnalysisService {

//	@Override
//	public double calculateAverageSales(int month) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double calculateAverageSalesRange(int month, int range) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double getMinSales(int month) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double getMaxSales(int month) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public String getMostSellingProduct() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Autowired
//	SalesRepository salesRepository;
//	
//	@Override
//    public double calculateAverageSales(int month) {
//        List<Sales> salesList = salesRepository.getSales();
//
//        // Filter sales for the given month and calculate the average transaction amount
//        OptionalDouble average = salesList.stream()
//                        .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
//                        .mapToDouble(Sales::getTransactionAmount)
//                        .average();
//
//        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month));
//    }
//
//    @Override
//    public double calculateAverageSalesRange(int month, int range) {
//        List<Sales> salesList = salesRepository.getSales();
//
//        // Filter sales for the given range of months and calculate the average transaction amount
//        OptionalDouble average = salesList.stream()
//                        .filter(sales -> {
//                            int salesMonth = getMonthFromYearMonth(sales.getYearMonth());
//                            return salesMonth >= (month - range) && salesMonth <= (month + range);
//                        })
//                        .mapToDouble(Sales::getTransactionAmount)
//                        .average();
//
//        return average.orElseThrow(() -> new NoSuchElementException("No sales data found for the specified range"));
//    }
//
//    @Override
//    public double getMinSales(int month) {
//        List<Sales> salesList = salesRepository.getSales();
//
//        // Filter by the given month and find the minimum transaction amount
//        return salesList.stream()
//                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
//                .min(Comparator.comparingDouble(Sales::getTransactionAmount))
//                .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
//                .getTransactionAmount();  // Return the transaction amount of the minimum sale
//    }
//
//    @Override
//    public double getMaxSales(int month) {
//        List<Sales> salesList = salesRepository.getSales();
//
//        // Filter by the given month and find the maximum transaction amount
//        return salesList.stream()
//                .filter(sales -> getMonthFromYearMonth(sales.getYearMonth()) == month)
//                .max(Comparator.comparingDouble(Sales::getTransactionAmount))
//                .orElseThrow(() -> new NoSuchElementException("No sales data found for month: " + month))
//                .getTransactionAmount();  // Return the transaction amount of the maximum sale
//    }
//
//    public String getMostSellingProduct() {
//        List<Sales> salesList = salesRepository.getSales();
//        if (salesList.isEmpty()) {
//            return "No sales data available.";
//        }
//
//        return salesList.stream()
//            .collect(Collectors.groupingBy(Sales::getItemName, Collectors.summingDouble(Sales::getTransactionAmount)))
//            .entrySet()
//            .stream()
//            .max(Map.Entry.comparingByValue())
//            .orElseThrow(() -> new NoSuchElementException("No sales data found"))
//            .getKey();
//    }
//
//    // Helper method to extract month from the Year-Month string (e.g., "2023-03")
//    private int getMonthFromYearMonth(String yearMonth) {
//        return Integer.parseInt(yearMonth.split("-")[1]);  // Extract the month as an integer
//    }
}
