package com.acs560.restaurantsales.restaurant_sales.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.acs560.restaurantsales.restaurant_sales.entities.ItemDetailsEntity;
import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails; // Updated import
import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest;

//import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest; // Updated import
import jakarta.validation.Valid;

public interface ItemDetailsService { 

    List<ItemDetails> getItemDetails(); 
    
    
    ItemDetails addItemDetail(@Valid ItemDetailsRequest itemDetailsRequest); 
    
    
	Optional<ItemDetails> getItemDetail(LocalDate saleDate, String itemName, String transactionType);

	ItemDetails updateItemDetail(LocalDate saleDate, String itemName, String transactionType,
			ItemDetailsRequest request);

	boolean deleteItemDetail(String itemName, String transactionType, LocalDate saleDate);


	List<ItemDetails> getItemDetailByPrice(Double itemPrice);


	void updateItemDetails(int id, ItemDetailsRequest idr);


	void deleteItemDetails(int id);


	List<ItemDetails> getItemDetailsByItemName(String itemName);
}
