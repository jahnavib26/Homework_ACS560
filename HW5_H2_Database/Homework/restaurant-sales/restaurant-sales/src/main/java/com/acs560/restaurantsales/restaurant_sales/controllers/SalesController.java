package com.acs560.restaurantsales.restaurant_sales.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntityId;
import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    /**
     * Retrieves the list of all sales data.
     * 
     * @return A ResponseEntity containing the list of Sales objects and an HTTP OK status.
     */
    @GetMapping
    public ResponseEntity<List<Sales>> getSales() {
        return ResponseEntity.ok(salesService.getSales());
    }

    /**
     * Adds new sales data.
     * 
     * @param salesEntity - The sales entity to be added.
     * @return A ResponseEntity containing a success message if the operation is successful or an error message in case of failure.
     * @throws IllegalArgumentException if the sales data already exists.
     * @throws Exception for any other internal errors.
     */
    @PostMapping
    public ResponseEntity<String> addSales(@RequestBody SalesEntity salesEntity) {
        try {
            salesService.addSales(salesEntity);
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
     * @param salesEntity - The sales entity with updated data.
     * @return A ResponseEntity containing a success message if the operation is successful or an error message if the data is not found or there is an internal error.
     * @throws NoSuchElementException if the sales data is not found.
     * @throws Exception for any other internal errors.
     */
    @PutMapping
    public ResponseEntity<String> updateSales(@RequestBody SalesEntity salesEntity) {
        try {
            salesService.updateSales(salesEntity);
            return new ResponseEntity<>("Sales updated successfully.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Sales data not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes sales data based on its composite key (SalesEntityId).
     * 
     * @param salesEntityId - The composite key (transactionType and itemName) identifying the sales record to be deleted.
     * @return A ResponseEntity containing a success message if the operation is successful or an error message if the data is not found or there is an internal error.
     * @throws NoSuchElementException if the sales data is not found.
     * @throws Exception for any other internal errors.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteSales(@RequestBody SalesEntityId salesEntityId) {
        try {
            salesService.deleteSales(salesEntityId);
            return new ResponseEntity<>("Sales deleted successfully.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Sales data not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves sales data based on the composite key (saleDate, itemName, transactionType).
     * 
     * @param saleDate - The date of the sale.
     * @param itemName - The name of the sold item.
     * @param transactionType - The type of transaction (e.g., purchase, refund).
     * @return A ResponseEntity containing the list of Sales objects matching the criteria or a 404 status if not found.
     */
    @GetMapping("/{saleDate}/{itemName}/{transactionType}")
    public ResponseEntity<List<Sales>> getSalesById(
            @PathVariable LocalDate saleDate,
            @PathVariable String itemName,
            @PathVariable String transactionType) {
        SalesEntityId salesEntityId = new SalesEntityId(saleDate, itemName, transactionType);
        List<Sales> salesEntity = salesService.getSalesById(saleDate, itemName, transactionType);

        if (salesEntity != null) {
            return ResponseEntity.ok(salesEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves sales data based on non-primary key attributes (itemType and timeOfSale).
     * 
     * @param itemType - The type of the sold item (e.g., food, beverage).
     * @param timeOfSale - The time the sale took place.
     * @return A ResponseEntity containing the list of Sales objects matching the criteria or a 404 status if not found.
     */
    @GetMapping("/{itemType}/{timeOfSale}")
    public ResponseEntity<List<Sales>> getSalesByNonPK(
            @PathVariable String itemType,
            @PathVariable String timeOfSale) {
        List<Sales> salesEntity = salesService.getSalesByIdNonPK(itemType, timeOfSale);

        if (salesEntity != null) {
            return ResponseEntity.ok(salesEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}