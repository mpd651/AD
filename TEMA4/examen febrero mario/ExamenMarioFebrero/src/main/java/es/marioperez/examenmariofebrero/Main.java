package es.marioperez.examenmariofebrero;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args)
    {
        Scanner tecladoi = new Scanner(System.in);
        Scanner teclados=new Scanner(System.in, "ISO-8859-1");
        GestorBd gestor = new GestorBd();

        int opcion = 0;

        do
        {
            System.out.println("\n1.- Modificar urgencia articulo\n"
                    + "2.- Eliminar pedido antiguo\n"
                    + "3.- Propietario en cuyas fabricas hay menos unidades no servidas\n"
                    + "4.- Lista de articulos de los que no se ha producido ningun pedido\n"
                    + "5.- Lista de fabricas por pais del propietario\n"
                    + "6.- Los 5 articulos con mas cantidad pedida\n"
                    + "0.- Salir");
            System.out.println("Introduce una opcion:");
            opcion = tecladoi.nextInt();

            switch (opcion)
            {
                case 1:
                    System.out.println("Introduce el nombre de un articulo al que modificar la urgencia");
                    String articuloUrgente=teclados.nextLine();
                    int modificados=gestor.modificarUrgenciaArticulo(articuloUrgente);
                    System.out.println("Numero de pedidos modificados: "+modificados);
                    break;
                    
                case 2:
                    boolean eliminado=gestor.eliminarPedidoAntiguo();
                    if (eliminado==true){
                        System.out.println("Se ha eliminado con exito el pedido");
                    }else{
                        System.out.println("Ha habido un error");
                    }
                    
                    break;
                    
                case 3:
                    Propietario p=gestor.menosUnidades();
                    System.out.println("Propietario con mas unidades sin enviar:");
                    System.out.println(p.toString());
                    break;
                    
                case 4:
                    List<Articulo> articulos=gestor.articulosSinPedidos();
                    System.out.println("Lista de articulos sin pedidos:");
                    for (Articulo a: articulos){
                        System.out.println(a.toString());
                    }
                    break;
                    
                case 5:
                    System.out.println("Introduzca un pais en el que buscar");
                    String pais=teclados.nextLine();
                    List<Fabrica> fabricas=gestor.listaFabricaPorPais(pais);
                    
                    System.out.println("Listado de fabricas");
                    for (Fabrica f: fabricas){
                        System.out.println("Nombre de Fabrica: "+f.getNombre()+", Nombre de propietario: "+f.getPropietario().getNombre());
                    }
                    break;
                    
                case 6:
                    Map<String, Integer> mapa=gestor.get5ArticulosMasCantidadPedida();
                    
                    for (String clave: mapa.keySet()){
                        System.out.println(clave+" -> "+mapa.get(clave));
                    }
                    break;
                    
                case 0:
                    break;
            }       
            
        } while (opcion!=0);
    }
}
