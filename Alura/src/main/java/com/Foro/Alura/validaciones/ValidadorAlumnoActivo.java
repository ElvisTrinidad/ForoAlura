package com.Foro.Alura.validaciones;
import med.voll.api.domain.ValidacionException;

import com.Foro.Alura.alumno.AlumnoRepository;
import com.Foro.Alura.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlumnoActivo implements ValidadorDeConsultas{

    @Autowired
    private AlumnoRepository repository;

    public void validar(DatosReservaConsulta datos) {
        var alumnoEstaActivo = repository.findActivoById(datos.idAlumno());
        //metodo findActivoById creado en PacienteRepository
        if (!alumnoEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con alumno excluido");
        }
    }
}
