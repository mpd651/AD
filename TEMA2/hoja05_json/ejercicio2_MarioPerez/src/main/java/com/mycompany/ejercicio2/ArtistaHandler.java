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
public class ArtistaHandler extends DefaultHandler {
    private List <Artista> artistas;
    private StringBuilder valor;
    private int numArtistas;
    private int contador=0;

    public void setContador(int contador) {
        this.numArtistas = contador;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }
    
    

    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
        artistas = new ArrayList();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (contador < numArtistas) {
            super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
            switch (qName) {
                case "artist":
                    artistas.add(new Artista());
                    contador++;
                    break;
                case "name":
                    valor = new StringBuilder();
                    break;
                case "url":
                    valor = new StringBuilder();
                    break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (contador < numArtistas) {
            super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
            if (valor == null) {
                valor = new StringBuilder();
            } else {
                valor.append(ch, start, length);
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (contador < numArtistas) {
            super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
            switch (qName) {
                case "name":
                    artistas.get(artistas.size() - 1).setName(valor.toString());
                    break;
                case "url":
                    artistas.get(artistas.size() - 1).setUrl(valor.toString());
                    break;
            }
        }

    }

    
    
    
    
    
    
}
