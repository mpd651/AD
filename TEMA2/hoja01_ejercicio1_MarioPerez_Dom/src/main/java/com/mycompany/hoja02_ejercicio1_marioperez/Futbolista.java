/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja02_ejercicio1_marioperez;

/**
 *
 * @author mpd65
 */
public class Futbolista {
    private short numero;
    private String alias;
    private String posicion;
    private float altura;
    private String equipo;

    public Futbolista(short numero, String alias, String posicion, float altura, String equipo) {
        this.numero = numero;
        this.alias = alias;
        this.posicion = posicion;
        this.altura = altura;
        this.equipo = equipo;
    }

    public short getNumero() {
        return numero;
    }

    public String getAlias() {
        return alias;
    }

    public String getPosicion() {
        return posicion;
    }

    public float getAltura() {
        return altura;
    }

    public String getEquipo() {
        return equipo;
    }
    

    
    
}
