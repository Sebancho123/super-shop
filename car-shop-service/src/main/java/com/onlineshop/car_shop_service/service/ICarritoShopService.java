package com.onlineshop.car_shop_service.service;

import com.onlineshop.car_shop_service.dto.CarritoShopDTO;
import com.onlineshop.car_shop_service.dto.ProductoDTO;
import com.onlineshop.car_shop_service.model.CarritoShop;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ICarritoShopService {

    public List<CarritoShop> getAll();
    public CarritoShop save(List<Long> codigos);
    public void deleteByid(Long id);
    public Optional<CarritoShop> findById(Long id);
    public CarritoShop update(CarritoShop carritoShop);
    public ResponseEntity<CarritoShopDTO> getInfoBasicOfProd(Long id_car);
    public ResponseEntity<List<ProductoDTO>> getAllInfoOfProd(Long id_car);
}
