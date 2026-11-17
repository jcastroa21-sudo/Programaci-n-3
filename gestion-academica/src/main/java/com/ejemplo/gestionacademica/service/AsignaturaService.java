package com.ejemplo.gestionacademica.service;

import com.ejemplo.gestionacademica.model.Asignatura;
import com.ejemplo.gestionacademica.repository.AsignaturaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AsignaturaService {

    private final AsignaturaRepository repository;

    public AsignaturaService(AsignaturaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Asignatura> listar() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Asignatura buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la asignatura con id " + id));
    }

    @Transactional
    public Asignatura guardar(Asignatura asignatura) {
        asignatura.setId(null);
        return repository.save(asignatura);
    }

    @Transactional
    public Asignatura actualizar(Long id, Asignatura datos) {
        Asignatura existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setCreditos(datos.getCreditos());
        existente.setDepartamento(datos.getDepartamento());
        return repository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) { repository.delete(buscarPorId(id)); }

    @Transactional(readOnly = true)
    public List<Asignatura> buscarPorDepartamento(String departamento) {
        return repository.findByDepartamento(departamento);
    }

    @Transactional(readOnly = true)
    public List<Asignatura> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }
}
