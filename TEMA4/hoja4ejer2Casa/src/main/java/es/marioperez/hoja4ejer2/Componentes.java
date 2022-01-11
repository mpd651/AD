package es.marioperez.hoja4ejer2;

/**
 *
 * @author usuario
 */
public class Componentes {
    public int idcomp;
    public String nombre;
    public String apellido;
    public String alias;
    public String funcion;
    public Grupo grupo;

    public int getIdcomp()
    {
        return idcomp;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public String getAlias()
    {
        return alias;
    }

    public String getFuncion()
    {
        return funcion;
    }

    public Grupo getGrupo()
    {
        return grupo;
    }

    public void setIdcomp(int idcomp)
    {
        this.idcomp = idcomp;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public void setFuncion(String funcion)
    {
        this.funcion = funcion;
    }

    public void setGrupo(Grupo grupo)
    {
        this.grupo = grupo;
    }
    
    
}
