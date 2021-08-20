package com.semana2.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class Producto{


    public Producto() {
    }

    private String id;

    private String nombre;

    private String description;

    private Double precio;

    private String imagen;

}