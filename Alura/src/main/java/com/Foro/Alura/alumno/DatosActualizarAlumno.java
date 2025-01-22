package com.Foro.Alura.alumno;

import com.Foro.Alura.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarAlumno(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
