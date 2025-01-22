package com.Foro.Alura.consulta;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idProfesor,
        Long idAlumno,
        LocalDateTime fecha
) {
    //creacion de este metodo y devolvemos en reservar() de ReservaDeconsultas y lo recibe ConsultaController
    public DatosDetalleConsulta(Consulta consulta) {

        this(consulta.getId(), consulta.getProfesor().getId(), consulta.getAlumno().getId(), consulta.getFecha());
    }
}
