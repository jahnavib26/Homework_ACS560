package com.acs560.restaurantsales.restaurant_sales.models;

import java.time.LocalDate;

import com.acs560.restaurantsales.restaurant_sales.entities.ItemDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ItemDetails {
	
	private LocalDate saleDate;

	private String itemName;
    private String transactionType;
    private double itemPrice;

   
    public ItemDetails(ItemDetailsEntity itemDetailsEntity) {
    	this.saleDate = itemDetailsEntity.getId().getSaleDate();
        this.itemName = itemDetailsEntity.getId().getItemName();  
        this.transactionType = itemDetailsEntity.getId().getTransactionType();  
        this.itemPrice = itemDetailsEntity.getItemPrice();  
    }
    
    public ItemDetails(LocalDate saleDate, String itemName, String transactionType, Double itemPrice) {
		// TODO Auto-generated constructor stub
    	this.saleDate = saleDate;
    	this.itemName = itemName;
    	this.transactionType = transactionType;
    	this.itemPrice = itemPrice;
	}
    
    public ItemDetails() {
//    	super();
    }

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
    
    

}
