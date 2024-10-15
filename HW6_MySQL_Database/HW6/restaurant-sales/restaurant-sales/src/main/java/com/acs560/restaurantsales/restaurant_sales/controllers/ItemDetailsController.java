package com.acs560.restaurantsales.restaurant_sales.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails; // Updated import
import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest;
//import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest; // Assuming you have created a request class
import com.acs560.restaurantsales.restaurant_sales.services.ItemDetailsService; // Updated service
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/v1/item-details") 
public class ItemDetailsController { 

    private ItemDetailsService itemDetailsService; 

    @Autowired
    public ItemDetailsController(ItemDetailsService itemDetailsService) { 
        this.itemDetailsService = itemDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDetails>> getItemDetails() { 
        return ResponseEntity.ok(itemDetailsService.getItemDetails()); 
    }

    @GetMapping("/{saleDate}/{itemName}/{transactionType}") 
    public ResponseEntity<String> getItemDetail(
    		@PathVariable LocalDate saleDate,
    		@PathVariable String itemName, 
            @PathVariable String transactionType) { 
        var itemDetail = itemDetailsService.getItemDetail(saleDate, itemName,transactionType ); 
        return itemDetail.isPresent() ? 
                ResponseEntity.ok("Record added successfully") : ResponseEntity.notFound().build();
    }
    

    @PostMapping
    public ResponseEntity<ItemDetails> addItemDetail(@Valid @RequestBody ItemDetailsRequest itemDetailsRequest) { 
        var addedItemDetail = itemDetailsService.addItemDetail(itemDetailsRequest); 
        return ResponseEntity.created(null).body(addedItemDetail);
    }

    @PutMapping("/{itemName}/{transactionType}/{saleDate}") 
    public ResponseEntity<String> updateItemDetail(
            @PathVariable String itemName,
            @PathVariable String transactionType,
            @PathVariable LocalDate saleDate,
            @Valid @RequestBody ItemDetailsRequest itemDetailsRequest) {
        itemDetailsService.updateItemDetail(saleDate, itemName,transactionType, itemDetailsRequest); 
        return ResponseEntity.ok("Record updated successfully");
    }

    @DeleteMapping("/{itemName}/{transactionType}/{saleDate}") 
    public ResponseEntity<String> deleteItemDetail(
            @PathVariable String itemName,
            @PathVariable String transactionType,
            @PathVariable LocalDate saleDate) { 
        itemDetailsService.deleteItemDetail(itemName, transactionType, saleDate); 
        return ResponseEntity.ok("Record deleted successfully");
    }
    
    @GetMapping("/{itemPrice}") 
    public ResponseEntity<List<ItemDetails>> getItemDetailByPrice(
            @PathVariable Double itemPrice) { 
    	return ResponseEntity.ok(itemDetailsService.getItemDetailByPrice(itemPrice));
    }
}
