/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio2;

import java.util.List;

/**
 *
 * @author mpd65
 */
public class Departamento {
    private int telefono;
    private char tipo;
    private String codigo;
    private String nombre;
    private List<Empleado> empleados;

    public Departamento() {
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public int getTelefono() {
        return telefono;
    }

    public char getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public String toString() {
        return "Departamento{" + "telefono=" + telefono + ", tipo=" + tipo + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
    
    
}
