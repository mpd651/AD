package carlos.llanilloantolin.examennoviembre;

import java.util.Objects;

/**
 *
 * @author usuario
 */
class Inmueble
{

    private String nombre;
    private double precio;
    private String descripcion;

    public Inmueble()
    {
    }

    public Inmueble(String nombre, double precio, String descripcion)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Inmueble other = (Inmueble) obj;
        if (!Objects.equals(this.nombre, other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("    | %-30s | %10.02f€ | %s\n", nombre, precio, descripcion);
    }

//Getter y Setter
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public double getPrecio()
    {
        return precio;
    }
}
