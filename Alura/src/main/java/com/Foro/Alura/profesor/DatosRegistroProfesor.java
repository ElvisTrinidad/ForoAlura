package com.Foro.Alura.profesor;

import com.Foro.Alura.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroProfesor(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp="\\d{4,8}")
        String documento,
        @NotNull
        Materia materia,
        @NotNull   // es un objeto, tiene q ser nulo
        @Valid
        DatosDireccion direccion
) {

}
