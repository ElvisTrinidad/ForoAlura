package com.Foro.Alura.profesor;

import com.Foro.Alura.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProfesor(@NotNull Long id,
                                      String nombre,
                                      String documento,
                                      DatosDireccion direccion) {

}
