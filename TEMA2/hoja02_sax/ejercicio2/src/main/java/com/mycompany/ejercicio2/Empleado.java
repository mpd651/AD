/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

/**
 *
 * @author mpd65
 */
public class Empleado {
    private int salario;
    private String puesto;
    private String nombre;

    public Empleado() {
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empleado{" + "salario=" + salario + ", puesto=" + puesto + ", nombre=" + nombre + '}';
    }
    
    
}
