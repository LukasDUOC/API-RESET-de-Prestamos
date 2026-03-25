package com.example.Prestamos.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity

public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //columnas tabla
    private String codigoLibro;
    private String rutUsuario;
    private int diaPrestamo;
    private LocalDate fechaInicio;


    
}
