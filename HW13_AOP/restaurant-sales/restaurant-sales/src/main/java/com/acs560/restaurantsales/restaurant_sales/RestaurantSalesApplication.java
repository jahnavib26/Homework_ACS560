package com.acs560.restaurantsales.restaurant_sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RestaurantSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSalesApplication.class, args);
    }
}
