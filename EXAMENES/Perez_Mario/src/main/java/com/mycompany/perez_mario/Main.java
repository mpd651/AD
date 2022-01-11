/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.perez_mario;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM220
 */
public class Main {
    private static final Path ficheroDAT = Paths.get("inmobiliarias.dat");
    private static final Path ficheroCSV = Paths.get("inmuebles.csv");
    private static final Path ficheroXML = Paths.get("datos.xml");
    private static final Path ficheroJSON = Paths.get("inmobiliariasFINAL.json");
    private static GestorDatos gestor;

    public static void main(String[] args) throws SAXException, IOException, TransformerException
    {
        gestor = new GestorDatos();

        Scanner teclado = new Scanner(System.in);
        int opcion;
        do
        {
            System.out.println("\n====== MENÚ =========");
            System.out.println("1. Modificar precios de inmuebles");
            System.out.println("2. Cargar datos binarios y CSV");
            System.out.println("3. Añadir etiqueta valoracion");
            System.out.println("4. Cargar datos XML");
            System.out.println("5. Guardar datos en formato JSON");
            System.out.println("6. Mostrar datos encolumnados");
            System.out.println("7. Mostrar datos ordenados por número de empleados descendente");
            System.out.println("8. Mostrar inmobiliarias ordenadas por suma de precios");
            System.out.println("0. Salir");
            opcion = teclado.nextInt();

            switch (opcion)
            {
                case 1:
                {
                    System.out.println("Introduce el identificador de la inmobiliaria: ");
                    int id = teclado.nextInt();
                    gestor.modificarPrecios(id);
                    
                }
                case 2:
                {
                    gestor.leerInmobiliarias(ficheroDAT);
                    gestor.leerInmuebles(ficheroCSV);
                }
                case 3 :
                    gestor.añadirEtiquetaValoracion(ficheroXML);
                case 4 :
                    gestor.procesarDatosXML(ficheroXML);
                case 5 :
                    gestor.guardarJSON(ficheroJSON);
                case 6 :
                    System.out.println(gestor.mostrarDatosEncolumnados());
                case 7 :
                    System.out.println(gestor.mostrarDatosOrdenadosEmpleados());
                case 8 :
                    System.out.println(gestor.mostrarInmobiliariasOrdenadasPorMediaPrecios() + "\n");
            }

        }
        while (opcion != 0);

    }
}
