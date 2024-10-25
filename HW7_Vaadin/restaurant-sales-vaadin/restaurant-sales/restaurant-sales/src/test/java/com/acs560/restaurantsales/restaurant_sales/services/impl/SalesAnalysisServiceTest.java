package com.acs560.restaurantsales.restaurant_sales.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;
import com.acs560.restaurantsales.restaurant_sales.services.SalesAnalysisService;

@SpringBootTest
public class SalesAnalysisServiceTest {

//    private static final ItemDetails ITEM1 = new ItemDetails("Item1", "Food");
//    private static final ItemDetails ITEM2 = new ItemDetails("Item2", "Drink");
//    private static final ItemDetails DOES_NOT_EXIST = new ItemDetails("Nonexistent", "Nonexistent");
//
//    private List<Sales> januarySales = List.of(
//            new Sales(1, 2024, ITEM1, 72.0, "Cash"),
//            new Sales(1, 2024, ITEM1, 81.0, "Online"));
//    
//    private List<Sales> februarySales = List.of(
//            new Sales(2, 2024, ITEM1, 90.0, "Cash"),
//            new Sales(2, 2024, ITEM2, 88.0, "Online"));
//
//    private List<Sales> marchSales = List.of(
//            new Sales(3, 2024, ITEM1, 109.0, "Cash"),
//            new Sales(3, 2024, ITEM2, 123.0, "Online"));
//
//    @Autowired
//    private SalesAnalysisService salesAnalysisService;
//
//    @Test
//    public void testCalculateAverageForAMonth_shouldReturnCorrectValue() {
//        double expectedAverage = januarySales
//                .stream()
//                .mapToDouble(Sales::getTransactionAmount).average().orElseThrow();
//
//        var result = salesAnalysisService.calculateAverage(1);
//
//        Assertions.assertEquals(expectedAverage, result, 0.01);
//    }
//
//    @Test
//    public void testCalculateAverageForNonexistingMonth_shouldThrowException() {
//        Assertions.assertThrows(NoSuchElementException.class,
//                () -> salesAnalysisService.calculateAverage(6));
//    }
//
//    @Test
//    public void testCalculateAverageForAMonthAndItem_shouldReturnCorrectValue() {
//        double expectedAverage = januarySales
//                .stream()
//                .filter(s -> s.getItemDetails().getItemName().equals(ITEM1.getItemName()))
//                .mapToDouble(Sales::getTransactionAmount).average().orElseThrow();
//
//        var result = salesAnalysisService.calculateAverageForMonth(ITEM1.getItemName(), 1);
//
//        Assertions.assertEquals(expectedAverage, result, 0.01);
//    }
//
//    @Test
//    public void testCalculateAverageForAMonthAndNonexistingItem_shouldThrowException() {
//        Assertions.assertThrows(NoSuchElementException.class,
//                () -> salesAnalysisService.calculateAverageForMonth(DOES_NOT_EXIST.getItemName(), 1));
//    }
//
//    @Test
//    public void testCalculateAverageForAMonthRange_shouldReturnCorrectValue() {
//        List<Sales> expectedSales = new ArrayList<>(januarySales);
//        expectedSales.addAll(februarySales);
//        expectedSales.addAll(marchSales);
//
//        Set<Integer> months = Set.of(1, 2, 3);
//
//        double expectedAverage = expectedSales
//                .stream()
//                .filter(s -> months.contains(s.getMonth()))
//                .mapToDouble(Sales::getTransactionAmount).average().orElseThrow();
//
//        var result = salesAnalysisService.calculateAverageForMonthRange(1, 3);
//
//        Assertions.assertEquals(expectedAverage, result, 0.01);
//    }
//
//    @Test
//    public void testCalculateAverageForAMonthRangeAndItem_shouldReturnCorrectValue() {
//        List<Sales> expectedSales = new ArrayList<>(januarySales);
//        expectedSales.addAll(februarySales);
//        expectedSales.addAll(marchSales);
//
//        Set<Integer> months = Set.of(1, 2, 3);
//
//        double expectedAverage = expectedSales
//                .stream()
//                .filter(s -> months.contains(s.getMonth()) && s.getItemDetails().getItemName().equals(ITEM1.getItemName()))
//                .mapToDouble(Sales::getTransactionAmount).average().orElseThrow();
//
//        var result = salesAnalysisService.calculateAverage(ITEM1.getItemName(), 1, 3);
//
//        Assertions.assertEquals(expectedAverage, result, 0.01);
//    }
//
//    @Test
//    public void testCalculateAverageForNonexistingMonthRangeAndItem_shouldThrowException() {
//        Assertions.assertThrows(NoSuchElementException.class,
//                () -> salesAnalysisService.calculateAverage(DOES_NOT_EXIST.getItemName(), 5, 6));
//    }
}