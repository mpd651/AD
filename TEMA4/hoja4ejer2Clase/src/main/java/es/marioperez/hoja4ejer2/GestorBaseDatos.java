package es.marioperez.hoja4ejer2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class GestorBaseDatos
{
    private Connection conexion = Conexion.getInstance().getConnection();

    public GestorBaseDatos() throws SQLException, ClassNotFoundException
    {
    }

    public List<Grupo> listaGrupos() throws SQLException
    {
        List<Grupo> lista = new ArrayList();
        String sql = "select * from grupos order by codgrupo;";
        Statement st = conexion.createStatement();
        ResultSet result = st.executeQuery(sql);

        while (result.next())
        {
            Grupo grupo = new Grupo();
            grupo.setCodgrupo(result.getInt("codgrupo"));
            grupo.setNombre(result.getString("nombre"));
            grupo.setLocalidad(result.getString("localidad"));
            grupo.setEstilo(result.getString("estilo"));
            grupo.setAnnograb(result.getInt("annograb"));
            grupo.setCompania(result.getString("compania"));
            grupo.setEsgrupo(result.getBoolean("esgrupo"));
            grupo.setFechaestreno(result.getDate("fechaestreno").toLocalDate());

            String sqlCanciones = "select * from canciones where grupo = ?";
            try{
                PreparedStatement consultaPreparada=conexion.prepareStatement(sqlCanciones);
                consultaPreparada.setInt(1, result.getInt("codgrupo"));
                ResultSet resultCanciones=consultaPreparada.executeQuery();
                List<Cancion> canciones=new ArrayList();
                while (resultCanciones.next()){
                    Cancion cancion=new Cancion();
                    cancion.setDuracion(resultCanciones.getTime("duracion").toLocalTime());
                    cancion.setGrupo(grupo);
                    cancion.setNumcancion(resultCanciones.getInt("numcancion"));
                    cancion.setTitulo(resultCanciones.getString("titulo"));
                    cancion.setTotalvotos(resultCanciones.getInt("total_votos"));
                    canciones.add(cancion);
                }
                grupo.setCanciones(canciones);
            } catch (SQLException ex)
            {
                System.err.println("Error");
            }
            lista.add(grupo);
        }
        return lista;
    }

    public List<Cancion> listaCanciones()
    {
        List<Cancion> lista=new ArrayList();
        String sql="select g.nombre, c.titulo from grupos g inner join canciones c ON g.codgrupo =c.grupo order by g.nombre;";
        return null;
        
    }
    
    public List<Cancion> opcion5(int num)
    {
        String sql="select * from canciones c order by total_votos desc limit ?";
        List<Cancion> canciones=null;
        try{
                PreparedStatement consultaPreparada=conexion.prepareStatement(sql);
                consultaPreparada.setInt(1, num);
                ResultSet resultCanciones=consultaPreparada.executeQuery();
                canciones=new ArrayList();
                while (resultCanciones.next()){
                    Cancion cancion=new Cancion();
                    cancion.setDuracion(resultCanciones.getTime("duracion").toLocalTime());
                    
                    String sql2="select nombre from grupos where codgrupo= "+resultCanciones.getInt("grupo");
                    ResultSet result=conexion.createStatement().executeQuery(sql2);
                    Grupo grupo=new Grupo();
                    result.next();
                    grupo.setNombre(result.getString("nombre"));
                    cancion.setGrupo(grupo);
                    
                    
                    cancion.setNumcancion(resultCanciones.getInt("numcancion"));
                    cancion.setTitulo(resultCanciones.getString("titulo"));
                    cancion.setTotalvotos(resultCanciones.getInt("total_votos"));
                    canciones.add(cancion);
                }
                
            } catch (SQLException ex)
            {
                System.err.println("Error");
            }
        return canciones;
    }    
    
    public List<Votos> votos() throws SQLException{
        String sql="select c.titulo, g.nombre, v.fecha from votos v inner join canciones c on v.cancion = c.numcancion inner join grupos g on c.grupo =g.codgrupo order by fecha desc limit 5";
        ResultSet result=conexion.createStatement().executeQuery(sql);
        List<Votos> votos=new ArrayList();
        while (result.next()){
            Votos voto=new Votos();
            voto.setFecha(result.getDate("fecha").toLocalDate());
            
            Cancion cancion=new Cancion();
            cancion.setTitulo(result.getString("titulo"));
            
            
            Grupo grupo = new Grupo();
            grupo.setNombre(result.getString("nombre"));
            
            cancion.setGrupo(grupo);
            voto.setCancion(cancion);
            votos.add(voto);
        }
        return votos;
    }
    
    public void borrarCanciones(String grupo){
        String sqlBorrarVotos="delete from votos using canciones, grupos where votos.cancion=canciones.id and canciones.grupo=grupos.id and grupos.nombre=?"
    }
    
    
    
    

}
