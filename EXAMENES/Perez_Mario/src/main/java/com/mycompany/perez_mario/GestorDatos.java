/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.perez_mario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.DoubleStream.builder;
import static java.util.stream.Stream.builder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static jdk.jshell.JShell.builder;
import static jdk.jshell.tool.JavaShellToolBuilder.builder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM220
 */
public class GestorDatos {
    
    public List<Inmobiliaria> inmobiliarias;
    
   

    public GestorDatos() {
    }
    
    public void modificarPrecios(int id){
        
    }
    
    public void leerInmobiliarias(Path ruta){
        
    }
    
    public void leerInmuebles(Path ruta){
        
    }
    
    public Inmobiliaria buscarPorid(int id){
        return null;
    }
    
    public void procesarDatosXML(Path ruta){
        try{
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser parser = factoria.newSAXParser();
            InmobiliariaHandler manejador = new InmobiliariaHandler();
            parser.parse(ruta.toFile(), manejador);
            for(Inmobiliaria inmo: manejador.getInmobiliarias()){
                inmobiliarias.add(inmo);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void añadirEtiquetaValoracion(Path ruta) throws SAXException, IOException, TransformerException {
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        try {
            dBFactory.setIgnoringComments(true);
            dBFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder=dBFactory.newDocumentBuilder();
            Document doc=builder.parse(ruta.toString());
            Element raiz=doc.getDocumentElement();
            NodeList listaInmobiliarias=raiz.getElementsByTagName("inmobiliaria");
            
            for (int i = 0; i < listaInmobiliarias.getLength(); i++) {
                Node nodoInmobiliaria=listaInmobiliarias.item(i);
                Element element=(Element)nodoInmobiliaria;
                NodeList listaInmuebles=element.getElementsByTagName("inmuebles");
                
                for (int j = 0; j < listaInmuebles.getLength(); j++) {
                    Node nodoInmueble=listaInmuebles.item(j);
                    Element elementInmueble=(Element)nodoInmueble;
                    String precio=elementInmueble.getElementsByTagName("precio").item(0).getTextContent();
                    int iprecio=Integer.parseInt(precio);
                    
                    NodeList listaValoraciones=elementInmueble.getElementsByTagName("valoracion");
                    if (listaValoraciones==null){
                        Element elementValoracion=doc.createElement("valoracion");
                        if (iprecio<100000){
                            elementValoracion.appendChild(doc.createTextNode("Precio bajo"));
                            elementInmueble.appendChild(elementValoracion);
                        }else if (iprecio>=100000&&iprecio<250000){
                            elementValoracion.appendChild(doc.createTextNode("Precio medio"));
                            elementInmueble.appendChild(elementValoracion);
                        }else if (iprecio>250000){
                            elementValoracion.appendChild(doc.createTextNode("Precio alto"));
                            elementInmueble.appendChild(elementValoracion);
                        }
                        
                    }
                }
            }
            Source source=new DOMSource(doc);
            Result result = new StreamResult(Files.newBufferedWriter(ruta));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);

            
        } catch (ParserConfigurationException e) {}
        
    }

    public void guardarJSON(Path ruta) throws IOException {
        try ( 
            BufferedWriter escritor = Files.newBufferedWriter(Paths.get("inmobiliariasFINAL.json"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (inmobiliarias!=null){
                gson.toJson(inmobiliarias, escritor);
            }else{
                System.out.println("La lista de inmobiliarias esta vacia");
            }
        }
    }
    
    public String mostrarDatosEncolumnados(){
        return null;
    }
    
    public String mostrarDatosOrdenadosEmpleados(){
        return null;
    }
    
    public String mostrarInmobiliariasOrdenadasPorMediaPrecios(){
        return null;
    }
    
    public Inmobiliaria getInmobiliarias(){
        return null;
    }
    
    public void borrarEspaciosBlanco(Node nodo){
        
    }
}
