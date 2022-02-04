package es.marioperez.examenmariofebrero;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Articulo {
    private String codigo;
    private String nombre;
    private float precio;
    private List<Pedido> pedidos;

    public String getCodigo()
    {
        return codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public float getPrecio()
    {
        return precio;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public List<Pedido> getPedidos()
    {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }

    @Override
    public String toString()
    {
        return "Articulo{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
    
    
    
    
    
    
}
