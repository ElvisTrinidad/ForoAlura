package com.Foro.Alura.alumno;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Page<Alumno> findAllByActivoTrue(Pageable paginacion);

    // metodo creado en la clase ValidadorAlumnoActivo
    @Query("""
        select a.activo
        from Alumno a
        where
        a.id = :idAlumno
        """)
    boolean findActivoById(@NotNull Long idAlumno);
}
