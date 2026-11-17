package com.ejemplo.gestionacademica.repository;

import com.ejemplo.gestionacademica.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // 1. Búsqueda exacta por carrera
    List<Estudiante> findByCarrera(String carrera);

    // 2. Búsqueda parcial por nombre, ignorando mayúsculas/minúsculas
    List<Estudiante> findByNombreCompletoContainingIgnoreCase(String palabra);

    // 3. Búsqueda por rango (ID >= X)
    List<Estudiante> findByIdGreaterThanEqual(Long id);

    // 4. Búsqueda con lógica Y (Carrera Y Código de matrícula)
    List<Estudiante> findByCarreraAndCodigoMatricula(String carrera, String codigoMatricula);

    // 5. Búsqueda por comienzo del nombre
    List<Estudiante> findByNombreCompletoStartingWith(String prefijo);

    // 6. Ordenamiento automático (Buscar por carrera y ordenar por nombre descendente)
    List<Estudiante> findByCarreraOrderByNombreCompletoDesc(String carrera);

    // 7. Contar registros por carrera
    long countByCarrera(String carrera);
}
