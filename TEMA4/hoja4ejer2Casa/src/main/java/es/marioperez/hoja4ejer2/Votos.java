package es.marioperez.hoja4ejer2;

import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Votos {
    public Usuario usuario;
    public LocalDate fecha;
    public Cancion cancion;

    public Usuario getUsuario()
    {
        return usuario;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public Cancion getCancion()
    {
        return cancion;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public void setCancion(Cancion cancion)
    {
        this.cancion = cancion;
    }
    
    
}
