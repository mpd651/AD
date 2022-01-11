/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author DAM220
 */
public class CancionHandler extends DefaultHandler {
    private List <Cancion> canciones;
    private StringBuilder valor;
    private boolean leido=false;

    public CancionHandler() {
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }
    
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
        canciones = new ArrayList();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
        
        switch(qName){

            case "name":
                if (leido==false){
                  canciones.get(canciones.size() - 1).setNombre(valor.toString());
                }
                break;
            case "url":
                if (leido==false){
                canciones.get(canciones.size() - 1).setUrl(valor.toString());
                leido=true;
                }
                break;
        }
    }

    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        
        switch(qName){
            case "track":
                canciones.add(new Cancion());
                valor=new StringBuilder();
                valor.append(attributes.getValue(0));
                canciones.get(canciones.size() - 1).setRanking(valor.toString());
                leido=false;
                break;
            case "name":
                valor=new StringBuilder();
                break;
            case "url":
                valor=new StringBuilder();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        if (valor == null) {
            valor = new StringBuilder();
        } else {
            valor.append(ch, start, length);
        }
    }


}
