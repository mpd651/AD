package es.marioperez.hoja4ejer2;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author usuario
 */
public class Cancion {
    private int numcancion;
    private Grupo grupo;
    private LocalTime duracion;
    private String titulo;
    private int totalvotos;

    public Cancion()
    {
    }
    
    

    public Cancion(int numcancion, Grupo grupo, LocalTime duracion, String titulo, int totalvotos)
    {
        this.numcancion = numcancion;
        this.grupo = grupo;
        this.duracion = duracion;
        this.titulo = titulo;
        this.totalvotos = totalvotos;
    }

    public int getNumcancion()
    {
        return numcancion;
    }

    public Grupo getGrupo()
    {
        return grupo;
    }

    public LocalTime getDuracion()
    {
        return duracion;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public int getTotalvotos()
    {
        return totalvotos;
    }

    public void setNumcancion(int numcancion)
    {
        this.numcancion = numcancion;
    }

    public void setGrupo(Grupo grupo)
    {
        this.grupo = grupo;
    }

    public void setDuracion(LocalTime duracion)
    {
        this.duracion = duracion;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public void setTotalvotos(int totalvotos)
    {
        this.totalvotos = totalvotos;
    }

    @Override
    public String toString()
    {
        return "Cancion{" + "numcancion=" + numcancion + ", grupo=" + grupo + ", duracion=" + duracion + ", titulo=" + titulo + ", totalvotos=" + totalvotos + '}';
    }
    
    
}
