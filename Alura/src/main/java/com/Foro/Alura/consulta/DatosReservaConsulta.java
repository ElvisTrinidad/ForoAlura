package com.Foro.Alura.consulta;

import com.Foro.Alura.profesor.Materia;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idProfesor,
        @NotNull
        Long idAlumno,
        @NotNull
        @Future    // para que sea una fecha adelante de la actual
        LocalDateTime fecha,
        Materia materia
) {
}
