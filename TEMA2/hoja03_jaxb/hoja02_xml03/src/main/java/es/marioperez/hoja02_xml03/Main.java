package es.marioperez.hoja02_xml03;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import xml03.jaxb.Departamento;
import xml03.jaxb.Empleado;
import xml03.jaxb.Universidad;

/**
 *
 * @author usuario
 */
public class Main {

    public static void main(String[] args) {
        List<Departamento> departamentos = deserializar(new File("universidad.xml")).getDepartamento();

        for (Departamento dep : departamentos) {
            System.out.println(dep.toString());
            for (Empleado e : dep.getEmpleado()) {
                System.out.println("\t" + e.toString());
            }
        }

    }

    private static Universidad deserializar(File fXML) {
        //Objeto de la clase binding en el que se deserializará el XML
        Universidad uni = null;
        try {
            //Contexto para class Clientes
            JAXBContext contextUniversidad = JAXBContext.newInstance(Universidad.class);
            //Obtener un deserializador para contexto
            Unmarshaller deserializador = contextUniversidad.createUnmarshaller();
            //Asignar la clase gestora de eventos al deserializador
            //No es obligatorio usarlo, aunque si recomendable
            deserializador.setEventHandler(new GestorEventos());
            //Si quisiéramos usar un fichero esquema XSD que hubiéramos recogido por teclado, 
            //después de obtener el deserializador, le tendríamos que haber asignado el esquema.
//            deserializador.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
//                .newSchema(new File("clientes.xsd")));
            //Deserializar en objeto clientes 
            uni = (Universidad) deserializador.unmarshal(fXML);
        } catch (JAXBException /*| SAXException */ ex) {
            System.err.println(ex.getMessage());
        }
        return uni;
    }
}
