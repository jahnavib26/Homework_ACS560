package com.acs560.restaurantsales.restaurant_sales.entities;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesEntityId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The date of the sale.
     */
    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    /**
     * The name of the item sold.
     */
    @Column(name = "item_name", nullable = false)
    private String itemName;

    /**
     * The type of transaction (e.g., purchase, refund).
     */
    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    /**
     * Constructs a new SalesEntityId with the provided date, item name, and transaction type.
     * 
     * @param saleDate - The date of the sale.
     * @param itemName - The name of the item sold.
     * @param transactionType - The type of transaction.
     */
    public SalesEntityId(LocalDate saleDate, String itemName, String transactionType) {
        this.saleDate = saleDate;
        this.itemName = itemName;
        this.transactionType = transactionType;
    }

    /**
     * Gets the date of the sale.
     * 
     * @return The sale date.
     */
    public LocalDate getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the date of the sale.
     * 
     * @param saleDate - The sale date to set.
     */
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * Gets the name of the sold item.
     * 
     * @return The item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the sold item.
     * 
     * @param itemName - The item name to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the type of the transaction (e.g., purchase, refund).
     * 
     * @return The transaction type.
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the type of the transaction.
     * 
     * @param transactionType - The transaction type to set.
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the serial version UID.
     * 
     * @return The serial version UID.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Default constructor for SalesEntityId.
     */
    public SalesEntityId() {
        super();
    }
}