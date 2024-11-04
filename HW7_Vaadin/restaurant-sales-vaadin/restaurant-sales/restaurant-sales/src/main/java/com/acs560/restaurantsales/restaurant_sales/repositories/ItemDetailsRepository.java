package com.acs560.restaurantsales.restaurant_sales.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.acs560.restaurantsales.restaurant_sales.entities.ItemDetailsEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntity;
import com.acs560.restaurantsales.restaurant_sales.entities.SalesEntityId;
import com.acs560.restaurantsales.restaurant_sales.models.ItemDetails;

public interface ItemDetailsRepository extends CrudRepository<ItemDetailsEntity, SalesEntityId> {
  
	@Query(value = "SELECT * FROM item_details", nativeQuery = true)
    List<ItemDetailsEntity> findAll();
	
	
	@Override
    Optional<ItemDetailsEntity> findById(SalesEntityId id);
	
	 @Override
	 <Itm extends ItemDetailsEntity> Itm save(Itm entity);

	 @Query(value = "SELECT * FROM item_details WHERE item_price = :itemPrice ", nativeQuery = true)
	List<ItemDetailsEntity> findByNonPk(Double itemPrice);


	@Query(value = "SELECT * FROM item_details where item_name=:itemName", nativeQuery = true)
    List<ItemDetailsEntity> findByItemName(String itemName);
}
