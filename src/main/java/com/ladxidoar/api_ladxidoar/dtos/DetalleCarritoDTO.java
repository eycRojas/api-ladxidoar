package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

@Data
public class DetalleCarritoDTO {

    private Long id;

    private int cantidad;

    private Long carritoId;

    private Long productoId;

    public DetalleCarritoDTO(Long id, int cantidad, Long carritoId, Long productoId) {
        this.id = id;
        this.cantidad = cantidad;
        this.carritoId = carritoId;
        this.productoId = productoId;
    }
}
