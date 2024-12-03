package com.acs560.restaurantsales.restaurant_sales.services;

/**
 * Service interface for performing sales analysis operations.
 * This includes calculating average, minimum, and maximum sales, as well as identifying the most selling product.
 */
public interface SalesAnalysisService {

    /**
     * Retrieves the minimum sales transaction amount for a specific month.
     * 
     * @param month - The month for which to retrieve the minimum sales (1-12).
     * @return The minimum transaction amount for the specified month.
     */
    double getMinSales(int month);

    /**
     * Retrieves the maximum sales transaction amount for a specific month.
     * 
     * @param month - The month for which to retrieve the maximum sales (1-12).
     * @return The maximum transaction amount for the specified month.
     */
    double getMaxSales(int month);

    /**
     * Retrieves the most selling product based on the total transaction amount.
     * 
     * @return The name of the most selling product.
     */
    String getMostSellingProduct();

    /**
     * Calculates the average sales transaction amount for a specific month.
     * 
     * @param month - The month for which to calculate the average sales (1-12).
     * @return The average transaction amount for the specified month.
     */
    double calculateAverageSales(int month);
}
