package es.marioperez.examenmariofebrero;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Fabrica {
    private int id;
    private String nombre;
    private Propietario propietario;
    private List<Pedido> pedidos;

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Propietario getPropietario()
    {
        return propietario;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPropietario(Propietario propietario)
    {
        this.propietario = propietario;
    }

    public List<Pedido> getPedidos()
    {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }
    
    
    
    
}
