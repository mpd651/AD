package es.marioperez.ejercicio1;

import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Instituto {
    private String nombre;
    private LocalDate fechaConstruccion;
    private String localidad;

    public Instituto(String nombre, LocalDate fechaConstruccion, String localidad)
    {
        this.nombre = nombre;
        this.fechaConstruccion = fechaConstruccion;
        this.localidad = localidad;
    }

    public String getNombre()
    {
        return nombre;
    }

    public LocalDate getFechaConstruccion()
    {
        return fechaConstruccion;
    }

    public String getLocalidad()
    {
        return localidad;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setFechaConstruccion(LocalDate fechaConstruccion)
    {
        this.fechaConstruccion = fechaConstruccion;
    }

    public void setLocalidad(String localidad)
    {
        this.localidad = localidad;
    }
    
    
}
