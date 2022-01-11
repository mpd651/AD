/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author DAM220
 */
public class DepartamentosHandler extends DefaultHandler{
    private List<Departamento> departamentos;
    private StringBuilder valorElemento;
    private StringBuilder valorDepartamento;
    
    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
        this.departamentos = new ArrayList();
        System.out.println("Se ha iniciado la lectura del documento");
    }
    
    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
        System.out.println("Finalizada la lectura del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        
        switch (qName){
            case "departamento":
                valorDepartamento=new StringBuilder();
                valorDepartamento.append("LISTADO DE DEPARTAMENTOS:\n");
                break;
            case "numero", "nombre", "localidad", "empleados":
                valorElemento=new StringBuilder();
                break;
        }
        
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
            case "numero":
                valorDepartamento.append("Departamento número ");
                valorDepartamento.append(valorElemento);
                break;
            case "nombre":
                valorDepartamento.append(" de ");
                valorDepartamento.append(valorElemento);
                valorDepartamento.append("\n\t");
                break;
            case "localidad":
                valorDepartamento.append("Localidad: ");
                valorDepartamento.append(valorElemento);
                valorDepartamento.append("\n\t");
                break;
            case "empleados":
                valorDepartamento.append("Empleados: ");
                valorDepartamento.append(valorElemento);
                valorDepartamento.append("\n\t");
                break;
            case "departamento":
                System.out.println(valorDepartamento);
                break;
        }
    }
    
    
    
    
    
    
}
