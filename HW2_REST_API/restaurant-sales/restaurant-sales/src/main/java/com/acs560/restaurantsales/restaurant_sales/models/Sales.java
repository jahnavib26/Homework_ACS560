package com.acs560.restaurantsales.restaurant_sales.models;

public class Sales {
    private String date;
    private String itemName;
    private String itemType;
    private double itemPrice;
    private int quantity;
    private double transactionAmount;
    private String transactionType;
    private String staffGender;
    private String timeOfSale;
    private String yearMonth;

    public Sales() {}

    public Sales(String date, String itemName, String itemType, double itemPrice, int quantity, double transactionAmount, String transactionType, String staffGender, String timeOfSale, String yearMonth) {
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

    // Getters and Setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public String getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(String timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}

