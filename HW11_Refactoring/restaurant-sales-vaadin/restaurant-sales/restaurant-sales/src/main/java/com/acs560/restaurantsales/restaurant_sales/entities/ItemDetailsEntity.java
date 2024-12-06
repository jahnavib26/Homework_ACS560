package com.acs560.restaurantsales.restaurant_sales.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ItemDetailsEntity {

    @EmbeddedId
    private SalesEntityId id;

    @Column(name = "item_price", nullable = false)
    private double itemPrice;

    @MapsId
    @JoinColumns({
        @JoinColumn(name = "sale_date", referencedColumnName = "sale_date"),
        @JoinColumn(name = "item_name", referencedColumnName = "item_name"),
        @JoinColumn(name = "transaction_type", referencedColumnName = "transaction_type")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private SalesEntity salesEntity;

    // Keeping only one constructor that includes itemPrice and salesEntity for initialization
    public ItemDetailsEntity(SalesEntityId id, double itemPrice, SalesEntity salesEntity) {
        this.id = id;
        this.itemPrice = itemPrice;
        this.salesEntity = salesEntity;
    }

	public SalesEntityId getId() {
		return id;
	}

	public void setId(SalesEntityId id) {
		this.id = id;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public SalesEntity getSalesEntity() {
		return salesEntity;
	}

	public void setSalesEntity(SalesEntity salesEntity) {
		this.salesEntity = salesEntity;
	}
    public ItemDetailsEntity() {
        super();
    }
    
}
