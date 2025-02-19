package com.onlineshop.products_shop_service.repository;

import com.onlineshop.products_shop_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
