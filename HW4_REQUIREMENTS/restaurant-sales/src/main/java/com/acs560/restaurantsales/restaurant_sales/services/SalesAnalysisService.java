package com.acs560.restaurantsales.restaurant_sales.services;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;

public interface SalesAnalysisService {
    double calculateAverageSales(int month);
    double calculateAverageSalesRange(int month, int range);
    double getMinSales(int month);
    double getMaxSales(int month);
    
//    Sales getMostSellingProductForMonth(int month);
//    Sales getMostSellingProductForAllMonths();
    String getMostSellingProduct();
}
