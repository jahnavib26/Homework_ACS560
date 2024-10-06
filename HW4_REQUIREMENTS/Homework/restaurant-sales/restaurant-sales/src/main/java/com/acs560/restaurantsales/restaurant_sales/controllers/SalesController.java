package com.acs560.restaurantsales.restaurant_sales.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    /**
     * Get the list of all sales data.
     *
     * @return a list of sales data wrapped in a ResponseEntity with HTTP status OK,
     * or an INTERNAL_SERVER_ERROR in case of an error
     */
    @GetMapping
    public ResponseEntity<List<Sales>> getSales() {
        try {
            List<Sales> salesList = salesService.getSales();
            return new ResponseEntity<>(salesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add new sales data.
     *
     * @param sales the sales data to be added
     * @return a message indicating success or failure, wrapped in a ResponseEntity with appropriate HTTP status
     * @throws IllegalArgumentException if the sales data already exists
     */
    @PostMapping
    public ResponseEntity<String> addSales(@RequestBody Sales sales) {
        try {
            salesService.addSales(sales);
            return new ResponseEntity<>("Sales added successfully.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Sales data already exists.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update existing sales data.
     *
     * @param sales the sales data to be updated
     * @return a message indicating success or failure, wrapped in a ResponseEntity with appropriate HTTP status
     * @throws NoSuchElementException if the sales data to update is not found
     */
    @PutMapping
    public ResponseEntity<String> updateSales(@RequestBody Sales sales) {
        try {
            salesService.updateSales(sales);
            return new ResponseEntity<>("Sales updated successfully.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Sales data not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete sales data.
     *
     * @param sales the sales data to be deleted
     * @return a message indicating success or failure, wrapped in a ResponseEntity with appropriate HTTP status
     * @throws NoSuchElementException if the sales data to delete is not found
     */
    @DeleteMapping
    public ResponseEntity<String> deleteSales(@RequestBody Sales sales) {
        try {
            salesService.deleteSales(sales);
            return new ResponseEntity<>("Sales deleted successfully.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Sales data not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
