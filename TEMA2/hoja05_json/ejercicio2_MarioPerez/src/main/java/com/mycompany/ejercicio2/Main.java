/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM220
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        Scanner teclado=new Scanner(System.in);
        Scanner tecladoS=new Scanner(System.in);
        int opc;
        URL url;
        HttpsURLConnection conexion;
        SAXParserFactory factoria = SAXParserFactory.newInstance();
        SAXParser parser = factoria.newSAXParser();

        do {
            System.out.println("MENU"
                    + "\n\t 1-Datos de artistas mas populares en tu pais"
                    + "\n\t 2-30 mejores temas de un artista"
                    + "\n\t 3-Datos de 20 artistas mas populares de un usuario concreto"
                    + "\n\t 4-Obtener 10 artistas similares a uno dado"
                    + "\n\t 0-Salir");
            System.out.println("Introduce una opcion:");
            opc=teclado.nextInt();
            
            switch (opc){
                case 1:
                    url=new URL("https://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=spain&api_key=98e1ef4cc9fbb2314149c0ff3bcea4a4");
                    conexion=(HttpsURLConnection)url.openConnection();
                    conexion.setRequestMethod("GET");
                    int statusCode = conexion.getResponseCode();
                    BufferedInputStream inputStream=null;
                    if (statusCode==200){
                        inputStream = new BufferedInputStream(conexion.getInputStream());
                    }
                    System.out.println("Introduce el numero de artistas que quieres mostrar:");
                    int artistas=teclado.nextInt();
                    ArtistaHandler manejador=new ArtistaHandler();
                    manejador.setContador(artistas);
                    parser.parse(inputStream, manejador);
                    manejador.getArtistas().forEach(System.out::println);
                    break;
                
                case 2:
                    System.out.println("Introduce el nombre del cantante:");
                    String nombre=tecladoS.nextLine();
                    url =new URL("https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist="+nombre+"&api_key=98e1ef4cc9fbb2314149c0ff3bcea4a4");                    
                    conexion=(HttpsURLConnection)url.openConnection();
                    conexion.setRequestMethod("GET");
                    statusCode = conexion.getResponseCode();
                    inputStream=null;
                    if (statusCode==200){
                        inputStream = new BufferedInputStream(conexion.getInputStream());
                    }
                    CancionHandler manejadorCancion=new CancionHandler();
                    parser.parse(inputStream, manejadorCancion);
                    manejadorCancion.getCanciones().forEach(System.out::println);
                    break;
                
                case 3:
                    URL url3=new URL("http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=spain&api_key=98e1ef4cc9fbb2314149c0ff3bcea4a4&format=json&limit=20");
                    HttpURLConnection conexion3=(HttpURLConnection)url3.openConnection();
                    conexion3.setRequestMethod("GET");
                    statusCode = conexion3.getResponseCode();
                    if (statusCode==200){
                        inputStream = new BufferedInputStream(conexion3.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader (inputStream));
                        Gson gson = new Gson();
                        JsonObject job = gson.fromJson(lector, JsonObject.class);
                        JsonElement artist=job.getAsJsonObject("topartists").getAsJsonArray("artist");
                        Artista[] listaArtistas=gson.fromJson(artist, Artista[].class);
                        for (Artista a:listaArtistas) {
                            System.out.println(a.getName());
                        }

                    }
                    break;

                case 4:
                    System.out.println("Introduce el nombre del artista a comparar:");
                    String artista=tecladoS.nextLine();
                    url =new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist="+artista+"&api_key=98e1ef4cc9fbb2314149c0ff3bcea4a4&format=json&limit=10");
                    HttpURLConnection conexion4=(HttpURLConnection)url.openConnection();
                    conexion4.setRequestMethod("GET");
                    statusCode = conexion4.getResponseCode();
                    if (statusCode==200){
                        inputStream = new BufferedInputStream(conexion4.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader (inputStream));
                        Gson gson = new Gson();
                        JsonObject job = gson.fromJson(lector, JsonObject.class);
                        JsonElement artist=job.getAsJsonObject("similarartists").getAsJsonArray("artist");
                        Artista[] listaArtistas=gson.fromJson(artist, Artista[].class);
                        for (Artista a:listaArtistas) {
                            System.out.println(a.getName());
                        }
                    }
                    break;
            }

        } while (opc != 0);

    }
}
