package es.marioperez.ejercicio1;

import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Jugador {
    private int id;
    private String nombre;
    private String apellidos;
    private String alias;
    private String puesto;
    private int altura;
    private LocalDate fechaNacimiento;
    private int codigoEquipo;

    public Jugador(int id, String nombre, String apellidos, String alias, String puesto, int altura, LocalDate fechaNacimiento, int codigoEquipo)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.alias = alias;
        this.puesto = puesto;
        this.altura = altura;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoEquipo = codigoEquipo;
    }

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public String getAlias()
    {
        return alias;
    }

    public String getPuesto()
    {
        return puesto;
    }

    public int getAltura()
    {
        return altura;
    }

    public LocalDate getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public int getCodigoEquipo()
    {
        return codigoEquipo;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public void setPuesto(String puesto)
    {
        this.puesto = puesto;
    }

    public void setAltura(int altura)
    {
        this.altura = altura;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCodigoEquipo(int codigoEquipo)
    {
        this.codigoEquipo = codigoEquipo;
    }

    @Override
    public String toString()
    {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", alias=" + alias + ", puesto=" + puesto + ", altura=" + altura + ", fechaNacimiento=" + fechaNacimiento + ", codigoEquipo=" + codigoEquipo + '}';
    }
    
    public String toCSV(){
        return id+","+nombre+","+apellidos+","+alias+","+puesto+","+altura+","+fechaNacimiento+","+codigoEquipo;
    }
    
    
}
