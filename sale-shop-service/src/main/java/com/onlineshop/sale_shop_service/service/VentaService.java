package com.onlineshop.sale_shop_service.service;

import com.onlineshop.sale_shop_service.dto.CarritoShopDto;
import com.onlineshop.sale_shop_service.dto.ProductoDto;
import com.onlineshop.sale_shop_service.dto.VentaDto;
import com.onlineshop.sale_shop_service.model.Venta;
import com.onlineshop.sale_shop_service.repository.ICarAPi;
import com.onlineshop.sale_shop_service.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository iVenRepo;

    @Autowired
    private ICarAPi iCarAPi;

    @Override
    public List<Venta> getAll() {
        return iVenRepo.findAll();
    }

    @Override
    public ResponseEntity<Object> save(VentaDto ventaDto) {

        //llamar al servicio de car_shop_service y traer el car por medio del id
        Optional<CarritoShopDto>carritoShop = Optional.ofNullable(iCarAPi.findCarById(ventaDto.getId_car()));

        //creamos nuesta venta normal y asignmos la fecha y el carro en cuestion y listo sncillo
        if(carritoShop.isPresent()) {
            System.out.println("ENTRAMOS?");
            CarritoShopDto newCarShopDto = carritoShop.get();
            Venta venta = new Venta();
            venta.setFecha(ventaDto.getFecha());
            venta.setId_car(newCarShopDto.getId());
            return ResponseEntity.ok(iVenRepo.save(venta));
        }else {
            return ResponseEntity.badRequest().body("el carrito que quieres guardar no existe");
        }
    }

    @Override
    public void deleteById(Long id) {
        iVenRepo.deleteById(id);
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return iVenRepo.findById(id);
    }

    @Override
    public ResponseEntity<Object> update(VentaDto ventaDto, Long id_venta) {

        Optional<Venta> ventaAEdit = this.findById(id_venta);

        if (ventaAEdit.isPresent()) {
            Venta venta = ventaAEdit.get();

            if (!venta.getId().equals(id_venta)) {
                return ResponseEntity.badRequest().body("No coinciden los ids!");
            }

            CarritoShopDto carritoShop = iCarAPi.findCarById(ventaDto.getId_car());
            venta.setId_car(carritoShop.getId());
            venta.setFecha(ventaDto.getFecha());
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Double> toKnowTotalById(Long id) {
        Optional<Venta> venta = this.findById(id);


        if (venta.isPresent()) {
            Venta newVenta = venta.get();
            CarritoShopDto carritoShopDto = iCarAPi.findCarById(newVenta.getId_car());
            return ResponseEntity.ok(carritoShopDto.getTotalMoneyProduts());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<ProductoDto>> getAllProds(Long id_venta) {

        Optional<Venta> venta = this.findById(id_venta);

        if (venta.isPresent()) {
            Venta newVenta = venta.get();
            CarritoShopDto carritoShopDto = iCarAPi.findCarById(newVenta.getId_car());
            return ResponseEntity.ok(iCarAPi.getAllInfoProds(carritoShopDto.getId()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<CarritoShopDto> lookDetailsCar(Long id_venta) {

        Optional<Venta> venta = this.findById(id_venta);

        if (venta.isPresent()) {
            Venta newVenta = venta.get();
            return ResponseEntity.ok(iCarAPi.findCarById(newVenta.getId_car()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
