package com.ejemplo.gestionacademica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aula {

    @Id
    @Column(name = "id_aula")
    private Long id;

    @Column(name = "edificio", nullable = false)
    private String edificio;

    @Column(name = "numero_aula", nullable = false)
    private String numeroAula;

    @Column(name = "capacidad")
    private Integer capacidad;

    public Aula() {
    }

    public Aula(Long id, String edificio, String numeroAula, Integer capacidad) {
        this.id = id;
        this.edificio = edificio;
        this.numeroAula = numeroAula;
        this.capacidad = capacidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }

    public String getNumeroAula() { return numeroAula; }
    public void setNumeroAula(String numeroAula) { this.numeroAula = numeroAula; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
}
