package com.acs560.restaurantsales.restaurant_sales.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;
import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest;
import com.acs560.restaurantsales.restaurant_sales.services.ItemDetailsService;

@SpringBootTest
public class ItemDetailsServiceTest {
//
//    @Autowired
//    private ItemDetailsService itemDetailsService;
//
//    @Test
//    public void testGetAllItemDetails_shouldReturnItemDetails() {
//        var result = itemDetailsService.getItemDetails();
//        Assertions.assertTrue(result.size() > 0);
//    }
//
//    @Test
//    public void testGetItemDetail_shouldReturnItemDetail() {
//        var result = itemDetailsService.getItemDetail("Pizza", "Dine-in", LocalDate.of(2023, 10, 21));
//        Assertions.assertTrue(result.isPresent());
//    }
//
//    @Test
//    public void testGetNonexistingItemDetail_shouldReturnEmpty() {
//        var result = itemDetailsService.getItemDetail("Nonexistent Item", "Dine-in", LocalDate.of(2023, 10, 21));
//        Assertions.assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void testAddItemDetail_shouldAdd() {
//        ItemDetailsRequest request = new ItemDetailsRequest("Burger", "Fast Food", 10, 100.0, "Dine-in");
//
//        var added = itemDetailsService.addItemDetail(request);
//        var itemDetail = itemDetailsService.getItemDetail("Burger", "Dine-in", LocalDate.now());
//
//        Assertions.assertEquals(added, itemDetail.get());
//    }
//
//    @Test
//    public void testAddDuplicateItemDetail_shouldThrowException() {
//        ItemDetailsRequest request = new ItemDetailsRequest("DuplicateItem", "Dessert", 5, 50.0, "Takeout");
//
//        itemDetailsService.addItemDetail(request);
//
//        Assertions.assertThrows(DataIntegrityViolationException.class,
//                () -> itemDetailsService.addItemDetail(request));
//    }
//
//    @Test
//    public void testUpdateItemDetail_shouldUpdate() {
//        LocalDate saleDate = LocalDate.of(2023, 10, 21);
//        String updatedName = "Updated Item";
//        ItemDetailsRequest request = new ItemDetailsRequest(updatedName, "Snacks", 15, 75.0, "Dine-in");
//
//        itemDetailsService.updateItemDetail(saleDate, "Pizza", "Dine-in", request);
//
//        var itemDetail = itemDetailsService.getItemDetail(updatedName, "Dine-in", saleDate);
//
//        Assertions.assertEquals(updatedName, itemDetail.get().getItemName());
//    }
//
//    @Test
//    public void testUpdateItemDetailToExistingName_shouldThrowException() {
//        LocalDate saleDate = LocalDate.of(2023, 10, 21);
//        String existingName = "Existing Item";
//        ItemDetailsRequest request = new ItemDetailsRequest(existingName, "Snacks", 15, 75.0, "Takeout");
//
//        Assertions.assertThrows(DataIntegrityViolationException.class,
//                () -> itemDetailsService.updateItemDetail(saleDate, "Pizza", "Dine-in", request));
//    }
//
//    @Test
//    public void testUpdateNonexistingItemDetail_shouldReturnNull() {
//        LocalDate saleDate = LocalDate.of(2030, 1, 1);
//        ItemDetailsRequest request = new ItemDetailsRequest("Nonexistent", "Category", 0, 0.0, "Dine-in");
//
//        var result = itemDetailsService.updateItemDetail(saleDate, "Nonexistent", "Dine-in", request);
//
//        Assertions.assertNull(result);
//    }
//
//    @Test
//    public void testDeleteItemDetail_shouldDelete() {
//        LocalDate saleDate = LocalDate.of(2023, 10, 21);
//        String itemName = "ItemToDelete";
//        String transactionType = "Dine-in";
//
//        itemDetailsService.deleteItemDetail(itemName, transactionType, saleDate);
//
//        Assertions.assertTrue(itemDetailsService.getItemDetail(itemName, transactionType, saleDate).isEmpty());
//    }
//
//    @Test
//    public void testDeleteNonexistingItemDetail_shouldReturnFalse() {
//        LocalDate saleDate = LocalDate.of(2030, 1, 1);
//        Assertions.assertFalse(itemDetailsService.deleteItemDetail("Nonexistent", "Dine-in", saleDate));
//    }
}