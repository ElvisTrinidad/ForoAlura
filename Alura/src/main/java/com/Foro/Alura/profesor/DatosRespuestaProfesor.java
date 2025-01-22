package com.Foro.Alura.profesor;

import com.Foro.Alura.direccion.DatosDireccion;

public record DatosRespuestaProfesor(Long id,
                                     String nombre,
                                     String email,
                                     String telefono,
                                     String documento,
                                     DatosDireccion direccion) {
}
