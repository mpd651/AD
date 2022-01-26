package es.marioperez.ejercicio1;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Profesor {
    public int id;
    public String nombre;
    public LocalDate fecha_nacimiento;
    public List<Curso> cursos;

    public List<Curso> getCursos()
    {
        return cursos;
    }

    public void setCursos(List<Curso> cursos)
    {
        this.cursos = cursos;
    }
    
    

    public Profesor()
    {
    }

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public LocalDate getFecha_nacimiento()
    {
        return fecha_nacimiento;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString()
    {
        return "Profesor{" + "id=" + id + ", nombre=" + nombre + ", fecha_nacimiento=" + fecha_nacimiento + ", cursos=" + cursos + '}';
    }
    
    
}
