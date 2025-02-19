package com.onlineshop.sale_shop_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto {
    private LocalDate fecha;
    private Long id_car;
}
