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
     * Retrieves a list of all sales data.
     * 
     * @return a ResponseEntity containing the list of sales and an HTTP status code
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
     * Adds new sales data.
     * 
     * @param sales - the sales data to be added
     * @return a ResponseEntity containing a message and an HTTP status code
     * @throws IllegalArgumentException if the sales data already exists
     * @throws Exception if an internal server error occurs
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
     * Updates existing sales data.
     * 
     * @param sales - the sales data to be updated
     * @return a ResponseEntity containing a message and an HTTP status code
     * @throws NoSuchElementException if the sales data does not exist
     * @throws Exception if an internal server error occurs
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
     * Deletes existing sales data.
     * 
     * @param sales - the sales data to be deleted
     * @return a ResponseEntity containing a message and an HTTP status code
     * @throws NoSuchElementException if the sales data does not exist
     * @throws Exception if an internal server error occurs
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
