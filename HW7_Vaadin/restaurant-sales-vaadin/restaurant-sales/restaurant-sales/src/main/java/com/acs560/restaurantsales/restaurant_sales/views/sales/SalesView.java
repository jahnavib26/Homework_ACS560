package com.acs560.restaurantsales.restaurant_sales.views.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import com.acs560.restaurantsales.restaurant_sales.services.SalesService;
import com.acs560.restaurantsales.restaurant_sales.services.ItemDetailsService;
import com.acs560.restaurantsales.restaurant_sales.views.MainLayout;
import com.acs560.restaurantsales.restaurant_sales.views.sales.SalesForm.AddEvent;
import com.acs560.restaurantsales.restaurant_sales.views.sales.SalesForm.CancelEvent;
import com.acs560.restaurantsales.restaurant_sales.views.sales.SalesForm.DeleteEvent;
import com.acs560.restaurantsales.restaurant_sales.views.sales.SalesForm.UpdateEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

import jakarta.annotation.security.PermitAll;

/**
 * The view to display and manage sales.
 */
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "sales", layout = MainLayout.class)
@PageTitle("Sales | Restaurant Sales")
public class SalesView extends VerticalLayout {

    private static final long serialVersionUID = 3031343634720386314L;

    @Autowired
    private SalesService salesService;
   
    private ItemDetailsService itemDetailsService;

    private final Grid<Sales> grid;
    private final TextField filterText;
    private final SalesForm salesForm;
    private boolean filtering = false;

    /**
     * Constructor Builds this component
     *
     * @param salesService - the autowired sales service
     * @param itemDetailsService - the autowired item details service
     */
    public SalesView(SalesService salesService, ItemDetailsService itemDetailsService) {
        this.salesService = salesService;
        this.itemDetailsService = itemDetailsService;

        addClassName("list-view");
        setSizeFull();

        grid = createGrid();
        salesForm = createForm();
        filterText = createFilter();

        add(getToolbar(filterText), getContent());
        updateGrid();
        closeForm();
    }

    /**
     * Create the sales grid
     * @return - the sales grid
     */
    private Grid<Sales> createGrid() {
        Grid<Sales> grid = new Grid<>(Sales.class);
        grid.addClassNames("sales-grid");
        grid.setSizeFull();

        grid.setColumns();
        grid.setColumns();
        grid.addColumn(Sales::getDate).setHeader("Sale Date");
        grid.addColumn(sales -> sales.getItemName()).setHeader("Item Name");
        grid.addColumn(sales -> sales.getItemType()).setHeader("Item Type");
        grid.addColumn(Sales::getItemPrice).setHeader("Item Price");
        grid.addColumn(Sales::getQuantity).setHeader("Quantity");
        grid.addColumn(Sales::getTransactionAmount).setHeader("Transaction Amount");
        grid.addColumn(Sales::getTransactionType).setHeader("Transaction Type");
        grid.addColumn(Sales::getStaffGender).setHeader("Staff Gender");
        grid.addColumn(Sales::getTimeOfSale).setHeader("Time of Sale");
        grid.addColumn(Sales::getYearMonth).setHeader("Year month");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> handleSelected(event.getValue()));

        return grid;
    }

    /**
     * Create the sales form
     *
     * @return - the sales form
     */
    private SalesForm createForm() {
        SalesForm salesForm = new SalesForm();
        salesForm.addListener(AddEvent.class, this::addSale);
        salesForm.addListener(DeleteEvent.class, this::deleteSale);
        salesForm.addListener(CancelEvent.class, e -> closeForm());
        salesForm.addListener(UpdateEvent.class, this::updateSale);

        return salesForm;
    }

    /**
     * Create the filter text field
     * @return - the filter text field
     */
    private TextField createFilter() {
        TextField filterText = new TextField();
        filterText.setValueChangeTimeout(2000);
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
    private Component getToolbar(TextField filterText) {
        Button addSaleButton = new Button("Add Sale");
        addSaleButton.addClickListener(click -> handleAdd());

        var toolbar = new HorizontalLayout(filterText, addSaleButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /**
     * Get the main content for the view
     * @return - the main content for the view
     */
    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, salesForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, salesForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    /**
     * Update the view's grid of data
     */
    private void updateGrid() {
        grid.setItems(salesService.getSales());
    }

    /**
     * Handler for selected sale from the grid
     * @param sales - the selected sale
     */
    private void handleSelected(Sales sales) {
        this.salesForm.update(sales, itemDetailsService.getItemDetails(), false);
        this.salesForm.setVisible(true);
        addClassName("editing");
    }

    /**
     * Handler for add sale button action
     */
    private void handleAdd() {
        grid.asSingleSelect().clear();
        salesForm.update(null, itemDetailsService.getItemDetails(), true);
        salesForm.setVisible(true);
        addClassName("editing");
    }

    /**
     * Handler for sales form close event
     */
    private void closeForm() {
        salesForm.setVisible(false);
        removeClassName("editing");
    }

    /**
     * Handler for sales form add event
     * @param event - the AddEvent
     */
    private void addSale(AddEvent event) {
        salesService.add(event.getSales());
        updateGrid();
        closeForm();
    }

    /**
     * Handler for sales form update event
     * @param event - the UpdateEvent
     */
    private void updateSale(UpdateEvent event) {
        salesService.update(event.getSales());
        updateGrid();
        closeForm();
    }

    /**
     * Handler for sales form delete event
     * @param event - the DeleteEvent
     */
    private void deleteSale(DeleteEvent event) {
        salesService.delete(event.getSales());

        updateGrid();
        closeForm();
    }

    /**
     * Handler for the filter text change listener
     */
    private void handleFilter() {
    	System.out.print("Inside handle filter");
//    	if (!filtering) {
//    		return;
//    	}
    	System.out.print("Inside handle filter 2");
    	String filter = filterText.getValue();
    	List<Sales> sales;
    	
    	if (filter.length() > 2) {
    		sales = salesService.getSalesByItemName(filter);
    	} else {
    		sales = salesService.getSales();
    	}
    	
    	grid.setItems(sales);
    }
}
