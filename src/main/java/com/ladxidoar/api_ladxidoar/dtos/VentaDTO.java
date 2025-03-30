package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class VentaDTO {

    private Long id;

    private LocalDateTime fechaVenta;

    private Long compradorId;

    private List<DetalleVentaDTO> detalleVentaDTO = new ArrayList<>();

    private Long pagoId;

}
