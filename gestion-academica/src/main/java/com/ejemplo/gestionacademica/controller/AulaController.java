package com.ejemplo.gestionacademica.controller;

import com.ejemplo.gestionacademica.model.Aula;
import com.ejemplo.gestionacademica.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaService service;

    public AulaController(AulaService service) { this.service = service; }

    @GetMapping
    public List<Aula> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Aula buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/buscar/edificio")
    public List<Aula> porEdificio(@RequestParam String edificio) {
        return service.buscarPorEdificio(edificio);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aula crear(@RequestBody Aula aula) { return service.guardar(aula); }

    @PutMapping("/{id}")
    public Aula actualizar(@PathVariable Long id, @RequestBody Aula aula) {
        return service.actualizar(id, aula);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
