package carlos.llanilloantolin.examennoviembre;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos Llanillo
 */
public class GestorDatos
{
    private Set<Inmobiliaria> inmobiliarias;

    public GestorDatos()
    {
        inmobiliarias = new HashSet();
    }

    public void modificarPrecios(int id)
    {
        Path datos = Paths.get("inmuebles.csv");
        Path nuevo = Paths.get("inmueblesNuevo.csv");
        try ( BufferedWriter escritor = Files.newBufferedWriter(nuevo))
        {
            for (String linea : Files.readAllLines(datos))
            {
                String[] campos = linea.split(",");
                if (campos[3].equals(String.valueOf(id)))
                {
                    int precio = Integer.valueOf(campos[1]);
                    campos[3] = String.valueOf(String.valueOf(precio * 1.10));
                }
                escritor.write(String.format("%s,%s,%s,%s", campos[0], campos[1], campos[2], campos[3]));
            }
            escritor.flush();
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    private void guardarEnInmueblesCSV(String l)
    {
        try
        {
            Path nuevo = Paths.get("inmueblesNuevo.csv");
            Files.write(nuevo, l.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    public void leerInmobiliarias(Path ficheroDAT)
    {
        try
        {
            DataInputStream lector = new DataInputStream(Files.newInputStream(ficheroDAT));
            while (true)
            {
                int identificador = lector.readInt();
                String nombre = lector.readUTF();
                int numeroEmpleados = lector.readInt();
                inmobiliarias.add(new Inmobiliaria(identificador, nombre, numeroEmpleados));
            }
        } catch (EOFException ex)
        {
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    public void leerInmuebles(Path ficheroCSV)
    {
        try
        {
            Files
                    .lines(ficheroCSV)
                    .map(l -> l.split(","))
                    .forEach(l
                            -> buscarPorId(Integer.valueOf(l[3]))
                            .añadirInmueble(
                                    new Inmueble(l[0], Double.valueOf(l[1]), l[2])));
        } catch (EOFException ex)
        {
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    private Inmobiliaria buscarPorId(int id)
    {
        return this.getInmobiliarias().stream().filter(i -> i.getIdentificador() == id).findFirst().orElse(null);
//        for (Inmobiliaria inmobiliaria : inmobiliarias)
//        {
//            if (inmobiliaria.getIdentificador() == id)
//                return inmobiliaria;
//        }
//        return null;
    }

    public void añadirEtiquetaValoracion(Path ficheroXML)
    {
        Document documento = this.abrirXML(ficheroXML);
        NodeList nodosInmuebles = documento.getElementsByTagName("inmueble");
        for (int i = 0; i < nodosInmuebles.getLength(); i++)
        {
            Element elementInmueble = (Element) nodosInmuebles.item(i);
            Element elementValoracion = (Element) elementInmueble.getElementsByTagName("valoracion").item(0);
            //Solo en caso de ser nulo creamos el elemento y le anclamos al inmuble
            if (elementValoracion == null)
            {
                elementValoracion = documento.createElement("valoracion");
                elementInmueble.appendChild(elementValoracion);
            }

            //Actualizamos el precio en caso de que haya podido cambiar
            Element elementPrecio = (Element) elementInmueble.getElementsByTagName("precio").item(0);
            double precio = Double.valueOf(elementPrecio.getTextContent());
            if (precio < 100000)
                elementValoracion.setTextContent("Precio bajo");
            else if (precio > 250000)
                elementValoracion.setTextContent("Precio alto");
            else
                elementValoracion.setTextContent("Precio medio");
        }
        this.guardarDOMcomoFILE(ficheroXML, documento);
    }

    private Document abrirXML(Path fichero)
    {
        try
        {
            //Se crea un objeto DocumentBuiderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            /*Indica que el modelo DOM no debe contemplar los comentarios que tenga el XML. 
            Es decir, cuando se convierte el XML al modelo DOM los comentarios serán ignorados.*/
            factory.setIgnoringComments(true);
            /*Ignora los espacios en blanco. Si no se hace esto entonces los espacios en blanco 
            aparecen como nodos.*/
            factory.setIgnoringElementContentWhitespace(true);
            /*Se crea un objeto DocumentBuilder para cargar en él la estructura de árbol DOM 
            a partir del XML seleccionado.*/
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Interpreta (parsea) el documento XML (file) y genera el DOM equivalente.
            return builder.parse(fichero.toFile());
            //Ahora documento apunta al árbol DOM listo para ser recorrido.
        } catch (IOException | ParserConfigurationException | SAXException e)
        {
            System.err.println("Error al abrir el XML");
        }
        return null;
    }

    private boolean guardarDOMcomoFILE(Path path, Document documento)
    {
        try
        {
            if (path.toFile().delete())
                path.toFile().createNewFile();
            borrarEspaciosBlanco(documento);
            Source source = new DOMSource(documento);
            // A partir del fuente se crea el resultado en el archivo
            Result result = new StreamResult(Files.newBufferedWriter(path));

            // Ahora hay que crear un objeto TransformerFactory para hacer la transformación
            // y convertir el documento en fichero
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            return true;
        } catch (IOException | TransformerException e)
        {
            System.err.println("Error al guardar el fichero");
            return false;
        }
    }

    private void borrarEspaciosBlanco(Node node)
    {
        NodeList hijos = node.getChildNodes();
        for (int i = 0; i < hijos.getLength(); ++i)
        {
            Node hijo = hijos.item(i);
            if (hijo.getNodeType() == Node.TEXT_NODE)
                hijo.setTextContent(hijo.getTextContent().trim());
            borrarEspaciosBlanco(hijo);
        }
    }

    public void procesarDatosXML(Path ficheroXML)
    {
        try
        {
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser parser = factoria.newSAXParser();
            DatosHandler manejador = new DatosHandler();
            parser.parse(Files.newInputStream(ficheroXML), manejador);
            this.getInmobiliarias().addAll(manejador.getInmobiliarias());
            //manejador.getInmobiliarias().forEach(i -> inmobiliarias.add(i));
        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            System.out.println(ex.toString());
        }
    }

    public void guardarJSON(Path ficheroJSON)
    {
        if (!this.getInmobiliarias().isEmpty())
        {
            try ( BufferedWriter escritor = Files.newBufferedWriter(ficheroJSON))
            {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(this.getInmobiliarias(), escritor);
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
    }

    public String mostrarDatosEncolumnados()
    {
        return inmobiliarias
                .stream()
                .map(i -> i.toString())
                .collect(Collectors.joining("\n"));
    }

    public String mostrarDatosOrdenadosEmpleados()
    {
        return inmobiliarias
                .stream()
                .sorted((i1, i2) -> i2.getNumeroEmpleados() - i1.getNumeroEmpleados())
                .map(inmobiliaria -> inmobiliaria.toString())
                .collect(Collectors.joining("\n"));
    }

    public String mostrarInmobiliariasOrdenadasPorMediaPrecios()
    {
        return inmobiliarias
                .stream()
                .sorted((i1, i2) -> (int) (i2.getMediaPrecios() - i1.getMediaPrecios()))
                .map(inmobiliaria -> String.format("%s (%.1fK €)", inmobiliaria.getNombre(), (inmobiliaria.getMediaPrecios() / 1000)))
                .collect(Collectors.joining("\n"));
    }

    public Set<Inmobiliaria> getInmobiliarias()
    {
        return inmobiliarias;
    }
}
