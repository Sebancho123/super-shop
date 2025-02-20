package com.onlineshop.eureka_shop_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class VariablesConfig {

    @PostConstruct
    public void env() {

        //para agregar la dirreccion del env
        Dotenv dotenv = Dotenv.configure()
                .directory("D:\\spring\\sistema_shop_online")
                .filename("variables.env")
                .load();

        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
