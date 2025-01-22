package com.Foro.Alura.validaciones;

import com.Foro.Alura.profesor.ProfesorRepository;
import com.Foro.Alura.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProfesorActivo implements ValidadorDeConsultas{

    @Autowired
    private ProfesorRepository repository;

    public void validar(DatosReservaConsulta datos) {
        //eleccion del profesor es opcional, segun reglas
        if (datos.idProfesor() == null) {
            return;
        }
        var profesorEstaActivo = repository.findActivoById(datos.idProfesor());
        //metodo findactivoById creado en profesorRepository
        if (!profesorEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con profesor excluido");

        }

    }
}
