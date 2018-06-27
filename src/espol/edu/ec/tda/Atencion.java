/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

/**
 *
 * @author Kenny Camba
 */
public enum Atencion {
    
    /**
    *Tipo de atencion con prioridad alta
    */
    CAPACIDAD_ESPECIAL(3, "A", "Capacidad Especial"),
    /**
    *Tipo de atencion con prioridad media
    */
    TERCERA_EDAD(2, "B", "Tercera Edad"),
    /**
    *Tipo de atencion con prioridad baja
    */
    NORMAL(1, "C", "Normal");
    
    private final int prioridad;
    private final String tipo;
    private final String name;
    Atencion(int prioridad, String tipo, String name) {
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.name = name;
    }
    
    public int getPrioridad(){
        return prioridad;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
