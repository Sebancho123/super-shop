package com.onlineshop.car_shop_service.controller;

import com.onlineshop.car_shop_service.dto.CarritoShopDTO;
import com.onlineshop.car_shop_service.dto.ProductoDTO;
import com.onlineshop.car_shop_service.model.CarritoShop;
import com.onlineshop.car_shop_service.service.ICarritoShopService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarritoShopController {

    @Autowired
    private ICarritoShopService iCarShopSer;

    @GetMapping("/getAll")
    public ResponseEntity<List<CarritoShop>> getAll() {
        return ResponseEntity.ok(iCarShopSer.getAll());
    }

    @PostMapping("/save/{codigos}")
    public ResponseEntity<Object> save(@PathVariable List<Long> codigos) {

        try{
            CarritoShop newCarShop = iCarShopSer.save(codigos);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .replacePath("/car/findById/{id}")
                    .buildAndExpand(newCarShop.getId())
                    .toUri();

            return ResponseEntity.created(location).body(newCarShop);

        }catch (Exception e) {

            return ResponseEntity.badRequest().body("no sabemos que paso quiza te falta parametros");
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable Long id) {

        try {
            iCarShopSer.deleteByid(id);
            return ResponseEntity.ok("eliminacion completada");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CarritoShop> findById(@PathVariable Long id) {

        Optional<CarritoShop> carritoShopEncont = iCarShopSer.findById(id);
        return carritoShopEncont.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(CarritoShop carritoShop, @PathVariable Long id) {

        try {

            if(!carritoShop.equals(id)) {
                return ResponseEntity.badRequest().body("los ids no coinciden");
            }

            return ResponseEntity.ok(iCarShopSer.update(carritoShop));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getBasicInfoProds/{id_car}")
    public ResponseEntity<CarritoShopDTO> getInfoBasicOfProd(@PathVariable Long id_car) {
        return iCarShopSer.getInfoBasicOfProd(id_car);
    }

    @GetMapping("/getAllInfoProds/{id_car}")
    public ResponseEntity<List<ProductoDTO>> getAllInfoOfProd(@PathVariable Long id_car) {

        return iCarShopSer.getAllInfoOfProd(id_car);
    }
}
