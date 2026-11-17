package com.ejemplo.gestionacademica.controller;

import com.ejemplo.gestionacademica.model.Asignatura;
import com.ejemplo.gestionacademica.service.AsignaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService service;

    public AsignaturaController(AsignaturaService service) { this.service = service; }

    @GetMapping
    public List<Asignatura> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Asignatura buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/buscar/departamento")
    public List<Asignatura> porDepartamento(@RequestParam String departamento) {
        return service.buscarPorDepartamento(departamento);
    }

    @GetMapping("/buscar/nombre")
    public List<Asignatura> porNombre(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Asignatura crear(@RequestBody Asignatura asignatura) { return service.guardar(asignatura); }

    @PutMapping("/{id}")
    public Asignatura actualizar(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        return service.actualizar(id, asignatura);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
