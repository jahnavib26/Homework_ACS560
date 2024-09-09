package com.acs560.bills_analyzer.repositories;

import com.acs560.bills_analyzer.models.Bill;
import com.acs560.bills_analyzer.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillsRepository {

    private static List<Bill> bills;

    public static List<Bill> getBills() {
        if (bills == null) {
            bills = initializeBills();
        }
        return bills;
    }

    private static List<Bill> initializeBills() {
        CSVReader csvReader = new CSVReader();
        List<Map<String, String>> csvData = csvReader.readCSV("data.csv");
        List<Bill> bills = new ArrayList<>();

        for (Map<String, String> row : csvData) {
            Bill bill = new Bill();
            bill.setMonth(Integer.parseInt(row.get("month")));
            bill.setYear(Integer.parseInt(row.get("year")));
            bill.setUtilityName(row.get("itemName"));
            bill.setAmount(Double.parseDouble(row.get("amount")));
            bills.add(bill);
        }
        return bills;
    }
}
