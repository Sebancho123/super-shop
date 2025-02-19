package com.onlineshop.car_shop_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long codigo;
    private String nombre;
    private String marca;
    private double precio;

}
