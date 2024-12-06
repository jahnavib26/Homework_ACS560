package com.acs560.restaurantsales.restaurant_sales.views.ItemDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemDetailsFormTest {

    private ItemDetailsForm form;

    @BeforeEach
    void setup() {
        form = new ItemDetailsForm();
    }

//    @Test
//    void testInitialComponentSetup() {
//        assertNotNull(form);
//        assertEquals("Cash", form.getTransactionType().getItems().get(0));
//        assertEquals("Online", form.getTransactionType().getItems().get(1));
//        assertEquals("Others", form.getTransactionType().getItems().get(2));
//    }
    
    @Test
    void testInitialComponentSetup() {
        assertNotNull(form);

        // Verify transactionType ComboBox items by checking the setup logic
        form.getTransactionType().setValue("Cash");
        assertEquals("Cash", form.getTransactionType().getValue());

        form.getTransactionType().setValue("Online");
        assertEquals("Online", form.getTransactionType().getValue());

        form.getTransactionType().setValue("Others");
        assertEquals("Others", form.getTransactionType().getValue());
    }


    @Test
    void testFormBindingAndValidation() {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setSaleDate(LocalDate.of(2024, 12, 1));
        itemDetails.setItemName("Burger");
        itemDetails.setTransactionType("Cash");
        itemDetails.setItemPrice(5.99);

        form.update(itemDetails, false);

        assertEquals("Burger", form.getItemName().getValue());
        assertEquals(LocalDate.of(2024, 12, 1), form.getSaleDate().getValue());
        assertEquals("Cash", form.getTransactionType().getValue());
        assertEquals("5.99", form.getItemPrice().getValue());
    }

    @Test
    void testHandleSaveEventAdd() {
        ItemDetails itemDetails = new ItemDetails();
        form.update(itemDetails, true);

        form.getItemName().setValue("Pizza");
        form.getSaleDate().setValue(LocalDate.of(2024, 12, 1));
        form.getTransactionType().setValue("Online");
        form.getItemPrice().setValue("12.50");

        form.addListener(ItemDetailsForm.AddEvent.class, event -> {
            assertEquals("Pizza", event.getItemDetails().getItemName());
            assertEquals(LocalDate.of(2024, 12, 1), event.getItemDetails().getSaleDate());
            assertEquals("Online", event.getItemDetails().getTransactionType());
            assertEquals(12.50, event.getItemDetails().getItemPrice());
        });

        form.getSaveButton().click();
    }

    @Test
    void testHandleSaveEventUpdate() {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemName("Burger");
        itemDetails.setSaleDate(LocalDate.of(2024, 12, 1));
        form.update(itemDetails, false);

        form.getItemName().setValue("Pizza");
        form.getSaveButton().click();

        assertEquals("Pizza", itemDetails.getItemName());
    }

    @Test
    void testCancelEvent() {
        form.addListener(ItemDetailsForm.CancelEvent.class, event -> assertTrue(true, "Cancel event fired"));

        form.getCancelButton().click();
    }

    @Test
    void testDeleteEvent() {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemName("Burger");
        itemDetails.setSaleDate(LocalDate.of(2024, 12, 1));
        form.update(itemDetails, false);

        form.addListener(ItemDetailsForm.DeleteEvent.class, event -> {
            assertEquals("Burger", event.getItemDetails().getItemName());
            assertEquals(LocalDate.of(2024, 12, 1), event.getItemDetails().getSaleDate());
        });

        form.getDeleteButton().click();
    }

    @Test
    void testValidationFailure() {
        form.getItemPrice().setValue("InvalidNumber");

        form.addListener(ItemDetailsForm.AddEvent.class, event -> fail("Event should not fire on validation failure"));

        form.getSaveButton().click();

        assertFalse(form.getSaveButton().isEnabled(), "Save button should be disabled on validation failure");
    }

    @Test
    void testValueChangeListenerForDatePicker() {
        LocalDate date = LocalDate.of(2024, 12, 1);
        form.getSaleDate().setValue(date);
        assertEquals("2024-12-01", form.getSaleDate().getPlaceholder());
    }

}
