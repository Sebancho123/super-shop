package com.onlineshop.sale_shop_service.controller;

import com.onlineshop.sale_shop_service.dto.CarritoShopDto;
import com.onlineshop.sale_shop_service.dto.ProductoDto;
import com.onlineshop.sale_shop_service.dto.VentaDto;
import com.onlineshop.sale_shop_service.model.Venta;
import com.onlineshop.sale_shop_service.service.IVentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private IVentaService iVenServ;

    @GetMapping("/getAll")
    public ResponseEntity<List<Venta>> getAll() {
        return ResponseEntity.ok(iVenServ.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody VentaDto ventaDto) {
        return iVenServ.save(ventaDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        try {
            iVenServ.deleteById(id);
            return ResponseEntity.ok("eliminacion completada");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("no existe not found!");
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Venta> findById(@PathVariable Long id) {

        Optional<Venta> newVenta = iVenServ.findById(id);
        return newVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody VentaDto ventaDto, @PathVariable Long id_venta) {
        return iVenServ.update(ventaDto, id_venta);
    }

    @GetMapping("/toKnowTotal/{id}")
    public ResponseEntity<Double> toKnowTotalById(@PathVariable Long id) {
        return iVenServ.toKnowTotalById(id);
    }

    @GetMapping("/getAllProdOfSale/{id_venta}")
    public ResponseEntity<List<ProductoDto>> getAllProds(@PathVariable Long id_venta) {
        return iVenServ.getAllProds(id_venta);
    }

    @GetMapping("/toKnowCarOfSale/{id_venta}")
    public ResponseEntity<CarritoShopDto> lookDetailsCar(@PathVariable Long id_venta) {
        return iVenServ.lookDetailsCar(id_venta);
    }
}
