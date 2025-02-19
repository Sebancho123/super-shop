package com.onlineshop.products_shop_service.service;

import com.onlineshop.products_shop_service.model.Producto;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    public List<Producto> getAll();
    public Producto save(Producto producto);
    public void deleteById(Long codigo);
    public Optional<Producto> findById(Long codigo);
    public List<Producto> getProducsByIds(List<Long> codigos);
}
