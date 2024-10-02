package com.acs560.restaurantsales.restaurant_sales.repositories;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesRepository {

    private static List<Sales> salesList = new ArrayList<>();

    public static List<Sales> getSales() {
        loadSalesData(); // Load sales data from the CSV file
        return salesList;
    }

    public static void loadSalesData() {
        salesList.clear();  // Clear the list before loading new data
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/updated_restaurant_sales_data_6months.csv"))) {
            List<String[]> rows = reader.readAll();
            boolean skipHeader = true;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (String[] row : rows) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // Parse the data from the row
                String dateStr = row[0];
                LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                String itemName = row[1];
                String itemType = row[2];
                double itemPrice = Double.parseDouble(row[3]);
                int quantity = Integer.parseInt(row[4]);
                double transactionAmount = Double.parseDouble(row[5]);
                String transactionType = row[6];
                String staffGender = row[7];
                String timeOfSale = row[8];
                String yearMonth = row[9];

                Sales sales = new Sales(date, itemName, itemType, itemPrice, quantity, transactionAmount, transactionType, staffGender, timeOfSale, yearMonth);
                salesList.add(sales);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void addSales(Sales sales) {
        System.out.println("Adding sales: " + sales);  // Log the incoming sales object
        if (salesList.contains(sales)) {
            throw new IllegalArgumentException("Sales data already exists");
        }
        salesList.add(sales);
        System.out.println("Sales added successfully: " + sales);
        appendSalesData(sales);  // Append only the new sale to the CSV file
    }

    public static void updateSales(Sales sales) {
        System.out.println("Attempting to update sales: " + sales);
        loadSalesData();  // Load the latest data from the CSV
        int index = findSalesIndex(sales);
        if (index == -1) {
            System.out.println("Sales data not found for update: " + sales);
            throw new IllegalArgumentException("Sales data not found");
        }
        System.out.println("Updating sales at index " + index + ": " + salesList.get(index));
        salesList.set(index, sales);  // Update the sales record at the found index
        System.out.println("Sales updated successfully.");
        rewriteCSV();  // Rewrites the updated data back into the CSV
    }

    public static void deleteSales(Sales sales) {
        System.out.println("Attempting to delete sales: " + sales);
        loadSalesData();  // Load the latest data from the CSV
        int index = findSalesIndex(sales);
        if (index == -1) {
            System.out.println("Sales data not found for deletion: " + sales);
            throw new IllegalArgumentException("Sales data not found");
        }
        System.out.println("Deleting sales at index " + index + ": " + salesList.get(index));  // Log the sale being deleted
        salesList.remove(index);
        System.out.println("Sales deleted successfully.");
        rewriteCSV();  // Rewrites the updated data back into the CSV
    }

    private static int findSalesIndex(Sales sales) {
        System.out.println("Looking for sales to match: " + sales);
        for (int i = 0; i < salesList.size(); i++) {
            Sales s = salesList.get(i);
            System.out.println("Comparing with sales in list: " + s);

            boolean dateMatches = s.getDate().equals(sales.getDate());
            boolean itemNameMatches = s.getItemName().equals(sales.getItemName());
            boolean itemTypeMatches = s.getItemType().equals(sales.getItemType());
            boolean quantityMatches = s.getQuantity() == sales.getQuantity();
            boolean transactionAmountMatches = Math.abs(s.getTransactionAmount() - sales.getTransactionAmount()) < 0.01;

            System.out.println("Date match: " + dateMatches);
            System.out.println("Item name match: " + itemNameMatches);
            System.out.println("Item type match: " + itemTypeMatches);
            System.out.println("Quantity match: " + quantityMatches);
            System.out.println("Transaction amount match: " + transactionAmountMatches);

            if (dateMatches && itemNameMatches && itemTypeMatches && quantityMatches && transactionAmountMatches) {
                System.out.println("Found matching sale at index " + i);
                return i;  // Return the index if all fields match
            }
        }
        System.out.println("No matching sale found for: " + sales);
        return -1;  // Return -1 if no matching sale is found
    }

    // Append a single sale to the CSV file (for adding sales)
    private static void appendSalesData(Sales sales) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/updated_restaurant_sales_data_6months.csv", true))) { 
            // Open the file in append mode (true)

            // Write only the new sales data (no header)
            writer.writeNext(new String[]{
                sales.getDate().toString(),
                sales.getItemName(),
                sales.getItemType(),
                String.valueOf(sales.getItemPrice()),
                String.valueOf(sales.getQuantity()),
                String.valueOf(sales.getTransactionAmount()),
                sales.getTransactionType(),
                sales.getStaffGender(),
                sales.getTimeOfSale(),
                sales.getYearMonth()
            });

            System.out.println("Sales data appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending sales data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Rewrite the entire sales data in the CSV (for update and delete)
    private static void rewriteCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/updated_restaurant_sales_data_6months.csv", false))) { 
            // Overwrite the file (false)

            // Write the header
            writer.writeNext(new String[]{"Date", "Item Name", "Item Type", "Item Price", "Quantity", "Transaction Amount", "Transaction Type", "Staff Gender", "Time of Sale", "Year-Month"});

            // Write all sales data
            for (Sales sales : salesList) {
                writer.writeNext(new String[]{
                    sales.getDate().toString(),
                    sales.getItemName(),
                    sales.getItemType(),
                    String.valueOf(sales.getItemPrice()),
                    String.valueOf(sales.getQuantity()),
                    String.valueOf(sales.getTransactionAmount()),
                    sales.getTransactionType(),
                    sales.getStaffGender(),
                    sales.getTimeOfSale(),
                    sales.getYearMonth()
                });
            }
            System.out.println("Sales data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving sales data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
