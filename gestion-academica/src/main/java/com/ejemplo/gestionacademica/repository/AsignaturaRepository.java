package com.ejemplo.gestionacademica.repository;

import com.ejemplo.gestionacademica.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    // 1. Búsqueda exacta por departamento
    List<Asignatura> findByDepartamento(String departamento);

    // 2. Búsqueda parcial por nombre, ignorando mayúsculas/minúsculas
    List<Asignatura> findByNombreContainingIgnoreCase(String palabra);

    // 3. Búsqueda por rango (Créditos >= X)
    List<Asignatura> findByCreditosGreaterThanEqual(Integer creditos);

    // 4. Búsqueda con lógica Y (Departamento Y Créditos)
    List<Asignatura> findByDepartamentoAndCreditos(String departamento, Integer creditos);

    // 5. Búsqueda por comienzo del nombre
    List<Asignatura> findByNombreStartingWith(String prefijo);

    // 6. Ordenamiento automático (Buscar por departamento y ordenar por créditos descendente)
    List<Asignatura> findByDepartamentoOrderByCreditosDesc(String departamento);

    // 7. Contar registros por departamento
    long countByDepartamento(String departamento);
}
