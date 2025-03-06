package com.onlineshop.sale_shop_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.onlineshop.sale_shop_service.repository")
public class SaleShopServiceApplication {

	public static void main(String[] args) {
		System.out.println("DB_URL : " + System.getenv("DB_URL"));
		SpringApplication.run(SaleShopServiceApplication.class, args);
	}
}
