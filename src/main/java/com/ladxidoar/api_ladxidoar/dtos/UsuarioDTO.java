package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioDTO {

    private Long id;

    private String nombres;

   private String apellidos;

    private String email;

    private String contrasenia;

    private CarritoDTO carritoDTO;

    private DireccionDTO direccionDTO;

    private List<VentaDTO> pedidosRealizadosDTO = new ArrayList<>();


}
