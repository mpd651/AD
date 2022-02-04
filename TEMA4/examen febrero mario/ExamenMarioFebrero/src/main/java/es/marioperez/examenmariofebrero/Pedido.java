package es.marioperez.examenmariofebrero;

import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Pedido {
    private Fabrica fabrica;
    private Articulo articulo;
    private LocalDate fecha;
    private int unidades;
    private boolean urgente;
    private boolean servido;

    public Fabrica getFabrica()
    {
        return fabrica;
    }

    public Articulo getArticulo()
    {
        return articulo;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public int getUnidades()
    {
        return unidades;
    }

    public boolean isUrgente()
    {
        return urgente;
    }

    public boolean isServido()
    {
        return servido;
    }

    public void setFabrica(Fabrica fabrica)
    {
        this.fabrica = fabrica;
    }

    public void setArticulo(Articulo articulo)
    {
        this.articulo = articulo;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public void setUnidades(int unidades)
    {
        this.unidades = unidades;
    }

    public void setUrgente(boolean urgente)
    {
        this.urgente = urgente;
    }

    public void setServido(boolean servido)
    {
        this.servido = servido;
    }
    
    
}
