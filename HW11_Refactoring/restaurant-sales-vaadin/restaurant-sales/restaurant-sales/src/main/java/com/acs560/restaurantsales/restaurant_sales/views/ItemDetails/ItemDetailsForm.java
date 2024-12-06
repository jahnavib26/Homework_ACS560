package com.acs560.restaurantsales.restaurant_sales.views.ItemDetails;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.shared.Registration;

/**
 * The form to manage an item's details.
 */
public class ItemDetailsForm extends FormLayout {
	
	// Getter methods for private fields
    public Button getSaveButton() {
        return save;
    }

    public Button getDeleteButton() {
        return delete;
    }

    public Button getCancelButton() {
        return cancel;
    }

    public ComboBox<String> getTransactionType() {
        return transactionType;
    }

    public DatePicker getSaleDate() {
        return saleDate;
    }

    public TextField getItemName() {
        return itemName;
    }

    public TextField getItemPrice() {
        return itemPrice;
    }

    private static final long serialVersionUID = 476310807171214015L;

    // Form fields
    private final DatePicker saleDate = new DatePicker("Sale Date");
    private final TextField itemName = new TextField("Item Name");
    private final ComboBox<String> transactionType = new ComboBox<>("Transaction Type");
    private final TextField itemPrice = new TextField("Item Price");

    // Buttons
    private final Button save = createButton("Save", ButtonVariant.LUMO_PRIMARY, Key.ENTER);
    final Button delete = createButton("Delete", ButtonVariant.LUMO_ERROR, null);
    private final Button cancel = createButton("Cancel", ButtonVariant.LUMO_TERTIARY, Key.ESCAPE);

    // Binder
    final Binder<ItemDetails> binder = new BeanValidationBinder<>(ItemDetails.class);
    private ItemDetails itemDetails;
    private boolean isAdd;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    
    /**
     * Constructor
     */
    public ItemDetailsForm() {
        addClassName("itemdetails-form");

        configureFields();
        configureBinder();

        add(saleDate, itemName, transactionType, itemPrice, createButtonsLayout());
        setWidth("25em");
    }

    /**
     * Configures the form fields
     */
    private void configureFields() {
        transactionType.setItems("Cash", "Online", "Others");
        saleDate.setLocale(Locale.US);

        saleDate.addValueChangeListener(event -> {
            LocalDate selectedDate = event.getValue();
            if (selectedDate != null) {
                saleDate.setPlaceholder(selectedDate.format(formatter));
            }
        });
    }

    /**
     * Configures the binder for field validation and data binding
     */
    private void configureBinder() {
        binder.forField(saleDate).bind(ItemDetails::getSaleDate, ItemDetails::setSaleDate);
        binder.forField(itemName).bind(ItemDetails::getItemName, ItemDetails::setItemName);
        binder.forField(transactionType).bind(ItemDetails::getTransactionType, ItemDetails::setTransactionType);
        binder.forField(itemPrice)
                .withConverter(new StringToDoubleConverter("Please enter a valid number"))
                .bind(ItemDetails::getItemPrice, ItemDetails::setItemPrice);

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
    }

    /**
     * Creates a button with specified properties
     *
     * @param text       - the button text
     * @param variant    - the button variant
     * @param shortcut   - the optional shortcut key
     * @return the configured button
     */
    private Button createButton(String text, ButtonVariant variant, Key shortcut) {
        Button button = new Button(text);
        button.addThemeVariants(variant);
        if (shortcut != null) {
            button.addClickShortcut(shortcut);
        }
        return button;
    }

    /**
     * Creates the buttons layout
     *
     * @return - the buttons layout component
     */
    private Component createButtonsLayout() {
        save.addClickListener(event -> handleSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, itemDetails)));
        cancel.addClickListener(event -> fireEvent(new CancelEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    /**
     * Handles the save action for the form
     */
    private void handleSave() {
        try {
            binder.writeBean(itemDetails);

            if (isAdd) {
                fireEvent(new AddEvent(this, itemDetails));
            } else {
                fireEvent(new UpdateEvent(this, itemDetails));
            }

        } catch (ValidationException e) {
            showNotification("Form validation failed: " + e.getMessage());
        } catch (Exception e) {
            showNotification("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Updates the form with item details and operation mode
     *
     * @param itemDetails - the item details
     * @param isAdd       - true if adding, false if updating
     */
//    public void update(ItemDetails itemDetails, boolean isAdd) {
//        this.isAdd = isAdd;
//        delete.setVisible(!isAdd);
//
//        if (itemDetails == null) {
//            this.itemDetails = new ItemDetails();
//        } else {
//            this.itemDetails = itemDetails;
//        }
//
//        binder.setBean(this.itemDetails);
//    }
    
    public void update(ItemDetails itemDetails, boolean isAdd) {
        this.isAdd = isAdd;

        // Debug visibility
        System.out.println("Update called with isAdd = " + isAdd);

        delete.setVisible(!isAdd);
        System.out.println("Delete button visibility: " + delete.isVisible());

        if (itemDetails != null) {
            this.itemDetails = itemDetails;
            System.out.println("Updated item details: " + itemDetails.getItemName());
        } else {
            this.itemDetails = new ItemDetails();
        }

        binder.setBean(this.itemDetails);
    }


    /**
     * Displays a notification message
     *
     * @param message - the notification message
     */
    private void showNotification(String message) {
        Notification.show(message, 3000, Notification.Position.MIDDLE);
    }

    /**
     * Abstract class for form events
     */
    public static abstract class ItemDetailsFormEvent extends ComponentEvent<ItemDetailsForm> {
        private static final long serialVersionUID = 8892029064323709532L;

        private final ItemDetails itemDetails;

        protected ItemDetailsFormEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, false);
            this.itemDetails = itemDetails;
        }

        public ItemDetails getItemDetails() {
            return itemDetails;
        }
    }

    // Specific event classes for form actions
    public static class AddEvent extends ItemDetailsFormEvent {
        public AddEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, itemDetails);
        }
    }

    public static class UpdateEvent extends ItemDetailsFormEvent {
        public UpdateEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, itemDetails);
        }
    }

    public static class DeleteEvent extends ItemDetailsFormEvent {
        public DeleteEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, itemDetails);
        }
    }

    public static class CancelEvent extends ItemDetailsFormEvent {
        public CancelEvent(ItemDetailsForm source) {
            super(source, null);
        }
    }

    /**
     * Adds a listener for the specified event type
     *
     * @param eventType - the event type
     * @param listener  - the event listener
     * @return the registration for the listener
     */
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
