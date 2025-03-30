package com.ladxidoar.api_ladxidoar.dtos;

import com.ladxidoar.api_ladxidoar.entities.Venta;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagoDTO {

    private Long id;

    private String metodoPago;

    private Double monto;

    private LocalDateTime fecha;

    private String estado;

    private Long ventaId;

}