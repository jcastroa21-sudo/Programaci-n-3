package com.ejemplo.gestionacademica.repository;

import com.ejemplo.gestionacademica.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    // 1. Búsqueda exacta por edificio
    List<Aula> findByEdificio(String edificio);

    // 2. Búsqueda parcial por número de aula, ignorando mayúsculas/minúsculas
    List<Aula> findByNumeroAulaContainingIgnoreCase(String palabra);

    // 3. Búsqueda por rango (Capacidad >= X)
    List<Aula> findByCapacidadGreaterThanEqual(Integer capacidad);

    // 4. Búsqueda con lógica Y (Edificio Y Capacidad)
    List<Aula> findByEdificioAndCapacidad(String edificio, Integer capacidad);

    // 5. Búsqueda por comienzo del número de aula
    List<Aula> findByNumeroAulaStartingWith(String prefijo);

    // 6. Ordenamiento automático (Buscar por edificio y ordenar por capacidad descendente)
    List<Aula> findByEdificioOrderByCapacidadDesc(String edificio);

    // 7. Contar registros por edificio
    long countByEdificio(String edificio);
}
