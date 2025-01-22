package com.Foro.Alura.controller;


import com.Foro.Alura.alumno.AlumnoRepository;
import com.Foro.Alura.alumno.DatosActualizarAlumno;
import com.Foro.Alura.alumno.DatosListadoAlumno;
import com.Foro.Alura.alumno.DatosRegistroAlumno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alumnos")
@SecurityRequirement(name = "bearer-key")
public class AlumnoController {

    @Autowired
    private AlumnoRepository repository;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosRegistroAlumno datos) {
        repository.save(new Alumno(datos));
    }

    @GetMapping
    public Page<DatosListadoAlumno> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosListadoAlumno::new);
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizarAlumno datos) {
        var paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformaciones(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.eliminar();
    }
}
