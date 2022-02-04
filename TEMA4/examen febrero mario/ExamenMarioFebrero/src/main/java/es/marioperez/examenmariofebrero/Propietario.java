package es.marioperez.examenmariofebrero;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Propietario {
    private int id;
    private String nombre;
    private String pais;
    private List<Fabrica> fabricas;

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getPais()
    {
        return pais;
    }

    public List<Fabrica> getFabricas()
    {
        return fabricas;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public void setFabricas(List<Fabrica> fabricas)
    {
        this.fabricas = fabricas;
    }

    @Override
    public String toString()
    {
        return "Propietario{" + "id=" + id + ", nombre=" + nombre + ", pais=" + pais + '}';
    }
    
    
    
}
