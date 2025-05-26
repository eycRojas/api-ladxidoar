package com.ladxidoar.api_ladxidoar.repository;

import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
    Optional<DetalleCarrito> findByCarritoIdAndProductoId(Long carritoId, Long productoId);

    void deleteAllByCarritoId(Long carritoId);
}
