/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.perez_mario;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author DAM220
 */
public class InmobiliariaHandler extends DefaultHandler{
    private List<Inmobiliaria> inmobiliarias=new ArrayList();
    private List<Inmueble> inmuebles;
    private StringBuilder valorElemento;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        if (this.valorElemento == null)
            this.valorElemento = new StringBuilder();
        else
            this.valorElemento.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
        switch (qName){
            case "nombre":
                if (inmuebles==null){
                    this.inmobiliarias.get(this.inmobiliarias.size()-1).setNombre(this.valorElemento.toString());
                }else{
                    this.inmuebles.get(this.inmuebles.size()-1).setNombre(this.valorElemento.toString());
                }
                break;
                
            case "empleados":
                this.inmobiliarias.get(this.inmobiliarias.size()-1).setNumeroEmpleados(Integer.parseInt(this.valorElemento.toString()));
                break;
            case "precio":
                this.inmuebles.get(this.inmuebles.size()-1).setPrecio(Double.parseDouble(this.valorElemento.toString()));
                break;
            case "descripcion":
                this.inmuebles.get(this.inmuebles.size()-1).setDescripcion(this.valorElemento.toString());
                break;
            case "inmueble":
                this.inmobiliarias.get(this.inmobiliarias.size()-1).añadirInmueble(this.inmuebles.get(this.inmuebles.size()-1));
                break;
            case "inmuebles":
                inmuebles=null;
                break;
                
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        switch(qName){
            case "inmobiliaria":
                int id=Integer.parseInt(attributes.getValue("id"));
                Inmobiliaria i=new Inmobiliaria();
                i.setIdentificador(id);
                inmobiliarias.add(i);
                break;
            case "nombre":
                valorElemento=new StringBuilder();
                break;
            case "empleados":
                valorElemento=new StringBuilder();
                break;
            case "inmuebles":
                inmuebles=new ArrayList();
                break;
            case "inmueble":
                inmuebles.add(new Inmueble());
                break;
            case "precio":
                valorElemento=new StringBuilder();
                break;
            case "descripcion":
                valorElemento=new StringBuilder();
                break; 
        }
    }

    public List<Inmobiliaria> getInmobiliarias() {
        return inmobiliarias;
    }
    
}
