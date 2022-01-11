/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author mpd65
 */
public class Modulo {
    public String nombre;
    public int horas;

    public Modulo(String nombre, int horas) {
        this.nombre = nombre;
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Modulo{" + "nombre=" + nombre + ", horas=" + horas + '}';
    }
    
    
}
