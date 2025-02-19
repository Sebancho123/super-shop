package com.onlineshop.products_shop_service.controller;

import com.onlineshop.products_shop_service.model.Producto;
import com.onlineshop.products_shop_service.service.IProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController{

    @Autowired
    private IProductoService iProdSer;


    @GetMapping("/getAll")
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(iProdSer.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Producto producto) {

        try {
            Producto newProduc = iProdSer.save(producto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .replacePath("/product/findById/{codigo}")
                    .buildAndExpand(newProduc.getCodigo())
                    .toUri();
            return ResponseEntity.created(location).body(newProduc);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("quiza te falta un campo");
        }
    }

    @DeleteMapping("/deleteById/{codigo}")
    public ResponseEntity<String> deleteById(@PathVariable Long codigo) {

        try{
            iProdSer.deleteById(codigo);
            return ResponseEntity.ok("eliminado correctamente");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/findById/{codigo}")
    public ResponseEntity<Producto> findById(Long codigo) {

        Optional<Producto> prodEncont = iProdSer.findById(codigo);
        return prodEncont.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/getProductsByCods/{codigos}")
    public List<Producto> getProducsByIds(@PathVariable List<Long> codigos) {
        return iProdSer.getProducsByIds(codigos);
    }
}
