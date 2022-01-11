
import dao.UsuarioDAOJDBC;
import java.sql.SQLException;
import java.time.LocalDate;
import modelo.Usuario;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        UsuarioDAOJDBC usuario=new UsuarioDAOJDBC();
        
        LocalDate fecha=LocalDate.now();
        
        Usuario entity=new Usuario("user1", "111a", "mario", "perez", fecha);
        usuario.insertar(entity);
    }

}
