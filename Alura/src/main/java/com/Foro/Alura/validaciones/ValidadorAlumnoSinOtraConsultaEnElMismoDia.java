package com.Foro.Alura.validaciones;

import com.Foro.Alura.consulta.ConsultaRepository;
import com.Foro.Alura.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlumnoSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {

        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);   //metodo en ConsultaRepository
        var alumnoTieneOtraConsultaEnElDia = repository.existsByAlumnoIdAndFechaBetween(datos.idAlumno(), primerHorario, ultimoHorario);

        if (alumnoTieneOtraConsultaEnElDia) {
            throw new ValidacionException("Alumno ya tiene una clases para ese día");
        }
    }

}
