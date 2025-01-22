package com.Foro.Alura.consulta;

import com.Foro.Alura.profesor.ProfesorRepository;
import com.Foro.Alura.profesor.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    //este metodo guarda la consulta dentro de la bd , por eso llamamos a repository
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired     // interfaz de las validaciones
    private List<ValidadorDeConsultas> validadores;


    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {

        if (!alumnoRepository.existsById(datos.idAlumno())) {
            throw new ValidacionException("No existe un alumno con ese id"); //creamos ValidacionException
        }
        if (datos.idProfesor() != null && !profesorRepository.existsById(datos.idProfesor())) {
            throw new ValidacionException("No existe un profesor con el id informado"); //creamos ValidacionException
        }

        validadores.forEach(v -> v.validar(datos));
        // var medico = medicoRepository.findById(datos.idMedico()).get();  // nos pide Optional , poniendo el get se soluciona
        var medico = elegirMedico(datos); // ccreamos metodo mas abajo
        if (medico == null) {
            throw new ValidacionException("No existe un profesor disponible en ese horario");
        }

        var paciente = alumnoRepository.findById(datos.idAlumno()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
        //nos devulve
        return new DatosDetalleConsulta(consulta);
    }

    //crearemos
    private Profesor elegirProfesor(DatosReservaConsulta datos) {
        if (datos.idProfesor() != null) {
            return profesorRepository.getReferenceById(datos.idProfesor());  //getreference.. = a findById + .get()
        }
        if (datos.materia() == null) {
            throw new ValidacionException("Es necesario elegir una materia cuando no se elige un profesor");
        }

        return profesorRepository.elegirProfesorAleatorioDisponibleEnLaFecha(datos.materia(), datos.fecha());

    }
}
