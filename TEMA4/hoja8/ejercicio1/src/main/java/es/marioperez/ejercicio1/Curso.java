package es.marioperez.ejercicio1;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Curso {
    public int id;
    public String nombre;
    public Profesor tutor;
    public List<Alumno> alumnos;

    @Override
    public String toString()
    {
        return "Curso{" + "id=" + id + ", nombre=" + nombre + ", tutor=" + tutor + '}';
    }

    public Curso()
    {
    }

    public List<Alumno> getAlumnos()
    {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos)
    {
        this.alumnos = alumnos;
    }
    

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Profesor getTutor()
    {
        return tutor;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setTutor(Profesor tutor)
    {
        this.tutor = tutor;
    }
    
    
}
