package com.ladxidoar.api_ladxidoar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"detalleCarrito"}) // Excluye ambos campos del toString()
@EqualsAndHashCode(exclude = {"detalleCarrito"}) // Excluye de equals/hashCode
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    private Double precio;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String urlImagen;

    @NotNull
    private int cantidad;

    private String autor;


    private String categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleCarrito> detalleCarrito = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVenta = new ArrayList<>();

}
