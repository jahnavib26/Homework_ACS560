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

    /**
     * Retrieve all sales data from the CSV file.
     *
     * @return a list of all sales records
     */
    public static List<Sales> getSales() {
        loadSalesData(); // Load sales data from the CSV file
        return salesList;
    }

    /**
     * Load sales data from the CSV file and populate the salesList.
     * This method clears the existing list before loading new data.
     */
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

    /**
     * Add a new sales record.
     * 
     * @param sales the sales record to be added
     * @throws IllegalArgumentException if the sales data already exists
     */
    public static void addSales(Sales sales) {
        if (salesList.contains(sales)) {
            throw new IllegalArgumentException("Sales data already exists");
        }
        salesList.add(sales);
        appendSalesData(sales);  // Append only the new sale to the CSV file
    }

    /**
     * Update an existing sales record.
     * 
     * @param sales the sales record to be updated
     * @throws IllegalArgumentException if the sales data is not found
     */
    public static void updateSales(Sales sales) {
        loadSalesData();  // Load the latest data from the CSV
        int index = findSalesIndex(sales);
        if (index == -1) {
            throw new IllegalArgumentException("Sales data not found");
        }
        salesList.set(index, sales);  // Update the sales record at the found index
        rewriteCSV();  // Rewrite the updated data back into the CSV
    }

    /**
     * Delete an existing sales record.
     * 
     * @param sales the sales record to be deleted
     * @throws IllegalArgumentException if the sales data is not found
     */
    public static void deleteSales(Sales sales) {
        loadSalesData();  // Load the latest data from the CSV
        int index = findSalesIndex(sales);
        if (index == -1) {
            throw new IllegalArgumentException("Sales data not found");
        }
        salesList.remove(index);
        rewriteCSV();  // Rewrite the updated data back into the CSV
    }

    /**
     * Find the index of the sales record in the salesList.
     * 
     * @param sales the sales record to find
     * @return the index of the sales record, or -1 if not found
     */
    private static int findSalesIndex(Sales sales) {
        for (int i = 0; i < salesList.size(); i++) {
            Sales s = salesList.get(i);

            boolean dateMatches = s.getDate().equals(sales.getDate());
            boolean itemNameMatches = s.getItemName().equals(sales.getItemName());
            boolean itemTypeMatches = s.getItemType().equals(sales.getItemType());
            boolean quantityMatches = s.getQuantity() == sales.getQuantity();
            boolean transactionAmountMatches = Math.abs(s.getTransactionAmount() - sales.getTransactionAmount()) < 0.01;

            if (dateMatches && itemNameMatches && itemTypeMatches && quantityMatches && transactionAmountMatches) {
                return i;  // Return the index if all fields match
            }
        }
        return -1;  // Return -1 if no matching sale is found
    }

    /**
     * Append a new sales record to the CSV file.
     * 
     * @param sales the sales record to append
     */
    private static void appendSalesData(Sales sales) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/updated_restaurant_sales_data_6months.csv", true))) { 
            // Open the file in append mode (true)
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rewrite all sales data back into the CSV file.
     */
    private static void rewriteCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/updated_restaurant_sales_data_6months.csv", false))) { 
            // Overwrite the file (false)
            writer.writeNext(new String[]{"Date", "Item Name", "Item Type", "Item Price", "Quantity", "Transaction Amount", "Transaction Type", "Staff Gender", "Time of Sale", "Year-Month"});
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
