package com.Foro.Alura.profesor;

import com.Foro.Alura.alumno.Alumno;
import com.Foro.Alura.alumno.DatosRegistroAlumno;
import com.Foro.Alura.consulta.Consulta;
import com.Foro.Alura.direccion.DatosDireccion;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProfesorRepositoryTest {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deberia devolver null cuando el profesor buscado existe pero no esta disponible en esa fecha")

    void elegirProfesorAleatorioDisponibleEnLaFechaEscenario1() {
        var lunesSiguienteALas8 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var profesor = registrarProfesor("Profesor1", "profesor@gmail.com", "12345", Materia.COMUNICACION );
        var alumno = registrarAlumno("Alumno1", "alumno@gmail.com", "123789");
        registrarConsulta(profesor, alumno, lunesSiguienteALas8);
        //when o act
        var profesorLibre = profesorRepository.elegirProfesorAleatorioDisponibleEnLaFecha(Materia.COMUNICACION, lunesSiguienteALas8);
        //then o assert
        assertThat(profesorLibre).isNull();

    }

    @Test
    @DisplayName("Deberia devolver medico cuando el medico buscado esta disponible en esa fecha")
    void elegirProfesorAleatorioDisponibleEnLaFechaEscenario2() {
        //given o arrange
        var lunesSiguienteALas8 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var profesor = registrarProfesor("Profesor1", "profesor@gmail.com", "12345", Materia.COMUNICACION );
        //when o act
        var profesorLibre = profesorRepository.elegirProfesorAleatorioDisponibleEnLaFecha(Materia.COMUNICACION, lunesSiguienteALas8);
        // then o assert
        assertThat(profesorLibre).isEqualTo(profesor);
    }

    private void registrarConsulta(Profesor profesor, Alumno alumno, LocalDateTime fecha) {
        em.persist(new Consulta(null, profesor, alumno, fecha));
    }

    private Profesor registrarProfesor(String nombre, String email, String documento, Materia materia) {
        var profesor = new Profesor(datosProfesor(nombre, email, documento, materia));
        em.persist(profesor);
        return profesor;
    }

    private Alumno registrarAlumno(String nombre, String email, String documento) {
        var alumno = new Alumno(datosAlumno(nombre, email, documento));
        em.persist(alumno);
        return alumno;
    }

    private DatosRegistroProfesor datosProfesor(String nombre, String email, String documento, Materia materia) {
        return new DatosRegistroProfesor(
                nombre,
                email,
                "4545489789",
                documento,
                materia,
                datosDireccion()
        );
    }

    private DatosRegistroAlumno datosAlumno(String nombre, String email, String documento) {
        return new DatosRegistroAlumno(
                nombre,
                email,
                "1234564",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle J",
                "distrito K",
                "ciudad L",
                "123",
                "11"
        );
    }


}