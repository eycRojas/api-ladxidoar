package com.ladxidoar.api_ladxidoar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank
    private String municipio;

    @NotBlank
    @Column(length = 1)
    private String categoria;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String urlLugar;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String urlImagen;

}
