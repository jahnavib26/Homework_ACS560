//package com.acs560.restaurantsales.restaurant_sales.repositories;
//
//import com.acs560.restaurantsales.restaurant_sales.models.Sales;
//import java.util.List;
//import java.util.Optional;
//
//public interface SalesRepository {
//    List<Sales> findAllSales();
//    Optional<Sales> findByDate(String date);
//    List<Sales> findByItemName(String itemName);
//}


package com.acs560.restaurantsales.restaurant_sales.repositories;

import com.acs560.restaurantsales.restaurant_sales.models.Sales;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository {
    List<Sales> findAllSales();
    Optional<Sales> findByDate(String date);
    List<Sales> findByItemName(String itemName);
}