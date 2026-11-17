# Gestión Académica — Spring Boot, JPA, H2 y REST

Proyecto Maven para abrir en NetBeans. Incluye entidades `Estudiante`, `Asignatura` y `Aula`; Spring Data JPA; H2; consultas derivadas; capas Service y RestController; y endpoints CRUD para probar con Postman.

## Ejecutar
1. Abrir la carpeta del proyecto en NetBeans como proyecto Maven.
2. Ejecutar `GestionAcademicaApplication.java`.
3. La aplicación usa el puerto `8081`.
4. La base de datos H2 está en memoria: se reinicia al detener la aplicación.
5. Consola H2: `http://localhost:8081/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuario: `sa`
   - Contraseña: (vacía)

## Endpoints

### Estudiantes
- `GET http://localhost:8081/api/estudiantes`
- `GET http://localhost:8081/api/estudiantes/1`
- `GET http://localhost:8081/api/estudiantes/buscar/carrera?carrera=Ingeniería%20de%20Sistemas`
- `GET http://localhost:8081/api/estudiantes/buscar/nombre?nombre=Jesus`
- `POST http://localhost:8081/api/estudiantes`
- `PUT http://localhost:8081/api/estudiantes/1`
- `DELETE http://localhost:8081/api/estudiantes/1`

### Asignaturas
- `GET http://localhost:8081/api/asignaturas`
- `GET http://localhost:8081/api/asignaturas/1`
- `GET http://localhost:8081/api/asignaturas/buscar/departamento?departamento=Ingeniería%20de%20Sistemas`
- `GET http://localhost:8081/api/asignaturas/buscar/nombre?nombre=datos`
- `POST http://localhost:8081/api/asignaturas`
- `PUT http://localhost:8081/api/asignaturas/1`
- `DELETE http://localhost:8081/api/asignaturas/1`

### Aulas
- `GET http://localhost:8081/api/aulas`
- `GET http://localhost:8081/api/aulas/1`
- `GET http://localhost:8081/api/aulas/buscar/edificio?edificio=Bloque%20A`
- `POST http://localhost:8081/api/aulas`
- `PUT http://localhost:8081/api/aulas/1`
- `DELETE http://localhost:8081/api/aulas/1`

## Probar con Postman

Para peticiones `POST` y `PUT`, elegir `Body > raw > JSON` y usar `Content-Type: application/json`.

### Crear una asignatura
`POST /api/asignaturas`
```json
{
  "nombre": "Redes",
  "creditos": 3,
  "departamento": "Ingeniería de Sistemas"
}
```

### Crear un estudiante con asignaturas existentes
Primero consulta `GET /api/asignaturas` y toma los `id` reales. Después usa `POST /api/estudiantes`:
```json
{
  "codigoMatricula": "2026003",
  "nombreCompleto": "Ana Pérez",
  "carrera": "Ingeniería de Sistemas",
  "asignaturas": [
    { "id": 1 },
    { "id": 2 }
  ]
}
```
Cambia los IDs por los que realmente aparezcan en la respuesta. La API valida que las asignaturas existan.

### Crear un aula
La entidad Aula usa un ID asignado manualmente:
`POST /api/aulas`
```json
{
  "id": 1,
  "edificio": "Bloque A",
  "numeroAula": "101",
  "capacidad": 30
}
```

## Notas
- Los datos iniciales se cargan desde `CommandLineRunner` en la clase principal, y se conservan las consultas derivadas de los repositories.
- La relación entre estudiantes y asignaturas se devuelve sin ciclos JSON.
- No se usa Lombok; las clases conservan constructores, getters y setters explícitos.
