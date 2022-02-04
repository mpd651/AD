package es.marioperez.examenmariofebrero;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorBd {
    public Connection con;

    public GestorBd()
    {
        con=Conexion.getInstance().getConnection();
    }
    
    public int modificarUrgenciaArticulo(String nombre){
        int contador=0;
        try
        {
            String sql="SELECT p.* FROM articulos a inner join pedidos p on a.codigo=p.codigo_articulo where a.nombre=? and p.urgente =1 and p.servido =1";
            PreparedStatement st=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, nombre);
            ResultSet result=st.executeQuery();
            
            
            while (result.next()){
                result.updateInt("urgente", 0);
                result.updateRow();
                contador++;
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contador;
    }
    
    public boolean eliminarPedidoAntiguo(){
        
        boolean eliminado=true;
        try
        {
            con.setAutoCommit(false);
            String sqlObtenerPedidoMasAntiguo="SELECT * FROM pedidos p inner join articulos a on p.codigo_articulo =a.codigo where p.servido = 0 order by p.fecha asc limit 1";
            Statement st=con.createStatement();
            ResultSet resultObtenerPedido=st.executeQuery(sqlObtenerPedidoMasAntiguo);
            
            resultObtenerPedido.next();
            float precio=resultObtenerPedido.getFloat("precio");
            String codigo=resultObtenerPedido.getString("codigo");
            int idFabrica=resultObtenerPedido.getInt("id_fabrica");
            LocalDate fecha=resultObtenerPedido.getDate("fecha").toLocalDate();
            precio=(float) (precio*0.10);
            
            String sqlModificarPrecio="UPDATE articulos set precio = ? where codigo=?";
            PreparedStatement preparest=con.prepareStatement(sqlModificarPrecio);
            preparest.setFloat(1, precio);
            preparest.setString(2, codigo);
            preparest.executeUpdate();
            
            String sqlEliminarPedido="DELETE FROM pedidos WHERE id_fabrica =? AND codigo_articulo =? AND fecha =?";
            PreparedStatement prepareStBorrar=con.prepareStatement(sqlEliminarPedido);
            prepareStBorrar.setInt(1, idFabrica);
            prepareStBorrar.setString(2, codigo);
            prepareStBorrar.setDate(3, Date.valueOf(fecha));
            prepareStBorrar.executeUpdate();
            
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException ex)
        {
            try
            {
                // Algo ha fallado en la ejecución del script, se anula la transacción
                eliminado=false;
                con.setAutoCommit(true);
                con.rollback();
            }
            catch (SQLException ex1)
            {
                System.err.println("Error al hacer el rollback");
                eliminado=false;
            }
            System.err.println("Error al ejecutar el script " + ex.getMessage());
            eliminado=false;
            
        }
        return eliminado;
    }
    
    public Propietario menosUnidades(){
        Propietario p=new Propietario();
        try
        {
            String sql="select p.id, p.nombre, p.pais, SUM(p2.unidades)as unidadesNoServidas from propietarios p inner join fabricas f on p.id =f.id_propietario INNER JOIN pedidos p2 on p2.id_fabrica =f.id where servido=0 group by p.id order by unidadesNoServidas desc limit 1 ";
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery(sql);
            result.next();
            
            p.setId(result.getInt("id"));
            p.setNombre(result.getString("nombre"));
            p.setPais(result.getString("pais"));
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public List<Articulo> articulosSinPedidos(){
        List<Articulo> articulos=new ArrayList();
        try
        {
            String sql="SELECT * FROM articulos a where codigo not in (select codigo from articulos a2 inner join pedidos p on a2.codigo=p.codigo_articulo)";
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery(sql);
            
            while (result.next()){
                Articulo a=new Articulo();
                a.setCodigo(result.getString("codigo"));
                a.setNombre(result.getString("nombre"));
                a.setPrecio(result.getFloat("precio"));
                articulos.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articulos;
        
    }
    
    public List<Fabrica> listaFabricaPorPais(String pais){
        List<Fabrica> fabricas=new ArrayList();
        
        try{
            String sql="SELECT * FROM propietarios p inner join fabricas f on p.id = f.id_propietario where p.pais = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, pais);
            ResultSet result=st.executeQuery();
            
            while (result.next()){
                Propietario p=new Propietario();
                Fabrica f=new Fabrica();
                
                p.setId(result.getInt("propietarios.id"));
                p.setNombre(result.getString("propietarios.nombre"));
                p.setPais(result.getString("pais"));
                
                f.setId(result.getInt("fabricas.id"));
                f.setNombre(result.getString("fabricas.nombre"));
                f.setPropietario(p);
                
                fabricas.add(f);
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricas;
    }
    
    public Map<String, Integer> get5ArticulosMasCantidadPedida(){
        Map<String, Integer> map=new HashMap();
        try
        {
            String sql="SELECT a.nombre, sum(unidades) as unidadesPedidas from articulos a inner join pedidos p on a.codigo =p.codigo_articulo group by codigo order by unidadesPedidas desc limit 5";
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery(sql);
            while (result.next()){
                String nombre=result.getString("nombre");
                int unidades=result.getInt("unidadesPedidas");
                map.put(nombre, unidades);
            }
            
            
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }
    
    
}
