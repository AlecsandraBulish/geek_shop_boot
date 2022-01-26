package ru.bulish.spring.geek_shop_boot;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeekShopBootApplication {

    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://192.168.99.100:5432/shop", "postgres", "bulish")
                .load();
        flyway.migrate();
        SpringApplication.run(GeekShopBootApplication.class, args);
    }

}
