package com.acs560.restaurantsales.restaurant_sales.services;

import java.time.LocalDate;
import java.util.List;

import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntityId;
import com.acs560.restaurantsales.restaurant_sales.models.Sales;

/**
 * Service interface for handling business logic related to sales.
 * Provides methods for adding, updating, deleting, and retrieving sales data.
 */
public interface SalesService {

    /**
     * Adds a new sales record.
     *
     * @param salesEntity - The sales entity to be added.
     */
    void addSales(SalesEntity salesEntity);

    /**
     * Updates an existing sales record.
     *
     * @param salesEntity - The sales entity with updated data.
     */
    void updateSales(SalesEntity salesEntity);

    /**
     * Deletes a sales record identified by its composite key.
     *
     * @param salesEntityId - The composite key identifying the sales record to be deleted.
     */
    void deleteSales(SalesEntityId salesEntityId);

    /**
     * Retrieves all sales records.
     *
     * @return A list of sales data as {@link Sales} objects.
     */
    List<Sales> getSales();

    /**
     * Retrieves sales records based on the primary key attributes: sale date, item name, and transaction type.
     *
     * @param saleDate - The date of the sale.
     * @param itemName - The name of the sold item.
     * @param transactionType - The type of transaction (e.g., purchase, refund).
     * @return A list of sales data that match the specified attributes.
     */
    List<Sales> getSalesById(LocalDate saleDate, String itemName, String transactionType);

    /**
     * Retrieves sales records based on non-primary key attributes: item type and time of sale.
     *
     * @param itemType - The type of the item (e.g., food, beverage).
     * @param timeOfSale - The time when the sale occurred.
     * @return A list of sales data that match the specified attributes.
     */
    List<Sales> getSalesByIdNonPK(String itemType, String timeOfSale);
}