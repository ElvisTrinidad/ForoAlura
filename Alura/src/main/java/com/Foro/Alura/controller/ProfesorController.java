package com.Foro.Alura.controller;

import com.Foro.Alura.direccion.DatosDireccion;
import com.Foro.Alura.profesor.DatosRegistroProfesor;
import com.Foro.Alura.profesor.ProfesorRepository;

import com.Foro.Alura.profesor.DatosActualizarProfesor;
import com.Foro.Alura.profesor.DatosListadoProfesor;
import com.Foro.Alura.profesor.Profesor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/profesores")
@SecurityRequirement(name = "bearer-key")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaProfesor> registrarProfesor(@RequestBody DatosRegistroProfesor datosRegistroProfesor,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
//   System.out.println("llega correctamente");
//   System.out.println(parametro);
        Profesor profesor = profesorRepository.save(new Profesor(datosRegistroProfesor));
        DatosRespuestaProfesor datosRespuestaMedico = new DatosRespuestaProfesor(profesor.getId(), profesor.getNombre(), profesor.getEmail(),
                profesor.getTelefono(), profesor.getMateria().toString(),
                new DatosDireccion(profesor.getDireccion().getCalle(), profesor.getDireccion().getDistrito(),
                        profesor.getDireccion().getCiudad(), profesor.getDireccion().getNumero(),
                        profesor.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoProfesor>> listadoProfesores(@PageableDefault(size=2) Pageable paginacion){
        //return profesorRepository.findAll().stream().map(DatosListadoProfesor::new).toList();  //findAll es metodo del Jpa
        return ResponseEntity.ok(profesorRepository.findByActivoTrue(paginacion).map(DatosListadoProfesor::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarProfesor(@RequestBody @Valid DatosActualizarProfesor datosActualizarProfesor){
        Profesor profesor = profesorRepository.getReferenceById(datosActualizarProfesor.id());//id desde ActualizarProfesor
         profesor.actualizarDatos(datosActualizarProfesor);
        return ResponseEntity.ok(new DatosRespuestaProfesor(profesor.getId(), profesor.getNombre(), profesor.getEmail(),
                profesor.getTelefono(), profesor.getMateria().toString(),
                new DatosDireccion(profesor.getDireccion().getCalle(), profesor.getDireccion().getDistrito(),
                        profesor.getDireccion().getCiudad(), profesor.getDireccion().getNumero(),
                        profesor.getDireccion().getComplemento())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarProfesor(@PathVariable Long id){
        Profesor profesor = profesorRepository.getReferenceById(id);
        profesor.desactivarProfesor();  //elimina la entidad
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaProfesor> retornaDatosMedico(@PathVariable Long id) {
        Profesor medico = profesorRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaProfesor(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getMateria().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);
    }
    }


