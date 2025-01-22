package com.Foro.Alura.validaciones;

import com.Foro.Alura.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFueraHorarioConsulta implements ValidadorDeConsultas{

    // metodos según regla de negocio -horario
    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaColegio = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreColegio = fechaConsulta.getHour() > 18; // es con una hora de anticipacion, cierra a las 19

        if (domingo || horarioAntesDeAperturaColegio || horarioDespuesDeCierreColegio) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atención de la clinica");
        }
    }
}
