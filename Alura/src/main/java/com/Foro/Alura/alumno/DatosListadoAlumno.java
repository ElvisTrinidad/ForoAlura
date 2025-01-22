package com.Foro.Alura.alumno;

public record DatosListadoAlumno(Long id,
                                 String nombre,
                                 String email,
                                 String documento) {

    public DatosListadoAlumno(Alumno alumno) {
        this(alumno.getId(), alumno.getNombre(),
                alumno.getEmail(), alumno.getDocumento());
    }
}
