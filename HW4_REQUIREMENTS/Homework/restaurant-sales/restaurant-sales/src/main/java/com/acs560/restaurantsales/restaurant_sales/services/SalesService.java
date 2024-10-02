package com.acs560.restaurantsales.restaurant_sales.services;

import java.util.List;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;

public interface SalesService {
    void addSales(Sales sales);
    void updateSales(Sales sales);
    void deleteSales(Sales sales);
	List<Sales> getSales();
}
