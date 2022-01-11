package es.marioperez.hoja4ejer2;

import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Usuario {
    private String usuario;
    private String contrasena;
    private String nombre; 
    private String apellidos;
    private LocalDate fechaNacimiento;
    private int numvotos;

    public Usuario()
    {
    }

    public Usuario(String usuario, String contraseña, String nombre, String apellidos, LocalDate fechaNacimiento)
    {
        this.usuario = usuario;
        this.contrasena = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public String getContrasena()
    {
        return contrasena;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public LocalDate getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public void setContrasena(String contraseña)
    {
        this.contrasena = contraseña;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
}
