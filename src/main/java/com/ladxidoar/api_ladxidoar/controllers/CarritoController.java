package com.ladxidoar.api_ladxidoar.controllers;

import com.ladxidoar.api_ladxidoar.dtos.CarritoDTO;
import com.ladxidoar.api_ladxidoar.dtos.DetalleCarritoDTO;
import com.ladxidoar.api_ladxidoar.dtos.ProductoDTO;
import com.ladxidoar.api_ladxidoar.entities.Carrito;
import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;
import com.ladxidoar.api_ladxidoar.entities.Lugar;
import com.ladxidoar.api_ladxidoar.entities.Usuario;
import com.ladxidoar.api_ladxidoar.services.CarritoService;
import com.ladxidoar.api_ladxidoar.services.DetalleCarritoService;
import com.ladxidoar.api_ladxidoar.services.LugarService;
import com.ladxidoar.api_ladxidoar.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @Autowired
    DetalleCarritoService detalleCarritoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<CarritoDTO> saveCarrito(@PathVariable Long usuarioId) {
        try {

            Usuario usuario = usuarioService.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
            Carrito carrito = new Carrito();
            carrito.setUsuario(usuario);
            usuario.setCarrito(carrito);
            Carrito carritoGuardado = carritoService.saveCarrito(carrito);

            CarritoDTO carritoDTO = new CarritoDTO();
            carritoDTO.setId(carritoGuardado.getId());
            carritoDTO.setUsuarioId(carritoGuardado.getUsuario().getId());

            return new ResponseEntity<>(carritoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{carritoId}/{productoId}/{cantidad}")
    public ResponseEntity<DetalleCarritoDTO> agregarProducto(
            @PathVariable Long carritoId, @PathVariable Long productoId, @PathVariable int cantidad) {
        try {
            DetalleCarrito detalle = detalleCarritoService.agregarProductoAlCarrito(carritoId, productoId, cantidad);
            DetalleCarritoDTO detalleDto = new DetalleCarritoDTO(
            detalle.getId(),
            detalle.getCantidad(),
            detalle.getCarrito().getId(),
            detalle.getProducto().getId()
            );
            return new ResponseEntity<>(detalleDto, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> getCarritoByUsuarioId(@PathVariable Long usuarioId) {
        Optional<Carrito> carrito = carritoService.getCarritoByUsuarioId(usuarioId);
        if (carrito.isPresent()) {
            System.out.println(carrito.get());
            CarritoDTO carritoDTO = new CarritoDTO();
            carritoDTO.setId(carrito.get().getId());
            carritoDTO.setUsuarioId(carrito.get().getUsuario().getId());

            return new ResponseEntity<>(carritoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{carritoId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long carritoId) {
        try {
            carritoService.vaciarCarrito(carritoId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/detalle/{detalleId}")
    public ResponseEntity<Void> quitarProducto(@PathVariable Long detalleId) {
        Optional<DetalleCarrito> detalle = detalleCarritoService.getDetalleCarritoById(detalleId);

        if (detalle.isPresent()) {
            detalleCarritoService.deleteDetalleCarrito(detalle.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
