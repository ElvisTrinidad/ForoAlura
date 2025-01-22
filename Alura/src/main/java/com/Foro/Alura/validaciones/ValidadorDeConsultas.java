package com.Foro.Alura.validaciones;

import com.Foro.Alura.consulta.DatosReservaConsulta;

public interface ValidadorDeConsultas {
    //interfaz ya es public, por eso no es necesario ponerlo
    void validar(DatosReservaConsulta datos);
}
