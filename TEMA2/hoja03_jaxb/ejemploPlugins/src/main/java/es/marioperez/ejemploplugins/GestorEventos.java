package es.marioperez.ejemploplugins;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

/**
 *
 * @author usuario
 */
public class GestorEventos implements ValidationEventHandler
{
    public GestorEventos()
    {
    }

    @Override
    public boolean handleEvent(ValidationEvent event)
    {
        System.err.println("Evento de validacion (" + event.getSeverity()
                + ") [linea: " + event.getLocator().getLineNumber() + " , columna :"
                + event.getLocator().getColumnNumber() + "] " + event.getMessage());
        return (event.getSeverity() != ValidationEvent.FATAL_ERROR);
    }
}
