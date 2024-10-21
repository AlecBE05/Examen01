package com.codigo.examen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "direccion")
@Getter
@Setter
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String avenida;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false, length = 200)
    private String distrito;

    @Column(nullable = false, length = 200)
    private String provincia;

    @Column(nullable = false)
    private int num_departamento;

}
