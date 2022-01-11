/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

/**
 *
 * @author DAM220
 */
public class Artista {
    public String name;
    public String url;

    public Artista() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Artista{" + "nombre=" + name + ", url=" + url + '}';
    }
    
    
}
