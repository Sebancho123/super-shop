package com.onlineshop.sale_shop_service.repository;

import com.onlineshop.sale_shop_service.dto.CarritoShopDto;
import com.onlineshop.sale_shop_service.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "car-shop-service")
public interface ICarAPi {

    @GetMapping("/car/findById/{id}")
    public CarritoShopDto findCarById(@PathVariable Long id);

    @GetMapping("/car/getAllInfoProds/{id_car}")
    public List<ProductoDto> getAllInfoProds(@PathVariable Long id_car);
}
