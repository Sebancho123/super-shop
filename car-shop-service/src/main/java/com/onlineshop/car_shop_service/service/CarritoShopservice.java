package com.onlineshop.car_shop_service.service;

import com.onlineshop.car_shop_service.dto.CarritoShopDTO;
import com.onlineshop.car_shop_service.dto.ProductoDTO;
import com.onlineshop.car_shop_service.model.CarritoShop;
import com.onlineshop.car_shop_service.repository.ICarritoShopRepository;
import com.onlineshop.car_shop_service.repository.IProductApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoShopservice implements ICarritoShopService{

    @Autowired
    private ICarritoShopRepository iCarShopRepo;

    @Autowired
    private IProductApi iProductApi;

    @Override
    public List<CarritoShop> getAll() {
        return iCarShopRepo.findAll();
    }

    @Override
    public CarritoShop save(List<Long> codigos) {
        //sacar el total de prod

        double total = 0;
        List<ProductoDTO> productoDTOS = iProductApi.getProducsByIds(codigos);

        for (ProductoDTO productoDTO : productoDTOS) {
            System.out.println("entro?");
            total += productoDTO.getPrecio();
        }

        CarritoShop carritoShop = new CarritoShop();
        carritoShop.setCodsProd(codigos);
        carritoShop.setTotalMoneyProduts(total);

        return iCarShopRepo.save(carritoShop);
    }

    @Override
    public void deleteByid(Long id) {

    }

    @Override
    public Optional<CarritoShop> findById(Long id) {
        return iCarShopRepo.findById(id);
    }

    @Override
    public CarritoShop update(CarritoShop carritoShop) {

        return iCarShopRepo.save(carritoShop);
    }


    @Override
    @CircuitBreaker(name = "PRODUCTS-SHOP-SERVICE", fallbackMethod = "fallBackGetInfoProd")
    @Retry(name = "PRODUCTS-SHOP-SERVICE")
    public ResponseEntity<CarritoShopDTO> getInfoBasicOfProd(Long id_car) {


        Optional<CarritoShop> carritoShop = this.findById(id_car);

        if(carritoShop.isPresent()) {
            CarritoShop newCarritoShop =  carritoShop.get();

            //obtener lista de nombres de los productos y su precio total
            List<ProductoDTO> productoDTOS = iProductApi.getProducsByIds(newCarritoShop.getCodsProd());
            List<String> nombresProd = new ArrayList<>();
            double totalMProd = 0;

            for (ProductoDTO productoDTO : productoDTOS) {
                nombresProd.add(productoDTO.getNombre());
                totalMProd += productoDTO.getPrecio();
            }

            //retornamos el carroDto ya con sus parametros correspondientes para el consultor "sale" que necesita estos datos
            return ResponseEntity.ok(new CarritoShopDTO(totalMProd, nombresProd));
        }else {
            //en caso que el carrito no este ps....
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    @CircuitBreaker(name = "PRODUCTS-SHOP-SERVICE", fallbackMethod = "fallBackGetInfoProd")
    @Retry(name = "PRODUCTS-SHOP-SERVICE")
    public ResponseEntity<List<ProductoDTO>> getAllInfoOfProd(Long id_car) {

        Optional<CarritoShop> carritoShop = this.findById(id_car);

        if(carritoShop.isPresent()) {
            CarritoShop newCarritoShop = carritoShop.get();
            return ResponseEntity.ok(iProductApi.getProducsByIds(newCarritoShop.getCodsProd()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public CarritoShopDTO fallBackGetInfoProd(Throwable throwable) {

        return new CarritoShopDTO(0L, List.of());
    }
}
