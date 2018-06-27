/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Kenny Camba
 */
public class TurnoGenerador {
    private final PriorityQueue<Turno> turnos;
    private final Map<Puesto, Integer> atencion;
    private static int TURNOS_CAPACIDADES_ESPECIALES = 0;
    private static int TURNOS_TERCERA_EDAD = 0;
    private static int TURNOS_ATENCION_NORMAL = 0;
    
    public TurnoGenerador() {
        turnos = new PriorityQueue<>((Turno t1, Turno t2) -> 
                t2.getTipoAtencion().getPrioridad() - t1.getTipoAtencion().getPrioridad());
        atencion = new HashMap<>();
        loadMap();
    }
    
    public Turno putInWait(Atencion atencion) {
        System.out.println(turnos);
        System.out.println(this.atencion);
        String numero = "";
        switch(atencion){
            case CAPACIDAD_ESPECIAL:
                numero = atencion.getTipo() + (++TURNOS_CAPACIDADES_ESPECIALES);
                break;
            case TERCERA_EDAD:
                numero = atencion.getTipo() + (++TURNOS_TERCERA_EDAD);
                break;
            case NORMAL:
                numero = atencion.getTipo() + (++TURNOS_ATENCION_NORMAL);
                break;
            default:
                break;
        }
        Puesto puesto = asignarPuesto();
        Turno turno = new Turno(numero, atencion, puesto);
        this.atencion.put(puesto, this.atencion.get(puesto) + 1);
        turnos.offer(turno);
        return turno;
    }
    
    public Turno getNext() {
        if(turnos.isEmpty())
            return null;
        atencion.put(turnos.peek().getPuesto(), atencion.get(turnos.peek().getPuesto()) - 1);
        return turnos.poll();
    }
    
    private Puesto asignarPuesto() {
        int menor = Integer.MAX_VALUE;
        Puesto p = null;
        for(Map.Entry<Puesto, Integer> dat: atencion.entrySet()){
            int var = dat.getValue();
            if(var == 0)
                return dat.getKey();
            else if(var < menor){
                p = dat.getKey();
                menor = var;
            }
        }  
        return p;
    }
    
    private void loadMap() {
        List<Puesto> puestos = Puesto.cargarPuestos();
        for(Puesto p: puestos) 
            atencion.put(p, 0);
    }
    
    public static void main(String[] args) {
        TurnoGenerador t = new TurnoGenerador();
        t.putInWait(Atencion.CAPACIDAD_ESPECIAL);
        t.putInWait(Atencion.NORMAL);
        t.putInWait(Atencion.TERCERA_EDAD);
        t.putInWait(Atencion.NORMAL);
        System.out.println(t.getNext());
        System.out.println(t.getNext());
        System.out.println(t.getNext());
        System.out.println(t.getNext());
    }
    
}
