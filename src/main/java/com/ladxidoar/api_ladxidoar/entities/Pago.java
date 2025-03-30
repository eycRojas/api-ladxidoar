package com.ladxidoar.api_ladxidoar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String metodoPago;

    @NotBlank
    private Double monto;

    @NotBlank
    private LocalDateTime fecha;

    @NotBlank
    private String estado;

    @OneToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

}
