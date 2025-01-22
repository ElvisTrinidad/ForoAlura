package com.Foro.Alura.profesor;

import com.Foro.Alura.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="profesores")
@Entity(name="Profesor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Materia materia;
    private Boolean activo;

    @Embedded
    private Direccion direccion;



    public Profesor(DatosRegistroProfesor datos) {
        this.activo = true;  //profesor creado por defecto esta activo
        this.nombre = datos.nombre(); // Accediendo al método del record
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.materia = datos.materia();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarDatos(DatosActualizarProfesor datosActualizarProfesor) {
        if (datosActualizarProfesor.nombre() != null) {
            this.nombre = datosActualizarProfesor.nombre();
        }
        if (datosActualizarProfesor.documento() != null) {
            this.documento = datosActualizarProfesor.documento();
        }
        if (datosActualizarProfesor.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarProfesor.direccion());
        }

    }

    public void desactivarProfesor(){
        this.activo=false;
    }
}
