package com.acs560.restaurantsales.restaurant_sales.models;

import java.time.LocalDate;

public class Sales {

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
     * Constructs a Sales object with the provided details.
     * 
     * @param date - the date of the sale
     * @param itemName - the name of the item sold
     * @param itemType - the type of the item sold
     * @param itemPrice - the price of the item
     * @param quantity - the quantity of items sold
     * @param transactionAmount - the total amount of the transaction
     * @param transactionType - the type of transaction (e.g., cash, card)
     * @param staffGender - the gender of the staff member who made the sale
     * @param timeOfSale - the time of the sale
     * @param yearMonth - the year and month of the sale
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

    /**
     * Gets the date of the sale.
     * 
     * @return the date of the sale
     */
    public LocalDate getDate() { return date; }

    /**
     * Sets the date of the sale.
     * 
     * @param date - the date of the sale
     */
    public void setDate(LocalDate date) { this.date = date; }

    /**
     * Gets the name of the item sold.
     * 
     * @return the name of the item
     */
    public String getItemName() { return itemName; }

    /**
     * Sets the name of the item sold.
     * 
     * @param itemName - the name of the item
     */
    public void setItemName(String itemName) { this.itemName = itemName; }

    /**
     * Gets the type of the item sold.
     * 
     * @return the type of the item
     */
    public String getItemType() { return itemType; }

    /**
     * Sets the type of the item sold.
     * 
     * @param itemType - the type of the item
     */
    public void setItemType(String itemType) { this.itemType = itemType; }

    /**
     * Gets the price of the item sold.
     * 
     * @return the price of the item
     */
    public double getItemPrice() { return itemPrice; }

    /**
     * Sets the price of the item sold.
     * 
     * @param itemPrice - the price of the item
     */
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    /**
     * Gets the quantity of items sold.
     * 
     * @return the quantity of items sold
     */
    public int getQuantity() { return quantity; }

    /**
     * Sets the quantity of items sold.
     * 
     * @param quantity - the quantity of items sold
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Gets the total amount of the transaction.
     * 
     * @return the total amount of the transaction
     */
    public double getTransactionAmount() { return transactionAmount; }

    /**
     * Sets the total amount of the transaction.
     * 
     * @param transactionAmount - the total amount of the transaction
     */
    public void setTransactionAmount(double transactionAmount) { this.transactionAmount = transactionAmount; }

    /**
     * Gets the type of transaction (e.g., cash, card).
     * 
     * @return the type of transaction
     */
    public String getTransactionType() { return transactionType; }

    /**
     * Sets the type of transaction.
     * 
     * @param transactionType - the type of transaction (e.g., cash, card)
     */
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    /**
     * Gets the gender of the staff member who made the sale.
     * 
     * @return the gender of the staff member
     */
    public String getStaffGender() { return staffGender; }

    /**
     * Sets the gender of the staff member who made the sale.
     * 
     * @param staffGender - the gender of the staff member
     */
    public void setStaffGender(String staffGender) { this.staffGender = staffGender; }

    /**
     * Gets the time of the sale.
     * 
     * @return the time of the sale
     */
    public String getTimeOfSale() { return timeOfSale; }

    /**
     * Sets the time of the sale.
     * 
     * @param timeOfSale - the time of the sale
     */
    public void setTimeOfSale(String timeOfSale) { this.timeOfSale = timeOfSale; }

    /**
     * Gets the year and month of the sale.
     * 
     * @return the year and month of the sale
     */
    public String getYearMonth() { return yearMonth; }

    /**
     * Sets the year and month of the sale.
     * 
     * @param yearMonth - the year and month of the sale
     */
    public void setYearMonth(String yearMonth) { this.yearMonth = yearMonth; }
}
