package com.ejemplo.gestionacademica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer creditos;
    private String departamento;

    @JsonIgnore
    @ManyToMany(mappedBy = "asignaturas")
    private Set<Estudiante> estudiantes = new HashSet<>();

    public Asignatura() {}

    public Asignatura(Long id, String nombre, Integer creditos, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.departamento = departamento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getCreditos() { return creditos; }
    public void setCreditos(Integer creditos) { this.creditos = creditos; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public Set<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Set<Estudiante> estudiantes) { this.estudiantes = estudiantes; }
}
