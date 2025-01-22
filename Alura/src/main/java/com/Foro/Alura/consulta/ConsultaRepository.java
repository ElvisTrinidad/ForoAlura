package com.Foro.Alura.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // metodo creado en la clase ValidadorAlumnoSinOtraConsultaEnElmismoDia
    boolean existsByAlumnoIdAndFechaBetween(@NotNull Long idAlumno, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    //metodo creado en la clase ValidadorProfesorConOtraConsultaEnElMismoHorario
    boolean existsByProfesorIdAndFecha(Long idProfesor, @NotNull @Future LocalDateTime fecha);
}
