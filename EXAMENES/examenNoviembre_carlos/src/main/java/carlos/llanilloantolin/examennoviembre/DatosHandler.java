package carlos.llanilloantolin.examennoviembre;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author usuario
 */
public class DatosHandler extends DefaultHandler
{

    private List<Inmobiliaria> inmobiliarias;
    private Inmobiliaria inmobiliaria;
    private Inmueble inmueble;
    private StringBuilder valor;

    private boolean esInmueble;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        super.characters(ch, start, length);
        if (this.valor == null)
            this.valor = new StringBuilder();
        else
            this.valor.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        super.endElement(uri, localName, qName);
        switch (qName)
        {
            case "nombre" ->
            {
                if (esInmueble)
                    inmueble.setNombre(valor.toString());
                else
                    inmobiliaria.setNombre(valor.toString());
            }
            case "empleados" ->
                inmobiliaria.setNumeroEmpleados(Integer.valueOf(valor.toString()));
            case "precio" ->
                inmueble.setPrecio(Double.valueOf(valor.toString()));
            case "descripcion" ->
                inmueble.setDescripcion(valor.toString());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        super.startElement(uri, localName, qName, attributes);
        switch (qName)
        {
            case "inmobiliaria" ->
            {
                esInmueble = false;
                inmobiliaria = new Inmobiliaria();
                inmobiliaria.setIdentificador(Integer.valueOf(attributes.getValue("id")));
                inmobiliarias.add(inmobiliaria);
            }
            case "inmueble" ->
            {
                esInmueble = true;
                inmueble = new Inmueble();
                inmobiliaria.añadirInmueble(inmueble);
            }
            case "nombre","empleados","precio","descripcion" ->
                valor = new StringBuilder();
        }
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
        this.inmobiliarias = new ArrayList();
    }

    public List<Inmobiliaria> getInmobiliarias()
    {
        return inmobiliarias;
    }
}
