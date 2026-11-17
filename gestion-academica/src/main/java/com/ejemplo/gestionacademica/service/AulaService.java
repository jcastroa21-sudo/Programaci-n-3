package com.ejemplo.gestionacademica.service;

import com.ejemplo.gestionacademica.model.Aula;
import com.ejemplo.gestionacademica.repository.AulaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AulaService {

    private final AulaRepository repository;

    public AulaService(AulaRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<Aula> listar() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Aula buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el aula con id " + id));
    }

    @Transactional
    public Aula guardar(Aula aula) {
        if (aula.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El aula requiere un id porque la entidad no genera uno automáticamente.");
        }
        if (repository.existsById(aula.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un aula con id " + aula.getId());
        }
        return repository.save(aula);
    }

    @Transactional
    public Aula actualizar(Long id, Aula datos) {
        Aula existente = buscarPorId(id);
        existente.setEdificio(datos.getEdificio());
        existente.setNumeroAula(datos.getNumeroAula());
        existente.setCapacidad(datos.getCapacidad());
        return repository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) { repository.delete(buscarPorId(id)); }

    @Transactional(readOnly = true)
    public List<Aula> buscarPorEdificio(String edificio) { return repository.findByEdificio(edificio); }
}
