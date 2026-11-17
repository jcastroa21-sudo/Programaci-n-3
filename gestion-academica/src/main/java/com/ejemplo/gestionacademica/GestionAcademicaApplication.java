package com.ejemplo.gestionacademica;

import com.ejemplo.gestionacademica.model.Asignatura;
import com.ejemplo.gestionacademica.model.Estudiante;
import com.ejemplo.gestionacademica.repository.AsignaturaRepository;
import com.ejemplo.gestionacademica.repository.EstudianteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionAcademicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionAcademicaApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            EstudianteRepository estudianteRepository,
            AsignaturaRepository asignaturaRepository) {

        return args -> {

            // ==========================================
            // CREAR ASIGNATURAS
            // ==========================================

            Asignatura programacion = new Asignatura(
                    null,
                    "Programación",
                    4,
                    "Ingeniería de Sistemas"
            );

            Asignatura basesDatos = new Asignatura(
                    null,
                    "Bases de Datos",
                    3,
                    "Ingeniería de Sistemas"
            );

            Asignatura matematica = new Asignatura(
                    null,
                    "Matemáticas",
                    4,
                    "Ciencias Básicas"
            );

            // Guardar asignaturas
            asignaturaRepository.save(programacion);
            asignaturaRepository.save(basesDatos);
            asignaturaRepository.save(matematica);

            // ==========================================
            // CREAR ESTUDIANTES
            // ==========================================

            Estudiante jesus = new Estudiante(
                    null,
                    "2026001",
                    "Jesus David Castro",
                    "Ingeniería de Sistemas"
            );

            Estudiante juan = new Estudiante(
                    null,
                    "2026002",
                    "Juan Carlos",
                    "Ingeniería de Sistemas"
            );

            // ==========================================
            // RELACIÓN MANY-TO-MANY
            // ==========================================

            // Jesús está matriculado en:
            // Programación, Bases de Datos y Matemáticas

            jesus.getAsignaturas().add(programacion);
            jesus.getAsignaturas().add(basesDatos);
            jesus.getAsignaturas().add(matematica);

            // Juan está matriculado en:
            // Programación y Bases de Datos

            juan.getAsignaturas().add(programacion);
            juan.getAsignaturas().add(basesDatos);

            // ==========================================
            // GUARDAR ESTUDIANTES
            // ==========================================

            estudianteRepository.save(jesus);
            estudianteRepository.save(juan);

            // ==========================================
            // MOSTRAR INFORMACIÓN EN CONSOLA
            // ==========================================

            System.out.println("==========================================");
            System.out.println("DATOS GUARDADOS Y CONSULTADOS");
            System.out.println("==========================================");

            estudianteRepository.findAll().forEach(estudiante -> {

                System.out.println(
                        "Estudiante: " + estudiante.getNombreCompleto()
                );

                System.out.println(
                        "Código: " + estudiante.getCodigoMatricula()
                );

                System.out.println(
                        "Carrera: " + estudiante.getCarrera()
                );

                System.out.println("Asignaturas:");

                estudiante.getAsignaturas().forEach(asignatura -> {
                    System.out.println(
                            " - " + asignatura.getNombre()
                            + " | Créditos: " + asignatura.getCreditos()
                    );
                });

                System.out.println("------------------------------------------");
            });

            System.out.println("==========================================");
            System.out.println("RELACIÓN @ManyToMany FUNCIONANDO");
            System.out.println("==========================================");
        };
    }
}