/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja02_ejercicio2;

import java.io.File;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author DAM220
 */
public class Main {
    public static void main (String [] args){
        File f=new File ("futbolistas.xml");
        Scanner tecladoI=new Scanner(System.in);
        Scanner tecladoS=new Scanner(System.in);
        int opc;
        GestorXML gestor=new GestorXML();
        gestor.abrirXML(f.toPath());
        
        do {
            System.out.println("MENU"
                    + "\n\t 1.-Listar Futbolistas"
                    + "\n\t 2.- Añadir futbolista\n" +
                      "\t 3.- Consultar futbolista por número\n" +
                      "\t 4.- Modificar equipo de futbolista\n" +
                      "\t 5.- Modificar altura de futbolista\n" +
                      "\t 6.- Eliminar futbolista\n" +
                      "\t 7.- Grabar en fichero"
                    + "\n\t 0.-Salir");
            System.out.println("Introduce una Opcion");
            opc=tecladoI.nextInt();
            
            switch (opc){
                case 1:
                    System.out.println(gestor.mostrarDOM());
                    break;
                case 2:
                    System.out.println("Introduce numero de futbolista:");
                    String num=tecladoS.nextLine();
                    System.out.println("Introduce alias de futbolista:");
                    String alias=tecladoS.nextLine();
                    System.out.println("Introduce posicion de futbolista:");
                    String posicion=tecladoS.nextLine();
                    System.out.println("Introduce altura de futbolista:");
                    String altura=tecladoS.nextLine();
                    gestor.insertarFutbolista(num, alias, posicion, altura);
                    break;
                case 3:
                    System.out.println("Introduce un numero de futbolista:");
                    num=tecladoS.nextLine();
                    gestor.imprimirFutbolista(gestor.buscarFutbolista(num));
                    break;
                case 4:
                    System.out.println("Introduce un numero de futbolista:");
                    num=tecladoS.nextLine();
                    Node nodo=gestor.buscarFutbolista(num);
                    gestor.imprimirFutbolista(nodo);
                    if (nodo!=null){
                        System.out.println("Introduce un nuevo codigo de equipo");
                        String equipo=tecladoS.nextLine();
                        gestor.modificarEquipoFutbolista(equipo, nodo);
                    }
                    break;
                case 5:
                    System.out.println("Introduce un numero de futbolista:");
                    num=tecladoS.nextLine();
                    nodo=gestor.buscarFutbolista(num);
                    gestor.imprimirFutbolista(nodo);
                    if (nodo!=null){
                        System.out.println("Introduce una nueva altura:");
                        altura=tecladoS.nextLine();
                        gestor.modicarAlturaFutbolista(altura, nodo);
                    }
                    break;
                case 6:
                    System.out.println("Introduce un numero de futbolista:");
                    num=tecladoS.nextLine();
                    nodo=gestor.buscarFutbolista(num);
                    gestor.imprimirFutbolista(nodo);
                    if (nodo!=null){
                        
                    }
                    break;
                case 7:
                    break;                    
            }
        } while (opc!=0);
        
    }      
}
