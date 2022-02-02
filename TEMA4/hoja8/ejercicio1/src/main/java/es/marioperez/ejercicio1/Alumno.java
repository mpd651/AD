package es.marioperez.ejercicio1;

/**
 *
 * @author usuario
 */
public class Alumno {
    public int id;
    public String nombre;
    public float nota_media;
    public Curso curso;

    public Alumno()
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

    public float getNota_media()
    {
        return nota_media;
    }

    public Curso getCurso()
    {
        return curso;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setNota_media(float nota_media)
    {
        this.nota_media = nota_media;
    }

    public void setCurso(Curso curso)
    {
        this.curso = curso;
    }

    @Override
    public String toString()
    {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", nota_media=" + nota_media + ", curso=" + curso + '}';
    }
    
    
}
