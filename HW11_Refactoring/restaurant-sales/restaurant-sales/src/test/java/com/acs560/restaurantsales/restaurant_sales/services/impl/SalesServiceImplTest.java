package com.acs560.restaurantsales.restaurant_sales.services.impl;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.repositories.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalesServiceImplTest {

    @Mock
    private SalesRepository salesRepository;

    @InjectMocks
    private SalesServiceImpl salesService;

    private Sales mockSales;

    @BeforeEach
    void setup() {
        mockSales = new Sales();
        mockSales.setItemName("Pizza");
        mockSales.setQuantity(5);
        mockSales.setTransactionAmount(50.0);
    }

    @Test
    void testGetSales_success() {
        when(salesRepository.findById(any())).thenReturn(Optional.of(mockSales));
        Sales result = salesService.getSales(mockSales.getItemName());
        assertNotNull(result);
        assertEquals("Pizza", result.getItemName());
        verify(salesRepository, times(1)).findById(any());
    }

    @Test
    void testGetSales_notFound() {
        when(salesRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, 
            () -> salesService.getSales(mockSales.getItemName()));
        assertEquals("Sales not found for item: Pizza", exception.getMessage());
        verify(salesRepository, times(1)).findById(any());
    }

    @Test
    void testAddSales_success() {
        when(salesRepository.save(any(Sales.class))).thenReturn(mockSales);
        Sales result = salesService.addSales(mockSales);
        assertNotNull(result);
        assertEquals("Pizza", result.getItemName());
        verify(salesRepository, times(1)).save(any(Sales.class));
    }

    @Test
    void testDeleteSales_success() {
        doNothing().when(salesRepository).deleteById(any());
        salesService.deleteSales(mockSales.getItemName());
        verify(salesRepository, times(1)).deleteById(any());
    }
}
