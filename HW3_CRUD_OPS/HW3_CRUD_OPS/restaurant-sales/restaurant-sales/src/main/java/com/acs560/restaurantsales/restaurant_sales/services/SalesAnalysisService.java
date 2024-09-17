package com.acs560.restaurantsales.restaurant_sales.services;

public interface SalesAnalysisService {
    double calculateAverageSales(int month);
    double calculateAverageSalesRange(int month, int range);
    double getMinSales(int month);
    double getMaxSales(int month);
}
