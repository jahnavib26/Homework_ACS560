package com.acs560.restaurantsales.restaurant_sales.services;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;

public interface SalesService {
    void addSales(Sales sales);
    void updateSales(Sales sales);
    void deleteSales(Sales sales);
}
