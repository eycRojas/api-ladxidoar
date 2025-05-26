package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.entities.Carrito;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    Carrito saveCarrito(Carrito carrito);

    List<Carrito> getCarritos();

    Optional<Carrito> getCarritoByUsuarioId(Long id);

    @Transactional
    void vaciarCarrito(Long carritoId);
}
