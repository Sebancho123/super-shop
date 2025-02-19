package com.onlineshop.car_shop_service.repository;

import com.onlineshop.car_shop_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-shop-service")
public interface IProductApi {

    @GetMapping("/product/getProductsByCods/{codigos}")
    public List<ProductoDTO> getProducsByIds(@PathVariable List<Long> codigos);


}
