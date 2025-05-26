package com.ladxidoar.api_ladxidoar.controllers;

import com.ladxidoar.api_ladxidoar.dtos.CarritoDTO;
import com.ladxidoar.api_ladxidoar.dtos.ProductoCarritoDTO;
import com.ladxidoar.api_ladxidoar.dtos.ProductoDTO;
import com.ladxidoar.api_ladxidoar.entities.Carrito;
import com.ladxidoar.api_ladxidoar.entities.Producto;
import com.ladxidoar.api_ladxidoar.services.LugarService;
import com.ladxidoar.api_ladxidoar.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestPart("producto") Producto producto, @RequestPart("imagen") MultipartFile imagen) {
        try {
            Producto savedProducto = productoService.saveProducto(producto, imagen);
            return new ResponseEntity<>(savedProducto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
        try {
            Producto savedProdcuto = productoService.updateProducto(producto);
            return new ResponseEntity<>(savedProdcuto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {

        List<ProductoDTO> productos = productoService.getProductos();
        //List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(usuarioService::convertToDTO).toList();

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {

        Optional<Producto> producto = productoService.getProductoById(id);
        if (producto.isPresent()) {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setId(producto.get().getId());
            productoDTO.setNombre(producto.get().getNombre());
            productoDTO.setDescripcion(producto.get().getDescripcion());
            productoDTO.setPrecio(producto.get().getPrecio());
            productoDTO.setNombre(producto.get().getNombre());
            productoDTO.setUrlImagen(producto.get().getUrlImagen());
            productoDTO.setCantidad(producto.get().getCantidad());
            return new ResponseEntity<>(productoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ProductoCarritoDTO>> getAllProductosCarrito(@PathVariable Long usuarioId) {
        List<ProductoCarritoDTO> productos = productoService.obtenerProductosEnCarrito(usuarioId);

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

}
