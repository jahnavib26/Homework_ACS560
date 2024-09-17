package com.acs560.restaurantsales.restaurant_sales.services.impl;

import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

    @Override
    public void addSales(Sales sales) {
        try {
            SalesRepository.addSales(sales);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sales data already exists");
        } catch (Exception e) {
            throw new RuntimeException("Error adding sales data");
        }
    }

    @Override
    public void updateSales(Sales sales) {
        try {
            SalesRepository.updateSales(sales);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Sales data not found");
        } catch (Exception e) {
            throw new RuntimeException("Error updating sales data");
        }
    }

    @Override
    public void deleteSales(Sales sales) {
        try {
            SalesRepository.deleteSales(sales);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Sales data not found");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting sales data");
        }
    }
}

