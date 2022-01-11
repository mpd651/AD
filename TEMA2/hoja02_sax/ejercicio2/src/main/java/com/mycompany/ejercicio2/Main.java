/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author mpd65
 */
public class Main {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        try {
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser parser = factoria.newSAXParser();
            UniversidadHandler manejador = new UniversidadHandler();
            File f = new File("universidad.xml");
            System.out.println("Introduce el codigo del departamento a buscar:");
            manejador.setCodigoDep(teclado.nextLine());
            parser.parse(f, manejador);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
