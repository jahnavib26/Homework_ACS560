package com.acs560.restaurantsales.restaurant_sales.models;

import java.util.Objects;

public class Sales {
    private String productName;
    private int month;
    private int year;
    private double amount;

    public Sales() {}

    public Sales(String productName, int month, int year, double amount) {
        this.productName = productName;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sales)) return false;
        Sales sales = (Sales) o;
        return month == sales.month && year == sales.year && productName.equals(sales.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, month, year);
    }
}