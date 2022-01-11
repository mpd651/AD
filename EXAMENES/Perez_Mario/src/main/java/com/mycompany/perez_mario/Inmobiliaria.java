/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.perez_mario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM220
 */
public class Inmobiliaria {
    public int identificador;
    public String nombre;
    public int numeroEmpleados;
    public List<Inmueble> inmuebles=new ArrayList();

    public Inmobiliaria() {
    }

    public Inmobiliaria(int identificador, String nombre, int numeroEmpleados) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }
    
    public boolean añadirInmueble(Inmueble inmueble){
        return inmuebles.add(inmueble);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.identificador;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inmobiliaria other = (Inmobiliaria) obj;
        if (this.identificador != other.identificador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inmobiliaria{" + "identificador=" + identificador + ", nombre=" + nombre + ", numeroEmpleados=" + numeroEmpleados + ", inmuebles=" + inmuebles + '}';
    }
    
    
}
