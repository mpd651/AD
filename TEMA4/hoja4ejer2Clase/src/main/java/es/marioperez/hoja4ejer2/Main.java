package es.marioperez.hoja4ejer2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        GestorBaseDatos gestor=new GestorBaseDatos();
        Scanner tecladoi = new Scanner(System.in);
        Scanner teclados=new Scanner(System.in);
        int opcion = 0;

        do
        {
            System.out.println("1.- Listado de grupos\n"
                    + "2.- Listado de canciones\n"
                    + "3.- Número de canciones por grupo\n"
                    + "4.- Canciones de un grupo\n"
                    + "5.- Las 5 canciones más votadas\n"
                    + "6.- Grupos sin canciones\n"
                    + "7.- Los últimos 5 votos\n"
                    + "8.- Eliminar canciones de un grupo\n"
                    + "9.- Modificar datos de grupo\n"
                    + "0.- Salir");
            System.out.println("Introduce una opcion:");
            opcion=tecladoi.nextInt();
            
            switch (opcion){
                case 1:
                    List<Grupo> lista=gestor.listaGrupos();
                    for (Grupo g:lista){
                        System.out.println(g.toString());
                    }
                    break;
                    
                case 2:
                    List <Grupo> lista2=gestor.listaGrupos();
                    for (Grupo g:lista2){
                        System.out.println(g.getNombre());
                        for (Cancion c: g.getCanciones()){
                            System.out.println("    "+c.getTitulo());
                        }
                    }
                    
                    break;
                    
                case 3:
                    List<Grupo> lista3=gestor.listaGrupos();
                    for (Grupo g:lista3){
                        System.out.println("Grupo: "+g.getNombre()+", numero de canciones: "+g.getCanciones().size());
                    }
                    break;
                    
                case 4:
                    System.out.println("Escribe el grupo que quieres buscar:");
                    String grupo=teclados.nextLine();
                    List<Grupo> lista4=gestor.listaGrupos();
                    for (Grupo g:lista4){
                        if (g.getNombre().equalsIgnoreCase(grupo)){
                            for(Cancion c: g.getCanciones()){
                                System.out.println("id cancion: "+c.getNumcancion()+", titulo: "+c.getTitulo()+", duracion: "+c.getDuracion());
                            }
                        }
                    }
                    break;
                    
                case 5:
                    System.out.println("Introduce el numero de canciones con mas votos que quieres ver:");
                    int num=tecladoi.nextInt();
                    
                    for (Cancion c:gestor.opcion5(num)){
                        System.out.println("Titulo cancion: "+c.getTitulo()+", nombre grupo: "+c.getGrupo().getNombre()+", total votos: "+c.getTotalvotos());
                    }
                    break;
                    
                case 6:
                    System.out.println("GRUPOS SIN CANCIONES");
                    for (Grupo g:gestor.listaGrupos()){
                        if (g.getCanciones().size()==0){
                            System.out.println("Grupo: "+g.getNombre());
                        }
                    }
                    break;
                    
                case 7:
                    gestor.votos();
                    for (Votos v: gestor.votos()){
                        System.out.println("Titulo: "+v.getCancion().getTitulo()+", grupo: "+v.getCancion().getGrupo().getNombre()+", fecha: "+v.getFecha());
                    }
                    break;
                    
                case 8:
                    
                    break;
                    
                case 9:
                    
                    break;
                    
                case 0:
                    break;
            }

        } while (opcion != 0);

    }

    public static List<Grupo> listaGrupos(Connection conexion) throws SQLException
    {
        List<Grupo> lista=new ArrayList();
        String sql = "select codgrupo, nombre, localidad, estilo from grupos order by codgrupo;";
        Statement st = conexion.createStatement();
        ResultSet result = st.executeQuery(sql);
        
        while (result.next()){
            Grupo grupo=new Grupo(result.getInt("codgrupo"), result.getString("nombre"), result.getString("localidad"), result.getString("estilo"));
            lista.add(grupo);
        }
        return lista;
    }
    
    

}
