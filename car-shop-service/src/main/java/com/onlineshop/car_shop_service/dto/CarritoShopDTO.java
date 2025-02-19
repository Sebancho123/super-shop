package com.onlineshop.car_shop_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoShopDTO {

    private double totalMoneyProduts;
    private List<String> nombreProducts;

}
