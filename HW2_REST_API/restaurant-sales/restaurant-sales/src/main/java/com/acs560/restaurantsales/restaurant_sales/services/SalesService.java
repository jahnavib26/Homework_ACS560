package com.acs560.restaurantsales.restaurant_sales.services;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;

import java.util.List;

public interface SalesService {
    List<Sales> getAllSales();
    Sales getSaleByDate(String date);
    List<Sales> getSalesByItemName(String itemName);
    double calculateMeanSales();
}
