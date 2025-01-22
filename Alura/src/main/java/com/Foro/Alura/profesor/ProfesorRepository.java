package com.Foro.Alura.profesor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    //este metodo no testeamos por que Jpa lo testea internamente
    Page<Profesor> findByActivoTrue(Pageable paginacion);


    //este metodo vamos a testear por que tiene un @query un poco complejo
    @Query("""
       select p from Profesor p
       where
       p.activo = TRUE
       and
       p.materia = :materia
       and p.id not in(
       select c.profesor.id from Consulta c
       where
       c.fecha = :fecha
       )
       order by rand()
       limit 1
       """)
    Profesor elegirProfesorAleatorioDisponibleEnLaFecha(Materia materia,
                                                        @NotNull @Future LocalDateTime fecha);

    //este metodo no testearemos por que su @Query es sencillo
    @Query("""
        select p.activo
        from Profesor p
        where
        p.id = :idProfesor
        """)
    boolean findActivoById(Long idProfesor);
}

