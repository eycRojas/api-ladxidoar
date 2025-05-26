package com.ladxidoar.api_ladxidoar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"usuario", "detalleCarrito"}) // Excluye ambos campos del toString()
@EqualsAndHashCode(exclude = {"usuario", "detalleCarrito"}) // Excluye de equals/hashCode
@NoArgsConstructor
@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<DetalleCarrito> detalleCarrito = new ArrayList<>();

}
