package com.example.shop;

import com.example.shop.service.StatisticService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StatisticService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println(service.getOrdersItemsByCustomerId(1L));
                System.out.println(service.getConsumersByProductId(1L));
            }
        };
    }

}
