/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja02_ejercicio1_marioperez;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author mpd65
 */
public class Main {

    public static void main(String[] args) throws IOException, TransformerConfigurationException, TransformerException {
        List <Futbolista> listaFutbolistas=leerBinario();
        Document documento=crearDocumento();
        anadirElementos(documento, listaFutbolistas);
        transformarXml(documento);
    }
    
    
    public static List<Futbolista> leerBinario() throws IOException {
        List<Futbolista> listaFutbolistas = new ArrayList();
        DataInputStream lector=null;
        try {
            lector = new DataInputStream(new FileInputStream("futbolistas.dat"));
            while (true) {

                Futbolista f = new Futbolista(lector.readShort(), lector.readUTF(), obtenerPosicion(lector.readByte()), lector.readFloat(), lector.readUTF());
                listaFutbolistas.add(f);
            }
        } catch (EOFException e) {

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lector.close();
        }
        return listaFutbolistas;
    }
    
    public static String obtenerPosicion(byte pos){
        String posicionTexto="";
        if (pos==1){
            posicionTexto="Portero";
        }else if (pos==2){
            posicionTexto="Defensa";
        }else if (pos==3){
            posicionTexto="Centrocampista";
        }else if (pos==4){
            posicionTexto="Delantero";
        }
        return posicionTexto;
    }

    public static Document crearDocumento() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document documento = builder.newDocument();
        documento.setXmlVersion("1.0");
        return documento;
    }
    
    public static void anadirElementos(Document documento, List<Futbolista> listaFutbolistas){
        Element elementoFutbolistas=documento.createElement("futbolistas");
        documento.appendChild(elementoFutbolistas);
        
        for (Futbolista fut:listaFutbolistas){
            Element elementoFutbolista=documento.createElement("futbolista");
            elementoFutbolistas.appendChild(elementoFutbolista);
            elementoFutbolista.setAttribute("equipo", fut.getEquipo());
            
            Main.anadirElementosHijos(documento,elementoFutbolista,"num", fut);
            Main.anadirElementosHijos(documento,elementoFutbolista,"alias", fut);
            Main.anadirElementosHijos(documento,elementoFutbolista,"posicion", fut);
            Main.anadirElementosHijos(documento,elementoFutbolista,"altura", fut);
        }
    }
    
    public static void anadirElementosHijos(Document documento, Element elementoPadre, String etiqueta, Futbolista fut){
        Element elemento=documento.createElement(etiqueta);
        if (etiqueta.equals("num")){
            elemento.appendChild(documento.createTextNode(String.valueOf(fut.getNumero())));
        }
        else if (etiqueta.equals("alias")){
            elemento.appendChild(documento.createTextNode(String.valueOf(fut.getAlias())));
        }
        else if (etiqueta.equals("posicion")){
            elemento.appendChild(documento.createTextNode(String.valueOf(fut.getPosicion())));
        }
        else if (etiqueta.equals("altura")){
            elemento.appendChild(documento.createTextNode(String.valueOf(fut.getAltura())));
        }
        elementoPadre.appendChild(elemento);
    }
    
    public static void transformarXml(Document documento) throws IOException, TransformerConfigurationException, TransformerException{
        Source source=new DOMSource(documento);
        Result result=new StreamResult(Files.newBufferedWriter(Paths.get("futbolistas.xml")));
        Transformer transformer=TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }
}
