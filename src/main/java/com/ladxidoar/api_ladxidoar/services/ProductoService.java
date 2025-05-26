package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.dtos.ProductoCarritoDTO;
import com.ladxidoar.api_ladxidoar.dtos.ProductoDTO;
import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;
import com.ladxidoar.api_ladxidoar.entities.Producto;
import com.ladxidoar.api_ladxidoar.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto saveProducto(Producto producto, MultipartFile imagen);

    Producto updateProducto(Producto producto);

    Optional<Producto> getProductoById(Long id);

    List<ProductoDTO> getProductos();

    List<DetalleCarrito> findDetallesConProductosByUsuarioId(Long usuarioId);

    List<ProductoCarritoDTO> obtenerProductosEnCarrito(Long usuarioId);
}
