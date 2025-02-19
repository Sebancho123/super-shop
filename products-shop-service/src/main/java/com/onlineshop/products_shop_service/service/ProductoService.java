package com.onlineshop.products_shop_service.service;

import com.onlineshop.products_shop_service.model.Producto;
import com.onlineshop.products_shop_service.repository.IProductoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository iProdRepo;

    @Override
    public List<Producto> getAll() {
        return iProdRepo.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return iProdRepo.save(producto);
    }

    @Override
    public void deleteById(Long codigo) {
        iProdRepo.deleteById(codigo);
    }

    @Override
    public Optional<Producto> findById(Long codigo) {
        return iProdRepo.findById(codigo);
    }

    @Override
    public List<Producto> getProducsByIds(List<Long> codigos) {

        List<Producto> productos = this.getAll();
        List<Producto> productoListR = new ArrayList<>();

        for (Producto producto : productos) {
            for (Long codigo : codigos) {

                if (producto.getCodigo().equals(codigo)) {
                    productoListR.add(producto);
                }
            }
        }

        return productoListR;
    }
}
