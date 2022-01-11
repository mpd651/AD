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
public class Cancion {
    public String ranking;
    public String nombre;
    public String url;

    public Cancion() {
    }

    public String getRanking() {
        return ranking;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Cancion{" + "ranking=" + ranking + ", nombre=" + nombre + ", url=" + url + '}';
    }
    
    
}
