package com.onlineshop.products_shop_service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsShopServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProductsShopServiceApplication.class, args);
	}


	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Producto-sv")
						.description("App de spring un servicio de produtos online")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
