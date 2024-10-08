package com.acs560.restaurantsales.restaurant_sales.repositories;

import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntityId;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing {@link SalesEntity} objects in the database.
 * Extends the {@link CrudRepository} interface to provide CRUD operations.
 */
public interface SalesRepository extends CrudRepository<SalesEntity, SalesEntityId> {

    /**
     * Retrieves all sales entities from the database.
     *
     * @return A list of all sales entities.
     */
    @Query(value = "SELECT * FROM sales", nativeQuery = true)
    List<SalesEntity> findAll();

    /**
     * Saves a sales entity to the database.
     * Overrides the save method of the {@link CrudRepository}.
     *
     * @param entity - The sales entity to be saved.
     * @param <S> - The type of the sales entity.
     * @return The saved sales entity.
     */
    @Override
    <S extends SalesEntity> S save(S entity);

    /**
     * Deletes a sales entity by its composite primary key (SalesEntityId).
     * Overrides the deleteById method of the {@link CrudRepository}.
     *
     * @param id - The composite primary key identifying the sales entity to delete.
     */
    @Override
    void deleteById(SalesEntityId id);

    /**
     * Retrieves sales entities based on the primary key fields: sale date, item name, and transaction type.
     *
     * @param saleDate - The date of the sale.
     * @param itemName - The name of the sold item.
     * @param transactionType - The type of the transaction (e.g., purchase, refund).
     * @return A list of sales entities that match the specified parameters.
     */
    @Query(value = """
            SELECT * FROM sales 
            WHERE sale_date = :saleDate 
            AND item_name = :itemName 
            AND transaction_type = :transactionType
            """, nativeQuery = true)
    List<SalesEntity> getSalesById(
            @Param("saleDate") LocalDate saleDate,
            @Param("itemName") String itemName,
            @Param("transactionType") String transactionType);

    /**
     * Retrieves sales entities based on non-primary key fields: item type and time of sale.
     *
     * @param itemType - The type of the item (e.g., food, beverage).
     * @param timeOfSale - The time when the sale occurred.
     * @return A list of sales entities that match the specified item type and time of sale.
     */
    @Query(value = """
            SELECT * FROM sales 
            WHERE item_type = :itemType 
            AND time_of_sale = :timeOfSale 
            """, nativeQuery = true)
    List<SalesEntity> getSalesByNonPK(
            @Param("itemType") String itemType,
            @Param("timeOfSale") String timeOfSale);
}
