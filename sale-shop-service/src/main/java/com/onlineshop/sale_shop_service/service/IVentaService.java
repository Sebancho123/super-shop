package com.onlineshop.sale_shop_service.service;

import com.onlineshop.sale_shop_service.dto.CarritoShopDto;
import com.onlineshop.sale_shop_service.dto.ProductoDto;
import com.onlineshop.sale_shop_service.dto.VentaDto;
import com.onlineshop.sale_shop_service.model.Venta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    public List<Venta> getAll();
    public ResponseEntity<Object> save(VentaDto ventaDto);
    public void deleteById(Long id);
    public Optional<Venta> findById(Long id);
    public ResponseEntity<Object> update(VentaDto ventaDto, Long id_venta);
    public ResponseEntity<Double> toKnowTotalById(Long id);
    public ResponseEntity<List<ProductoDto>> getAllProds(Long id_venta);
    public ResponseEntity<CarritoShopDto> lookDetailsCar(Long id_venta);
}
