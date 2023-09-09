package com.upc.german_embassy.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;
@Data
public class ApplicantDTO {
    private Long id;
    private String name;
    private String dni;
    private Integer age;
    private String status;
    private double salary;
    private boolean bag;
    private transient double qualification;
}
