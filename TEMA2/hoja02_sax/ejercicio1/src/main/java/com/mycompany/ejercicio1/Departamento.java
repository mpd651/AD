/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author DAM220
 */
public class Departamento {
    private int numero;
    private String nombre;
    private String localidad;
    private int empleados;

    public Departamento() {
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public int getEmpleados() {
        return empleados;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Departamento{" + "numero=" + numero + ", nombre=" + nombre + ", localidad=" + localidad + ", empleados=" + empleados + '}';
    }
    
    
}
