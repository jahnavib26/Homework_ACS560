package com.acs560.restaurantsales.restaurant_sales.services.impl;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalesAnalysisServiceImplTest {

    @Mock
    private SalesRepository salesRepository;

    @InjectMocks
    private SalesAnalysisServiceImpl salesAnalysisService;

    private List<Sales> mockSalesList;

    @BeforeEach
    void setup() {
        Sales sale1 = new Sales();
        sale1.setItemName("Pizza");
        sale1.setQuantity(10);
        sale1.setTransactionAmount(100.0);

        Sales sale2 = new Sales();
        sale2.setItemName("Burger");
        sale2.setQuantity(5);
        sale2.setTransactionAmount(50.0);

        Sales sale3 = new Sales();
        sale3.setItemName("Pasta");
        sale3.setQuantity(15);
        sale3.setTransactionAmount(150.0);

        mockSalesList = Arrays.asList(sale1, sale2, sale3);
    }

    @Test
    void testCalculateAverageSales_success() {
        when(salesRepository.findAll()).thenReturn(mockSalesList);
        double result = salesAnalysisService.calculateAverageSales();
        assertEquals(100.0, result); // Average = (100 + 50 + 150) / 3
        verify(salesRepository, times(1)).findAll();
    }

    @Test
    void testCalculateAverageSales_emptyList() {
        when(salesRepository.findAll()).thenReturn(List.of());
        double result = salesAnalysisService.calculateAverageSales();
        assertEquals(0.0, result); // No sales data
        verify(salesRepository, times(1)).findAll();
    }

    @Test
    void testGetMinSales_success() {
        when(salesRepository.findAll()).thenReturn(mockSalesList);
        Sales result = salesAnalysisService.getMinSales();
        assertNotNull(result);
        assertEquals("Burger", result.getItemName());
        assertEquals(50.0, result.getTransactionAmount());
        verify(salesRepository, times(1)).findAll();
    }

    @Test
    void testGetMaxSales_success() {
        when(salesRepository.findAll()).thenReturn(mockSalesList);
        Sales result = salesAnalysisService.getMaxSales();
        assertNotNull(result);
        assertEquals("Pasta", result.getItemName());
        assertEquals(150.0, result.getTransactionAmount());
        verify(salesRepository, times(1)).findAll();
    }

    @Test
    void testGetMostSellingProduct_success() {
        when(salesRepository.findAll()).thenReturn(mockSalesList);
        Sales result = salesAnalysisService.getMostSellingProduct();
        assertNotNull(result);
        assertEquals("Pasta", result.getItemName()); // Pasta has the highest quantity: 15
        verify(salesRepository, times(1)).findAll();
    }

    @Test
    void testGetMostSellingProduct_emptyList() {
        when(salesRepository.findAll()).thenReturn(List.of());
        Sales result = salesAnalysisService.getMostSellingProduct();
        assertNull(result); // No sales data
        verify(salesRepository, times(1)).findAll();
    }
}
