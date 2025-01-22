package com.Foro.Alura.validaciones;

import com.Foro.Alura.consulta.ConsultaRepository;
import com.Foro.Alura.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProfesorConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {

        var profesorTieneOtraConsultaEnelMismoHorario = repository.existsByProfesorIdAndFecha(datos.idProfesor(), datos.fecha());

        if (profesorTieneOtraConsultaEnelMismoHorario) {
            throw new ValidacionException("Profesor ya tiene otra clase en esa misma hora y fecha");
        }
    }
}
