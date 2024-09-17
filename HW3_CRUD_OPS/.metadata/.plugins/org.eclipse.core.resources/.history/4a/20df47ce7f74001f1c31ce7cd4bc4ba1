package com.acs560.restaurantsales.restaurant_sales.repositories;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.opencsv.CSVWriter;

public class SalesRepository {
    private static List<Sales> salesList = new ArrayList<>();

    public static List<Sales> getSales() {
        return salesList;
    }

    public static void addSales(Sales sales) {
        if (salesList.contains(sales)) {
            throw new IllegalArgumentException("Sales data already exists");
        }
        salesList.add(sales);
        saveSalesData();
    }

    public static void updateSales(Sales sales) {
        int index = salesList.indexOf(sales);
        if (index == -1) {
            throw new IllegalArgumentException("Sales data not found");
        }
        salesList.set(index, sales);
        saveSalesData();
    }

    public static void deleteSales(Sales sales) {
        if (!salesList.remove(sales)) {
            throw new IllegalArgumentException("Sales data not found");
        }
        saveSalesData();
    }

    private static void saveSalesData() {
        try (CSVWriter writer = new CSVWriter(new FileWriter("sales_data.csv", false))) {
            for (Sales sales : salesList) {
                writer.writeNext(new String[] { sales.getProductName(), String.valueOf(sales.getMonth()),
                        String.valueOf(sales.getYear()), String.valueOf(sales.getAmount()) });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
