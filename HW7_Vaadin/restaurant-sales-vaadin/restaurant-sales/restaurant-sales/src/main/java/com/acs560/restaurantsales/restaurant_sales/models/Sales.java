package com.acs560.restaurantsales.restaurant_sales.models;

import java.time.LocalDate;

import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;

public class Sales {
	
	private ItemDetails ItemDetails;
    private LocalDate date;
    private String itemName;
    private String itemType;
    private double itemPrice;
    private int quantity;
    private double transactionAmount;
    private String transactionType;
    private String staffGender;
    private String timeOfSale;
    private String yearMonth;
    
    /**
     * Constructs a new Sales object using detailed sales data.
     * 
     * @param date - The date of the sale.
     * @param itemName - The name of the item sold.
     * @param itemType - The type of the item (e.g., food, beverage).
     * @param itemPrice - The price of the item.
     * @param quantity - The quantity sold.
     * @param transactionAmount - The total amount of the transaction.
     * @param transactionType - The type of transaction (e.g., purchase, refund).
     * @param staffGender - The gender of the staff involved in the sale.
     * @param timeOfSale - The time when the sale occurred.
     * @param yearMonth - The year and month of the sale (in YYYY-MM format).
     */

    public Sales(LocalDate date, String itemName, String itemType, double itemPrice, int quantity, double transactionAmount,
                 String transactionType, String staffGender, String timeOfSale, String yearMonth) {
        this.date = date;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.staffGender = staffGender;
        this.timeOfSale = timeOfSale;
        this.yearMonth = yearMonth;
    }

    public Sales(SalesEntity se) {
        this.date = se.getId().getSaleDate();
        this.itemName = se.getId().getItemName();
        this.transactionType = se.getId().getTransactionType();
        this.itemType = se.getItemType();
        this.itemPrice = se.getItemPrice();
        this.quantity = se.getQuantity();
        this.transactionAmount = se.getTransactionAmount();
        this.staffGender = se.getStaffGender();
        this.timeOfSale = se.getTimeOfSale();
        this.yearMonth = se.getYearMonth();
    }


	public Sales() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters for each field
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(double transactionAmount) { this.transactionAmount = transactionAmount; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public String getStaffGender() { return staffGender; }
    public void setStaffGender(String staffGender) { this.staffGender = staffGender; }

    public String getTimeOfSale() { return timeOfSale; }
    public void setTimeOfSale(String timeOfSale) { this.timeOfSale = timeOfSale; }

    public String getYearMonth() { return yearMonth; }
    public void setYearMonth(String yearMonth) { this.yearMonth = yearMonth; }
}