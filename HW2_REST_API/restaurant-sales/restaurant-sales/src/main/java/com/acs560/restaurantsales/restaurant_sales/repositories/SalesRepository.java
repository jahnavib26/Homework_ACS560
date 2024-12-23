package com.acs560.restaurantsales.restaurant_sales.repositories;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The temporary sales repository class.
 * This class will be refactored when database access is used.
 */
@Repository
public class SalesRepository {

    private static List<Sales> sales;

    /**
     * Get the list of all sales
     * @return - the list of all sales
     */
    public static List<Sales> getSales() {
        if (sales == null) {
            sales = initializeSales();
        }
        return sales;
    }

    /**
     * Initializes the list of sales.
     * Reads the input data file and converts the data into Sales objects.
     * Returns an empty list if an error occurs when reading the input file.
     * @return - the list of all sales
     */
    private static List<Sales> initializeSales() {
        CSVReader reader;

        try {
            reader = new CSVReader(new FileReader("src/main/resources/updated_restaurant_sales_data_6months.csv"));

            // Read and discard the header row
            reader.readNext();  // This will read and skip the first line (header row)

            ColumnPositionMappingStrategy<Sales> beanStrategy = new ColumnPositionMappingStrategy<>();
            beanStrategy.setType(Sales.class);
            beanStrategy.setColumnMapping(new String[]{"date", "itemName", "itemType", "itemPrice", "quantity", "transactionAmount", "transactionType", "staffGender", "timeOfSale", "yearMonth"});

            CsvToBean<Sales> csvToBean = new CsvToBean<>();
            csvToBean.setMappingStrategy(beanStrategy);
            csvToBean.setCsvReader(reader);

            sales = csvToBean.parse();

            // Optional: Log the parsed sales data for debugging
            sales.forEach(sale -> {
                System.out.println("Loaded Sale: " + sale.getItemName() + " | " + sale.getYearMonth() + " | " + sale.getTransactionAmount());
            });
        } catch (FileNotFoundException e) {
            sales = new ArrayList<>();
            System.err.println("CSV file not found: " + e.getMessage());
        } catch (Exception e) {
            sales = new ArrayList<>();
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return sales;
    }
}


