package es.marioperez.ejemploplugins;

import ejemploPlugins.jaxb.Clientes;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args)
    {
        deserializar(new File ("clientes.xml"))
                .getCliente()
                .stream()
                .forEach(c->System.out.println(c.getApellidos()));
    }
    
    private static Clientes deserializar(File fXML)
    {
        //Objeto de la clase binding en el que se deserializará el XML
        Clientes clientes = null;
        try
        {
            //Contexto para class Clientes
            JAXBContext contextClientes = JAXBContext.newInstance(Clientes.class);
            //Obtener un deserializador para contexto
            Unmarshaller deserializador = contextClientes.createUnmarshaller();
            //Asignar la clase gestora de eventos al deserializador
            //No es obligatorio usarlo, aunque si recomendable
            deserializador.setEventHandler(new GestorEventos());
            //Si quisiéramos usar un fichero esquema XSD que hubiéramos recogido por teclado, 
            //después de obtener el deserializador, le tendríamos que haber asignado el esquema.
//            deserializador.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
//                .newSchema(new File("clientes.xsd")));
            //Deserializar en objeto clientes 
            clientes = (Clientes) deserializador.unmarshal(fXML);
        }
        catch (JAXBException /*| SAXException */ex)
        {
            System.err.println(ex.getMessage());
        }
        return clientes;
    }
}
