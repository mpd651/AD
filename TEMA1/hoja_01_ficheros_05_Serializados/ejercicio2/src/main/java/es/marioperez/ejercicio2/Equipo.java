package es.marioperez.ejercicio2;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Equipo implements Serializable{
    private String nombre;
    private int numAlumnos;
    private float puntos;
    private Alumno [] alumnos;

    public Equipo(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getNumAlumnos()
    {
        return numAlumnos;
    }

    public float getPuntos()
    {
        return puntos;
    }

    public Alumno[] getAlumnos()
    {
        return alumnos;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setNumAlumnos(int numAlumnos)
    {
        this.numAlumnos = numAlumnos;
    }

    public void setPuntos(float puntos)
    {
        this.puntos = puntos;
    }

    public void setAlumnos(Alumno[] alumnos)
    {
        this.alumnos = alumnos;
    }

    @Override
    public String toString()
    {
        return "Equipo{" + "nombre=" + nombre + ", numAlumnos=" + numAlumnos + ", puntos=" + puntos +'}';
    }
    
    
}
