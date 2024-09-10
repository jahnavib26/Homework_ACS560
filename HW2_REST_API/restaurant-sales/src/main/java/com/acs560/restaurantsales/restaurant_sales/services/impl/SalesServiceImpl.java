package com.acs560.restaurantsales.restaurant_sales.services.impl;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAllSales();
    }

    @Override
    public Sales getSaleByDate(String date) {
        return salesRepository.findByDate(date).orElse(null);
    }

    @Override
    public List<Sales> getSalesByItemName(String itemName) {
        return salesRepository.findByItemName(itemName);
    }

    @Override
    public double calculateMeanSales() {
        return salesRepository.findAllSales().stream().mapToDouble(Sales::getTransactionAmount).average().orElse(0);
    }
}
