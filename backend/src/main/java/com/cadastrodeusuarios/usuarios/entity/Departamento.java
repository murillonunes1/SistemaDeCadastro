package com.cadastrodeusuarios.usuarios.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Departamento {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String departamento;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setId(Long id) {
           this.id = id;
    }

    public Long getId() {
        return id;
    }

}
