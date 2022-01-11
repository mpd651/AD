/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author DAM220
 */
public class Main {
    public static void main(String[] args) throws TransformerConfigurationException, FileNotFoundException, TransformerException {
        Source estilos=new StreamSource("departamentos.xsl");
        Source datos=new StreamSource("departamentos.xml");
        
        FileOutputStream output=new FileOutputStream("salida.html");
        
        Result result=new StreamResult(output);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
        
        transformer.transform(datos, result);

    }
    
}
