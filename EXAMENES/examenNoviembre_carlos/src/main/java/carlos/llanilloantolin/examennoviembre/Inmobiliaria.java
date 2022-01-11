package carlos.llanilloantolin.examennoviembre;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author usuario
 */
class Inmobiliaria
{
    private int identificador;
    private String nombre;
    private int numeroEmpleados;
    private Set<Inmueble> inmuebles;

    public Inmobiliaria()
    {
        inmuebles = new HashSet();
    }

    public Inmobiliaria(int identificador, String nombre, int numeroEmpleados)
    {
        this.identificador = identificador;
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;

        inmuebles = new HashSet();
    }

    public boolean añadirInmueble(Inmueble inmueble)
    {
        return inmuebles.add(inmueble);
    }

    public double getMediaPrecios()
    {
        if (inmuebles.isEmpty())
            return 0;
        double suma = inmuebles
                .stream()
                .map(i -> i.getPrecio())
                .reduce(0.0, Double::sum);
        return suma /= this.getInmuebles().size();
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 73 * hash + this.identificador;
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
        final Inmobiliaria other = (Inmobiliaria) obj;
        if (this.identificador != other.identificador)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        String inmuebles = this.inmuebles.stream().map(i -> i.toString()).collect(Collectors.joining());
        return String.format("%3d | %-30s | %d empleados\n" + inmuebles, identificador, nombre, numeroEmpleados);
    }

//Getter y Setter
    public int getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getNumeroEmpleados()
    {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados)
    {
        this.numeroEmpleados = numeroEmpleados;
    }

    public Set<Inmueble> getInmuebles()
    {
        return inmuebles;
    }
}
