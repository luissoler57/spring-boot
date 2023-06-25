package com.example.springboot.mybeans;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springboot.models.Product;
import com.example.springboot.services.IOrderService;
import com.example.springboot.services.OrderService;

@Configuration // Contiene declaraciones de bean
public class MiPrimerBeans {

    @Bean // Crea bean perzonalizado
    public MiBean crearMiBean() {
        return new MiBean();
    }

    @Bean
    public IOrderService instanciarOrderService() {
        final boolean esProduccion = true;
        if (esProduccion) {
            return new OrderService();
        } else {
            return iOrderService();
        }
    }

    public IOrderService iOrderService() {
        return new IOrderService() {

            @Override
            public void saveOrder(List<Product> products) {
                System.out.println("Guardando en base de datos DUMMY");
            }

        };
    }
}
