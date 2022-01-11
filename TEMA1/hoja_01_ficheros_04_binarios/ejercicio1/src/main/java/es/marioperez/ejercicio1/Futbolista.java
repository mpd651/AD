package es.marioperez.ejercicio1;

/**
 *
 * @author usuario
 */

public class Futbolista
{
    private int identificador;
    private String alias;
    private String codigo;
    private Puesto puesto;
    private float altura;

    public Futbolista(int identificador, String alias, String codigo, Puesto puesto, float altura)
    {
        this.identificador = identificador;
        this.alias = alias;
        this.codigo = codigo;
        this.puesto = puesto;
        this.altura = altura;
    }

    public int getIdentificador()
    {
        return identificador;
    }

    public String getAlias()
    {
        return alias;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public float getAltura()
    {
        return altura;
    }

    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public void setPuesto(Puesto puesto)
    {
        this.puesto = puesto;
    }

    public void setAltura(float altura)
    {
        this.altura = altura;
    }
    
    

    
    
    @Override
    public String toString()
    {
        String linea = String.format("%ed %-25s %3s %-16s %4.2f", identificador, alias, codigo, puesto, altura);
        return linea;
    }


        
}


