package com.acs560.restaurantsales.restaurant_sales.views.ItemDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.acs560.restaurantsales.restaurant_sales.entities.ItemDetailsEntity;
import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;
import com.acs560.restaurantsales.restaurant_sales.requests.ItemDetailsRequest;
import com.acs560.restaurantsales.restaurant_sales.services.ItemDetailsService;
import com.acs560.restaurantsales.restaurant_sales.views.MainLayout;
import com.acs560.restaurantsales.restaurant_sales.views.ItemDetails.ItemDetailsForm.AddEvent;
import com.acs560.restaurantsales.restaurant_sales.views.ItemDetails.ItemDetailsForm.CancelEvent;
import com.acs560.restaurantsales.restaurant_sales.views.ItemDetails.ItemDetailsForm.DeleteEvent;
import com.acs560.restaurantsales.restaurant_sales.views.ItemDetails.ItemDetailsForm.UpdateEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

import jakarta.annotation.security.PermitAll;

/**
 * The view to display and manage item details.
 */
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "item_details", layout = MainLayout.class)
@PageTitle("Item Details | Restaurant Sales")
public class ItemDetailsView extends VerticalLayout {

    private static final long serialVersionUID = 6436483924131073477L;

//    @Autowired 
    private ItemDetailsService itemDetailsService;

    private final Grid<ItemDetails> grid;
    private final TextField filterText;
    private final ItemDetailsForm itemDetailsForm;

    private boolean filtering = false;

    /**
     * Constructor.
     * Builds this component.
     * @param itemDetailsService - the autowired ItemDetailsService
     */
    public ItemDetailsView(ItemDetailsService itemDetailsService) {
        this.itemDetailsService = itemDetailsService;

        addClassName("list-view");
        setSizeFull();

        grid = createGrid();
        itemDetailsForm = createForm();
        filterText = createFilter();

        add(createToolbar(filterText), getContent());
        updateGrid();
        closeForm();
    }

    /**
     * Create the ItemDetails Form
     * @return - the item details form
     */
    private ItemDetailsForm createForm() {
        ItemDetailsForm itemDetailsForm = new ItemDetailsForm();

        itemDetailsForm.addListener(AddEvent.class, this::addItemDetails);
        itemDetailsForm.addListener(DeleteEvent.class, this::deleteItemDetails);
        itemDetailsForm.addListener(CancelEvent.class, e -> closeForm());
        itemDetailsForm.addListener(UpdateEvent.class, this::updateItemDetails);

        return itemDetailsForm;
    }

    /**
     * Create the item details grid
     * @return - the item details grid
     */
    private Grid<ItemDetails> createGrid() {
        Grid<ItemDetails> grid = new Grid<>(ItemDetails.class);
        grid.setSizeFull();

        grid.addClassNames("itemdetails-grid");
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(ItemDetails::getSaleDate).setHeader("Sale Date");
        grid.addColumn(ItemDetails::getItemName).setHeader("Item name");
        grid.addColumn(ItemDetails::getTransactionType).setHeader("Transaction Type");
        grid.addColumn(ItemDetails::getItemPrice).setHeader("Item price");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
            handleSelected(event.getValue())
        );

        return grid;
    }

    /**
     * Create the filter text field
     * @return - the filter text field
     */
    private TextField createFilter() {
        TextField filterText = new TextField();
        filterText.setValueChangeTimeout(1000);
        filterText.setPlaceholder("Filter by item name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> handleFilter());

        return filterText;
    }

    /**
     * Create the toolbar
     * @param filterText - the filter text field
     * @return - the toolbar
     */
    private Component createToolbar(TextField filterText) {
        Button addItemButton = new Button("Add item");
        addItemButton.addClickListener(click -> handleAdd());

        var toolbar = new HorizontalLayout(filterText, addItemButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    /**
     * Get the main content for the view
     * @return - the main content for the view
     */
    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, itemDetailsForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, itemDetailsForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    /**
     * Handler for the filter text change listener
     */
    private void handleFilter() {

        if (!filtering) {
            return;
        }

        String filter = filterText.getValue();
        List<ItemDetails> itemDetailsList;

        if (filter.length() > 2) {
            itemDetailsList = itemDetailsService.getItemDetailsByItemName(filter);
        } else {
            itemDetailsList = itemDetailsService.getItemDetails();
        }

        grid.setItems(itemDetailsList);
    }

    /**
     * Update the view's grid of data
     */
    private void updateGrid() {
        List<ItemDetails> itemDetailsList = itemDetailsService.getItemDetails();
        grid.setItems(itemDetailsList);
        filtering = false;
        filterText.clear();
        filtering = true;
    }

    /**
     * Handler for selected item from the grid
     * @param itemDetails - the selected item details
     */
    private void handleSelected(ItemDetails itemDetails) {

        // Set the selected item details on the form
        itemDetailsForm.update(itemDetails, false);

        itemDetailsForm.setVisible(true);
        addClassName("editing");

        // TODO MCP disable grid selection...and reenable when cancel is clicked
    }

    /**
     * Handler for add item button action
     */
    private void handleAdd() {
        grid.asSingleSelect().clear();
        itemDetailsForm.update(null, true);
        itemDetailsForm.setVisible(true);
        addClassName("editing");
    }

    /**
     * Handler for item details form close event
     */
    private void closeForm() {
        itemDetailsForm.setVisible(false);
        removeClassName("editing");
    }

    /**
     * Handler for item details form add event
     * @param event - the AddEvent
     */
    private void addItemDetails(AddEvent event) {
        ItemDetailsRequest idr = new ItemDetailsRequest(event.getItemDetails().getSaleDate(),event.getItemDetails().getItemName(),event.getItemDetails().getTransactionType(),event.getItemDetails().getItemPrice());
        itemDetailsService.addItemDetail(idr);
        updateGrid();
        closeForm();
    }

    /**
     * Handler for item details form update event
     * @param event - the UpdateEvent
     */
    private void updateItemDetails(UpdateEvent event) {
        final int id = event.getItemDetails().getId();
        final ItemDetailsRequest idr = new ItemDetailsRequest(event.getItemDetails().getSaleDate(),event.getItemDetails().getItemName(),event.getItemDetails().getTransactionType(),event.getItemDetails().getItemPrice());
        itemDetailsService.updateItemDetails(id, idr);
        updateGrid();
        closeForm();
    }

    /**
     * Handler for item details form delete event
     * @param event - the DeleteEvent
     */
    private void deleteItemDetails(DeleteEvent event) {
        itemDetailsService.deleteItemDetails(event.getItemDetails().getId());
        updateGrid();
        closeForm();
    }

}
