package es.marioperez.ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("MENU"
                + "\n   1-añadir futbolista"
                + "\n   2-Listar futbolista"
                + "\n   3-Listar futbolistas de equipo"
                + "\n   4-Buscar futbolista"
                + "\n   5-Modificar equipo de futbolista"
                + "\n   6-Modificar datos de futbolista"
                + "\n   7-Eliminar futbolista"
                + "\n   0-Salir");
        Scanner tecladoI = new Scanner(System.in);
        Scanner tecladoS = new Scanner(System.in);
        System.out.println("Introduzca una opcion:");
        int opc = tecladoI.nextInt();
        File f = new File("futbolistas.dat");

        while (opc != 0)
        {
            switch (opc)
            {
                case 1:
                    System.out.println("Introduce identificador del futbolista:");
                    int id = tecladoI.nextInt();
                    Boolean encontrado = false;
                    DataInputStream lector1 = null;
                    {
                        try
                        {
                            lector1 = new DataInputStream(new FileInputStream(f));
                            while (encontrado == false)
                            {
                                int identificador = lector1.readInt();
                                String alias = lector1.readUTF();
                                String codigo = lector1.readUTF();
                                String puesto = lector1.readUTF();
                                Float altura = lector1.readFloat();
                                if (identificador==id)
                                {
                                    encontrado = true;
                                }

                            }

                        } catch (FileNotFoundException ex)
                        {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex)
                        {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } finally
                        {
                            lector1.close();
                        }

                        DataOutputStream escritor1 = null;
                        try
                        {
                            if (encontrado == true)
                            {
                                System.out.println("El jugador ya existe");
                            } else
                            {
                                System.out.println("Introduce alias del futbolista: ");
                                String alias = tecladoS.nextLine();
                                System.out.println("Introduce el codigo del equipo: ");
                                String codigo = tecladoS.nextLine();
                                System.out.println("Introduce el puesto del futbolista: ");
                                String puesto = tecladoS.nextLine();
                                System.out.println("Introduce la altura del futbolista: ");
                                float altura = tecladoI.nextFloat();

                                escritor1 = new DataOutputStream(new FileOutputStream(f, true));
                                escritor1.writeInt(id);
                                escritor1.writeUTF(alias);
                                escritor1.writeUTF(codigo);
                                escritor1.writeUTF(puesto);
                                escritor1.writeFloat(altura);
                                System.out.println("El futbolista ha sido guardado");

                            }

                        } finally
                        {
                            escritor1.close();
                        }
                    }
                    break;

                case 2:
                    DataInputStream lector2 = null;

                    try
                    {
                        lector2 = new DataInputStream(new FileInputStream(f));
                        while (true)
                        {
                            System.out.println("Identificador: " + lector2.readInt());
                            System.out.println("alias: " + lector2.readUTF());
                            System.out.println("codigo: " + lector2.readUTF());
                            System.out.println("puesto: " + lector2.readUTF());
                            System.out.println("altura: " + lector2.readFloat());
                            System.out.println("");

                        }
                    } catch (EOFException e)
                    {

                    } finally
                    {
                        lector2.close();
                    }
                    break;

                case 3:
                    System.out.println("Introduce el codigo de un equipo para ver jugadores:");
                    String cod = tecladoS.nextLine();
                    DataInputStream lector3 = null;
                    Boolean encontrado3 = false;

                    try
                    {
                        lector3 = new DataInputStream(new FileInputStream(f));
                        while (true)
                        {
                            int identificador = lector3.readInt();
                            String alias = lector3.readUTF();
                            String codigo = lector3.readUTF();
                            String puesto = lector3.readUTF();
                            Float altura = lector3.readFloat();
                            if (cod.equals(codigo))
                            {
                                encontrado3 = true;
                                System.out.println("Identificador: " + identificador);
                                System.out.println("alias: " + alias);
                                System.out.println("codigo: " + codigo);
                                System.out.println("puesto: " + puesto);
                                System.out.println("altura: " + altura);
                            }
                        }
                    } catch (EOFException e)
                    {

                    } finally
                    {
                        if (encontrado3 == false)
                        {
                            System.out.println("No se ha encontrado el equipo");
                        }
                        lector3.close();
                    }
                    break;

                case 4:
                    System.out.println("Introduce el identificador del futbolista: ");
                    int id4 = tecladoI.nextInt();
                    Boolean encontrado4 = false;
                    DataInputStream lector4 = null;
                    {
                        try
                        {
                            lector4 = new DataInputStream(new FileInputStream(f));
                            while (encontrado4 == false)
                            {
                                String identificador = lector4.readUTF();
                                String alias = lector4.readUTF();
                                String codigo = lector4.readUTF();
                                String puesto = lector4.readUTF();
                                Float altura = lector4.readFloat();
                                if (identificador.equals(id4))
                                {
                                    encontrado4 = true;
                                    System.out.println("Identificador: " + identificador);
                                    System.out.println("alias: " + alias);
                                    System.out.println("codigo: " + codigo);
                                    System.out.println("puesto: " + puesto);
                                    System.out.println("altura: " + altura);
                                }

                            }

                        } catch (FileNotFoundException ex)
                        {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex)
                        {
                            System.out.println("El id de futbolista no se ha encontrado");
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } finally
                        {
                            lector4.close();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Introduce el identificador del futbolista a modificar: ");
                    File fAuxiliar = new File("auxiliar.dat");

                    String id5 = tecladoS.nextLine();
                    DataInputStream lector5 = null;
                    DataOutputStream escritor5 = null;
                    boolean encontrado5 = false;

                    try
                    {
                        escritor5 = new DataOutputStream(new FileOutputStream(fAuxiliar));
                        lector5 = new DataInputStream(new FileInputStream(f));
                        while (true)
                        {
                            String identificador = lector5.readUTF();
                            String alias = lector5.readUTF();
                            String codigo = lector5.readUTF();
                            String puesto = lector5.readUTF();
                            Float altura = lector5.readFloat();

                            if (identificador.equals(id5))
                            {
                                encontrado5 = true;
                                escritor5.writeUTF(identificador);
                                escritor5.writeUTF(alias);
                                System.out.println("Introduce el codigo del equipo nuevo al que cambiara:");
                                String cod5 = tecladoS.nextLine();
                                escritor5.writeUTF(cod5);
                                escritor5.writeUTF(puesto);
                                escritor5.writeFloat(altura);
                            } else
                            {
                                escritor5.writeUTF(identificador);
                                escritor5.writeUTF(alias);
                                escritor5.writeUTF(codigo);
                                escritor5.writeUTF(puesto);
                                escritor5.writeFloat(altura);
                            }
                        }
                    } catch (EOFException e)
                    {
                        System.out.println();
                    } finally
                    {
                        lector5.close();
                        escritor5.close();
                    }
                    if (encontrado5 == true)
                    {
                        f.delete();
                        fAuxiliar.renameTo(f);
                    } else
                    {
                        System.out.println("No se ha encontrado el futbolista");
                        fAuxiliar.delete();
                    }
                    break;

                case 6:
                    File fAuxiliar6 = new File("auxiliar.dat");
                    System.out.println("Introduce el identificador del futbolista a modificar: ");
                    String id6 = tecladoS.nextLine();
                    DataInputStream lector6 = null;
                    DataOutputStream escritor6 = null;
                    boolean encontrado6 = false;

                    try
                    {
                        escritor6 = new DataOutputStream(new FileOutputStream(fAuxiliar6));
                        lector6 = new DataInputStream(new FileInputStream(f));
                        while (true)
                        {
                            String identificador = lector6.readUTF();
                            String alias = lector6.readUTF();
                            String codigo = lector6.readUTF();
                            String puesto = lector6.readUTF();
                            Float altura = lector6.readFloat();

                            if (identificador.equals(id6))
                            {
                                System.out.println("Identificiador " + identificador);
                                System.out.println("alias " + alias);
                                System.out.println("codigo " + codigo);
                                System.out.println("puesto " + puesto);
                                System.out.println("altura " + altura);

                                encontrado6 = true;
                                escritor6.writeUTF(identificador);
                                System.out.println("Introduce el nuevo alias");
                                String al = tecladoS.nextLine();
                                escritor6.writeUTF(al);
                                System.out.println("Introduce el nuevo codigo del equipo:");
                                String cod6 = tecladoS.nextLine();
                                escritor6.writeUTF(cod6);
                                System.out.println("Introduce el nuevo puesto:");
                                String pu = tecladoS.nextLine();
                                escritor6.writeUTF(pu);
                                System.out.println("Introduce la nueva altura:");
                                Float alt = tecladoI.nextFloat();
                                escritor6.writeFloat(alt);
                            } else
                            {
                                escritor6.writeUTF(identificador);
                                escritor6.writeUTF(alias);
                                escritor6.writeUTF(codigo);
                                escritor6.writeUTF(puesto);
                                escritor6.writeFloat(altura);
                            }
                        }
                    } catch (EOFException e)
                    {
                        System.out.println();
                    } finally
                    {
                        lector6.close();
                        escritor6.close();
                    }
                    if (encontrado6 == true)
                    {
                        f.delete();
                        fAuxiliar6.renameTo(f);
                    } else
                    {
                        System.out.println("No se ha encontrado el futbolista");
                        fAuxiliar6.delete();
                    }
                    break;

                case 7:
                    File fAuxiliar7 = new File("auxiliar.dat");
                    System.out.println("Introduce el identificador del futbolista a modificar: ");
                    String id7 = tecladoS.nextLine();
                    DataInputStream lector7 = null;
                    DataOutputStream escritor7 = null;
                    boolean encontrado7 = false;

                    try
                    {
                        escritor7 = new DataOutputStream(new FileOutputStream(fAuxiliar7));
                        lector7 = new DataInputStream(new FileInputStream(f));
                        while (true)
                        {
                            String identificador = lector7.readUTF();
                            String alias = lector7.readUTF();
                            String codigo = lector7.readUTF();
                            String puesto = lector7.readUTF();
                            Float altura = lector7.readFloat();

                            if (identificador.equals(id7))
                            {
                                encontrado7=true;
                                System.out.println("El futbolista ha sido borrado");
                            } else
                            {
                                escritor7.writeUTF(identificador);
                                escritor7.writeUTF(alias);
                                escritor7.writeUTF(codigo);
                                escritor7.writeUTF(puesto);
                                escritor7.writeFloat(altura);
                            }
                        }
                    } catch (EOFException e)
                    {
                        System.out.println();
                    } finally
                    {
                        lector7.close();
                        escritor7.close();
                    }
                    if (encontrado7 == true)
                    {
                        f.delete();
                        fAuxiliar7.renameTo(f);
                    } else
                    {
                        System.out.println("No se ha encontrado el futbolista");
                        fAuxiliar7.delete();
                    }
                    break;

            }
            System.out.println("MENU"
                    + "\n   1-añadir futbolista"
                    + "\n   2-Listar futbolista"
                    + "\n   3-Listar futbolistas de equipo"
                    + "\n   4-Buscar futbolista"
                    + "\n   5-Modificar equipo de futbolista"
                    + "\n   6-Modificar datos de futbolista"
                    + "\n   7-Eliminar futbolista"
                    + "\n   0-Salir");
            System.out.println("Introduzca una opcion:");
            opc = tecladoI.nextInt();
        }
    }
}
