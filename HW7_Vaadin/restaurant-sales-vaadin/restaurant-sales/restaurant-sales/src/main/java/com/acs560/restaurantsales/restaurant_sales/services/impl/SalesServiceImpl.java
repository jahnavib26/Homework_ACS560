package com.acs560.restaurantsales.restaurant_sales.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntityId;
import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;

/**
 * Implementation of {@link SalesService}, responsible for business logic
 * related to sales data management such as adding, updating, deleting, and retrieving sales data.
 */
@Service
public class SalesServiceImpl implements SalesService {
    
    @Autowired
    SalesRepository salesRepository;

    /**
     * Retrieves all sales records from the repository.
     * 
     * @return A list of sales records as {@link Sales} objects.
     */
    @Override
    public List<Sales> getSales() {
        var salesEntities = (List<SalesEntity>) salesRepository.findAll();
        return from(salesEntities);
    }

    /**
     * Helper method to convert a list of {@link SalesEntity} objects to {@link Sales} model objects.
     * 
     * @param salesEntities - The list of sales entities to convert.
     * @return A list of {@link Sales} model objects.
     */
    private List<Sales> from(List<SalesEntity> salesEntities) {
        return salesEntities.stream()
                .map(Sales::new)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new sales record to the repository.
     * 
     * @param salesEntity - The sales entity to be added.
     * @throws IllegalArgumentException if sales data already exists.
     * @throws RuntimeException for any other errors during the add operation.
     */
    @Override
    public void addSales(SalesEntity salesEntity) {
        try {
            salesRepository.save(salesEntity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sales data already exists");
        } catch (Exception e) {
            throw new RuntimeException("Error adding sales data");
        }
    }

    /**
     * Updates an existing sales record in the repository.
     * 
     * @param salesEntity - The sales entity with updated data.
     * @throws NoSuchElementException if the sales data is not found.
     * @throws RuntimeException for any other errors during the update operation.
     */
    @Override
    public void updateSales(SalesEntity salesEntity) {
        try {
            salesRepository.save(salesEntity);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Sales data not found");
        } catch (Exception e) {
            throw new RuntimeException("Error updating sales data");
        }
    }

    /**
     * Deletes a sales record identified by its composite key from the repository.
     * 
     * @param salesEntityId - The composite key identifying the sales record to be deleted.
     * @throws NoSuchElementException if the sales data is not found.
     * @throws RuntimeException for any other errors during the delete operation.
     */
    @Override
    public void deleteSales(SalesEntityId salesEntityId) {
        try {
            salesRepository.deleteById(salesEntityId);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Sales data not found");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting sales data");
        }
    }

    /**
     * Retrieves sales records based on the primary key attributes: sale date, item name, and transaction type.
     * 
     * @param saleDate - The date of the sale.
     * @param itemName - The name of the sold item.
     * @param transactionType - The type of transaction (e.g., purchase, refund).
     * @return A list of sales data that match the specified attributes.
     */
    @Override
    public List<Sales> getSalesById(LocalDate saleDate, String itemName, String transactionType) {
        List<SalesEntity> salesById = salesRepository.getSalesById(saleDate, itemName, transactionType);
        return from(salesById);
    }

    /**
     * Retrieves sales records based on non-primary key attributes: item type and time of sale.
     * 
     * @param itemType - The type of the item (e.g., food, beverage).
     * @param timeOfSale - The time when the sale occurred.
     * @return A list of sales data that match the specified attributes.
     */
    @Override
    public List<Sales> getSalesByIdNonPK(String itemType, String timeOfSale) {
        List<SalesEntity> salesByIdNonPK = salesRepository.getSalesByNonPK(itemType, timeOfSale);
        return from(salesByIdNonPK);
    }

	@Override
	public Sales add(Sales sales) {
		// TODO Auto-generated method stub
		Sales addedSales = null;
		
		SalesEntity saleToAdd = new SalesEntity(sales);
		
		if (!salesRepository.existsById(saleToAdd.getId())) {
			var salesEntity = salesRepository.save(saleToAdd);
			addedSales = new Sales(salesEntity);
		}

		return addedSales;
	}

	@Override
	public Sales update(Sales sales) {
		// TODO Auto-generated method stub
		Sales updatedSales = null;
		
		SalesEntity billToUpdate = new SalesEntity(sales);
		
		if (salesRepository.existsById(billToUpdate.getId())) {
			var updatedSalesEntity = salesRepository.save(new SalesEntity(sales));
			updatedSales = new Sales(updatedSalesEntity);
		}

		return updatedSales;
	}

	@Override
	public boolean delete(Sales sales) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		SalesEntity billToDelete = new SalesEntity(sales);
		
		if (salesRepository.existsById(billToDelete.getId())) {
			salesRepository.delete(new SalesEntity(sales));
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public List<Sales> getSalesByItemName(String itemName) {
		// TODO Auto-generated method stub
		List<SalesEntity> salesByIdNonPK = salesRepository.getSalesByItemName(itemName);
        return from(salesByIdNonPK);
	}
}