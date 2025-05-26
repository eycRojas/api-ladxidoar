package com.ladxidoar.api_ladxidoar.repository;

import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;
import com.ladxidoar.api_ladxidoar.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, Long> {
    @Query("SELECT dc FROM DetalleCarrito dc " +
            "JOIN FETCH dc.producto " +
            "WHERE dc.carrito.usuario.id = :usuarioId")
    List<DetalleCarrito> findDetallesConProductosByUsuarioId(@Param("usuarioId") Long usuarioId);
}
