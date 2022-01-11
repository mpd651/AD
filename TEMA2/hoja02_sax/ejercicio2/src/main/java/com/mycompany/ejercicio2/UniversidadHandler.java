/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author mpd65
 */
public class UniversidadHandler extends DefaultHandler {
    public Departamento departamento;
    public Empleado empleado;
    public StringBuilder valorElemento;
    public String codigoDep;
    public boolean existe;
    public boolean impreso;

    public void setCodigoDep(String codigoDep) {
        this.codigoDep = codigoDep;
    }    
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        if (valorElemento==null){
            valorElemento=new StringBuilder();
        }
        else{
            valorElemento.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
        switch (qName){
            
            case "codigo":
                departamento.setCodigo(valorElemento.toString());
                if (departamento.getCodigo().equalsIgnoreCase(codigoDep)){
                    existe=true;
                }
                break;
            case "departamento":
                if (existe==true){
                    System.out.println(departamento.toString());
                    for (Empleado e:departamento.getEmpleados()){
                        System.out.println("\t"+e.toString());
                    }
                    impreso =true;
                }
            case "nombre":
                if (departamento.getEmpleados()==null){
                    departamento.setNombre(valorElemento.toString());
                    departamento.setEmpleados(new ArrayList());

                }
                else{
                    empleado.setNombre(valorElemento.toString());
                }
                break;
            case "empleado":
                departamento.getEmpleados().add(empleado);
                break;
            case "puesto":
                empleado.setPuesto(valorElemento.toString());
                break;
            
                
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        switch (qName){
            case "departamento":
                departamento=new Departamento();
                departamento.setTelefono(Integer.parseInt(attributes.getValue("telefono")));
                departamento.setTipo(attributes.getValue("tipo").charAt(0));
                existe=false;
                break;
            case "codigo":
                valorElemento=new StringBuilder();
                break;
            case "nombre":
                valorElemento=new StringBuilder();
                break;
            case "empleado":
                empleado = new Empleado();
                empleado.setSalario(Integer.parseInt(attributes.getValue("salario")));
                break;
            case "puesto":
                valorElemento=new StringBuilder();
                break;
            
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
        if (impreso==false){
            System.out.println("No se encontro el codigo de departamento");
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
        impreso=false;        
    }
    
}
