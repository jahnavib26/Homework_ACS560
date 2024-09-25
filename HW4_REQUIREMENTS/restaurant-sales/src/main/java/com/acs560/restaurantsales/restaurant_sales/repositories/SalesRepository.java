package com.acs560.restaurantsales.restaurant_sales.repositories;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;

public class SalesRepository {
    private static List<Sales> salesList = new ArrayList<>();

    // Explicit method to load sales data
//    public static void loadSalesData() {
//        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource("updated_restaurant_sales_data_6months.csv").getInputStream()))) {
//            List<String[]> rows = reader.readAll();
//            for (String[] row : rows) {
//                Sales sales = new Sales(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]), Double.parseDouble(row[3]));
//                salesList.add(sales);
//            }
//            // Logging the loaded sales data to verify
//            System.out.println("Loaded sales data: " + salesList);
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        } 
//    }
    
    public static void loadSalesData() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource("updated_restaurant_sales_data_6months.csv").getInputStream()))) {
            List<String[]> rows = reader.readAll();

            // Skip the first row, which contains the header
            boolean skipHeader = true;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Corrected date format

            for (String[] row : rows) {
                if (skipHeader) {
                    skipHeader = false;
                    continue; // Skip the first row
                }

                // Parse the date from the first column (e.g., "2023-03-01")
                String dateStr = row[0];  // "Date" column
                LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                
                String productName = row[1]; // "Item Name"
                int month = date.getMonthValue();  // Extract month from "Date"
                int year = date.getYear();  // Extract year from "Date"
                double amount = Double.parseDouble(row[5]); // "Transaction Amount"

                Sales sales = new Sales(productName, month, year, amount);
                salesList.add(sales);
            }

            // Logging the loaded sales data to verify
            System.out.println("Loaded sales data: " + salesList);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

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
        try (CSVWriter writer = new CSVWriter(new FileWriter("updated_restaurant_sales_data_6months.csv", false))) {
            for (Sales sales : salesList) {
                writer.writeNext(new String[] { sales.getProductName(), String.valueOf(sales.getMonth()),
                        String.valueOf(sales.getYear()), String.valueOf(sales.getAmount()) });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate the most selling product
    public static String getMostSellingProduct() {
        if (salesList.isEmpty()) {
            return "No sales data available.";
        }

        return salesList.stream()
            .collect(Collectors.groupingBy(Sales::getProductName, Collectors.summingDouble(Sales::getAmount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow(() -> new NoSuchElementException("No sales data found"))
            .getKey();
    }
}
