package es.marioperez.hoja4ejer2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Grupo {
    private int codgrupo;
    private String nombre;
    private String localidad;
    private String estilo;
    private boolean esgrupo;
    private int annograb;
    private LocalDate fechaestreno;
    private String compania;
    private List<Cancion> canciones;
    private List<Componentes> componentes;

    public Grupo()
    {
    }

    public boolean isEsgrupo()
    {
        return esgrupo;
    }

    public int getAnnograb()
    {
        return annograb;
    }

    public LocalDate getFechaestreno()
    {
        return fechaestreno;
    }

    public String getCompania()
    {
        return compania;
    }

    public List<Cancion> getCanciones()
    {
        return canciones;
    }

    public List<Componentes> getComponentes()
    {
        return componentes;
    }

    public void setEsgrupo(boolean esgrupo)
    {
        this.esgrupo = esgrupo;
    }

    public void setAnnograb(int annograb)
    {
        this.annograb = annograb;
    }

    public void setFechaestreno(LocalDate fechaestreno)
    {
        this.fechaestreno = fechaestreno;
    }

    public void setCompania(String compania)
    {
        this.compania = compania;
    }

    public void setCanciones(List<Cancion> canciones)
    {
        this.canciones = canciones;
    }

    public void setComponentes(List<Componentes> componentes)
    {
        this.componentes = componentes;
    }
    
    
    

    public Grupo(int codgrupo, String nombre, String localidad, String estilo)
    {
        this.codgrupo = codgrupo;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
    }

    public int getCodgrupo()
    {
        return codgrupo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getLocalidad()
    {
        return localidad;
    }

    public String getEstilo()
    {
        return estilo;
    }

    public void setCodgrupo(int codgrupo)
    {
        this.codgrupo = codgrupo;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setLocalidad(String localidad)
    {
        this.localidad = localidad;
    }

    public void setEstilo(String estilo)
    {
        this.estilo = estilo;
    }

    @Override
    public String toString()
    {
        return "Grupo{" + "codgrupo=" + codgrupo + ", nombre=" + nombre + ", localidad=" + localidad + ", estilo=" + estilo + '}';
    }
    
    

}
