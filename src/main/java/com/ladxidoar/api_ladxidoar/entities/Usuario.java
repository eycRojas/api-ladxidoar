package com.ladxidoar.api_ladxidoar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"carrito"})
@NoArgsConstructor
@Entity
public class Usuario {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String email;

    @NotBlank
    private String contrasenia;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carrito carrito;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Direccion direccion;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    private List<Venta> pedidosRealizados = new ArrayList<>();

}
