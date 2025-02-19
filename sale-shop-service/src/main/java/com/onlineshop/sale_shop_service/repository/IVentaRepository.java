package com.onlineshop.sale_shop_service.repository;

import com.onlineshop.sale_shop_service.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
}
