package com.acs560.restaurantsales.restaurant_sales.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDetailsRequest {

    @NotNull(message = "Sale date is required")
    private LocalDate saleDate;

    @NotEmpty(message = "Item name is required")
    private String itemName;

    @NotEmpty(message = "Transaction type is required")
    private String transactionType;
    
    @NotNull(message = "Item price is required") 
    private Double itemPrice;

    public ItemDetailsRequest(LocalDate saleDate, String itemName, String transactionType, double itemPrice) {
		// TODO Auto-generated constructor stub
    	this.saleDate=saleDate;
    	this.itemName=itemName;
    	this.transactionType=transactionType;
    	this.itemPrice = itemPrice;
	}

	public ItemDetailsRequest() {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
