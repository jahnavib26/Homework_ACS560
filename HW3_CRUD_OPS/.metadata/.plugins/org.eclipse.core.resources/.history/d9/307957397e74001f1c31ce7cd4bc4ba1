package com.acs560.restaurantsales.restaurant_sales.services.impl;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of SalesService.
 */
@Service
public class SalesServiceImpl implements SalesService {

    // Inject the SalesRepository
    private final SalesRepository salesRepository;

    // Constructor-based injection
    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.getSales();  // Fetch all sales from the repository
    }

    @Override
    public Sales getSaleByDate(String date) {
        // Find a sale by date from the repository (this will need a custom method in the repo)
        return salesRepository.getSales()
                .stream()
                .filter(sale -> sale.getDate().equals(date))
                .findFirst()
                .orElse(null);  // Return null if no sale is found for the given date
    }

    @Override
    public List<Sales> getSalesByItemName(String itemName) {
        // Fetch sales filtered by item name from the repository
        return salesRepository.getSales()
                .stream()
                .filter(sale -> sale.getItemName().equalsIgnoreCase(itemName))
                .toList();
    }

    @Override
    public double calculateMeanSales() {
        // Calculate the mean (average) sales
        return salesRepository.getSales()
                .stream()
                .mapToDouble(Sales::getTransactionAmount)
                .average()
                .orElse(0.0);  // Return 0.0 if there are no sales
    }
}
