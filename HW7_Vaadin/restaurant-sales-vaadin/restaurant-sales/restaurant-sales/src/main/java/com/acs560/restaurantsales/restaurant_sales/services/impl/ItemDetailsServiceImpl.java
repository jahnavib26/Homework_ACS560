package com.acs560.restaurantsales.restaurant_sales.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.restaurantsales.restaurant_sales.entities.ItemDetailsEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntityId;
import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails; // Updated import
import com.acs560.restaurantsales.restaurant_sales.repositories.ItemDetailsRepository; // Updated import
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest;
//import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest; // Updated import
import com.acs560.restaurantsales.restaurant_sales.services.ItemDetailsService; // Updated interface import

@Service
public class ItemDetailsServiceImpl implements ItemDetailsService { 

    private final ItemDetailsRepository itemDetailsRepository;
    private final SalesRepository salesRepository;

    @Autowired
    public ItemDetailsServiceImpl(ItemDetailsRepository itemDetailsRepository, SalesRepository salesRepository) {
        this.itemDetailsRepository = itemDetailsRepository; 
        this.salesRepository = salesRepository;
    }

    @Override
    public List<ItemDetails> getItemDetails() {
        var itemDetailsEntities = itemDetailsRepository.findAll(); 
        List<ItemDetails> itemDetailsList = new ArrayList<>();
        itemDetailsEntities.forEach(ide -> itemDetailsList.add(new ItemDetails(ide))); 

        return itemDetailsList;
    }

    @Override
    public Optional<ItemDetails> getItemDetail(LocalDate saleDate,String itemName, String transactionType) {
        var itemDetailsEntityOpt = itemDetailsRepository.findById(new SalesEntityId(saleDate, itemName, transactionType));
      
        return itemDetailsEntityOpt.map(ItemDetails::new); 
    }

    @Override
    public ItemDetails addItemDetail(ItemDetailsRequest request) {
    	
       
        Optional<SalesEntity> salesEntityOpt = salesRepository.findByTransactionTypeAndItemName(
            request.getTransactionType(), request.getItemName(),request.getSaleDate());

        if (salesEntityOpt.isPresent()) {
            SalesEntity salesEntity = salesEntityOpt.get();
            ItemDetailsEntity itemDetailsToAdd = new ItemDetailsEntity(
                new SalesEntityId(request.getSaleDate(), request.getItemName(), request.getTransactionType()),
                request.getItemPrice(),
                salesEntity
            );
            
            ItemDetailsEntity itemDetailsEntity = itemDetailsRepository.save(itemDetailsToAdd); 
            return new ItemDetails(itemDetailsEntity); 
        } else {
        	System.out.print("Printing in else condition");
            throw new IllegalArgumentException("Related SalesEntity not found");
        }
    }

    @Override
    public ItemDetails updateItemDetail(LocalDate saleDate, String itemName, String transactionType, ItemDetailsRequest request) {
    	
        ItemDetails updatedItemDetails = null;
         
        if (itemDetailsRepository.existsById(new SalesEntityId(saleDate, itemName, transactionType))) { 
        	
        	  // Fetch the related SalesEntity
            Optional<SalesEntity> salesEntityOpt = salesRepository.findByTransactionTypeAndItemName(
            		request.getTransactionType(), request.getItemName(),request.getSaleDate());

            if (salesEntityOpt.isPresent()) {
                SalesEntity salesEntity = salesEntityOpt.get();
                
                var itemDetailsEntity = new ItemDetailsEntity(
                    new SalesEntityId(saleDate, itemName, transactionType),
                    request.getItemPrice(),
                    salesEntity
                );
                itemDetailsEntity = itemDetailsRepository.save(itemDetailsEntity); 
                updatedItemDetails = new ItemDetails(itemDetailsEntity); 
            } else {
               throw new IllegalArgumentException("Related SalesEntity not found");
            }
        }

        return updatedItemDetails; 
    }

    @Override
    public boolean deleteItemDetail(String itemName, String transactionType, LocalDate saleDate) { 
        boolean isDeleted = false;

        if (itemDetailsRepository.existsById(new SalesEntityId(saleDate, itemName, transactionType))) { 
            itemDetailsRepository.deleteById(new SalesEntityId(saleDate, itemName, transactionType)); 
            isDeleted = true;
        }

        return isDeleted; 
    }

	@Override
	public List<ItemDetails> getItemDetailByPrice(Double itemPrice) {
		// TODO Auto-generated method stub
		var itemDetailsEntities = itemDetailsRepository.findByNonPk(itemPrice); 
        List<ItemDetails> itemDetailsList = new ArrayList<>();
        itemDetailsEntities.forEach(ide -> itemDetailsList.add(new ItemDetails(ide))); 

        return itemDetailsList;
	}

	@Override
	public void updateItemDetails(int id, ItemDetailsRequest idr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItemDetails(int id) {
		// TODO Auto-generated method stub
		
	}

	
}
