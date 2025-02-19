package com.onlineshop.sale_shop_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoShopDto {

    private Long id;
    private double totalMoneyProduts;
    private List<Integer> codsProd;
}
