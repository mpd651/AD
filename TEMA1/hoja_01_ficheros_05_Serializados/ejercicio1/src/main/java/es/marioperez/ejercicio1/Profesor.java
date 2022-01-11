package es.marioperez.ejercicio1;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Profesor implements Serializable {
    private int numeroRegistro;
    private String nombre;
    private LocalDate fechaIngreso;
    private Instituto instituto;

    public Profesor(int numeroRegistro, String nombre, LocalDate fechaIngreso, Instituto instituto)
    {
        this.numeroRegistro = numeroRegistro;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.instituto = instituto;
    }

    public int getNumeroRegistro()
    {
        return numeroRegistro;
    }

    public String getNombre()
    {
        return nombre;
    }

    public LocalDate getFechaIngreso()
    {
        return fechaIngreso;
    }

    public Instituto getInstituto()
    {
        return instituto;
    }

    @Override
    public String toString()
    {
        return "Profesor{" + "numeroRegistro=" + numeroRegistro + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso + '}';
    }

}
