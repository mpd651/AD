package dao;

import es.marioperez.hoja4ejer1.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author usuario
 */
public class UsuarioDAOJDBC implements UsuarioDAO{
    private final Connection conexion;

    public UsuarioDAOJDBC() throws SQLException, ClassNotFoundException
    {
        this.conexion = Conexion.getInstance().getConnection();
    }
    
    
    
    @Override
    public List<Usuario> getUsuariosMayoresEdad()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(Usuario entity)
    {
        boolean insertado=false;
        String sql = "INSERT INTO usuarios (\"user\", contraseña, nombre, apellidos, fechanac) VALUES (?, md5(?), ?, ?, ?);";

        try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
        {
            // Sustituye la ? primera por el contenido de nombre
            
            consultaPreparada.setString(1, entity.getUsuario());
            // Sustituye la ? segunda por el contenido de localidad
            consultaPreparada.setString(2, entity.getContraseña());
            consultaPreparada.setString(3, entity.getNombre());
            consultaPreparada.setString(4, entity.getApellidos());
            consultaPreparada.setObject(5, entity.getFechaNacimiento());

            int numeroInsertados = consultaPreparada.executeUpdate();
            if (numeroInsertados==1){
                insertado=true;
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar un grupo");
        }

        return insertado;
    }

    @Override
    public void actualizar(Usuario entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Usuario entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscar(String key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getTodos()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
