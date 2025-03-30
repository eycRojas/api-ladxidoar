package com.ladxidoar.api_ladxidoar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String calle;

    @NotBlank
    private String numeroCalle;

    @NotBlank
    private String colonia;

    @NotBlank
    private String referencia;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String cp;

    @NotBlank
    private String estado;

    @NotBlank
    private String pais;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
