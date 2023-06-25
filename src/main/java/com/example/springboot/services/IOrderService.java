package com.example.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot.models.Product;

@Service
public interface IOrderService {

    public void saveOrder(List<Product> products);
}
