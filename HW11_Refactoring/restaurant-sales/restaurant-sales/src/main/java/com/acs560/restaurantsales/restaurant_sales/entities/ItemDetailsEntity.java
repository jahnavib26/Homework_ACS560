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
    private Double itemPrice;

    
    @MapsId
    @JoinColumns({
        @JoinColumn(name = "sale_date", referencedColumnName = "sale_date"),
        @JoinColumn(name = "item_name", referencedColumnName = "item_name"),
        @JoinColumn(name = "transaction_type", referencedColumnName = "transaction_type")
    })
    @ManyToOne(fetch = FetchType.LAZY) 
    private SalesEntity salesEntity;

    
    public ItemDetailsEntity(SalesEntityId id, Double itemPrice, SalesEntity salesEntity) {
        this.id = id;
        this.itemPrice = itemPrice;
        this.salesEntity = salesEntity; 
    }
    
    public ItemDetailsEntity(SalesEntityId id, Double itemPrice) {
        this.id = id;
        this.itemPrice = itemPrice;
    }

	public SalesEntityId getId() {
		return id;
	}

	public void setId(SalesEntityId id) {
		this.id = id;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	
	
	public ItemDetailsEntity() {
        super();
    }

   
}
