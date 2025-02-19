package com.onlineshop.car_shop_service.repository;

import com.onlineshop.car_shop_service.model.CarritoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoShopRepository extends JpaRepository<CarritoShop, Long> {
}
