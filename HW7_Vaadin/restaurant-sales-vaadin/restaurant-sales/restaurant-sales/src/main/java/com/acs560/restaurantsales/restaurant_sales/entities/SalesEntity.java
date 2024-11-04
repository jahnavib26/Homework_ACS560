package com.acs560.restaurantsales.restaurant_sales.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import jakarta.persistence.Column;
import lombok.ToString;

@Entity
@Table(name = "sales")
@ToString
public class SalesEntity {

    // Composite primary key
    @EmbeddedId
    private SalesEntityId id;

    @Column(name = "item_type")
    private String itemType;	

    @Column(name = "item_price")
    private Double itemPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "staff_gender")
    private String staffGender;

    @Column(name = "time_of_sale")
    private String timeOfSale;

 
    @Column(name = "`yearMonth`")
    private String yearMonth;

    /**
     * Gets the composite ID of the sales entity.
     * 
     * @return The sales entity ID.
     */
    public SalesEntityId getId() {
        return id;
    }

    /**
     * Sets the composite ID of the sales entity.
     * 
     * @param id - The sales entity ID to be set.
     */
    public void setId(SalesEntityId id) {
        this.id = id;
    }

    /**
     * Gets the item type.
     * 
     * @return The item type (e.g., food, beverage).
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the item type.
     * 
     * @param itemType - The item type to set.
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the price of the item.
     * 
     * @return The item price.
     */
    public Double getItemPrice() {
        return itemPrice;
    }

    /**
     * Sets the price of the item.
     * 
     * @param itemPrice - The item price to set.
     */
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Gets the quantity of items sold.
     * 
     * @return The quantity of items sold.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of items sold.
     * 
     * @param quantity - The quantity to set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the transaction amount for the sale.
     * 
     * @return The transaction amount.
     */
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the transaction amount for the sale.
     * 
     * @param transactionAmount - The transaction amount to set.
     */
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Gets the gender of the staff involved in the transaction.
     * 
     * @return The staff gender.
     */
    public String getStaffGender() {
        return staffGender;
    }

    /**
     * Sets the gender of the staff involved in the transaction.
     * 
     * @param staffGender - The staff gender to set.
     */
    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    /**
     * Gets the time when the sale occurred.
     * 
     * @return The time of sale.
     */
    public String getTimeOfSale() {
        return timeOfSale;
    }

    /**
     * Sets the time when the sale occurred.
     * 
     * @param timeOfSale - The time of sale to set.
     */
    public void setTimeOfSale(String timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    /**
     * Gets the year and month of the sale in the format YYYY-MM.
     * 
     * @return The year and month of the sale.
     */
    public String getYearMonth() {
        return yearMonth;
    }

    /**
     * Sets the year and month of the sale in the format YYYY-MM.
     * 
     * @param yearMonth - The year and month to set.
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * Constructor that converts a Sales model object into a SalesEntity object.
     * 
     * @param sales - The Sales model object.
     */
    public SalesEntity(Sales sales) {
        this.id = new SalesEntityId(sales.getDate(), sales.getItemName(), sales.getTransactionType());
        this.itemType = sales.getItemType();
        this.itemPrice = sales.getItemPrice();
        this.quantity = sales.getQuantity();
        this.transactionAmount = sales.getTransactionAmount();
        this.staffGender = sales.getStaffGender();
        this.timeOfSale = sales.getTimeOfSale();
        this.yearMonth = sales.getYearMonth();
    }

    /**
     * Default constructor for SalesEntity.
     */
    public SalesEntity() {
        super();
    }
}