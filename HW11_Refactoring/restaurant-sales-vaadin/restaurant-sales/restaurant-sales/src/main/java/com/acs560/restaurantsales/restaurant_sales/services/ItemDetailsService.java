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

	ItemDetails updateItemDetail(@Valid ItemDetailsRequest itemDetailsRequest);

	boolean deleteItemDetail(LocalDate saleDate,String itemName, String transactionType);


	List<ItemDetails> getItemDetailByPrice(Double itemPrice);


	

	List<ItemDetails> getItemDetailsByItemName(String itemName);
}
