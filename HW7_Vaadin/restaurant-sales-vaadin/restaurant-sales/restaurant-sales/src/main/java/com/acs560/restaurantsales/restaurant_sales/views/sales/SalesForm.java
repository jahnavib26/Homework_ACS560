package com.acs560.restaurantsales.restaurant_sales.views.sales;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
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
 * The form to manage sales data.
 */
public class SalesForm extends FormLayout {

    private static final long serialVersionUID = 476310807171214015L;

    private final TextField itemName = new TextField("Item Name");
    private final TextField quantity = new TextField("Quantity");
    private final TextField transactionAmount = new TextField("Transaction Amount");
    private final ComboBox<String> transactionType = new ComboBox<>("Transaction Type");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button cancel = new Button("Cancel");

    private final Binder<Sales> binder = new BeanValidationBinder<>(Sales.class);
    private Sales sales;
    private boolean isAdd;

    /**
     * Constructor
     */
    public SalesForm() {
        addClassName("sales-form");

        transactionType.setItems("Cash", "Online", "Others");

        binder.bindInstanceFields(this);

        add(itemName, quantity, transactionAmount, transactionType, createButtonsLayout());
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
            binder.writeBean(sales);

            if (isAdd) {
                fireEvent(new AddEvent(this, sales));
            } else {
                fireEvent(new UpdateEvent(this, sales));
            }

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the form with the sales data and whether it is to add or update
     * @param sales - the sales object
     * @param isAdd - true indicates add, otherwise update
     */
    public void update(Sales sales, boolean isAdd) {
        this.isAdd = isAdd;

        // Set whether the delete button is visible
        delete.setVisible(!isAdd);

        if (sales != null) {
            this.sales = sales;

            // Set fields with values from the existing sales object
            itemName.setValue(sales.getItemName());
            quantity.setValue(String.valueOf(sales.getQuantity()));  // Convert int to String
            transactionAmount.setValue(String.valueOf(sales.getTransactionAmount()));  // Convert double to String
            transactionType.setValue(sales.getTransactionType());
        } else {
            // Reset fields to defaults
            itemName.setValue("");
            quantity.setValue("");
            transactionAmount.setValue("");
            transactionType.clear();
            this.sales = new Sales();  // Create a new instance of Sales
        }

        binder.setBean(this.sales);  // Always bind a non-null object
    }

    /**
     * The abstract SalesFormEvent
     */
    public static abstract class SalesFormEvent extends ComponentEvent<SalesForm> {

        private static final long serialVersionUID = 8892029064323709532L;

        private final Sales sales;

        /**
         * Constructor
         * @param source - the SalesForm
         * @param sales - the sales object
         */
        protected SalesFormEvent(SalesForm source, Sales sales) {
            super(source, false);
            this.sales = sales;
        }

        public Sales getSales() {
            return sales;
        }
    }

    /**
     * The AddEvent for this form
     */
    public static class AddEvent extends SalesFormEvent {

        private static final long serialVersionUID = -8168200990060394704L;

        /**
         * Constructor
         * @param source - the SalesForm
         * @param sales - the sales object
         */
        protected AddEvent(SalesForm source, Sales sales) {
            super(source, sales);
        }
    }

    /**
     * The CancelEvent for this form
     */
    public static class CancelEvent extends SalesFormEvent {

        private static final long serialVersionUID = -6473184605060760145L;

        /**
         * Constructor
         * @param source - the SalesForm
         */
        protected CancelEvent(SalesForm source) {
            super(source, null);
        }
    }

    /**
     * The DeleteEvent for this form
     */
    public static class DeleteEvent extends SalesFormEvent {

        private static final long serialVersionUID = -5085028297106734234L;

        /**
         * Constructor
         * @param source - the SalesForm 
         * @param sales - the deleted sales object
         */
        protected DeleteEvent(SalesForm source, Sales sales) {
            super(source, sales);
        }
    }

    /**
     * The UpdateEvent for this form
     */
    public static class UpdateEvent extends SalesFormEvent {

        private static final long serialVersionUID = -5085028297106734234L;

        /**
         * Constructor
         * @param source - the SalesForm
         * @param sales - the updated sales object
         */
        protected UpdateEvent(SalesForm source, Sales sales) {
            super(source, sales);
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