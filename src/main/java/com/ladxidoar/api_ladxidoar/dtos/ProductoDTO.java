package com.ladxidoar.api_ladxidoar.dtos;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private Double precio;

    private String urlImagen;

    private int cantidad;

    private String autor;

    private String categoria;

}
