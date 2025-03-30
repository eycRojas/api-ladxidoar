package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarritoDTO {

    private Long id;

    private Long usuarioId;

    private List<DetalleCarritoDTO> detalleCarritoDTO = new ArrayList<>();

}