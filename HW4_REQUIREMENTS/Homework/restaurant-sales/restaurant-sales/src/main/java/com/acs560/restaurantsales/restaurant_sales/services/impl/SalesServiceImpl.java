package com.acs560.restaurantsales.restaurant_sales.services.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

    /**
     * Retrieve the list of all sales data.
     *
     * @return a list of sales records
     */
    @Override
    public List<Sales> getSales() {
        return SalesRepository.getSales();
    }

    /**
     * Add a new sales record.
     *
     * @param sales the sales data to be added
     * @throws IllegalArgumentException if the sales data already exists
     * @throws RuntimeException if there is an error adding the sales data
     */
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

    /**
     * Update an existing sales record.
     *
     * @param sales the sales data to be updated
     * @throws NoSuchElementException if the sales data is not found
     * @throws RuntimeException if there is an error updating the sales data
     */
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

    /**
     * Delete an existing sales record.
     *
     * @param sales the sales data to be deleted
     * @throws NoSuchElementException if the sales data is not found
     * @throws RuntimeException if there is an error deleting the sales data
     */
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
