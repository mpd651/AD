/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio1;

import com.google.gson.Gson;
import static com.google.gson.internal.bind.TypeAdapters.URL;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author DAM220
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url=new URL("https://aplicaciones.ivanlorenzo.es/ad/dam2.json");
        HttpsURLConnection conexion=(HttpsURLConnection)url.openConnection();
        conexion.setRequestMethod("GET");
        int statusCode = conexion.getResponseCode();
        if (statusCode==200){
            BufferedInputStream inputStream = new BufferedInputStream(conexion.getInputStream());
            BufferedReader lector = new BufferedReader(new InputStreamReader (inputStream));
            Gson gson = new Gson();
            Ciclo ciclo=gson.fromJson(lector, Ciclo.class);
            System.out.println(ciclo);
        }

        
    }
}
