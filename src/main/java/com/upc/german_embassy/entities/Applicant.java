package com.upc.german_embassy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Postulante")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nameApplicant",nullable = false)
    private String name;
    @Column(name = "dniApplicant",length = 8,nullable = false, unique = true) //como hacer para que se ingrese como minimo y maximo 8 caracteres del DNI
    @Size(min = 8, max = 8)
    private String dni;
    @Column(name = "ageApplicant", nullable = false)
    private Integer age;
    @Column(name = "statusApplicant", nullable = false)
    private String status;
    @Column(name="salaryApplicant", nullable = false)
    private double salary;
    @Column(name ="bagApplicant", nullable = false)
    private boolean bag;
    private transient double qualification;
    //formula para aprobar calificacion
    //calificación = (edad * factor estado civil) + (sueldo neto / 1000.00) + (factor bolsa de viaje)
}
