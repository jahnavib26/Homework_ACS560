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
     * Retrieves the list of all sales from the repository.
     *
     * @return a list of Sales objects
     */
    @Override
    public List<Sales> getSales() {
        return SalesRepository.getSales();
    }

    /**
     * Adds a new sales entry to the repository.
     *
     * @param sales - the sales data to be added
     * @throws IllegalArgumentException if the sales data already exists
     * @throws RuntimeException if an error occurs while adding the sales data
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
     * Updates an existing sales entry in the repository.
     *
     * @param sales - the sales data to be updated
     * @throws NoSuchElementException if the sales data is not found
     * @throws RuntimeException if an error occurs while updating the sales data
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
     * Deletes a sales entry from the repository.
     *
     * @param sales - the sales data to be deleted
     * @throws NoSuchElementException if the sales data is not found
     * @throws RuntimeException if an error occurs while deleting the sales data
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
