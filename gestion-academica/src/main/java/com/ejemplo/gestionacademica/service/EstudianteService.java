package com.ejemplo.gestionacademica.service;

import com.ejemplo.gestionacademica.model.Asignatura;
import com.ejemplo.gestionacademica.model.Estudiante;
import com.ejemplo.gestionacademica.repository.AsignaturaRepository;
import com.ejemplo.gestionacademica.repository.EstudianteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final AsignaturaRepository asignaturaRepository;

    public EstudianteService(EstudianteRepository estudianteRepository,
                             AsignaturaRepository asignaturaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    @Transactional(readOnly = true)
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el estudiante con id " + id));
    }

    @Transactional
    public Estudiante guardar(Estudiante estudiante) {
        estudiante.setId(null);
        estudiante.setAsignaturas(resolverAsignaturas(estudiante.getAsignaturas()));
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public Estudiante actualizar(Long id, Estudiante datos) {
        Estudiante existente = buscarPorId(id);
        existente.setCodigoMatricula(datos.getCodigoMatricula());
        existente.setNombreCompleto(datos.getNombreCompleto());
        existente.setCarrera(datos.getCarrera());
        existente.setAsignaturas(resolverAsignaturas(datos.getAsignaturas()));
        return estudianteRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Estudiante estudiante = buscarPorId(id);
        estudianteRepository.delete(estudiante);
    }

    @Transactional(readOnly = true)
    public List<Estudiante> buscarPorCarrera(String carrera) {
        return estudianteRepository.findByCarrera(carrera);
    }

    @Transactional(readOnly = true)
    public List<Estudiante> buscarPorNombre(String nombre) {
        return estudianteRepository.findByNombreCompletoContainingIgnoreCase(nombre);
    }

    private Set<Asignatura> resolverAsignaturas(Set<Asignatura> enviadas) {
        Set<Asignatura> resueltas = new HashSet<>();
        if (enviadas == null) return resueltas;

        for (Asignatura asignatura : enviadas) {
            if (asignatura.getId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Cada asignatura debe incluir un id existente. Consulta primero GET /api/asignaturas.");
            }
            Asignatura existente = asignaturaRepository.findById(asignatura.getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "No existe la asignatura con id " + asignatura.getId()));
            resueltas.add(existente);
        }
        return resueltas;
    }
}
