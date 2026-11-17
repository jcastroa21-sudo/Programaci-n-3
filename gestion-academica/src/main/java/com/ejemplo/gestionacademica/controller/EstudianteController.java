package com.ejemplo.gestionacademica.controller;

import com.ejemplo.gestionacademica.model.Estudiante;
import com.ejemplo.gestionacademica.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) { this.service = service; }

    @GetMapping
    public List<Estudiante> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Estudiante buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/buscar/carrera")
    public List<Estudiante> porCarrera(@RequestParam String carrera) {
        return service.buscarPorCarrera(carrera);
    }

    @GetMapping("/buscar/nombre")
    public List<Estudiante> porNombre(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudiante crear(@RequestBody Estudiante estudiante) { return service.guardar(estudiante); }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return service.actualizar(id, estudiante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
