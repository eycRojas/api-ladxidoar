package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

@Data
public class DetalleVentaDTO {

    private Long id;

    private int cantidad;

    private Long ventaId;

    private Long productoId;

}
