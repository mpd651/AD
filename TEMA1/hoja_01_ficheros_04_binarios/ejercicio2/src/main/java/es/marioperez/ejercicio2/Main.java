package es.marioperez.ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args)
    {
        File f = new File("/home/usuario/Escritorio/DAM2/AD/TEMA1/hoja_01_ficheros_04/ejercicio2/futbolistas.dat");
        ArrayList <Futbolista> lista = new ArrayList();
        DataInputStream lector = null;
        try
        {
            lector = new DataInputStream(new FileInputStream(f));
            while (true)
            {
                int id = lector.readInt();
                String alias = lector.readUTF();
                String codigo = lector.readUTF();
                String puestoSt = lector.readUTF();
                Puesto puesto = null;
                if (puestoSt.equalsIgnoreCase("portero"))
                {
                    puesto = puesto.PORTERO;
                } else if (puestoSt.equalsIgnoreCase("centrocampista"))
                {
                    puesto = puesto.CENTROCAMPISTA;
                } else if (puestoSt.equalsIgnoreCase("delantero"))
                {
                    puesto = puesto.DELANTERO;
                } else
                {
                    puesto = puesto.DEFENSA;
                }
                Float altura = lector.readFloat();
                Futbolista fut = new Futbolista(id, alias, codigo, puesto, altura);

                lista.add(fut);
            }

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException e)
        {

        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                lector.close();
            } catch (IOException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Collections.sort(lista);
        DataOutputStream escritor = null;
        File fAuxiliar = new File("futbolistas_ordenados.dat");

        try
        {
            escritor = new DataOutputStream(new FileOutputStream(fAuxiliar));
            for (Futbolista futbolista : lista)
            {
                escritor.writeInt(futbolista.getIdentificador());
                escritor.writeUTF(futbolista.getAlias());
                escritor.writeUTF(futbolista.getCodigo());
                escritor.writeUTF(futbolista.getPuesto().toString());
                escritor.writeFloat(futbolista.getAltura());
                futbolista.toString();
            }

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                escritor.close();
            } catch (IOException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
