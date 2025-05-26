package com.ladxidoar.api_ladxidoar.services;


import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;

import java.util.List;
import java.util.Optional;

public interface DetalleCarritoService {
    DetalleCarrito agregarProductoAlCarrito(Long carritoId, Long productoId, int cantidad);

    Optional<DetalleCarrito> findByCarritoAndProducto(Long carritoId, Long productoId);

    DetalleCarrito saveDetalleCarrito(DetalleCarrito usuario);

    DetalleCarrito updateDetalleCarrito(DetalleCarrito usuario);

    List<DetalleCarrito> getDetallesCarrito();

    Optional<DetalleCarrito> getDetalleCarritoById(Long id);

    void deleteDetalleCarrito(Long id);

    void deleteAllByCarritoId(Long carritoId);
}
