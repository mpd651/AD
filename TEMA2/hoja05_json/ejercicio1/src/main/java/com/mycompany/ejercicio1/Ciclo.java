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
public class Ciclo {
    public String cicloformativo;
    public String curso;
    public Modulo [] modulos;

    public Ciclo(String cicloformativo, String curso, Modulo[] modulos) {
        this.cicloformativo = cicloformativo;
        this.curso = curso;
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        String cadena= "Ciclo{" + "ciclo_formativo=" + cicloformativo + ", curso=" + curso + ", modulos=";
        for (int i = 0; i < modulos.length; i++) {
            cadena =cadena+"\n\t"+modulos[i].toString();
        }
        return cadena;
    }
    
    
}
