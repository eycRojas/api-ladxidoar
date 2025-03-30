package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

@Data
public class DetalleCarritoDTO {

    private Long id;

    private int cantidad;

    private Long carritoId;

    private Long productoId;

}
