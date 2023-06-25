package com.example.springboot.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.example.springboot.models.Product;

public class OrderService implements IOrderService {

    @Value("${baseurl}")
    private String dataBaseUrl;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void saveOrder(List<Product> products) {
        System.out.println("Guardando ......  " + dataBaseUrl);
        products.forEach(product -> logger.debug("El producto es {}", product.nombre));
    }
}
