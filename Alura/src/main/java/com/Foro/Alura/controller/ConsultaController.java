package com.Foro.Alura.controller;


import com.Foro.Alura.consulta.DatosReservaConsulta;
import com.Foro.Alura.consulta.ReservaDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        //System.out.println(datos);
        //reserva.reservar(datos);
        var detalleConsulta = reserva.reservar(datos);
        // return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null)); // devolvemos esto del record DatosDetalleConsulta
        return ResponseEntity.ok(detalleConsulta);

    }
}
