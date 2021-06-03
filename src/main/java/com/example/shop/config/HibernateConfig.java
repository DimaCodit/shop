package com.example.shop.config;

import com.example.shop.model.Consumer;
import com.example.shop.model.Order;
import com.example.shop.model.OrderItem;
import com.example.shop.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public org.hibernate.cfg.Configuration config () {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Consumer.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(OrderItem.class);

    }

    @Bean
    SessionFactory sessionFactory() {
        return config().buildSessionFactory();
    }
}
