package com.Foro.Alura.profesor;

public record DatosListadoProfesor(Long id,
                                   String nombre,
                                   String materia,
                                   String documento,
                                   String email) {

    public DatosListadoProfesor(Profesor profesor) {
        this(profesor.getId(), profesor.getNombre(),
                profesor.getMateria().toString(),
                profesor.getDocumento(), profesor.getEmail());
    }
}

