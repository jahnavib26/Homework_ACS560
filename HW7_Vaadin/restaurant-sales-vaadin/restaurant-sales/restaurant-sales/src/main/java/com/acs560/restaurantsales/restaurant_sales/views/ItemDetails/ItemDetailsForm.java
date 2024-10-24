package com.acs560.restaurantsales.restaurant_sales.views.ItemDetails;

import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

/**
 * The form to manage an item's details.
 */
public class ItemDetailsForm extends FormLayout {

    private static final long serialVersionUID = 476310807171214015L;

    private final TextField itemName = new TextField("Item Name");
    private final TextField itemType = new TextField("Item Type");
    private final TextField quantity = new TextField("Quantity");
    private final TextField transactionAmount = new TextField("Transaction Amount");
    private final ComboBox<String> transactionType = new ComboBox<>("Transaction Type");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button cancel = new Button("Cancel");

    private final Binder<ItemDetails> binder = new BeanValidationBinder<>(ItemDetails.class);
    private ItemDetails itemDetails;
    private boolean isAdd;

    /**
     * Constructor
     */
    public ItemDetailsForm() {
        addClassName("itemdetails-form");

        transactionType.setItems("Cash", "Online", "Others");

        binder.bindInstanceFields(this);

        add(itemName, itemType, quantity, transactionAmount, transactionType, createButtonsLayout());
        setWidth("25em");
    }

    /**
     * Create the buttons component
     * @return - the buttons component
     */
    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> handleSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        cancel.addClickListener(event -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, cancel);
    }

    /**
     * Handler for save action
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
            e.printStackTrace();
        }
    }

    /**
     * Update the form with the item details and whether it is to add or update
     * @param itemDetails - the item details
     * @param isAdd - true indicates add, otherwise update
     */
    public void update(ItemDetails itemDetails, boolean isAdd) {
        this.isAdd = isAdd;

        // Set whether the delete button is visible
        delete.setVisible(!isAdd);

        if (itemDetails != null) {
            this.itemDetails = itemDetails;
        } else {
            // Reset fields to defaults
            itemName.setValue("");
            itemType.setValue("");
            quantity.setValue("");
            transactionAmount.setValue("");
            transactionType.clear();
            this.itemDetails = new ItemDetails();
        }

        binder.setBean(itemDetails);
    }

    /**
     * The abstract ItemDetailsFormEvent
     */
    public static abstract class ItemDetailsFormEvent extends ComponentEvent<ItemDetailsForm> {

        private static final long serialVersionUID = 8892029064323709532L;

        private final ItemDetails itemDetails;

        /**
         * Constructor
         * @param source - the ItemDetailsForm
         * @param itemDetails - the item details
         */
        protected ItemDetailsFormEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, false);
            this.itemDetails = itemDetails;
        }

        public ItemDetails getItemDetails() {
            return itemDetails;
        }
    }

    /**
     * The AddEvent for this form
     */
    public static class AddEvent extends ItemDetailsFormEvent {

        private static final long serialVersionUID = -8168200990060394704L;

        /**
         * Constructor
         * @param source - the ItemDetailsForm
         * @param itemDetails - the item details
         */
        protected AddEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, itemDetails);
        }
    }

    /**
     * The CancelEvent for this form
     */
    public static class CancelEvent extends ItemDetailsFormEvent {

        private static final long serialVersionUID = -6473184605060760145L;

        /**
         * Constructor
         * @param source - the ItemDetailsForm
         */
        protected CancelEvent(ItemDetailsForm source) {
            super(source, null);
        }
    }

    /**
     * The DeleteEvent for this form
     */
    public static class DeleteEvent extends ItemDetailsFormEvent {

        private static final long serialVersionUID = -5085028297106734234L;

        /**
         * Constructor
         * @param source - the ItemDetailsForm 
         * @param itemDetails - the deleted item details
         */
        protected DeleteEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, itemDetails);
        }
    }

    /**
     * The UpdateEvent for this form
     */
    public static class UpdateEvent extends ItemDetailsFormEvent {

        private static final long serialVersionUID = -5085028297106734234L;

        /**
         * Constructor
         * @param source - the ItemDetailsForm
         * @param itemDetails - the updated item details
         */
        protected UpdateEvent(ItemDetailsForm source, ItemDetails itemDetails) {
            super(source, itemDetails);
        }
    }

    /**
     * Add a listener to this form
     * @param eventType - the event type for which to call the listener
     * @param listener  - the listener
     * @return - an object that can be used to remove the listener
     */
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
            ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
