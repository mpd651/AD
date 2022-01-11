package es.marioperez.ejercicio1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author usuario
 */
public class ObjectOutputStreamSinCabeceras extends ObjectOutputStream
{
    public ObjectOutputStreamSinCabeceras(OutputStream out) throws IOException
    {
        super(out);
    }

    protected ObjectOutputStreamSinCabeceras() throws IOException, SecurityException
    {
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException
    {
    }
}
