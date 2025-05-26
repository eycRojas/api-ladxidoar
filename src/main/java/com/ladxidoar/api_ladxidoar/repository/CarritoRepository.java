package com.ladxidoar.api_ladxidoar.repository;

import com.ladxidoar.api_ladxidoar.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository <Carrito, Long> {
    @Query("SELECT c FROM Carrito c " +
            "WHERE c.usuario.id = :usuarioId")
    Optional<Carrito> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Query("DELETE FROM DetalleCarrito dc WHERE dc.carrito.id = :carritoId")
    @Transactional
    void vaciarCarrito(@Param("carritoId") Long carritoId);
}
