package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

@Data
public class DireccionDTO {

    private Long id;

    private String calle;

    private String numeroCalle;

    private String colonia;

    private String referencia;

    private String ciudad;

    private String cp;

    private String estado;

    private String pais;

    private int usuarioID;

}
